databaseChangeLog = {

	changeSet(author: "dus73 (generated)", id: "1481317918893-1") {
		addColumn(tableName: "item") {
			column(name: "last_updated", type: "datetime")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1481317918893-2") {
		dropIndex(indexName: "unique_project_id", tableName: "sequencing_cohort")
	}
}
