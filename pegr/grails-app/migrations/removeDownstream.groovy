databaseChangeLog = {

	changeSet(author: "dus73 (generated)", id: "1465226475091-5") {
		dropIndex(indexName: "name_uniq_1456345093953", tableName: "downstream_analysis")
	}

	changeSet(author: "dus73 (generated)", id: "1465226475091-6") {
		dropTable(tableName: "downstream_analysis")
	}
}
