package pegr
import com.opencsv.CSVParser
import com.opencsv.CSVReader
import groovy.json.*
    
class ImportAlignmentStatsService {
    ImportAlignmentStatsService() {}
    
    void migrateSampleStats(String filename, int startLine, int endLine) {
        def lineNo = 0    
        
        def file = new FileReader(filename)
		CSVReader reader = new CSVReader(file);
		String [] rawdata;

		while ((rawdata = reader.readNext()) != null) {
		    ++lineNo
		    if (lineNo < startLine) {
		        continue
		    } else if (endLine > 0 && lineNo > endLine) {
		        break
		    }
            try {
                def data = getNamedDataForSampleStats(rawdata)
                
                // convert UNIQ_ID to seqId
                def seqId
                if (data.UNIQ_ID.size() > 11 && data.UNIQ_ID[0..6] == "Peconic" ) {
                    seqId = data.UNIQ_ID[7..11]
                } else if (data.UNIQ_ID.size() > 8 && data.UNIQ_ID[0..3] == "Pugh") {
                    seqId = data.UNIQ_ID[4..8]
                } else if (data.UNIQ_ID.size() > 4) {
                    seqId = data.UNIQ_ID[0..4]
                }                
                def sample = Sample.findBySourceId(seqId)
                def experiment = SequencingExperiment.findBySample(sample)    
                def alignment = SequenceAlignment.findBySequencingExperiment(experiment)

                if (alignment) {
                    // experiment
                    experiment.indexMismatch = data.indexMismatch
                    experiment.totalReads = data.totalReads
                    experiment.adapterDimerCount = data.adapterDimerCount 
                    experiment.save(flush: true)
                    
                    // alignment
                    alignment.mappedReads = data.mappedReads 
                    alignment.uniquelyMappedReads = data.uniquelyMappedReads
                    alignment.dedupUniquelyMappedReads = data.dedupUniquelyMappedReads 
                    alignment.avgInsertSize = data.avgInsertSize 

                    def params = [:]
                    if (data.ALIGNER_PARAMETERS == "-m 1") {
                        params["aligner"] = "-m 1"
                    } 
                    
                    switch (data.ALIGNER_SOFTWARE) {
                        case "BWA":
                            alignment.aligner = getAligner("BWA", null)
                            break
                        case "Bowtie 1.0.0":
                            alignment.aligner = getAligner("Bowtie", "1.0.0")
                            break
                        case "BWA 0.6.2":
                            alignment.aligner = getAligner("BWA", "0.6.2")
                            break
                        case "BWA 0.7.6":
                            alignment.aligner = getAligner("BWA", "0.7.6")
                            break
                        case ["bwa mem", "bwa mem -"]:
                            alignment.aligner = getAligner("BWA MEM", null)
                            break
                        case "bwa mem -k 17":
                            alignment.aligner = getAligner("BWA MEM", null)
                            params["aligner"] = "-k 17"
                            break
                        case "bwa mem -t 1 -T 20 -k 20":
                            alignment.aligner = getAligner("BWA MEM", null)
                            params["aligner"] = "-t 1 -T 20 -k 20"
                            break
                        case "bwa mem -T 20":
                            alignment.aligner = getAligner("BWA MEM", null)
                            params["aligner"] = "-T 20"
                            break
                        case "bwa mem -t 4 -T 20 -k 20":
                            alignment.aligner = getAligner("BWA MEM", null)
                            params["aligner"] = "-t 4 -T 20 -k 20"
                            break
                        case ["bwa mem 0.7.9.a", "bwa mem 0.79a"]:
                            alignment.aligner = getAligner("BWA MEM", "0.7.9a")
                            break
                    }
                    alignment.params = JsonOutput.toJson(params)
                    alignment.save()
                    
                    def stats = [:]
                    if (data.spikeInCount) {
                        stats["spikeInCount"] = data.spikeInCount 
                    }
                    if (data.ipStrength) {
                        stats["ipStrength"] = data.ipStrength 
                    }
                    if (stats.size > 0) {
                        new Analysis(alignment: alignment,
                                     tool:"Unkonwn",
                                     statistics: JsonOutput.toJson(stats)).save()
                    }
                }
		 	}catch(Exception e) {
		        log.error "Error: line ${lineNo}. " + e
		        continue
		    }   
		}

    }
    
    
    void migratePeakStats(String filename, int startLine, int endLine) {
        def lineNo = 0    
        
        def file = new FileReader(filename)
		CSVReader reader = new CSVReader(file);
		String [] rawdata;

		while ((rawdata = reader.readNext()) != null) {
		    ++lineNo
		    if (lineNo < startLine) {
		        continue
		    } else if (endLine > 0 && lineNo > endLine) {
		        break
		    }
            try {
                def data = getNamedDataForPeakStats(rawdata)                
                // convert PEAK_NAME to seqId
                def seqId
                if (data.PEAK_NAME.size() > 11 &&data.PEAK_NAME[0..6] == "Peconic" ) {
                    seqId = data.PEAK_NAME[7..11]
                } else if (data.PEAK_NAME.size() > 8 && data.PEAK_NAME[0..3] == "Pugh") {
                    seqId = data.PEAK_NAME[4..8]
                } else if (data.PEAK_NAME.size() > 4) {
                    seqId = data.PEAK_NAME[0..4]
                }
                def sample = Sample.findBySourceId(seqId)
                def experiment = SequencingExperiment.findBySample(sample)    
                def alignment = SequenceAlignment.findBySequencingExperiment(experiment)

                if (alignment) {
                    alignment.genomeCoverage = data.genomeCoverage
                    alignment.seqDuplicationLevel = data.seqDuplicationLevel
                    alignment.save()
                    
                    def stats = [:]
                    ["peakFile", "peaks", "singletons", "peakMedian", "peakMean", "peakMedianStd", "peakMeanStd", "MedianTagSingletons", "peakPairNos", "peakPairWis", "tssProximal", "tssDistal", "repeatedRegions"].each { property ->
                        if (data[property]) {
                            stats[property] = data[property]
                        }
                    }
                    if (stats.size() > 0) {
                        new Analysis(alignment: alignment,
                                     tool:"Unkonwn",
                                     statistics: JsonOutput.toJson(stats)).save()
                    }
                }
            }catch(Exception e) {
		        log.error "Error: line ${lineNo}. " + e
		        continue
		    }   
		}
    }
            
    def getAligner(String software, String version) {
        def aligner = Aligner.findBySoftwareAndAlignerVersion(software, version)
        if (aligner == null) {
            aligner = new Aligner(software: software, alignerVersion: version).save(flush: true)
        }
        return aligner
    }
    
    def getInteger(String s) {
        def i 
        try {
            i = s.toInteger()
        }catch(Exception e) {
            
        }
        return i
    }
    
    def getFloat(String s) {
        def f
	    try {
	        f = Float.parseFloat(s)
	    } catch(Exception e) {
            
	    }
	    return f
    }
    
    def getPct(String s) {
        def f
        try {
            f = Float.parseFloat(s[0..-2]) / 100.0
	    } catch(Exception e) {
	        
        }
        return f
    }
    
    def getNamedDataForSampleStats(String[] data) {
	    [ 
            SAMPLE_ID          : data[0],     
            indexMismatch      : getInteger(data[1]), 
            ALIGNER_SOFTWARE   : data[2],  
            ALIGNER_PARAMETERS : data[3],  
            READ_START         : data[4],  
            READ_END           : data[5],  
            totalReads         : getInteger(data[6]),  
            INDEX_PERCENT      : data[7],  
            mappedReads    : getInteger(data[8]),  
            uniquelyMappedReads  : getInteger(data[9]),  
            COMMENT            : data[10],  
            UNIQ_ID            : data[11],  
            VERSION            : data[12],  
            spikeInCount       : getInteger(data[13]),  
            adapterDimerCount       : getInteger(data[14]),  
            NON_UNIQ_COUNT     : data[15],  
            DUP_READ_COUNT     : data[16],  
            dedupUniquelyMappedReads     : getInteger(data[17]),  
            avgInsertSize      : getInteger(data[18]),  
            ipStrength         : getFloat(data[19]),  
        ]
    }
    
    def getNamedDataForPeakStats(String[] data) {
        [
            PEAK_ID               : data[0],  
            SUMMARY_REPORT        : data[1],  
            PEAK_NAME             : data[2],  
            peakFile              : data[3],  
            peaks                 : getInteger(data[4]),  
            singletons            : getInteger(data[5]),  
            peakMedian            : getFloat(data[6]),  
            peakMean              : getFloat(data[7]),  
            peakMedianStd         : getFloat(data[8]),  
            peakMeanStd           : getFloat(data[9]),  
            medianTagSingletons   : getFloat(data[10]),  
            peakPairNos           : getFloat(data[11]),  
            peakPairWis           : getFloat(data[12]),  
            genomeCoverage        : getPct(data[13]),  
            seqDuplicationLevel   : getPct(data[14]),  
            VERSION               : data[15],  
            tssProximal           : getInteger(data[16]),  
            tssDistal             : getInteger(data[17]),  
            repeatedRegions       : getInteger(data[18]),  
        ]
    }
    
}