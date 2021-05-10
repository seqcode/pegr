databaseChangeLog = {

    changeSet(author: "danyingshao (generated)", id: "1620671544566-1") {
        addNotNullConstraint(columnDataType: "varchar(255)", columnName: "barcode", tableName: "item")
    }

    changeSet(author: "danyingshao (generated)", id: "1620671544566-2") {
        addNotNullConstraint(columnDataType: "varchar(255)", columnName: "name", tableName: "item")
    }

}
