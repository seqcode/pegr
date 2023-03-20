databaseChangeLog = {

    changeSet(author: "danyingshao (generated)", id: "1620671544568-1") {
        addColumn(tableName: "project") {
            column(name: "links", type: "CLOB")
        }
    }
}
