package pegr

def statsMigrate = new ImportAlignmentStatsService()

def samplefile = "files/sampleStats.csv"
def startLine = 1
def endLine = 4235
statsMigrate.migrateSampleStats(samplefile, startLine, endLine)

def peakfile = "files/peakStats.csv"
startLine = 1
endLine = 3185
statsMigrate.migratePeakStats(peakfile, startLine, endLine)

    