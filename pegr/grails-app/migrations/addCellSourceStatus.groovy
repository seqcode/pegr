databaseChangeLog = {

	changeSet(author: "dus73 (generated)", id: "1477921051005-1") {
		addColumn(tableName: "cell_source") {
			column(name: "status", type: "varchar(255)")
		}
	}
}
