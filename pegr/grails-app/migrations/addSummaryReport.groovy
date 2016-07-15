databaseChangeLog = {

	changeSet(author: "dus73 (generated)", id: "1468264665159-1") {
		createTable(tableName: "summary_report") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "summary_reporPK")
			}

			column(name: "project_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "run_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "dus73 (generated)", id: "1468264665159-2") {
		addColumn(tableName: "sequence_alignment") {
			column(name: "summary_report_id", type: "bigint")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1468264665159-3") {
		addColumn(tableName: "sequencing_experiment") {
			column(name: "adapter_dimer_count", type: "bigint")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1468264665159-11") {
		createIndex(indexName: "FK_305x86nih993ksbixfnq599oc", tableName: "sequence_alignment") {
			column(name: "summary_report_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1468264665159-12") {
		createIndex(indexName: "FK_3avk8v4ooy5p6pgj95viim6uy", tableName: "summary_report") {
			column(name: "project_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1468264665159-13") {
		createIndex(indexName: "FK_bu7egp9o5u8ktnkkplehxxsjf", tableName: "summary_report") {
			column(name: "run_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1468264665159-14") {
		createIndex(indexName: "unique_project_id", tableName: "summary_report", unique: "true") {
			column(name: "run_id")

			column(name: "project_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1468264665159-15") {
		dropColumn(columnName: "adapter_count", tableName: "sequencing_experiment")
	}

	changeSet(author: "dus73 (generated)", id: "1468264665159-8") {
		addForeignKeyConstraint(baseColumnNames: "summary_report_id", baseTableName: "sequence_alignment", constraintName: "FK_305x86nih993ksbixfnq599oc", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "summary_report", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1468264665159-9") {
		addForeignKeyConstraint(baseColumnNames: "project_id", baseTableName: "summary_report", constraintName: "FK_3avk8v4ooy5p6pgj95viim6uy", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "project", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1468264665159-10") {
		addForeignKeyConstraint(baseColumnNames: "run_id", baseTableName: "summary_report", constraintName: "FK_bu7egp9o5u8ktnkkplehxxsjf", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sequence_run", referencesUniqueColumn: "false")
	}
}
