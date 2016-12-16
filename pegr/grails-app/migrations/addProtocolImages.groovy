databaseChangeLog = {

	changeSet(author: "dus73 (generated)", id: "1481830867639-1") {
		addColumn(tableName: "protocol") {
			column(name: "images", type: "varchar(255)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1481830867639-2") {
		addColumn(tableName: "protocol_instance") {
			column(name: "images", type: "varchar(255)")
		}
	}
}
