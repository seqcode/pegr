databaseChangeLog = {

	changeSet(author: "dus73 (generated)", id: "1455811710570-1") {
		modifyDataType(columnName: "concentration", newDataType: "float", tableName: "antibody")
	}

	changeSet(author: "dus73 (generated)", id: "1455811710570-2") {
		dropNotNullConstraint(columnDataType: "float", columnName: "concentration", tableName: "antibody")
	}

	changeSet(author: "dus73 (generated)", id: "1455811710570-3") {
		modifyDataType(columnName: "details", newDataType: "text", tableName: "protocol")
	}

	changeSet(author: "dus73 (generated)", id: "1455811710570-4") {
		modifyDataType(columnName: "note", newDataType: "text", tableName: "sample")
	}
}
