databaseChangeLog = {

	changeSet(author: "danyingshao (generated)", id: "1454990771840-1") {
		modifyDataType(columnName: "details", newDataType: "text", tableName: "protocol")
	}

	changeSet(author: "danyingshao (generated)", id: "1454990771840-2") {
		modifyDataType(columnName: "date", newDataType: "datetime", tableName: "sample")
	}

	changeSet(author: "danyingshao (generated)", id: "1454990771840-3") {
		dropNotNullConstraint(columnDataType: "datetime", columnName: "date", tableName: "sample")
	}
}
