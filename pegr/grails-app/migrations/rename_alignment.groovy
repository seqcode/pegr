databaseChangeLog = {

    changeSet(author: "danyingshao (generated)", id: "1588082889454-46") {
        addColumn(tableName: "sequence_alignment") {
            column(name:"workflow_category", type: "VARCHAR(255)") {
                constraints(nullable: "true")
            }
        }
        
        addColumn(tableName: "sequence_alignment") {
            column(name:"results", type: "CLOB") {
                constraints(nullable: "true")
            }
        }
        
        renameTable(oldTableName: "sequence_alignment", newTableName: "analysis_workflow_run")
    }

    changeSet(author: "danyingshao (generated)", id: "1588082889454-47") {
        renameTable(oldTableName: "report_alignments", newTableName: "report_analysis_workflow_runs")
        
        renameColumn(tableName: "report_analysis_workflow_runs", oldColumnName: "alignment_id", newColumnName: "analysis_workflow_run_id", columnDataType: "BIGINT")
    }

    changeSet(author: "danyingshao (generated)", id: "1588082889454-48") {
        renameColumn(tableName: "analysis", oldColumnName: "alignment_id", newColumnName: "analysis_workflow_run_id", columnDataType: "BIGINT")
    }
}
