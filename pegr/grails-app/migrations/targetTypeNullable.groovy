databaseChangeLog = {

	changeSet(author: "dus73 (generated)", id: "1454955124677-1") {
		modifyDataType(columnName: "details", newDataType: "text", tableName: "protocol")
	}

	changeSet(author: "dus73 (generated)", id: "1454955124677-2") {
		dropNotNullConstraint(columnDataType: "bigint", columnName: "target_type_id", tableName: "target")
	}
}
