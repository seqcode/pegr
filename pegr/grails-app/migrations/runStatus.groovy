databaseChangeLog = {

	changeSet(author: "dus73 (generated)", id: "1455914339885-1") {
		addColumn(tableName: "sequence_run") {
			column(name: "status", type: "varchar(255)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1455914339885-2") {
		modifyDataType(columnName: "details", newDataType: "text", tableName: "protocol")
	}

	changeSet(author: "dus73 (generated)", id: "1455914339885-3") {
		modifyDataType(columnName: "note", newDataType: "text", tableName: "sample")
	}
}
