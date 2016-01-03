databaseChangeLog = {

	changeSet(author: "danyingshao (generated)", id: "1451678637854-1") {
		modifyDataType(columnName: "details", newDataType: "text", tableName: "protocol")
	}

	changeSet(author: "danyingshao (generated)", id: "1451678637854-2") {
		modifyDataType(columnName: "cell_number", newDataType: "integer", tableName: "sample")
	}

	changeSet(author: "danyingshao (generated)", id: "1451678637854-3") {
		dropNotNullConstraint(columnDataType: "integer", columnName: "cell_number", tableName: "sample")
	}

	changeSet(author: "danyingshao (generated)", id: "1451678637854-4") {
		modifyDataType(columnName: "chromosome_amount", newDataType: "integer", tableName: "sample")
	}

	changeSet(author: "danyingshao (generated)", id: "1451678637854-5") {
		dropNotNullConstraint(columnDataType: "integer", columnName: "chromosome_amount", tableName: "sample")
	}

	changeSet(author: "danyingshao (generated)", id: "1451678637854-6") {
		modifyDataType(columnName: "concentration", newDataType: "float", tableName: "sample")
	}

	changeSet(author: "danyingshao (generated)", id: "1451678637854-7") {
		dropNotNullConstraint(columnDataType: "float", columnName: "concentration", tableName: "sample")
	}

	changeSet(author: "danyingshao (generated)", id: "1451678637854-8") {
		modifyDataType(columnName: "quantity_received", newDataType: "integer", tableName: "sample")
	}

	changeSet(author: "danyingshao (generated)", id: "1451678637854-9") {
		dropNotNullConstraint(columnDataType: "integer", columnName: "quantity_received", tableName: "sample")
	}

	changeSet(author: "danyingshao (generated)", id: "1451678637854-10") {
		modifyDataType(columnName: "requested_tag_number", newDataType: "integer", tableName: "sample")
	}

	changeSet(author: "danyingshao (generated)", id: "1451678637854-11") {
		dropNotNullConstraint(columnDataType: "integer", columnName: "requested_tag_number", tableName: "sample")
	}
}
