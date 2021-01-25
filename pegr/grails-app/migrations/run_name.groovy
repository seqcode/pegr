databaseChangeLog = {

    changeSet(author: "danyingshao (generated)", id: "1611490736543-48") {
        addColumn(tableName: "sequence_run") {
            column(name: "run_name", type: "varchar(255)")
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1611490736543-49") {
        addUniqueConstraint(columnNames: "run_name", constraintName: "UC_SEQUENCE_RUNRUN_NAME_COL", tableName: "sequence_run")
    }

    changeSet(author: "danyingshao (generated)", id: "1611490736543-50") {
        dropColumn(columnName: "run_num_alias", tableName: "sequence_run")
    }

}
