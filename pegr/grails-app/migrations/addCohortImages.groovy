databaseChangeLog = {

	changeSet(author: "dus73 (generated)", id: "1477660961013-1") {
		addColumn(tableName: "sequencing_cohort") {
			column(name: "images", type: "longtext")
		}
	}
}
