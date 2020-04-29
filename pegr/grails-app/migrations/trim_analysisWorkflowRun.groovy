databaseChangeLog = {

    changeSet(author: "danyingshao (generated)", id: "1588165728754-45") {
        dropColumn(columnName: "avg_insert_size", tableName: "analysis_workflow_run")
    }

    changeSet(author: "danyingshao (generated)", id: "1588165728754-46") {
        dropColumn(columnName: "bam_file", tableName: "analysis_workflow_run")
    }

    changeSet(author: "danyingshao (generated)", id: "1588165728754-47") {
        dropColumn(columnName: "dedup_uniquely_mapped_reads", tableName: "analysis_workflow_run")
    }

    changeSet(author: "danyingshao (generated)", id: "1588165728754-48") {
        dropColumn(columnName: "genome_coverage", tableName: "analysis_workflow_run")
    }

    changeSet(author: "danyingshao (generated)", id: "1588165728754-49") {
        dropColumn(columnName: "mapped_reads", tableName: "analysis_workflow_run")
    }

    changeSet(author: "danyingshao (generated)", id: "1588165728754-50") {
        dropColumn(columnName: "pe_histogram", tableName: "analysis_workflow_run")
    }

    changeSet(author: "danyingshao (generated)", id: "1588165728754-51") {
        dropColumn(columnName: "read_db_id", tableName: "analysis_workflow_run")
    }

    changeSet(author: "danyingshao (generated)", id: "1588165728754-52") {
        dropColumn(columnName: "seq_duplication_level", tableName: "analysis_workflow_run")
    }

    changeSet(author: "danyingshao (generated)", id: "1588165728754-53") {
        dropColumn(columnName: "std_dev_insert_size", tableName: "analysis_workflow_run")
    }

    changeSet(author: "danyingshao (generated)", id: "1588165728754-54") {
        dropColumn(columnName: "uniquely_mapped_reads", tableName: "analysis_workflow_run")
    }

}
