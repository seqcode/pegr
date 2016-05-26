databaseChangeLog = {

	changeSet(author: "dus73 (generated)", id: "1464284214580-1") {
		createTable(tableName: "analysis") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "analysisPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "alignment_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "category", type: "varchar(255)")

			column(name: "datasets", type: "text")

			column(name: "note", type: "varchar(255)")

			column(name: "parameters", type: "text")

			column(name: "pipeline_id", type: "bigint")

			column(name: "statistics", type: "text")

			column(name: "tool", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "workflow_id", type: "varchar(255)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-2") {
		createTable(tableName: "api_user") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "api_userPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "api_key", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-3") {
		createTable(tableName: "pipeline") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "pipelinePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "note", type: "varchar(255)")

			column(name: "pipeline_version", type: "varchar(255)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-4") {
		addColumn(tableName: "antibody") {
			column(name: "default_target_id", type: "bigint")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-5") {
		addColumn(tableName: "inventory") {
			column(name: "location", type: "varchar(255)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-6") {
		addColumn(tableName: "sample") {
			column(name: "antibody_notes", type: "varchar(255)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-7") {
		addColumn(tableName: "sample") {
			column(name: "assay_id", type: "bigint")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-8") {
		addColumn(tableName: "sample") {
			column(name: "requested_genomes", type: "varchar(255)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-9") {
		addColumn(tableName: "sample_sequence_indices") {
			column(name: "set_id", type: "integer")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-10") {
		addColumn(tableName: "sequence_alignment") {
			column(name: "avg_insert_size", type: "float")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-11") {
		addColumn(tableName: "sequence_alignment") {
			column(name: "bam_file", type: "varchar(1000)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-12") {
		addColumn(tableName: "sequence_alignment") {
			column(name: "dedup_uniquely_mapped_reads", type: "bigint")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-13") {
		addColumn(tableName: "sequence_alignment") {
			column(name: "genome_coverage", type: "float")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-14") {
		addColumn(tableName: "sequence_alignment") {
			column(name: "mapped_reads", type: "bigint")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-15") {
		addColumn(tableName: "sequence_alignment") {
			column(name: "pe_histogram", type: "varchar(1000)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-16") {
		addColumn(tableName: "sequence_alignment") {
			column(name: "seq_duplication_level", type: "float")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-17") {
		addColumn(tableName: "sequence_alignment") {
			column(name: "std_dev_insert_size", type: "float")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-18") {
		addColumn(tableName: "sequence_alignment") {
			column(name: "uniquely_mapped_reads", type: "bigint")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-19") {
		addColumn(tableName: "sequencing_experiment") {
			column(name: "adapter_count", type: "bigint")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-20") {
		addColumn(tableName: "sequencing_experiment") {
			column(name: "fastq_file", type: "varchar(1000)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-21") {
		addColumn(tableName: "sequencing_experiment") {
			column(name: "fastqc_report", type: "varchar(1000)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-22") {
		addColumn(tableName: "sequencing_experiment") {
			column(name: "index_mismatch", type: "integer")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-23") {
		addColumn(tableName: "sequencing_experiment") {
			column(name: "total_reads", type: "bigint")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-24") {
		modifyDataType(columnName: "cell_number", newDataType: "bigint", tableName: "sample")
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-25") {
		modifyDataType(columnName: "chromosome_amount", newDataType: "float", tableName: "sample")
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-26") {
		modifyDataType(columnName: "note", newDataType: "text", tableName: "sample")
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-27") {
		modifyDataType(columnName: "requested_tag_number", newDataType: "bigint", tableName: "sample")
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-28") {
		modifyDataType(columnName: "volume", newDataType: "float", tableName: "sample")
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-29") {
		modifyDataType(columnName: "index_id", newDataType: "varchar(255)", tableName: "sequence_index")
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-30") {
		dropForeignKeyConstraint(baseTableName: "core_pipeline", baseTableSchemaName: "pegr", constraintName: "FK_qhv857uuhkmcorb4pio2vhpy3")
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-31") {
		dropForeignKeyConstraint(baseTableName: "core_pipeline", baseTableSchemaName: "pegr", constraintName: "FK_mpgn6hl6vvbrcq3dd3tp130lx")
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-32") {
		dropForeignKeyConstraint(baseTableName: "core_pipeline", baseTableSchemaName: "pegr", constraintName: "FK_ceyrkr3xuabv7koe9ro018awy")
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-33") {
		dropForeignKeyConstraint(baseTableName: "core_pipeline", baseTableSchemaName: "pegr", constraintName: "FK_rm79jw9v0buyydkoy0nv05o52")
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-34") {
		dropForeignKeyConstraint(baseTableName: "file_metadata", baseTableSchemaName: "pegr", constraintName: "FK_8ei1cybnbixggmxfhwril3vac")
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-35") {
		dropForeignKeyConstraint(baseTableName: "file_metadata", baseTableSchemaName: "pegr", constraintName: "FK_p0dwk23v4mpuph7bvybfimjn9")
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-36") {
		dropForeignKeyConstraint(baseTableName: "sequence_alignment", baseTableSchemaName: "pegr", constraintName: "FK_i49xk3gljhqol3cvfkkumjksh")
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-37") {
		dropForeignKeyConstraint(baseTableName: "sequence_alignment", baseTableSchemaName: "pegr", constraintName: "FK_hc4f4br571qwgavrswy22w5wo")
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-38") {
		dropForeignKeyConstraint(baseTableName: "task", baseTableSchemaName: "pegr", constraintName: "FK_2ovu0g1rssdjbpgb6fj0l2vwy")
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-39") {
		dropForeignKeyConstraint(baseTableName: "task", baseTableSchemaName: "pegr", constraintName: "FK_89y9w7ykrtfvr9quf0acepu0k")
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-40") {
		dropForeignKeyConstraint(baseTableName: "task", baseTableSchemaName: "pegr", constraintName: "FK_4fmjedju7b35tb5cr71n3ntb0")
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-45") {
		dropIndex(indexName: "unique_name", tableName: "base_calling")
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-46") {
		dropIndex(indexName: "name_uniq_1456345093948", tableName: "computing_infrastructure")
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-47") {
		dropIndex(indexName: "unique_name", tableName: "core_pipeline")
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-48") {
		dropIndex(indexName: "name_uniq_1456345093952", tableName: "data_processing")
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-49") {
		dropIndex(indexName: "unique_name", tableName: "file_metadata")
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-50") {
		dropIndex(indexName: "name_uniq_1456345093956", tableName: "file_type")
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-51") {
		dropIndex(indexName: "name_uniq_1456345093966", tableName: "peak_finding")
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-52") {
		dropIndex(indexName: "unique_sample_id", tableName: "sample_sequence_indices")
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-53") {
		createIndex(indexName: "FK_8qg2onufaj9spg9xlnmdn6jk6", tableName: "analysis") {
			column(name: "pipeline_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-54") {
		createIndex(indexName: "FK_jegylnmnp65k1q1698nu5sjjw", tableName: "analysis") {
			column(name: "alignment_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-55") {
		createIndex(indexName: "FK_m6sxsi7lepp2alm9d8832uv26", tableName: "antibody") {
			column(name: "default_target_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-56") {
		createIndex(indexName: "unique_name", tableName: "pipeline", unique: "true") {
			column(name: "pipeline_version")

			column(name: "name")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-57") {
		createIndex(indexName: "FK_dhxymxy1936tgj756fntpg6xw", tableName: "sample") {
			column(name: "assay_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-58") {
		dropColumn(columnName: "alignment_stats_id", tableName: "sequence_alignment")
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-59") {
		dropColumn(columnName: "bam_file_path", tableName: "sequence_alignment")
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-60") {
		dropColumn(columnName: "core_pipeline_id", tableName: "sequence_alignment")
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-61") {
		dropColumn(columnName: "fastq_file_path", tableName: "sequencing_experiment")
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-62") {
		dropTable(tableName: "alignment_stats")
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-63") {
		dropTable(tableName: "base_calling")
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-64") {
		dropTable(tableName: "computing_infrastructure")
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-65") {
		dropTable(tableName: "core_pipeline")
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-66") {
		dropTable(tableName: "data_processing")
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-67") {
		dropTable(tableName: "file_metadata")
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-68") {
		dropTable(tableName: "file_type")
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-69") {
		dropTable(tableName: "peak_finding")
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-70") {
		dropTable(tableName: "task")
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-41") {
		addForeignKeyConstraint(baseColumnNames: "alignment_id", baseTableName: "analysis", constraintName: "FK_jegylnmnp65k1q1698nu5sjjw", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sequence_alignment", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-42") {
		addForeignKeyConstraint(baseColumnNames: "pipeline_id", baseTableName: "analysis", constraintName: "FK_8qg2onufaj9spg9xlnmdn6jk6", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "pipeline", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-43") {
		addForeignKeyConstraint(baseColumnNames: "default_target_id", baseTableName: "antibody", constraintName: "FK_m6sxsi7lepp2alm9d8832uv26", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "target", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1464284214580-44") {
		addForeignKeyConstraint(baseColumnNames: "assay_id", baseTableName: "sample", constraintName: "FK_dhxymxy1936tgj756fntpg6xw", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "assay", referencesUniqueColumn: "false")
	}
}
