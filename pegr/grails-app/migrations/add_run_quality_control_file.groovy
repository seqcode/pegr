databaseChangeLog = {

    changeSet(author: "danyingshao (generated)", id: "1620671544573-1") {
        addColumn(tableName: "sequence_run") {
            column(name: "quality_control_file", type: "varchar(255)")
        }
    }
    
}