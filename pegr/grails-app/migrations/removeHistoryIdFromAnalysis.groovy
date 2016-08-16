databaseChangeLog = {

	changeSet(author: "dus73 (generated)", id: "1471366553380-6") {
		addNotNullConstraint(columnDataType: "varchar(255)", columnName: "history_id", tableName: "sequence_alignment")
	}

	changeSet(author: "dus73 (generated)", id: "1471366553380-7") {
		addNotNullConstraint(columnDataType: "bigint", columnName: "pipeline_id", tableName: "sequence_alignment")
	}

	changeSet(author: "dus73 (generated)", id: "1471366553380-8") {
		dropForeignKeyConstraint(baseTableName: "analysis", baseTableSchemaName: "pegr", constraintName: "FK_8qg2onufaj9spg9xlnmdn6jk6")
	}

	changeSet(author: "dus73 (generated)", id: "1471366553380-9") {
		createIndex(indexName: "unique_history_id", tableName: "sequence_alignment", unique: "true") {
			column(name: "pipeline_id")

			column(name: "genome_id")

			column(name: "sequencing_experiment_id")

			column(name: "history_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1471366553380-10") {
		dropColumn(columnName: "history_id", tableName: "analysis")
	}

	changeSet(author: "dus73 (generated)", id: "1471366553380-11") {
		dropColumn(columnName: "pipeline_id", tableName: "analysis")
	}

	changeSet(author: "dus73 (generated)", id: "1471366553380-12") {
		dropColumn(columnName: "workflow_id", tableName: "analysis")
	}
}
