databaseChangeLog = {

	changeSet(author: "dus73 (generated)", id: "1481230470768-1") {
		addColumn(tableName: "item") {
			column(name: "status", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}
}
