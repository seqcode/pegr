databaseChangeLog = {
	changeSet(author: "danyingshao (generated)", id: "1470711554944-6") {
		dropForeignKeyConstraint(baseTableName: "sample_bags", baseTableSchemaName: "pegr", constraintName: "FK_cnr1t3lrixla6a72js4vswgow")
	}

	changeSet(author: "danyingshao (generated)", id: "1470711554944-7") {
		dropForeignKeyConstraint(baseTableName: "sample_bags", baseTableSchemaName: "pegr", constraintName: "FK_m94py76hnab3k5yd83xstrenu")
	}

	changeSet(author: "danyingshao (generated)", id: "1470711554944-8") {
		dropTable(tableName: "sample_bags")
	}
}
