databaseChangeLog = {

	changeSet(author: "danyingshao (generated)", id: "1451503483880-1") {
		createTable(tableName: "biological_replicate_set") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "biological_rePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503483880-2") {
		createTable(tableName: "project_samples") {
			column(name: "project_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "sample_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503483880-3") {
		createTable(tableName: "protocol") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "protocolPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "varchar(255)")

			column(name: "details", type: "text")

			column(name: "name", type: "varchar(30)") {
				constraints(nullable: "false")
			}

			column(name: "protocol_version", type: "varchar(10)")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503483880-4") {
		createTable(tableName: "protocol_group") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "protocol_grouPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(30)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503483880-5") {
		createTable(tableName: "protocol_group_protocols") {
			column(name: "protocol_group_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "protocol_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "protocols_idx", type: "integer")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503483880-6") {
		createTable(tableName: "sample") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "samplePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "antibody_id", type: "bigint")

			column(name: "biological_replicate_set_id", type: "bigint")

			column(name: "cell_number", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "cell_source_id", type: "bigint")

			column(name: "chromosome_amount", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "concentration", type: "float") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "note", type: "varchar(255)")

			column(name: "protocol_group_id", type: "bigint")

			column(name: "publication_reference", type: "varchar(30)")

			column(name: "quantity_received", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "requested_tag_number", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "spike_in_cell_source_id", type: "bigint")

			column(name: "status_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "target_id", type: "bigint")

			column(name: "technical_replicate_set_id", type: "bigint")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503483880-7") {
		createTable(tableName: "sample_sequence_indices") {
			column(name: "sequence_index_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "sample_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503483880-8") {
		createTable(tableName: "sample_status") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "sample_statusPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(20)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503483880-9") {
		createTable(tableName: "sequence_index") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "sequence_indePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "index_id", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "index_version", type: "varchar(10)") {
				constraints(nullable: "false")
			}

			column(name: "oligo", type: "varchar(255)")

			column(name: "sequence", type: "varchar(30)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503483880-10") {
		createTable(tableName: "technical_replicate_set") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "technical_repPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503483880-11") {
		addPrimaryKey(columnNames: "project_id, sample_id", tableName: "project_samples")
	}

	changeSet(author: "danyingshao (generated)", id: "1451503483880-12") {
		addPrimaryKey(columnNames: "sample_id, sequence_index_id", tableName: "sample_sequence_indices")
	}

	changeSet(author: "danyingshao (generated)", id: "1451503483880-27") {
		createIndex(indexName: "FK_bcuquxjs68mn5vyq7d0yphn8p", tableName: "project_samples") {
			column(name: "project_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503483880-28") {
		createIndex(indexName: "FK_qfu2n2hwoukibpvvx51eftp9y", tableName: "project_samples") {
			column(name: "sample_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503483880-29") {
		createIndex(indexName: "unique_name", tableName: "protocol", unique: "true") {
			column(name: "protocol_version")

			column(name: "name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503483880-30") {
		createIndex(indexName: "name_uniq_1451503483558", tableName: "protocol_group", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503483880-31") {
		createIndex(indexName: "FK_6i86p4hqvh803hqeam63pf0fo", tableName: "protocol_group_protocols") {
			column(name: "protocol_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503483880-32") {
		createIndex(indexName: "FK_6sjm1imt3f4b8rd492wm9vt7e", tableName: "protocol_group_protocols") {
			column(name: "protocol_group_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503483880-33") {
		createIndex(indexName: "FK_1d2270u07cw6ytkajov7d2wqs", tableName: "sample") {
			column(name: "status_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503483880-34") {
		createIndex(indexName: "FK_4lb993olybjsa6bqlbt90as3c", tableName: "sample") {
			column(name: "antibody_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503483880-35") {
		createIndex(indexName: "FK_a1ro7uobsoyc87a51ssw7n5sw", tableName: "sample") {
			column(name: "cell_source_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503483880-36") {
		createIndex(indexName: "FK_cfxg067kqgsk55rjibh2pbnso", tableName: "sample") {
			column(name: "target_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503483880-37") {
		createIndex(indexName: "FK_hd6wcvumdm4eysvsdnro5hd2f", tableName: "sample") {
			column(name: "technical_replicate_set_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503483880-38") {
		createIndex(indexName: "FK_iab90bxgihv46lqg7ib51kmwn", tableName: "sample") {
			column(name: "spike_in_cell_source_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503483880-39") {
		createIndex(indexName: "FK_libuiv9iymg5okp79f1maxt3", tableName: "sample") {
			column(name: "protocol_group_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503483880-40") {
		createIndex(indexName: "FK_lkd1fyshpt94t6hik30q046a3", tableName: "sample") {
			column(name: "biological_replicate_set_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503483880-41") {
		createIndex(indexName: "FK_qh0akbm1a3893iwxbwqps9xmn", tableName: "sample_sequence_indices") {
			column(name: "sample_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503483880-42") {
		createIndex(indexName: "FK_ruriwyvbrja8cfrayq2ux8c1u", tableName: "sample_sequence_indices") {
			column(name: "sequence_index_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503483880-43") {
		createIndex(indexName: "name_uniq_1451503483564", tableName: "sample_status", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503483880-44") {
		createIndex(indexName: "unique_index_id", tableName: "sequence_index", unique: "true") {
			column(name: "index_version")

			column(name: "index_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503483880-13") {
		addForeignKeyConstraint(baseColumnNames: "project_id", baseTableName: "project_samples", constraintName: "FK_bcuquxjs68mn5vyq7d0yphn8p", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "project", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451503483880-14") {
		addForeignKeyConstraint(baseColumnNames: "sample_id", baseTableName: "project_samples", constraintName: "FK_qfu2n2hwoukibpvvx51eftp9y", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sample", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451503483880-15") {
		addForeignKeyConstraint(baseColumnNames: "protocol_group_id", baseTableName: "protocol_group_protocols", constraintName: "FK_6sjm1imt3f4b8rd492wm9vt7e", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "protocol_group", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451503483880-16") {
		addForeignKeyConstraint(baseColumnNames: "protocol_id", baseTableName: "protocol_group_protocols", constraintName: "FK_6i86p4hqvh803hqeam63pf0fo", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "protocol", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451503483880-17") {
		addForeignKeyConstraint(baseColumnNames: "antibody_id", baseTableName: "sample", constraintName: "FK_4lb993olybjsa6bqlbt90as3c", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "antibody", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451503483880-18") {
		addForeignKeyConstraint(baseColumnNames: "biological_replicate_set_id", baseTableName: "sample", constraintName: "FK_lkd1fyshpt94t6hik30q046a3", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "biological_replicate_set", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451503483880-19") {
		addForeignKeyConstraint(baseColumnNames: "cell_source_id", baseTableName: "sample", constraintName: "FK_a1ro7uobsoyc87a51ssw7n5sw", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cell_source", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451503483880-20") {
		addForeignKeyConstraint(baseColumnNames: "protocol_group_id", baseTableName: "sample", constraintName: "FK_libuiv9iymg5okp79f1maxt3", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "protocol_group", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451503483880-21") {
		addForeignKeyConstraint(baseColumnNames: "spike_in_cell_source_id", baseTableName: "sample", constraintName: "FK_iab90bxgihv46lqg7ib51kmwn", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cell_source", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451503483880-22") {
		addForeignKeyConstraint(baseColumnNames: "status_id", baseTableName: "sample", constraintName: "FK_1d2270u07cw6ytkajov7d2wqs", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sample_status", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451503483880-23") {
		addForeignKeyConstraint(baseColumnNames: "target_id", baseTableName: "sample", constraintName: "FK_cfxg067kqgsk55rjibh2pbnso", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "target", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451503483880-24") {
		addForeignKeyConstraint(baseColumnNames: "technical_replicate_set_id", baseTableName: "sample", constraintName: "FK_hd6wcvumdm4eysvsdnro5hd2f", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "technical_replicate_set", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451503483880-25") {
		addForeignKeyConstraint(baseColumnNames: "sample_id", baseTableName: "sample_sequence_indices", constraintName: "FK_qh0akbm1a3893iwxbwqps9xmn", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sample", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451503483880-26") {
		addForeignKeyConstraint(baseColumnNames: "sequence_index_id", baseTableName: "sample_sequence_indices", constraintName: "FK_ruriwyvbrja8cfrayq2ux8c1u", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sequence_index", referencesUniqueColumn: "false")
	}
}
