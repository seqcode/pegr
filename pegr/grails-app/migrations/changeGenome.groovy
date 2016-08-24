databaseChangeLog = {

	changeSet(author: "dus73 (generated)", id: "1471974065450-1") {
		addColumn(tableName: "genome") {
			column(name: "genome_version", type: "varchar(255)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1471974065450-2") {
		addColumn(tableName: "genome") {
			column(name: "url", type: "varchar(255)")
		}
	}
}
