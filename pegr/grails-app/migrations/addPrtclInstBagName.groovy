databaseChangeLog = {

	changeSet(author: "dus73 (generated)", id: "1453314808924-1") {
		addColumn(tableName: "protocol_instance_bag") {
			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "dus73 (generated)", id: "1453314808924-2") {
		modifyDataType(columnName: "details", newDataType: "text", tableName: "protocol")
	}
}
