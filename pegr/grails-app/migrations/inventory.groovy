databaseChangeLog = {

	changeSet(author: "danyingshao (generated)", id: "1454984440571-1") {
		createTable(tableName: "inventory") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "inventoryPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "date_received", type: "datetime")

			column(name: "notes", type: "varchar(255)")

			column(name: "receiving_user_id", type: "bigint")

			column(name: "source_type", type: "varchar(255)")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1454984440571-2") {
		addColumn(tableName: "cell_source") {
			column(name: "inventory_id", type: "bigint")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1454984440571-3") {
		modifyDataType(columnName: "details", newDataType: "text", tableName: "protocol")
	}

	changeSet(author: "danyingshao (generated)", id: "1454984440571-6") {
		createIndex(indexName: "FK_71fd478uakc4ikgvf7jkqrbic", tableName: "cell_source") {
			column(name: "inventory_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1454984440571-7") {
		createIndex(indexName: "FK_mnah8ybq73nyl126do8agmk08", tableName: "inventory") {
			column(name: "receiving_user_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1454984440571-4") {
		addForeignKeyConstraint(baseColumnNames: "inventory_id", baseTableName: "cell_source", constraintName: "FK_71fd478uakc4ikgvf7jkqrbic", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "inventory", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1454984440571-5") {
		addForeignKeyConstraint(baseColumnNames: "receiving_user_id", baseTableName: "inventory", constraintName: "FK_mnah8ybq73nyl126do8agmk08", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}
}
