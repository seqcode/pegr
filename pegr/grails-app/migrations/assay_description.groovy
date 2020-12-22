databaseChangeLog = {

    changeSet(author: "danyingshao (generated)", id: "1608661967757-48") {
        addColumn(tableName: "assay") {
            column(name: "description", type: "varchar(255)")
        }
    }
}
