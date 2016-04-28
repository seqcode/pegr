databaseChangeLog = {

	changeSet(author: "danyingshao (generated)", id: "1461808626792-1") {
		modifyDataType(columnName: "note", newDataType: "text", tableName: "sample")
	}

	changeSet(author: "danyingshao (generated)", id: "1461808626792-2") {
		dropColumn(columnName: "seq_id", tableName: "sequencing_experiment")
	}
}
