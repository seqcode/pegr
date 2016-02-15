databaseChangeLog = {

	changeSet(author: "dus73 (generated)", id: "1455565960147-1") {
		addColumn(tableName: "sequence_index") {
			column(name: "status", type: "varchar(255)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1455565960147-2") {
		modifyDataType(columnName: "details", newDataType: "text", tableName: "protocol")
	}

	changeSet(author: "dus73 (generated)", id: "1455565960147-3") {
		modifyDataType(columnName: "note", newDataType: "text", tableName: "sample")
	}

	changeSet(author: "dus73 (generated)", id: "1455565960147-4") {
		dropForeignKeyConstraint(baseTableName: "item_bags", baseTableSchemaName: "pegr", constraintName: "FK_n2qqb9a3kp6jjh4um4vl1g0jg")
	}

	changeSet(author: "dus73 (generated)", id: "1455565960147-5") {
		dropForeignKeyConstraint(baseTableName: "item_bags", baseTableSchemaName: "pegr", constraintName: "FK_8cx51q97y1kfc9xpv1uxokl3a")
	}

	changeSet(author: "dus73 (generated)", id: "1455565960147-6") {
		dropTable(tableName: "item_bags")
	}
}
