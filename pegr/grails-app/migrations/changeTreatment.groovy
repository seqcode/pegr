databaseChangeLog = {

	changeSet(author: "danyingshao (generated)", id: "1457313915132-1") {
		addColumn(tableName: "cell_source_treatment") {
			column(name: "note", type: "varchar(255)")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1457313915132-2") {
		modifyDataType(columnName: "details", newDataType: "text", tableName: "protocol")
	}

	changeSet(author: "danyingshao (generated)", id: "1457313915132-3") {
		modifyDataType(columnName: "note", newDataType: "text", tableName: "sample")
	}

	changeSet(author: "danyingshao (generated)", id: "1457313915132-4") {
		dropColumn(columnName: "compound", tableName: "cell_source_treatment")
	}

	changeSet(author: "danyingshao (generated)", id: "1457313915132-5") {
		dropColumn(columnName: "duration", tableName: "cell_source_treatment")
	}

	changeSet(author: "danyingshao (generated)", id: "1457313915132-6") {
		dropColumn(columnName: "quantity", tableName: "cell_source_treatment")
	}
}
