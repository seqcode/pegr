databaseChangeLog = {

	changeSet(author: "dus73 (generated)", id: "1484332960349-1") {
		addColumn(tableName: "cell_source_batch") {
			column(name: "notes", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}
}
