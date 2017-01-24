databaseChangeLog = {

	changeSet(author: "dus73 (generated)", id: "1485202170886-1") {
		addColumn(tableName: "sample") {
			column(name: "recommend", type: "varchar(255)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1485202170886-2") {
		dropForeignKeyConstraint(baseTableName: "sample", baseTableSchemaName: "pegr", constraintName: "FK_qbpe56xpt5u1qewbly5ivorrx")
	}

	changeSet(author: "dus73 (generated)", id: "1485202170886-3") {
		dropColumn(columnName: "audit_id", tableName: "sample")
	}

	changeSet(author: "dus73 (generated)", id: "1485202170886-4") {
		dropTable(tableName: "sample_audit")
	}
}
