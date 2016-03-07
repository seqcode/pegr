databaseChangeLog = {

	changeSet(author: "dus73 (generated)", id: "1457376391513-1") {
		modifyDataType(columnName: "note", newDataType: "text", tableName: "sample")
	}

	changeSet(author: "dus73 (generated)", id: "1457376391513-2") {
		dropColumn(columnName: "details", tableName: "protocol")
	}
}
