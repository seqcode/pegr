databaseChangeLog = {

	changeSet(author: "danyingshao (generated)", id: "1451770466174-1") {
		addColumn(tableName: "sample") {
			column(name: "status", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451770466174-2") {
		modifyDataType(columnName: "details", newDataType: "text", tableName: "protocol")
	}

	changeSet(author: "danyingshao (generated)", id: "1451770466174-3") {
		dropForeignKeyConstraint(baseTableName: "sample", baseTableSchemaName: "pegr", constraintName: "FK_1d2270u07cw6ytkajov7d2wqs")
	}

	changeSet(author: "danyingshao (generated)", id: "1451770466174-4") {
		dropIndex(indexName: "name_uniq_1451508121214", tableName: "sample_status")
	}

	changeSet(author: "danyingshao (generated)", id: "1451770466174-5") {
		dropColumn(columnName: "status_id", tableName: "sample")
	}

	changeSet(author: "danyingshao (generated)", id: "1451770466174-6") {
		dropTable(tableName: "sample_status")
	}
}
