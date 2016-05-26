databaseChangeLog = {

	changeSet(author: "dus73 (generated)", id: "1464187953466-1") {
		addColumn(tableName: "sample") {
			column(name: "requested_genomes", type: "varchar(255)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1464187953466-2") {
		modifyDataType(columnName: "datasets", newDataType: "text", tableName: "analysis")
	}

	changeSet(author: "dus73 (generated)", id: "1464187953466-3") {
		modifyDataType(columnName: "parameters", newDataType: "text", tableName: "analysis")
	}

	changeSet(author: "dus73 (generated)", id: "1464187953466-4") {
		modifyDataType(columnName: "statistics", newDataType: "text", tableName: "analysis")
	}

	changeSet(author: "dus73 (generated)", id: "1464187953466-5") {
		modifyDataType(columnName: "note", newDataType: "text", tableName: "sample")
	}
}
