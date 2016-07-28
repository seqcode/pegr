databaseChangeLog = {

	changeSet(author: "dus73 (generated)", id: "1469632897658-1") {
		addColumn(tableName: "analysis") {
			column(name: "step_id", type: "varchar(255)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1469632897658-2") {
		addColumn(tableName: "analysis") {
			column(name: "user", type: "varchar(255)")
		}
	}
}
