package pegr

def sequences = SequenceIndex.executeQuery("select distinct sequence from SequenceIndex")

sequences.each { sequence ->
    def indices = SequenceIndex.findAllBySequenceAndIndexId(sequence, "0")
    if (indices.size() > 1) {
        for (int i = 1; i < indices.size(); ++i ) {
            SampleSequenceIndices.executeUpdate("update SampleSequenceIndices set index.id =:standardId where index.id =:thisId", [standardId: indices[0].id, thisId: indices[i].id])
            indices[i].delete()
        }
    }
}