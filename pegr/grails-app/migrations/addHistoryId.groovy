databaseChangeLog = {

	changeSet(author: "dus73 (generated)", id: "1465403321859-1") {
		addColumn(tableName: "analysis") {
			column(name: "history_id", type: "varchar(255)") {
				constraints(nullable: "true")
			}
		}
	}

}
