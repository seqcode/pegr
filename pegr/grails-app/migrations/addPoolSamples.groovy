databaseChangeLog = {

	changeSet(author: "dus73 (generated)", id: "1457965459857-1") {
		createTable(tableName: "pool_samples") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "pool_samplesPK")
			}

			column(name: "pool_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "sample_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "dus73 (generated)", id: "1457965459857-2") {
		modifyDataType(columnName: "note", newDataType: "text", tableName: "sample")
	}

	changeSet(author: "dus73 (generated)", id: "1457965459857-5") {
		createIndex(indexName: "FK_ad5h1h1ndrx123chi4kvxkijg", tableName: "pool_samples") {
			column(name: "sample_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1457965459857-6") {
		createIndex(indexName: "FK_ek00bovrrt47tc8bpgn5g2gdg", tableName: "pool_samples") {
			column(name: "pool_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1457965459857-7") {
		createIndex(indexName: "unique_sample_id", tableName: "pool_samples", unique: "true") {
			column(name: "pool_id")

			column(name: "sample_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1457965459857-3") {
		addForeignKeyConstraint(baseColumnNames: "pool_id", baseTableName: "pool_samples", constraintName: "FK_ek00bovrrt47tc8bpgn5g2gdg", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "item", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1457965459857-4") {
		addForeignKeyConstraint(baseColumnNames: "sample_id", baseTableName: "pool_samples", constraintName: "FK_ad5h1h1ndrx123chi4kvxkijg", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sample", referencesUniqueColumn: "false")
	}
}
