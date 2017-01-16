databaseChangeLog = {

	changeSet(author: "dus73 (generated)", id: "1484579194244-1") {
		dropNotNullConstraint(columnDataType: "varchar(255)", columnName: "notes", tableName: "cell_source_batch")
	}
}
