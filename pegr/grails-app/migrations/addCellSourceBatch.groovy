databaseChangeLog = {

	changeSet(author: "dus73 (generated)", id: "1480971923830-1") {
		createTable(tableName: "batch_cell_sources") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "batch_cell_soPK")
			}

			column(name: "batch_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "cell_source_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "dus73 (generated)", id: "1480971923830-2") {
		createTable(tableName: "cell_source_batch") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "cell_source_bPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "date", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "dus73 (generated)", id: "1480971923830-6") {
		createIndex(indexName: "FK_dxfv34tuthnspoi2l6g7oiw4p", tableName: "batch_cell_sources") {
			column(name: "batch_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1480971923830-7") {
		createIndex(indexName: "FK_q2lpcxg9qdtvvx0qcqhn6vhvi", tableName: "batch_cell_sources") {
			column(name: "cell_source_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1480971923830-8") {
		createIndex(indexName: "FK_7gfjxyn93jlkww2lllmwvxwvq", tableName: "cell_source_batch") {
			column(name: "user_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1480971923830-3") {
		addForeignKeyConstraint(baseColumnNames: "batch_id", baseTableName: "batch_cell_sources", constraintName: "FK_dxfv34tuthnspoi2l6g7oiw4p", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cell_source_batch", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1480971923830-4") {
		addForeignKeyConstraint(baseColumnNames: "cell_source_id", baseTableName: "batch_cell_sources", constraintName: "FK_q2lpcxg9qdtvvx0qcqhn6vhvi", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cell_source", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1480971923830-5") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "cell_source_batch", constraintName: "FK_7gfjxyn93jlkww2lllmwvxwvq", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}
}
