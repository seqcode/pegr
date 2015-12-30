databaseChangeLog = {

	changeSet(author: "danyingshao (generated)", id: "1451504596317-1") {
		createTable(tableName: "file_metadata") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "file_metadataPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "file_type_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "insertion_size", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "md5check_sum", type: "varchar(50)") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(30)") {
				constraints(nullable: "false")
			}

			column(name: "note", type: "varchar(255)")

			column(name: "sequence_alignment_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "standard_deviation", type: "float") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451504596317-2") {
		createTable(tableName: "file_type") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "file_typePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "varchar(200)") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(30)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451504596317-3") {
		createTable(tableName: "history") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "historyPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "action", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "log", type: "varchar(255)")

			column(name: "object_id", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "object_type", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "project_id", type: "bigint")

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451504596317-4") {
		createTable(tableName: "item") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "itemPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "barcode", type: "varchar(255)")

			column(name: "image_path", type: "varchar(255)")

			column(name: "location", type: "varchar(255)")

			column(name: "notes", type: "varchar(255)")

			column(name: "protocol_instance_id", type: "bigint")

			column(name: "reference_id", type: "bigint")

			column(name: "type_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451504596317-5") {
		createTable(tableName: "item_type") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "item_typePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "fields", type: "varchar(255)")

			column(name: "name", type: "varchar(30)") {
				constraints(nullable: "false")
			}

			column(name: "object_type", type: "varchar(30)")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451504596317-6") {
		createTable(tableName: "pool") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "poolPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451504596317-7") {
		createTable(tableName: "protocol_instance") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "protocol_instPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "note", type: "varchar(255)")

			column(name: "prior_id", type: "bigint")

			column(name: "protocol_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451504596317-8") {
		createTable(tableName: "read_type") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "read_typePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(20)") {
				constraints(nullable: "false")
			}

			column(name: "note", type: "varchar(255)")

			column(name: "short_name", type: "varchar(10)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451504596317-9") {
		createTable(tableName: "sequence_alignment") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "sequence_aligPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "align_type_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "aligner_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "alignment_params", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "bam_file_path", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "core_pipeline_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "genome_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "idx_file_path", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "is_preferred_version", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "number_pairs", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "number_tags1", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "number_tags2", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "read_db_id", type: "integer")

			column(name: "sequencing_experiment_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "total_pair_weight", type: "float") {
				constraints(nullable: "false")
			}

			column(name: "total_type1weight", type: "float") {
				constraints(nullable: "false")
			}

			column(name: "total_type2weight", type: "float") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451504596317-10") {
		createTable(tableName: "sequence_run") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "sequence_runPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "directory_name", type: "varchar(255)")

			column(name: "fc_id", type: "varchar(255)")

			column(defaultValue: "12", name: "index_cycle", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "lane", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "note", type: "varchar(255)")

			column(name: "platform_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "pool_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(defaultValue: "40", name: "read1cycle", type: "integer") {
				constraints(nullable: "false")
			}

			column(defaultValue: "40", name: "read2cycle", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "run_num", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451504596317-11") {
		createTable(tableName: "sequencing_experiment") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "sequencing_exPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "fastqcreport_paths", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "fastq_file_paths", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "note", type: "varchar(255)")

			column(name: "number_reads", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "public_db_id", type: "varchar(255)")

			column(name: "read_type_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "sample_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "seq_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "sequence_run_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451504596317-12") {
		createTable(tableName: "sequencing_platform") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "sequencing_plPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(30)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451504596317-13") {
		modifyDataType(columnName: "details", newDataType: "text", tableName: "protocol")
	}

	changeSet(author: "danyingshao (generated)", id: "1451504596317-35") {
		createIndex(indexName: "FK_8ei1cybnbixggmxfhwril3vac", tableName: "file_metadata") {
			column(name: "file_type_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451504596317-36") {
		createIndex(indexName: "FK_p0dwk23v4mpuph7bvybfimjn9", tableName: "file_metadata") {
			column(name: "sequence_alignment_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451504596317-37") {
		createIndex(indexName: "unique_name", tableName: "file_metadata", unique: "true") {
			column(name: "file_type_id")

			column(name: "name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451504596317-38") {
		createIndex(indexName: "name_uniq_1451504595914", tableName: "file_type", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451504596317-39") {
		createIndex(indexName: "FK_fej8h317q2acuy144kav1oyxc", tableName: "history") {
			column(name: "project_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451504596317-40") {
		createIndex(indexName: "FK_fuutexvtx28fs971iq0kbfbmp", tableName: "history") {
			column(name: "user_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451504596317-41") {
		createIndex(indexName: "FK_qxnbu16tlqfmub9pgfj3h2e41", tableName: "item") {
			column(name: "type_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451504596317-42") {
		createIndex(indexName: "FK_rv13l1tnj5m3x3r61t7yq97ue", tableName: "item") {
			column(name: "protocol_instance_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451504596317-43") {
		createIndex(indexName: "unique_barcode", tableName: "item", unique: "true") {
			column(name: "type_id")

			column(name: "barcode")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451504596317-44") {
		createIndex(indexName: "name_uniq_1451504595922", tableName: "item_type", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451504596317-45") {
		createIndex(indexName: "FK_m6wrsc7nbw6p4ulsp0cg65jhi", tableName: "pool") {
			column(name: "user_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451504596317-46") {
		createIndex(indexName: "FK_d4v22o39k3xu8m216w8r1nyx1", tableName: "protocol_instance") {
			column(name: "protocol_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451504596317-47") {
		createIndex(indexName: "FK_p9g9nnnknkaftxrkf1gktn8x6", tableName: "protocol_instance") {
			column(name: "prior_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451504596317-48") {
		createIndex(indexName: "FK_sgj3vhj59cxyb10md7ybwmtm7", tableName: "protocol_instance") {
			column(name: "user_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451504596317-49") {
		createIndex(indexName: "name_uniq_1451504595931", tableName: "read_type", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451504596317-50") {
		createIndex(indexName: "short_name_uniq_1451504595931", tableName: "read_type", unique: "true") {
			column(name: "short_name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451504596317-51") {
		createIndex(indexName: "FK_5tdpednm424bwlyaygxb91cj8", tableName: "sequence_alignment") {
			column(name: "genome_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451504596317-52") {
		createIndex(indexName: "FK_8gcis40hwme6s7ywn4jikodd2", tableName: "sequence_alignment") {
			column(name: "align_type_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451504596317-53") {
		createIndex(indexName: "FK_fee47iwmejna7h55jp8dpkowd", tableName: "sequence_alignment") {
			column(name: "aligner_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451504596317-54") {
		createIndex(indexName: "FK_hc4f4br571qwgavrswy22w5wo", tableName: "sequence_alignment") {
			column(name: "core_pipeline_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451504596317-55") {
		createIndex(indexName: "FK_js0whth80fyna834brwbbo8v0", tableName: "sequence_alignment") {
			column(name: "sequencing_experiment_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451504596317-56") {
		createIndex(indexName: "FK_gerfjuiu4n7m0iukeud2wrux7", tableName: "sequence_run") {
			column(name: "user_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451504596317-57") {
		createIndex(indexName: "FK_jrr4ojwkpl4jryj9ippkg55ed", tableName: "sequence_run") {
			column(name: "pool_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451504596317-58") {
		createIndex(indexName: "FK_l1ofj1csp7k439e52l32soay9", tableName: "sequence_run") {
			column(name: "platform_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451504596317-59") {
		createIndex(indexName: "FK_3mx03u80x4ynhsx1i65yrrxcq", tableName: "sequencing_experiment") {
			column(name: "sequence_run_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451504596317-60") {
		createIndex(indexName: "FK_3s9e144d345uyhay28toy8jwg", tableName: "sequencing_experiment") {
			column(name: "read_type_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451504596317-61") {
		createIndex(indexName: "FK_7gsumwrx4h3nji2g2q5htn098", tableName: "sequencing_experiment") {
			column(name: "sample_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451504596317-62") {
		createIndex(indexName: "name_uniq_1451504595945", tableName: "sequencing_platform", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451504596317-14") {
		addForeignKeyConstraint(baseColumnNames: "file_type_id", baseTableName: "file_metadata", constraintName: "FK_8ei1cybnbixggmxfhwril3vac", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "file_type", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451504596317-15") {
		addForeignKeyConstraint(baseColumnNames: "sequence_alignment_id", baseTableName: "file_metadata", constraintName: "FK_p0dwk23v4mpuph7bvybfimjn9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sequence_alignment", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451504596317-16") {
		addForeignKeyConstraint(baseColumnNames: "project_id", baseTableName: "history", constraintName: "FK_fej8h317q2acuy144kav1oyxc", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "project", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451504596317-17") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "history", constraintName: "FK_fuutexvtx28fs971iq0kbfbmp", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451504596317-18") {
		addForeignKeyConstraint(baseColumnNames: "protocol_instance_id", baseTableName: "item", constraintName: "FK_rv13l1tnj5m3x3r61t7yq97ue", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "protocol_instance", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451504596317-19") {
		addForeignKeyConstraint(baseColumnNames: "type_id", baseTableName: "item", constraintName: "FK_qxnbu16tlqfmub9pgfj3h2e41", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "item_type", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451504596317-20") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "pool", constraintName: "FK_m6wrsc7nbw6p4ulsp0cg65jhi", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451504596317-21") {
		addForeignKeyConstraint(baseColumnNames: "prior_id", baseTableName: "protocol_instance", constraintName: "FK_p9g9nnnknkaftxrkf1gktn8x6", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "protocol_instance", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451504596317-22") {
		addForeignKeyConstraint(baseColumnNames: "protocol_id", baseTableName: "protocol_instance", constraintName: "FK_d4v22o39k3xu8m216w8r1nyx1", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "protocol", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451504596317-23") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "protocol_instance", constraintName: "FK_sgj3vhj59cxyb10md7ybwmtm7", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451504596317-24") {
		addForeignKeyConstraint(baseColumnNames: "align_type_id", baseTableName: "sequence_alignment", constraintName: "FK_8gcis40hwme6s7ywn4jikodd2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "align_type", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451504596317-25") {
		addForeignKeyConstraint(baseColumnNames: "aligner_id", baseTableName: "sequence_alignment", constraintName: "FK_fee47iwmejna7h55jp8dpkowd", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "aligner", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451504596317-26") {
		addForeignKeyConstraint(baseColumnNames: "core_pipeline_id", baseTableName: "sequence_alignment", constraintName: "FK_hc4f4br571qwgavrswy22w5wo", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "core_pipeline", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451504596317-27") {
		addForeignKeyConstraint(baseColumnNames: "genome_id", baseTableName: "sequence_alignment", constraintName: "FK_5tdpednm424bwlyaygxb91cj8", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "genome", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451504596317-28") {
		addForeignKeyConstraint(baseColumnNames: "sequencing_experiment_id", baseTableName: "sequence_alignment", constraintName: "FK_js0whth80fyna834brwbbo8v0", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sequencing_experiment", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451504596317-29") {
		addForeignKeyConstraint(baseColumnNames: "platform_id", baseTableName: "sequence_run", constraintName: "FK_l1ofj1csp7k439e52l32soay9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sequencing_platform", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451504596317-30") {
		addForeignKeyConstraint(baseColumnNames: "pool_id", baseTableName: "sequence_run", constraintName: "FK_jrr4ojwkpl4jryj9ippkg55ed", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "pool", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451504596317-31") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "sequence_run", constraintName: "FK_gerfjuiu4n7m0iukeud2wrux7", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451504596317-32") {
		addForeignKeyConstraint(baseColumnNames: "read_type_id", baseTableName: "sequencing_experiment", constraintName: "FK_3s9e144d345uyhay28toy8jwg", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "read_type", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451504596317-33") {
		addForeignKeyConstraint(baseColumnNames: "sample_id", baseTableName: "sequencing_experiment", constraintName: "FK_7gsumwrx4h3nji2g2q5htn098", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sample", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451504596317-34") {
		addForeignKeyConstraint(baseColumnNames: "sequence_run_id", baseTableName: "sequencing_experiment", constraintName: "FK_3mx03u80x4ynhsx1i65yrrxcq", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sequence_run", referencesUniqueColumn: "false")
	}
}
