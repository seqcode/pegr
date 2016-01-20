databaseChangeLog = {

	changeSet(author: "dus73 (generated)", id: "1453309018341-1") {
		addColumn(tableName: "protocol_instance_bag") {
			column(name: "type", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "dus73 (generated)", id: "1453309018341-2") {
		modifyDataType(columnName: "details", newDataType: "text", tableName: "protocol")
	}

	changeSet(author: "dus73 (generated)", id: "1453309018341-3") {
		dropNotNullConstraint(columnDataType: "bigint", columnName: "user_id", tableName: "protocol_instance")
	}
}
