databaseChangeLog = {

	changeSet(author: "dus73 (generated)", id: "1480627226009-1") {
		addColumn(tableName: "sample") {
			column(name: "natural_id", type: "varchar(255)")
		}
	}
}
