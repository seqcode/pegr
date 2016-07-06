databaseChangeLog = {

	changeSet(author: "dus73 (generated)", id: "1464724427163-1") {
		createTable(tableName: "sample_audit") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "sample_auditPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "contaminated", type: "bit")

			column(name: "notes", type: "varchar(255)")

			column(name: "warning", type: "bit")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1464724427163-2") {
		addColumn(tableName: "sample") {
			column(name: "audit_id", type: "bigint")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1464724427163-3") {
		addColumn(tableName: "sample_sequence_indices") {
			column(name: "index_in_set", type: "integer")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1464724427163-8") {
		addNotNullConstraint(columnDataType: "varchar(255)", columnName: "index_id", tableName: "sequence_index")
	}

	changeSet(author: "dus73 (generated)", id: "1464724427163-10") {
		createIndex(indexName: "FK_qbpe56xpt5u1qewbly5ivorrx", tableName: "sample") {
			column(name: "audit_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1464724427163-9") {
		addForeignKeyConstraint(baseColumnNames: "audit_id", baseTableName: "sample", constraintName: "FK_qbpe56xpt5u1qewbly5ivorrx", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sample_audit", referencesUniqueColumn: "false")
	}
}
