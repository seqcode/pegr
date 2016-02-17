databaseChangeLog = {

	changeSet(author: "dus73 (generated)", id: "1455732332445-1") {
		addColumn(tableName: "item_type") {
			column(name: "category", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "dus73 (generated)", id: "1455732332445-2") {
		modifyDataType(columnName: "details", newDataType: "text", tableName: "protocol")
	}

	changeSet(author: "dus73 (generated)", id: "1455732332445-3") {
		modifyDataType(columnName: "note", newDataType: "text", tableName: "sample")
	}

	changeSet(author: "dus73 (generated)", id: "1455732332445-4") {
		dropColumn(columnName: "object_type", tableName: "item_type")
	}
}
