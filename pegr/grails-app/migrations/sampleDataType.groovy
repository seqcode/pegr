databaseChangeLog = {

	changeSet(author: "dus73 (generated)", id: "1464803967045-4") {
		modifyDataType(columnName: "cell_number", newDataType: "double precision", tableName: "sample")
	}

	changeSet(author: "dus73 (generated)", id: "1464803967045-5") {
		modifyDataType(columnName: "chromosome_amount", newDataType: "double precision", tableName: "sample")
	}

	changeSet(author: "dus73 (generated)", id: "1464803967045-7") {
		modifyDataType(columnName: "requested_tag_number", newDataType: "double precision", tableName: "sample")
	}

	changeSet(author: "dus73 (generated)", id: "1464803967045-8") {
		modifyDataType(columnName: "volume", newDataType: "double precision", tableName: "sample")
	}
}
