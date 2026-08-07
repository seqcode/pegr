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
}
