databaseChangeLog = {

    changeSet(author: "danyingshao (generated)", id: "1620671544568-2") {
        addColumn(tableName: "sex") {
            column(name: "notes", type: "varchar(255)")
        }
    }
}
