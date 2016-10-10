databaseChangeLog = {

	changeSet(author: "dus73 (generated)", id: "1476105580424-1") {
		addColumn(tableName: "item") {
			column(name: "project_id", type: "bigint")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1476105580424-3") {
		createIndex(indexName: "FK_7rlelwn0ya4pik9fcudk0vv9r", tableName: "item") {
			column(name: "project_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1476105580424-2") {
		addForeignKeyConstraint(baseColumnNames: "project_id", baseTableName: "item", constraintName: "FK_7rlelwn0ya4pik9fcudk0vv9r", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "project", referencesUniqueColumn: "false")
	}
}
