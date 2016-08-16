databaseChangeLog = {

	changeSet(author: "danyingshao (generated)", id: "1471313502353-1") {
		addColumn(tableName: "analysis") {
			column(name: "date", type: "datetime")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1471313502353-2") {
		addColumn(tableName: "sequence_alignment") {
			column(name: "history_id", type: "varchar(255)")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1471313502353-3") {
		addColumn(tableName: "sequence_alignment") {
			column(name: "pipeline_id", type: "bigint")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1471313502353-4") {
		addColumn(tableName: "sequence_alignment") {
			column(name: "workflow_id", type: "varchar(255)")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1471313502353-11") {
		createIndex(indexName: "FK_ep9ilxtqeiwutdrjx5hp1xmax", tableName: "sequence_alignment") {
			column(name: "pipeline_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1471313502353-10") {
		addForeignKeyConstraint(baseColumnNames: "pipeline_id", baseTableName: "sequence_alignment", constraintName: "FK_ep9ilxtqeiwutdrjx5hp1xmax", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "pipeline", referencesUniqueColumn: "false")
	}
}
