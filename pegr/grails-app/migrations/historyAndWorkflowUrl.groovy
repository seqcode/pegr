databaseChangeLog = {

    changeSet(author: "danyingshao (generated)", id: "1576267457331-49") {
        addColumn(tableName: "sequence_alignment") {
            column(name: "history_url", type: "varchar(255)")
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1576267457331-50") {
        addColumn(tableName: "history") {
            column(name: "notes", type: "varchar(255)")
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1576267457331-51") {
        addColumn(tableName: "pipeline") {
            column(name: "workflow_url", type: "varchar(255)")
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1576267457331-52") {
        dropColumn(columnName: "log", tableName: "history")
    }

    changeSet(author: "danyingshao (generated)", id: "1576267457331-1") {
        addDefaultValue(columnDataType: "varchar(255)", columnName: "pipeline_version", defaultValue: "0.0.0", tableName: "pipeline")
    }

    changeSet(author: "danyingshao (generated)", id: "1576267457331-2") {
        addDefaultValue(columnDataType: "varchar(255)", columnName: "status", defaultValue: "GOOD", tableName: "item")
    }

}
