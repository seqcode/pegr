databaseChangeLog = {

	changeSet(author: "danyingshao (generated)", id: "1451660477872-1") {
		addColumn(tableName: "user") {
			column(name: "affiliation_id", type: "bigint")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451660477872-2") {
		modifyDataType(columnName: "details", newDataType: "text", tableName: "protocol")
	}

	changeSet(author: "danyingshao (generated)", id: "1451660477872-3") {
		dropForeignKeyConstraint(baseTableName: "user", baseTableSchemaName: "pegr", constraintName: "FK_5dvidjwskvh1r6ev4f5m5n0j7")
	}

	changeSet(author: "danyingshao (generated)", id: "1451660477872-5") {
		createIndex(indexName: "FK_qdusuaq6oge31t7nlq10wm6ku", tableName: "user") {
			column(name: "affiliation_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451660477872-6") {
		dropColumn(columnName: "lab_id", tableName: "user")
	}

	changeSet(author: "danyingshao (generated)", id: "1451660477872-4") {
		addForeignKeyConstraint(baseColumnNames: "affiliation_id", baseTableName: "user", constraintName: "FK_qdusuaq6oge31t7nlq10wm6ku", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "organization", referencesUniqueColumn: "false")
	}
}
