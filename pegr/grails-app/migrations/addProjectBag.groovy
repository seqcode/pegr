databaseChangeLog = {

	changeSet(author: "dus73 (generated)", id: "1475780890127-1") {
		createTable(tableName: "project_bags") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "project_bagsPK")
			}

			column(name: "bag_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "project_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "dus73 (generated)", id: "1475780890127-4") {
		createIndex(indexName: "FK_fc80yrft9ah9jrxcf1comtkag", tableName: "project_bags") {
			column(name: "project_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1475780890127-5") {
		createIndex(indexName: "FK_t52g06gh1mit551a04c7r9u11", tableName: "project_bags") {
			column(name: "bag_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1475780890127-6") {
		createIndex(indexName: "unique_project_id", tableName: "project_bags", unique: "true") {
			column(name: "bag_id")

			column(name: "project_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1475780890127-2") {
		addForeignKeyConstraint(baseColumnNames: "bag_id", baseTableName: "project_bags", constraintName: "FK_t52g06gh1mit551a04c7r9u11", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "protocol_instance_bag", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1475780890127-3") {
		addForeignKeyConstraint(baseColumnNames: "project_id", baseTableName: "project_bags", constraintName: "FK_fc80yrft9ah9jrxcf1comtkag", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "project", referencesUniqueColumn: "false")
	}
}
