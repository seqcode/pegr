databaseChangeLog = {

	changeSet(author: "dus73 (generated)", id: "1478552128314-1") {
		addColumn(tableName: "protocol") {
			column(name: "file", type: "varchar(255)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1478552128314-2") {
		addColumn(tableName: "sequencing_cohort") {
			column(name: "notes", type: "longText")
		}
	}
}
