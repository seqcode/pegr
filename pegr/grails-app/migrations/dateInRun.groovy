databaseChangeLog = {

	changeSet(author: "dus73 (generated)", id: "1454966359034-1") {
		addColumn(tableName: "sequence_run") {
			column(name: "date", type: "datetime")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1454966359034-2") {
		modifyDataType(columnName: "details", newDataType: "text", tableName: "protocol")
	}

	changeSet(author: "dus73 (generated)", id: "1454966359034-3") {
		dropColumn(columnName: "date_created", tableName: "sequence_run")
	}
}
