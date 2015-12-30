databaseChangeLog = {

	changeSet(author: "danyingshao (generated)", id: "1451504833330-1") {
		createTable(tableName: "requestmap") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "requestmapPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "config_attribute", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "http_method", type: "varchar(255)")

			column(name: "url", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451504833330-2") {
		createTable(tableName: "sample_in_pool") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "sample_in_pooPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "concentration", type: "float") {
				constraints(nullable: "false")
			}

			column(name: "pool_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "pool_dilution", type: "float") {
				constraints(nullable: "false")
			}

			column(name: "qubit_dilution_factor", type: "float") {
				constraints(nullable: "false")
			}

			column(name: "qubit_reading", type: "float") {
				constraints(nullable: "false")
			}

			column(name: "sample_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "volume_to_pool", type: "float") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451504833330-3") {
		createTable(tableName: "task") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "taskPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "computing_infrastructure_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "core_pipe_line_id", type: "bigint")

			column(name: "end_time", type: "datetime")

			column(name: "input_file_paths", type: "varchar(255)")

			column(name: "job_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "output_file_paths", type: "varchar(255)")

			column(name: "script_file_path", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "start_time", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "status", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451504833330-4") {
		createTable(tableName: "user_role") {
			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "role_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451504833330-5") {
		modifyDataType(columnName: "details", newDataType: "text", tableName: "protocol")
	}

	changeSet(author: "danyingshao (generated)", id: "1451504833330-6") {
		addPrimaryKey(columnNames: "user_id, role_id", constraintName: "user_rolePK", tableName: "user_role")
	}

	changeSet(author: "danyingshao (generated)", id: "1451504833330-14") {
		createIndex(indexName: "unique_url", tableName: "requestmap", unique: "true") {
			column(name: "http_method")

			column(name: "url")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451504833330-15") {
		createIndex(indexName: "FK_41xrmt2o01xwuxfs7lg976piw", tableName: "sample_in_pool") {
			column(name: "sample_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451504833330-16") {
		createIndex(indexName: "FK_96fu40wr3u20fqapuj2gdcm6m", tableName: "sample_in_pool") {
			column(name: "pool_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451504833330-17") {
		createIndex(indexName: "FK_2ovu0g1rssdjbpgb6fj0l2vwy", tableName: "task") {
			column(name: "computing_infrastructure_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451504833330-18") {
		createIndex(indexName: "FK_4fmjedju7b35tb5cr71n3ntb0", tableName: "task") {
			column(name: "user_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451504833330-19") {
		createIndex(indexName: "FK_89y9w7ykrtfvr9quf0acepu0k", tableName: "task") {
			column(name: "core_pipe_line_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451504833330-20") {
		createIndex(indexName: "FK_apcc8lxk2xnug8377fatvbn04", tableName: "user_role") {
			column(name: "user_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451504833330-21") {
		createIndex(indexName: "FK_it77eq964jhfqtu54081ebtio", tableName: "user_role") {
			column(name: "role_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451504833330-7") {
		addForeignKeyConstraint(baseColumnNames: "pool_id", baseTableName: "sample_in_pool", constraintName: "FK_96fu40wr3u20fqapuj2gdcm6m", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "pool", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451504833330-8") {
		addForeignKeyConstraint(baseColumnNames: "sample_id", baseTableName: "sample_in_pool", constraintName: "FK_41xrmt2o01xwuxfs7lg976piw", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sample", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451504833330-9") {
		addForeignKeyConstraint(baseColumnNames: "computing_infrastructure_id", baseTableName: "task", constraintName: "FK_2ovu0g1rssdjbpgb6fj0l2vwy", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "computing_infrastructure", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451504833330-10") {
		addForeignKeyConstraint(baseColumnNames: "core_pipe_line_id", baseTableName: "task", constraintName: "FK_89y9w7ykrtfvr9quf0acepu0k", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "core_pipeline", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451504833330-11") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "task", constraintName: "FK_4fmjedju7b35tb5cr71n3ntb0", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451504833330-12") {
		addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "user_role", constraintName: "FK_it77eq964jhfqtu54081ebtio", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "role", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451504833330-13") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_role", constraintName: "FK_apcc8lxk2xnug8377fatvbn04", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}
}
