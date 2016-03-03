databaseChangeLog = {

	changeSet(author: "dus73 (generated)", id: "1457040994204-1") {
		addColumn(tableName: "item") {
			column(name: "user_id", type: "bigint")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1457040994204-2") {
		modifyDataType(columnName: "details", newDataType: "text", tableName: "protocol")
	}

	changeSet(author: "dus73 (generated)", id: "1457040994204-3") {
		modifyDataType(columnName: "note", newDataType: "text", tableName: "sample")
	}

	changeSet(author: "dus73 (generated)", id: "1457040994204-5") {
		createIndex(indexName: "FK_br92r4wqm19mvpcyhxn5lg7m7", tableName: "item") {
			column(name: "user_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1457040994204-6") {
		dropColumn(columnName: "image_path", tableName: "item")
	}

	changeSet(author: "dus73 (generated)", id: "1457040994204-4") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "item", constraintName: "FK_br92r4wqm19mvpcyhxn5lg7m7", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}
}
