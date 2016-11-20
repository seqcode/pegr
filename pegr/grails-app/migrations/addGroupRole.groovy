databaseChangeLog = {

	changeSet(author: "danyingshao (generated)", id: "1479613639392-1") {
		createTable(tableName: "role_group") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "role_groupPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1479613639392-2") {
		createTable(tableName: "role_group_role") {
			column(name: "role_group_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "role_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1479613639392-3") {
		createTable(tableName: "token") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "tokenPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "date", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "token", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1479613639392-4") {
		createTable(tableName: "user_role_group") {
			column(name: "role_group_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1479613639392-5") {
		addColumn(tableName: "sequence_alignment") {
			column(name: "notes", type: "varchar(255)")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1479613639392-6") {
		addPrimaryKey(columnNames: "role_group_id, role_id", constraintName: "role_group_roPK", tableName: "role_group_role")
	}

	changeSet(author: "danyingshao (generated)", id: "1479613639392-7") {
		addPrimaryKey(columnNames: "role_group_id, user_id", constraintName: "user_role_groPK", tableName: "user_role_group")
	}

	changeSet(author: "danyingshao (generated)", id: "1479613639392-13") {
		dropIndex(indexName: "unique_url", tableName: "requestmap")
	}

	changeSet(author: "danyingshao (generated)", id: "1479613639392-14") {
		createIndex(indexName: "name_uniq_1479613636890", tableName: "role_group", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1479613639392-15") {
		createIndex(indexName: "FK_cqr9g8iosupkylg4fw10paumq", tableName: "role_group_role") {
			column(name: "role_group_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1479613639392-16") {
		createIndex(indexName: "FK_dxc3snhixkg9qn3c46p6hyjli", tableName: "role_group_role") {
			column(name: "role_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1479613639392-17") {
		createIndex(indexName: "FK_g7im3j7f0g31yhl6qco2iboy5", tableName: "token") {
			column(name: "user_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1479613639392-18") {
		createIndex(indexName: "FK_9se04wabb1vif6yjcfged3s2p", tableName: "user_role_group") {
			column(name: "role_group_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1479613639392-19") {
		createIndex(indexName: "FK_d9ttuc99ggo3nld2pd96x6pe7", tableName: "user_role_group") {
			column(name: "user_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1479613639392-20") {
		dropTable(tableName: "requestmap")
	}

	changeSet(author: "danyingshao (generated)", id: "1479613639392-8") {
		addForeignKeyConstraint(baseColumnNames: "role_group_id", baseTableName: "role_group_role", constraintName: "FK_cqr9g8iosupkylg4fw10paumq", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "role_group", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1479613639392-9") {
		addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "role_group_role", constraintName: "FK_dxc3snhixkg9qn3c46p6hyjli", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "role", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1479613639392-10") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "token", constraintName: "FK_g7im3j7f0g31yhl6qco2iboy5", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1479613639392-11") {
		addForeignKeyConstraint(baseColumnNames: "role_group_id", baseTableName: "user_role_group", constraintName: "FK_9se04wabb1vif6yjcfged3s2p", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "role_group", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1479613639392-12") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_role_group", constraintName: "FK_d9ttuc99ggo3nld2pd96x6pe7", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}
}
