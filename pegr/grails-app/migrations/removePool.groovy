databaseChangeLog = {

	changeSet(author: "dus73 (generated)", id: "1454705983189-1") {
		createTable(tableName: "invoice") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "invoicePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "date", type: "datetime")

			column(name: "invoice_num", type: "varchar(255)")

			column(name: "service_id", type: "varchar(255)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1454705983189-2") {
		createTable(tableName: "sample_in_run") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "sample_in_runPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "params", type: "varchar(255)")

			column(name: "pool", type: "varchar(255)")

			column(name: "pool_date", type: "datetime")

			column(name: "run_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "sample_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "volume_to_pool", type: "float")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1454705983189-3") {
		addColumn(tableName: "cell_source") {
			column(name: "prep_user_id", type: "bigint")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1454705983189-4") {
		addColumn(tableName: "sample") {
			column(name: "invoice_id", type: "bigint")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1454705983189-5") {
		addColumn(tableName: "sample") {
			column(name: "volume", type: "integer")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1454705983189-6") {
		addColumn(tableName: "sequence_alignment") {
			column(name: "file_paths", type: "varchar(1000)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1454705983189-7") {
		addColumn(tableName: "sequence_alignment") {
			column(name: "is_preferred", type: "bit") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "dus73 (generated)", id: "1454705983189-8") {
		addColumn(tableName: "sequence_alignment") {
			column(name: "params", type: "varchar(2000)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1454705983189-9") {
		addColumn(tableName: "sequence_run") {
			column(name: "pool", type: "varchar(255)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1454705983189-10") {
		addColumn(tableName: "sequencing_experiment") {
			column(name: "file_paths", type: "varchar(500)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1454705983189-11") {
		dropNotNullConstraint(columnDataType: "bigint", columnName: "ab_host_id", tableName: "antibody")
	}

	changeSet(author: "dus73 (generated)", id: "1454705983189-12") {
		modifyDataType(columnName: "details", newDataType: "text", tableName: "protocol")
	}

	changeSet(author: "dus73 (generated)", id: "1454705983189-13") {
		modifyDataType(columnName: "bag_idx", newDataType: "integer", tableName: "protocol_instance")
	}

	changeSet(author: "dus73 (generated)", id: "1454705983189-14") {
		addNotNullConstraint(columnDataType: "integer", columnName: "bag_idx", tableName: "protocol_instance")
	}

	changeSet(author: "dus73 (generated)", id: "1454705983189-15") {
		dropNotNullConstraint(columnDataType: "bigint", columnName: "protocol_id", tableName: "protocol_instance")
	}

	changeSet(author: "dus73 (generated)", id: "1454705983189-16") {
		dropNotNullConstraint(columnDataType: "bigint", columnName: "align_type_id", tableName: "sequence_alignment")
	}

	changeSet(author: "dus73 (generated)", id: "1454705983189-17") {
		dropNotNullConstraint(columnDataType: "bigint", columnName: "aligner_id", tableName: "sequence_alignment")
	}

	changeSet(author: "dus73 (generated)", id: "1454705983189-18") {
		dropNotNullConstraint(columnDataType: "bigint", columnName: "core_pipeline_id", tableName: "sequence_alignment")
	}

	changeSet(author: "dus73 (generated)", id: "1454705983189-19") {
		modifyDataType(columnName: "number_reads", newDataType: "integer", tableName: "sequencing_experiment")
	}

	changeSet(author: "dus73 (generated)", id: "1454705983189-20") {
		dropNotNullConstraint(columnDataType: "integer", columnName: "number_reads", tableName: "sequencing_experiment")
	}

	changeSet(author: "dus73 (generated)", id: "1454705983189-21") {
		dropNotNullConstraint(columnDataType: "bigint", columnName: "read_type_id", tableName: "sequencing_experiment")
	}

	changeSet(author: "dus73 (generated)", id: "1454705983189-22") {
		dropNotNullConstraint(columnDataType: "varchar(255)", columnName: "seq_id", tableName: "sequencing_experiment")
	}

	changeSet(author: "dus73 (generated)", id: "1454705983189-23") {
		dropNotNullConstraint(columnDataType: "bigint", columnName: "sequence_run_id", tableName: "sequencing_experiment")
	}

	changeSet(author: "dus73 (generated)", id: "1454705983189-24") {
		dropForeignKeyConstraint(baseTableName: "antibody", baseTableSchemaName: "pegr", constraintName: "FK_1n4i1c14220wwmiyv15uddx1r")
	}

	changeSet(author: "dus73 (generated)", id: "1454705983189-25") {
		dropForeignKeyConstraint(baseTableName: "pool", baseTableSchemaName: "pegr", constraintName: "FK_m6wrsc7nbw6p4ulsp0cg65jhi")
	}

	changeSet(author: "dus73 (generated)", id: "1454705983189-26") {
		dropForeignKeyConstraint(baseTableName: "sample_in_pool", baseTableSchemaName: "pegr", constraintName: "FK_96fu40wr3u20fqapuj2gdcm6m")
	}

	changeSet(author: "dus73 (generated)", id: "1454705983189-27") {
		dropForeignKeyConstraint(baseTableName: "sample_in_pool", baseTableSchemaName: "pegr", constraintName: "FK_41xrmt2o01xwuxfs7lg976piw")
	}

	changeSet(author: "dus73 (generated)", id: "1454705983189-28") {
		dropForeignKeyConstraint(baseTableName: "sequence_run", baseTableSchemaName: "pegr", constraintName: "FK_jrr4ojwkpl4jryj9ippkg55ed")
	}

	changeSet(author: "dus73 (generated)", id: "1454705983189-33") {
		dropIndex(indexName: "unique_sample_id", tableName: "sample_in_pool")
	}

	changeSet(author: "dus73 (generated)", id: "1454705983189-34") {
		dropIndex(indexName: "name_uniq_1454293004147", tableName: "target")
	}

	changeSet(author: "dus73 (generated)", id: "1454705983189-35") {
		createIndex(indexName: "FK_osoanky7172r4r5skdfatpr2n", tableName: "cell_source") {
			column(name: "prep_user_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1454705983189-36") {
		createIndex(indexName: "FK_tqw4ka9uvmywhb4gw0ib7nxy9", tableName: "sample") {
			column(name: "invoice_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1454705983189-37") {
		createIndex(indexName: "FK_993bhxmuyjlul88wourwo5eyw", tableName: "sample_in_run") {
			column(name: "run_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1454705983189-38") {
		createIndex(indexName: "FK_fsayjgiqhlt7cctq8n1escwvk", tableName: "sample_in_run") {
			column(name: "sample_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1454705983189-39") {
		createIndex(indexName: "unique_sample_id", tableName: "sample_in_run", unique: "true") {
			column(name: "run_id")

			column(name: "sample_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1454705983189-40") {
		createIndex(indexName: "unique_name", tableName: "target", unique: "true") {
			column(name: "c_term_tag")

			column(name: "n_term_tag")

			column(name: "name")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1454705983189-41") {
		dropColumn(columnName: "target_id", tableName: "antibody")
	}

	changeSet(author: "dus73 (generated)", id: "1454705983189-42") {
		dropColumn(columnName: "genome_build", tableName: "genome")
	}

	changeSet(author: "dus73 (generated)", id: "1454705983189-43") {
		dropColumn(columnName: "concentration", tableName: "sample")
	}

	changeSet(author: "dus73 (generated)", id: "1454705983189-44") {
		dropColumn(columnName: "quantity_received", tableName: "sample")
	}

	changeSet(author: "dus73 (generated)", id: "1454705983189-45") {
		dropColumn(columnName: "alignment_params", tableName: "sequence_alignment")
	}

	changeSet(author: "dus73 (generated)", id: "1454705983189-46") {
		dropColumn(columnName: "bam_file_path", tableName: "sequence_alignment")
	}

	changeSet(author: "dus73 (generated)", id: "1454705983189-47") {
		dropColumn(columnName: "idx_file_path", tableName: "sequence_alignment")
	}

	changeSet(author: "dus73 (generated)", id: "1454705983189-48") {
		dropColumn(columnName: "is_preferred_version", tableName: "sequence_alignment")
	}

	changeSet(author: "dus73 (generated)", id: "1454705983189-49") {
		dropColumn(columnName: "number_pairs", tableName: "sequence_alignment")
	}

	changeSet(author: "dus73 (generated)", id: "1454705983189-50") {
		dropColumn(columnName: "number_tags1", tableName: "sequence_alignment")
	}

	changeSet(author: "dus73 (generated)", id: "1454705983189-51") {
		dropColumn(columnName: "number_tags2", tableName: "sequence_alignment")
	}

	changeSet(author: "dus73 (generated)", id: "1454705983189-52") {
		dropColumn(columnName: "total_pair_weight", tableName: "sequence_alignment")
	}

	changeSet(author: "dus73 (generated)", id: "1454705983189-53") {
		dropColumn(columnName: "total_type1weight", tableName: "sequence_alignment")
	}

	changeSet(author: "dus73 (generated)", id: "1454705983189-54") {
		dropColumn(columnName: "total_type2weight", tableName: "sequence_alignment")
	}

	changeSet(author: "dus73 (generated)", id: "1454705983189-55") {
		dropColumn(columnName: "pool_id", tableName: "sequence_run")
	}

	changeSet(author: "dus73 (generated)", id: "1454705983189-56") {
		dropColumn(columnName: "fastq_file_paths", tableName: "sequencing_experiment")
	}

	changeSet(author: "dus73 (generated)", id: "1454705983189-57") {
		dropColumn(columnName: "fastqcreport_paths", tableName: "sequencing_experiment")
	}

	changeSet(author: "dus73 (generated)", id: "1454705983189-58") {
		dropTable(tableName: "pool")
	}

	changeSet(author: "dus73 (generated)", id: "1454705983189-59") {
		dropTable(tableName: "sample_in_pool")
	}

	changeSet(author: "dus73 (generated)", id: "1454705983189-29") {
		addForeignKeyConstraint(baseColumnNames: "prep_user_id", baseTableName: "cell_source", constraintName: "FK_osoanky7172r4r5skdfatpr2n", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1454705983189-30") {
		addForeignKeyConstraint(baseColumnNames: "invoice_id", baseTableName: "sample", constraintName: "FK_tqw4ka9uvmywhb4gw0ib7nxy9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "invoice", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1454705983189-31") {
		addForeignKeyConstraint(baseColumnNames: "run_id", baseTableName: "sample_in_run", constraintName: "FK_993bhxmuyjlul88wourwo5eyw", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sequence_run", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1454705983189-32") {
		addForeignKeyConstraint(baseColumnNames: "sample_id", baseTableName: "sample_in_run", constraintName: "FK_fsayjgiqhlt7cctq8n1escwvk", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sample", referencesUniqueColumn: "false")
	}
}
