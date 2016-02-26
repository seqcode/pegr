databaseChangeLog = {

	changeSet(author: "dus73 (generated)", id: "1456516046680-1") {
		createTable(tableName: "chores") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "choresPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "value", type: "varchar(255)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456516046680-2") {
		modifyDataType(columnName: "details", newDataType: "text", tableName: "protocol")
	}

	changeSet(author: "dus73 (generated)", id: "1456516046680-3") {
		modifyDataType(columnName: "note", newDataType: "text", tableName: "sample")
	}

	changeSet(author: "dus73 (generated)", id: "1456516046680-4") {
		createIndex(indexName: "name_uniq_1456516043812", tableName: "chores", unique: "true") {
			column(name: "name")
		}
	}
}
