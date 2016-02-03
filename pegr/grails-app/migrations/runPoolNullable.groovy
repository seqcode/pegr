databaseChangeLog = {

	changeSet(author: "danyingshao (generated)", id: "1454469699205-1") {
		addColumn(tableName: "sample") {
			column(name: "send_data_to_id", type: "bigint")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1454469699205-2") {
		modifyDataType(columnName: "details", newDataType: "text", tableName: "protocol")
	}

	changeSet(author: "danyingshao (generated)", id: "1454469699205-3") {
		modifyDataType(columnName: "lane", newDataType: "integer", tableName: "sequence_run")
	}

	changeSet(author: "danyingshao (generated)", id: "1454469699205-4") {
		dropNotNullConstraint(columnDataType: "integer", columnName: "lane", tableName: "sequence_run")
	}

	changeSet(author: "danyingshao (generated)", id: "1454469699205-5") {
		dropNotNullConstraint(columnDataType: "bigint", columnName: "pool_id", tableName: "sequence_run")
	}

	changeSet(author: "danyingshao (generated)", id: "1454469699205-6") {
		dropNotNullConstraint(columnDataType: "bigint", columnName: "user_id", tableName: "sequence_run")
	}

	changeSet(author: "danyingshao (generated)", id: "1454469699205-7") {
		dropForeignKeyConstraint(baseTableName: "cell_culture", baseTableSchemaName: "pegr", constraintName: "FK_2jnsge1ius53ujh095tyb0btt")
	}

	changeSet(author: "danyingshao (generated)", id: "1454469699205-8") {
		dropForeignKeyConstraint(baseTableName: "cell_culture", baseTableSchemaName: "pegr", constraintName: "FK_8ync1orit84c4wrsdexfj1qhh")
	}

	changeSet(author: "danyingshao (generated)", id: "1454469699205-9") {
		dropForeignKeyConstraint(baseTableName: "cell_culture", baseTableSchemaName: "pegr", constraintName: "FK_drm78iqx6w1qitkar2823wpg5")
	}

	changeSet(author: "danyingshao (generated)", id: "1454469699205-10") {
		dropForeignKeyConstraint(baseTableName: "cell_culture", baseTableSchemaName: "pegr", constraintName: "FK_5u995pcua3ruj70qtf246jq1f")
	}

	changeSet(author: "danyingshao (generated)", id: "1454469699205-11") {
		dropForeignKeyConstraint(baseTableName: "cell_culture", baseTableSchemaName: "pegr", constraintName: "FK_imxwk4pri0pie4ruxjn04whfu")
	}

	changeSet(author: "danyingshao (generated)", id: "1454469699205-12") {
		dropForeignKeyConstraint(baseTableName: "cell_culture_cell_culture_treatments", baseTableSchemaName: "pegr", constraintName: "FK_k6eolqxw0qcj2sfh4rsu4rjmk")
	}

	changeSet(author: "danyingshao (generated)", id: "1454469699205-13") {
		dropForeignKeyConstraint(baseTableName: "cell_culture_cell_culture_treatments", baseTableSchemaName: "pegr", constraintName: "FK_4ac0jh13nlm1hwf1a8n3r96l")
	}

	changeSet(author: "danyingshao (generated)", id: "1454469699205-14") {
		dropForeignKeyConstraint(baseTableName: "treatments_in_cell_source", baseTableSchemaName: "pegr", constraintName: "FK_iscrofqbr08eg7kiclnaguiqt")
	}

	changeSet(author: "danyingshao (generated)", id: "1454469699205-15") {
		dropForeignKeyConstraint(baseTableName: "treatments_in_cell_source", baseTableSchemaName: "pegr", constraintName: "FK_bi89gahfch2torjpj7is5bdn")
	}

	changeSet(author: "danyingshao (generated)", id: "1454469699205-17") {
		dropIndex(indexName: "name_uniq_1453300533278", tableName: "cell_culture_treatment")
	}

	changeSet(author: "danyingshao (generated)", id: "1454469699205-18") {
		dropIndex(indexName: "unique_cell_source_id", tableName: "treatments_in_cell_source")
	}

	changeSet(author: "danyingshao (generated)", id: "1454469699205-19") {
		createIndex(indexName: "FK_psm0kim6hte7fy0577fwsbdi", tableName: "sample") {
			column(name: "send_data_to_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1454469699205-20") {
		dropTable(tableName: "cell_culture")
	}

	changeSet(author: "danyingshao (generated)", id: "1454469699205-21") {
		dropTable(tableName: "cell_culture_cell_culture_treatments")
	}

	changeSet(author: "danyingshao (generated)", id: "1454469699205-22") {
		dropTable(tableName: "cell_culture_treatment")
	}

	changeSet(author: "danyingshao (generated)", id: "1454469699205-23") {
		dropTable(tableName: "treatments_in_cell_source")
	}

	changeSet(author: "danyingshao (generated)", id: "1454469699205-16") {
		addForeignKeyConstraint(baseColumnNames: "send_data_to_id", baseTableName: "sample", constraintName: "FK_psm0kim6hte7fy0577fwsbdi", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}
}
