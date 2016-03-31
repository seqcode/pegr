databaseChangeLog = {

	changeSet(author: "dus73 (generated)", id: "1459431286493-1") {
		modifyDataType(columnName: "note", newDataType: "text", tableName: "sample")
	}

	changeSet(author: "dus73 (generated)", id: "1459431286493-2") {
		dropColumn(columnName: "total_num_reads", tableName: "sequence_run")
	}
}
