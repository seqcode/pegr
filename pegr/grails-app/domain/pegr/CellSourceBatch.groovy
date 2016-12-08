package pegr

class CellSourceBatch {
    User user
    Date date
    
    String toString() {
        "${user.username} ${date}"
    }
    
    def getCellSources() {
        return BatchCellSources.findAllByBatch(this).collect {it.cellSource}
    }
}