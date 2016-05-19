databaseChangeLog = {

	changeSet(author: "dus73 (generated)", id: "1463680775473-1") {
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

			column(name: "statistics", type: "text") {
				constraints(nullable: "false")
			}

			column(name: "tool", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "workflow_id", type: "varchar(255)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1463680775473-2") {
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

	changeSet(author: "dus73 (generated)", id: "1463680775473-3") {
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

	changeSet(author: "dus73 (generated)", id: "1463680775473-4") {
		addColumn(tableName: "antibody") {
			column(name: "default_target_id", type: "bigint")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1463680775473-5") {
		addColumn(tableName: "inventory") {
			column(name: "location", type: "varchar(255)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1463680775473-6") {
		addColumn(tableName: "sample") {
			column(name: "antibody_notes", type: "varchar(255)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1463680775473-7") {
		addColumn(tableName: "sample") {
			column(name: "assay_id", type: "bigint")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1463680775473-8") {
		addColumn(tableName: "sample_sequence_indices") {
			column(name: "set_id", type: "integer")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1463680775473-9") {
		addColumn(tableName: "sequence_alignment") {
			column(name: "avg_insert_size", type: "float")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1463680775473-10") {
		addColumn(tableName: "sequence_alignment") {
			column(name: "bam_file", type: "varchar(1000)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1463680775473-11") {
		addColumn(tableName: "sequence_alignment") {
			column(name: "dedup_uniquely_mapped_reads", type: "integer")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1463680775473-12") {
		addColumn(tableName: "sequence_alignment") {
			column(name: "genome_coverage", type: "float")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1463680775473-13") {
		addColumn(tableName: "sequence_alignment") {
			column(name: "mapped_reads", type: "integer")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1463680775473-14") {
		addColumn(tableName: "sequence_alignment") {
			column(name: "pe_histogram", type: "varchar(1000)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1463680775473-15") {
		addColumn(tableName: "sequence_alignment") {
			column(name: "seq_duplication_level", type: "float")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1463680775473-16") {
		addColumn(tableName: "sequence_alignment") {
			column(name: "std_dev_insert_size", type: "float")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1463680775473-17") {
		addColumn(tableName: "sequence_alignment") {
			column(name: "uniquely_mapped_reads", type: "integer")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1463680775473-18") {
		addColumn(tableName: "sequencing_experiment") {
			column(name: "adapter_count", type: "integer")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1463680775473-19") {
		addColumn(tableName: "sequencing_experiment") {
			column(name: "fastq_file", type: "varchar(1000)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1463680775473-20") {
		addColumn(tableName: "sequencing_experiment") {
			column(name: "fastqc_report", type: "varchar(1000)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1463680775473-21") {
		addColumn(tableName: "sequencing_experiment") {
			column(name: "index_mismatch", type: "integer")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1463680775473-22") {
		addColumn(tableName: "sequencing_experiment") {
			column(name: "total_reads", type: "integer")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1463680775473-23") {
		modifyDataType(columnName: "note", newDataType: "text", tableName: "sample")
	}

	changeSet(author: "dus73 (generated)", id: "1463680775473-24") {
		dropForeignKeyConstraint(baseTableName: "core_pipeline", baseTableSchemaName: "pegr", constraintName: "FK_qhv857uuhkmcorb4pio2vhpy3")
	}

	changeSet(author: "dus73 (generated)", id: "1463680775473-25") {
		dropForeignKeyConstraint(baseTableName: "core_pipeline", baseTableSchemaName: "pegr", constraintName: "FK_mpgn6hl6vvbrcq3dd3tp130lx")
	}

	changeSet(author: "dus73 (generated)", id: "1463680775473-26") {
		dropForeignKeyConstraint(baseTableName: "core_pipeline", baseTableSchemaName: "pegr", constraintName: "FK_ceyrkr3xuabv7koe9ro018awy")
	}

	changeSet(author: "dus73 (generated)", id: "1463680775473-27") {
		dropForeignKeyConstraint(baseTableName: "core_pipeline", baseTableSchemaName: "pegr", constraintName: "FK_rm79jw9v0buyydkoy0nv05o52")
	}

	changeSet(author: "dus73 (generated)", id: "1463680775473-28") {
		dropForeignKeyConstraint(baseTableName: "file_metadata", baseTableSchemaName: "pegr", constraintName: "FK_8ei1cybnbixggmxfhwril3vac")
	}

	changeSet(author: "dus73 (generated)", id: "1463680775473-29") {
		dropForeignKeyConstraint(baseTableName: "file_metadata", baseTableSchemaName: "pegr", constraintName: "FK_p0dwk23v4mpuph7bvybfimjn9")
	}

	changeSet(author: "dus73 (generated)", id: "1463680775473-30") {
		dropForeignKeyConstraint(baseTableName: "sequence_alignment", baseTableSchemaName: "pegr", constraintName: "FK_i49xk3gljhqol3cvfkkumjksh")
	}

	changeSet(author: "dus73 (generated)", id: "1463680775473-31") {
		dropForeignKeyConstraint(baseTableName: "sequence_alignment", baseTableSchemaName: "pegr", constraintName: "FK_hc4f4br571qwgavrswy22w5wo")
	}

	changeSet(author: "dus73 (generated)", id: "1463680775473-32") {
		dropForeignKeyConstraint(baseTableName: "task", baseTableSchemaName: "pegr", constraintName: "FK_2ovu0g1rssdjbpgb6fj0l2vwy")
	}

	changeSet(author: "dus73 (generated)", id: "1463680775473-33") {
		dropForeignKeyConstraint(baseTableName: "task", baseTableSchemaName: "pegr", constraintName: "FK_89y9w7ykrtfvr9quf0acepu0k")
	}

	changeSet(author: "dus73 (generated)", id: "1463680775473-34") {
		dropForeignKeyConstraint(baseTableName: "task", baseTableSchemaName: "pegr", constraintName: "FK_4fmjedju7b35tb5cr71n3ntb0")
	}

	changeSet(author: "dus73 (generated)", id: "1463680775473-39") {
		dropIndex(indexName: "unique_name", tableName: "base_calling")
	}

	changeSet(author: "dus73 (generated)", id: "1463680775473-40") {
		dropIndex(indexName: "name_uniq_1456345093948", tableName: "computing_infrastructure")
	}

	changeSet(author: "dus73 (generated)", id: "1463680775473-41") {
		dropIndex(indexName: "unique_name", tableName: "core_pipeline")
	}

	changeSet(author: "dus73 (generated)", id: "1463680775473-42") {
		dropIndex(indexName: "name_uniq_1456345093952", tableName: "data_processing")
	}

	changeSet(author: "dus73 (generated)", id: "1463680775473-43") {
		dropIndex(indexName: "unique_name", tableName: "file_metadata")
	}

	changeSet(author: "dus73 (generated)", id: "1463680775473-44") {
		dropIndex(indexName: "name_uniq_1456345093956", tableName: "file_type")
	}

	changeSet(author: "dus73 (generated)", id: "1463680775473-45") {
		dropIndex(indexName: "name_uniq_1456345093966", tableName: "peak_finding")
	}

	changeSet(author: "dus73 (generated)", id: "1463680775473-46") {
		createIndex(indexName: "FK_8qg2onufaj9spg9xlnmdn6jk6", tableName: "analysis") {
			column(name: "pipeline_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1463680775473-47") {
		createIndex(indexName: "FK_jegylnmnp65k1q1698nu5sjjw", tableName: "analysis") {
			column(name: "alignment_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1463680775473-48") {
		createIndex(indexName: "FK_m6sxsi7lepp2alm9d8832uv26", tableName: "antibody") {
			column(name: "default_target_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1463680775473-49") {
		createIndex(indexName: "unique_name", tableName: "pipeline", unique: "true") {
			column(name: "pipeline_version")

			column(name: "name")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1463680775473-50") {
		createIndex(indexName: "FK_dhxymxy1936tgj756fntpg6xw", tableName: "sample") {
			column(name: "assay_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1463680775473-51") {
		dropColumn(columnName: "alignment_stats_id", tableName: "sequence_alignment")
	}

	changeSet(author: "dus73 (generated)", id: "1463680775473-52") {
		dropColumn(columnName: "bam_file_path", tableName: "sequence_alignment")
	}

	changeSet(author: "dus73 (generated)", id: "1463680775473-53") {
		dropColumn(columnName: "core_pipeline_id", tableName: "sequence_alignment")
	}

	changeSet(author: "dus73 (generated)", id: "1463680775473-54") {
		dropColumn(columnName: "fastq_file_path", tableName: "sequencing_experiment")
	}

	changeSet(author: "dus73 (generated)", id: "1463680775473-55") {
		dropTable(tableName: "alignment_stats")
	}

	changeSet(author: "dus73 (generated)", id: "1463680775473-56") {
		dropTable(tableName: "base_calling")
	}

	changeSet(author: "dus73 (generated)", id: "1463680775473-57") {
		dropTable(tableName: "computing_infrastructure")
	}

	changeSet(author: "dus73 (generated)", id: "1463680775473-58") {
		dropTable(tableName: "core_pipeline")
	}

	changeSet(author: "dus73 (generated)", id: "1463680775473-59") {
		dropTable(tableName: "data_processing")
	}

	changeSet(author: "dus73 (generated)", id: "1463680775473-60") {
		dropTable(tableName: "file_metadata")
	}

	changeSet(author: "dus73 (generated)", id: "1463680775473-61") {
		dropTable(tableName: "file_type")
	}

	changeSet(author: "dus73 (generated)", id: "1463680775473-62") {
		dropTable(tableName: "peak_finding")
	}

	changeSet(author: "dus73 (generated)", id: "1463680775473-63") {
		dropTable(tableName: "task")
	}

	changeSet(author: "dus73 (generated)", id: "1463680775473-35") {
		addForeignKeyConstraint(baseColumnNames: "alignment_id", baseTableName: "analysis", constraintName: "FK_jegylnmnp65k1q1698nu5sjjw", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sequence_alignment", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1463680775473-36") {
		addForeignKeyConstraint(baseColumnNames: "pipeline_id", baseTableName: "analysis", constraintName: "FK_8qg2onufaj9spg9xlnmdn6jk6", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "pipeline", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1463680775473-37") {
		addForeignKeyConstraint(baseColumnNames: "default_target_id", baseTableName: "antibody", constraintName: "FK_m6sxsi7lepp2alm9d8832uv26", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "target", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1463680775473-38") {
		addForeignKeyConstraint(baseColumnNames: "assay_id", baseTableName: "sample", constraintName: "FK_dhxymxy1936tgj756fntpg6xw", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "assay", referencesUniqueColumn: "false")
	}
}
