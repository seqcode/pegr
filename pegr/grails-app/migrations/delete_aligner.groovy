databaseChangeLog = {

    changeSet(author: "danyingshao (generated)", id: "1588127332358-47") {
        dropForeignKeyConstraint(baseTableName: "analysis_workflow_run", constraintName: "FK_8gcis40hwme6s7ywn4jikodd2")
    }

    changeSet(author: "danyingshao (generated)", id: "1588127332358-48") {
        dropForeignKeyConstraint(baseTableName: "analysis_workflow_run", constraintName: "FK_fee47iwmejna7h55jp8dpkowd")
    }

    changeSet(author: "danyingshao (generated)", id: "1588127332358-49") {
        dropUniqueConstraint(constraintName: "name_uniq_1470678902659", tableName: "align_type")
    }

    changeSet(author: "danyingshao (generated)", id: "1588127332358-50") {
        dropUniqueConstraint(constraintName: "short_name_uniq_1470678902660", tableName: "align_type")
    }

    changeSet(author: "danyingshao (generated)", id: "1588127332358-51") {
        dropUniqueConstraint(constraintName: "unique_software", tableName: "aligner")
    }

    changeSet(author: "danyingshao (generated)", id: "1588127332358-52") {
        dropTable(tableName: "align_type")
    }

    changeSet(author: "danyingshao (generated)", id: "1588127332358-53") {
        dropTable(tableName: "aligner")
    }

    changeSet(author: "danyingshao (generated)", id: "1588127332358-54") {
        dropColumn(columnName: "align_type_id", tableName: "analysis_workflow_run")
    }

    changeSet(author: "danyingshao (generated)", id: "1588127332358-55") {
        dropColumn(columnName: "aligner_id", tableName: "analysis_workflow_run")
    }

    changeSet(author: "danyingshao (generated)", id: "1588127332358-1") {
        addNotNullConstraint(columnDataType: "bigint", columnName: "analysis_workflow_run_id", tableName: "analysis")
    }

    changeSet(author: "danyingshao (generated)", id: "1588127332358-2") {
        addNotNullConstraint(columnDataType: "bigint", columnName: "analysis_workflow_run_id", tableName: "report_analysis_workflow_runs")
    }

}
