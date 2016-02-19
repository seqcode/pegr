databaseChangeLog = {

	changeSet(author: "dus73 (generated)", id: "1455917598966-1") {
		modifyDataType(columnName: "details", newDataType: "text", tableName: "protocol")
	}

	changeSet(author: "dus73 (generated)", id: "1455917598966-2") {
		modifyDataType(columnName: "note", newDataType: "text", tableName: "sample")
	}

	changeSet(author: "dus73 (generated)", id: "1455917598966-3") {
		dropColumn(columnName: "pool", tableName: "sequence_run")
	}
}
