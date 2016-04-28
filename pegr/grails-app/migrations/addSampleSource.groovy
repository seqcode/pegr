databaseChangeLog = {

	changeSet(author: "danyingshao (generated)", id: "1461806240988-1") {
		addColumn(tableName: "sample") {
			column(name: "source", type: "varchar(255)")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1461806240988-2") {
		addColumn(tableName: "sample") {
			column(name: "source_id", type: "varchar(255)")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1461806240988-3") {
		modifyDataType(columnName: "note", newDataType: "text", tableName: "sample")
	}
}
