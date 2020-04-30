databaseChangeLog = {

    changeSet(author: "danyingshao (generated)", id: "1588170925013-45") {
        renameColumn(tableName: "sample", oldColumnName: "requested_genomes", newColumnName: "requested_workflows", columnDataType: "varchar(255)")
    }

}
