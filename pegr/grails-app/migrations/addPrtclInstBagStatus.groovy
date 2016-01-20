databaseChangeLog = {

	changeSet(author: "dus73 (generated)", id: "1453313762286-1") {
		addColumn(tableName: "protocol_instance") {
			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "dus73 (generated)", id: "1453313762286-2") {
		addColumn(tableName: "protocol_instance_bag") {
			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "dus73 (generated)", id: "1453313762286-3") {
		addColumn(tableName: "protocol_instance_bag") {
			column(name: "status", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "dus73 (generated)", id: "1453313762286-4") {
		modifyDataType(columnName: "details", newDataType: "text", tableName: "protocol")
	}

	changeSet(author: "dus73 (generated)", id: "1453313762286-5") {
		dropColumn(columnName: "date_created", tableName: "protocol_instance")
	}
}
