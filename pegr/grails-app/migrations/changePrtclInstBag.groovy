databaseChangeLog = {

	changeSet(author: "dus73 (generated)", id: "1453388731050-1") {
		addColumn(tableName: "protocol_instance_bag") {
			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "dus73 (generated)", id: "1453388731050-2") {
		addColumn(tableName: "protocol_instance_bag") {
			column(name: "protocol_group_id", type: "bigint")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1453388731050-3") {
		modifyDataType(columnName: "details", newDataType: "text", tableName: "protocol")
	}

	changeSet(author: "dus73 (generated)", id: "1453388731050-5") {
		createIndex(indexName: "FK_r1uxlmgfs4nieck8kad4wo79q", tableName: "protocol_instance_bag") {
			column(name: "protocol_group_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1453388731050-6") {
		dropColumn(columnName: "name", tableName: "protocol_instance_bag")
	}

	changeSet(author: "dus73 (generated)", id: "1453388731050-4") {
		addForeignKeyConstraint(baseColumnNames: "protocol_group_id", baseTableName: "protocol_instance_bag", constraintName: "FK_r1uxlmgfs4nieck8kad4wo79q", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "protocol_group", referencesUniqueColumn: "false")
	}
}
