databaseChangeLog = {

	changeSet(author: "dus73 (generated)", id: "1469798894175-6") {
		dropForeignKeyConstraint(baseTableName: "cell_source", baseTableSchemaName: "pegr", constraintName: "FK_dyakrl2jrduv34rm8abrpdpea")
	}

	changeSet(author: "dus73 (generated)", id: "1469798894175-7") {
		dropForeignKeyConstraint(baseTableName: "cell_source_treatments", baseTableSchemaName: "pegr", constraintName: "FK_xht7d6039ax9kbh4gmdceh5k")
	}

	changeSet(author: "dus73 (generated)", id: "1469798894175-8") {
		dropForeignKeyConstraint(baseTableName: "cell_source_treatments", baseTableSchemaName: "pegr", constraintName: "FK_jggm0quygtml4729ct1v2li8q")
	}

	changeSet(author: "dus73 (generated)", id: "1469798894175-9") {
		dropIndex(indexName: "unique_cell_source_id", tableName: "cell_source_treatments")
	}

	changeSet(author: "dus73 (generated)", id: "1469798894175-10") {
		dropColumn(columnName: "growth_media_id", tableName: "cell_source")
	}

	changeSet(author: "dus73 (generated)", id: "1469798894175-11") {
		dropTable(tableName: "cell_source_treatments")
	}
}
