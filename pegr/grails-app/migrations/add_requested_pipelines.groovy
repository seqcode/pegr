databaseChangeLog = {

    changeSet(author: "danyingshao (generated)", id: "1620671544571-1") {
        addColumn(tableName: "sample") {
            column(name: "requested_pipelines", type: "varchar(255)")
        }
    }
    
}