databaseChangeLog = {

    changeSet(author: "danyingshao (generated)", id: "1620671544572-1") {
        addColumn(tableName: "pipeline") {
            column(defaultValueBoolean: "false", name: "is_default", type: "BOOLEAN")
        }
    }
    
}