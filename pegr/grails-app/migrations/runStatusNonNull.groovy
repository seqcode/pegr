databaseChangeLog = {

	changeSet(author: "dus73 (generated)", id: "1455914571258-1") {
		modifyDataType(columnName: "details", newDataType: "text", tableName: "protocol")
	}

	changeSet(author: "dus73 (generated)", id: "1455914571258-2") {
		modifyDataType(columnName: "note", newDataType: "text", tableName: "sample")
	}

	changeSet(author: "dus73 (generated)", id: "1455914571258-3") {
		addNotNullConstraint(columnDataType: "varchar(255)", columnName: "status", tableName: "sequence_run")
	}
}
