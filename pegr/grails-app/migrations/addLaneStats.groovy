databaseChangeLog = {

	changeSet(author: "dus73 (generated)", id: "1480617832506-1") {
		addColumn(tableName: "run_stats") {
			column(name: "library_pool_archive_id", type: "varchar(255)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1480617832506-2") {
		addColumn(tableName: "run_stats") {
			column(name: "notes", type: "varchar(255)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1480617832506-3") {
		addColumn(tableName: "run_stats") {
			column(name: "q_pcr_date", type: "datetime")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1480617832506-4") {
		addColumn(tableName: "run_stats") {
			column(name: "technician_id", type: "bigint")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1480617832506-5") {
		addColumn(tableName: "sequencing_cohort") {
			column(name: "name", type: "varchar(255)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1480617832506-6") {
		modifyDataType(columnName: "cycles", newDataType: "varchar(255)", tableName: "run_stats")
	}

	changeSet(author: "dus73 (generated)", id: "1480617832506-8") {
		createIndex(indexName: "FK_fot189hkkf2166h0g0pm3fpjp", tableName: "run_stats") {
			column(name: "technician_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1480617832506-7") {
		addForeignKeyConstraint(baseColumnNames: "technician_id", baseTableName: "run_stats", constraintName: "FK_fot189hkkf2166h0g0pm3fpjp", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}
}
