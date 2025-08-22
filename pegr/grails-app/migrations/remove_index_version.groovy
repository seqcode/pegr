databaseChangeLog = {
    
    changeSet(author: "danyingshao (generated)", id: "1620671544574-1") {
        dropColumn(columnName: "index_version", tableName: "sequence_index")
    }
    
}
