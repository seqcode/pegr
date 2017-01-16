package pegr

class CellSourceBatch {
    User user
    Date date
    String notes
    
    String toString() {
        "${user.username} ${date}"
    }
    
    def getCellSources() {
        return BatchCellSources.findAllByBatch(this).collect {it.cellSource}.sort{it.id}
    }
    
    static constraints = {
        notes nullable: true
    }
}