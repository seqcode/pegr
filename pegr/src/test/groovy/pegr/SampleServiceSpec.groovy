package pegr

import grails.test.hibernate.HibernateSpec

class SampleServiceSpec extends HibernateSpec {

    List<Class> getDomainClasses() { [Sample, SequenceIndex, SampleSequenceIndices, Genome] }

    // The app's dataSource is MySQL; run this spec against an in-memory H2 in MySQL mode
    Map getConfiguration() {
        [
            'dataSource.driverClassName': 'org.h2.Driver',
            'dataSource.dialect': 'org.hibernate.dialect.H2Dialect',
            'dataSource.url': 'jdbc:h2:mem:sampleServiceSpecDb;MODE=MYSQL;LOCK_TIMEOUT=10000;DB_CLOSE_DELAY=-1',
            'dataSource.username': 'sa',
            'dataSource.password': '',
            'dataSource.dbCreate': 'create-drop'
        ]
    }

    SampleService sampleService

    def setup() {
        sampleService = new SampleService(utilityService: new UtilityService())
    }

    private Sample newSample(String requestedGenomes = null) {
        new Sample(status: SampleStatus.PREP, requestedGenomes: requestedGenomes).save(failOnError: true)
    }

    private SequenceIndex newIndex(String indexId, String sequence) {
        new SequenceIndex(indexId: indexId, sequence: sequence, status: DictionaryStatus.Y).save(failOnError: true)
    }

    private List searchIdsById(String id) {
        sampleService.search(new QuerySampleRegistrationCommand(id: id))*.id.sort()
    }

    void "search by sample ID matches the ID exactly, or with the wildcard %"() {
        given:
        def sample1 = newSample()
        def sample2 = newSample()

        expect:
        searchIdsById("${sample1.id}") == [sample1.id]
        searchIdsById(" ${sample2.id} ") == [sample2.id]
        searchIdsById("%") == [sample1.id, sample2.id].sort()
        searchIdsById("999999") == []
    }

    void "search by sample ID does not run SQL injected through the ID: #id"() {
        given:
        newSample()
        newSample()

        expect: "the input is matched as an ID, so nothing is found and nothing fails"
        searchIdsById(id) == []

        where:
        id << [
            "x' or '1'='1",
            "1' or 1=1 -- ",
            "1') or ('1'='1",
            "1'; drop table sample; --"
        ]
    }

    void "searchSampleIds finds samples by index sequence, including indices that share a sequence"() {
        given: "two indices sharing a sequence, each used by a different sample"
        def index1 = newIndex("IDX1", "ACGT")
        def index2 = newIndex("IDX2", "ACGT")
        def index3 = newIndex("IDX3", "TTTT")
        def sample1 = newSample()
        def sample2 = newSample()
        def sample3 = newSample()
        new SampleSequenceIndices(sample: sample1, index: index1, setId: 1, indexInSet: 1).save(failOnError: true)
        new SampleSequenceIndices(sample: sample2, index: index2, setId: 1, indexInSet: 1).save(failOnError: true)
        new SampleSequenceIndices(sample: sample3, index: index3, setId: 1, indexInSet: 1).save(failOnError: true)

        expect:
        sampleService.searchSampleIds("ACGT", null, null) == [sample1.id, sample2.id].sort()
        sampleService.searchSampleIds("acgt", null, null) == [sample1.id, sample2.id].sort()
        sampleService.searchSampleIds("TTTT", null, null) == [sample3.id]
        sampleService.searchSampleIds("GGGG", null, null) == []
    }

    void "searchSampleIds finds samples by index ID"() {
        given:
        def index = newIndex("IDX1", "ACGT")
        def sample = newSample()
        new SampleSequenceIndices(sample: sample, index: index, setId: 1, indexInSet: 1).save(failOnError: true)

        expect:
        sampleService.searchSampleIds(null, "IDX1", null) == [sample.id]
        sampleService.searchSampleIds(null, "idx1", null) == [sample.id]
        sampleService.searchSampleIds(null, "IDX2", null) == []
    }

    void "searchSampleIds returns each sample once when a sample carries the index twice"() {
        given:
        def index = newIndex("IDX1", "ACGT")
        def sample = newSample()
        new SampleSequenceIndices(sample: sample, index: index, setId: 1, indexInSet: 1).save(failOnError: true)
        new SampleSequenceIndices(sample: sample, index: index, setId: 2, indexInSet: 1).save(failOnError: true)

        expect:
        sampleService.searchSampleIds("ACGT", null, null) == [sample.id]
    }

    void "searchSampleIds requires the sequence and the index ID to both match when both are given"() {
        given:
        def index1 = newIndex("IDX1", "ACGT")
        def index2 = newIndex("IDX2", "TTTT")
        def sample1 = newSample()
        def sample2 = newSample()
        new SampleSequenceIndices(sample: sample1, index: index1, setId: 1, indexInSet: 1).save(failOnError: true)
        new SampleSequenceIndices(sample: sample2, index: index2, setId: 1, indexInSet: 1).save(failOnError: true)

        expect:
        sampleService.searchSampleIds("ACGT", "IDX1", null) == [sample1.id]
        sampleService.searchSampleIds("ACGT", "IDX2", null) == []
    }

    void "searchSampleIds matches a whole genome build name, not a substring of one"() {
        given: "requested genomes whose names are prefixes or suffixes of each other"
        def sample1 = newSample("hg19")
        def sample2 = newSample("hg19,sacCer3")
        def sample3 = newSample("hg19_cegr")
        def sample4 = newSample("mm10")

        expect:
        sampleService.searchSampleIds(null, null, "hg19") == [sample1.id, sample2.id].sort()
        sampleService.searchSampleIds(null, null, "hg19_cegr") == [sample3.id]
        sampleService.searchSampleIds(null, null, "sacCer3") == [sample2.id]
        sampleService.searchSampleIds(null, null, "mm10") == [sample4.id]
        sampleService.searchSampleIds(null, null, "hg") == []
    }

    void "searchSampleIds ignores whitespace around the requested genome builds"() {
        given:
        def sample = newSample("hg19, sacCer3 , mm10")

        expect:
        sampleService.searchSampleIds(null, null, "sacCer3") == [sample.id]
        sampleService.searchSampleIds(null, null, "mm10") == [sample.id]
    }

    void "searchSampleIds matches the genome build regardless of case"() {
        given:
        def sample = newSample("sacCer3")

        expect:
        sampleService.searchSampleIds(null, null, "SACCER3") == [sample.id]
        sampleService.searchSampleIds(null, null, "saccer3") == [sample.id]
    }

    void "searchSampleIds skips the samples that requested no genome build"() {
        given:
        def withGenome = newSample("hg19")
        newSample(null)

        expect:
        sampleService.searchSampleIds(null, null, "hg19") == [withGenome.id]
    }

    void "searchSampleIds requires the index and the genome build to both match when both are given"() {
        given: "two samples sharing an index but requesting different genome builds"
        def index = newIndex("IDX1", "ACGT")
        def sample1 = newSample("hg19")
        def sample2 = newSample("sacCer3")
        def sample3 = newSample("hg19")
        new SampleSequenceIndices(sample: sample1, index: index, setId: 1, indexInSet: 1).save(failOnError: true)
        new SampleSequenceIndices(sample: sample2, index: index, setId: 1, indexInSet: 1).save(failOnError: true)

        expect: "sample3 requested the genome build, but does not carry the index"
        sampleService.searchSampleIds("ACGT", null, "hg19") == [sample1.id]
        sampleService.searchSampleIds(null, "IDX1", "sacCer3") == [sample2.id]
        sampleService.searchSampleIds("ACGT", null, "mm10") == []
    }

    void "searchSampleIds fails when no criterion is given"() {
        when:
        sampleService.searchSampleIds(sequence, indexId, genome)

        then:
        thrown(SampleException)

        where:
        sequence | indexId | genome
        null     | null    | null
        ""       | ""      | ""
        "  "     | null    | "   "
    }

    private List searchIds(Map criteria) {
        sampleService.search(new QuerySampleRegistrationCommand(criteria))*.id.sort()
    }

    void "search filters by a whole genome build name, not a substring of one"() {
        given: "requested genomes whose names are prefixes or suffixes of each other"
        def sample1 = newSample("hg19")
        def sample2 = newSample("hg19,sacCer3")
        def sample3 = newSample("hg19_cegr")
        def sample4 = newSample("mm10")
        newSample(null)

        expect:
        searchIds(genome: "hg19") == [sample1.id, sample2.id].sort()
        searchIds(genome: "hg19_cegr") == [sample3.id]
        searchIds(genome: "sacCer3") == [sample2.id]
        searchIds(genome: "mm10") == [sample4.id]
        searchIds(genome: "hg") == []
    }

    void "search matches the genome build regardless of case and of whitespace"() {
        given:
        def sample = newSample("hg19, sacCer3 , mm10")

        expect:
        searchIds(genome: "SACCER3") == [sample.id]
        searchIds(genome: " mm10 ") == [sample.id]
        searchIds(genome: "hg19") == [sample.id]
    }

    void "search ignores a blank genome build"() {
        given:
        def sample1 = newSample("hg19")
        def sample2 = newSample(null)

        expect:
        searchIds(genome: genome) == [sample1.id, sample2.id].sort()

        where:
        genome << [null, "", "   "]
    }

    void "the web search and the API match genome '#genome' the same way"() {
        given:
        def samples = [
            hg19   : newSample("hg19"),
            both   : newSample("hg19, mm10"),
            cegr   : newSample("hg19_cegr"),
            cegrX  : newSample("hg19xcegr"),
            bang   : newSample("dm6!test"),
            spaced : newSample("sacCer3 ,  dm6"),
            none   : newSample(null)
        ]
        def expectedIds = expected.collect { samples[it].id }.sort()

        expect: "search, used by the web page and by fetchSampleData, and fetchSampleIds agree"
        searchIds(genome: genome) == expectedIds
        sampleService.searchSampleIds(null, null, genome) == expectedIds

        where:
        genome       || expected
        "hg19"       || ["hg19", "both"]
        "HG19"       || ["hg19", "both"]
        " mm10 "     || ["both"]
        "dm6"        || ["spaced"]
        "hg19_cegr"  || ["cegr"]      // "_" is not a wildcard
        "hg%"        || []            // "%" is not a wildcard
        "%"          || []
        "hg19,mm10"  || []            // one genome build per search
        "dm6!test"   || ["bang"]      // the escape character itself is matched literally
        "hg"         || []
    }

    void "search requires the genome build and the other filters to both match"() {
        given:
        def prep = newSample("hg19")
        def created = new Sample(status: SampleStatus.CREATED, requestedGenomes: "hg19").save(failOnError: true)
        newSample("mm10")

        expect:
        searchIds(genome: "hg19", status: SampleStatus.PREP) == [prep.id]
        searchIds(genome: "hg19", status: SampleStatus.CREATED) == [created.id]
        searchIds(genome: "mm10", status: SampleStatus.CREATED) == []
    }
}
