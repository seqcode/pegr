databaseChangeLog = {

	changeSet(author: "dus73 (generated)", id: "1463766553938-1") {
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

	changeSet(author: "dus73 (generated)", id: "1463766553938-2") {
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

	changeSet(author: "dus73 (generated)", id: "1463766553938-3") {
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

	changeSet(author: "dus73 (generated)", id: "1463766553938-4") {
		addColumn(tableName: "antibody") {
			column(name: "default_target_id", type: "bigint")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1463766553938-5") {
		addColumn(tableName: "inventory") {
			column(name: "location", type: "varchar(255)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1463766553938-6") {
		addColumn(tableName: "sample") {
			column(name: "antibody_notes", type: "varchar(255)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1463766553938-7") {
		addColumn(tableName: "sample") {
			column(name: "assay_id", type: "bigint")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1463766553938-8") {
		addColumn(tableName: "sample_sequence_indices") {
			column(name: "set_id", type: "integer")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1463766553938-9") {
		addColumn(tableName: "sequence_alignment") {
			column(name: "avg_insert_size", type: "float")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1463766553938-10") {
		addColumn(tableName: "sequence_alignment") {
			column(name: "bam_file", type: "varchar(1000)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1463766553938-11") {
		addColumn(tableName: "sequence_alignment") {
			column(name: "dedup_uniquely_mapped_reads", type: "bigint")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1463766553938-12") {
		addColumn(tableName: "sequence_alignment") {
			column(name: "genome_coverage", type: "float")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1463766553938-13") {
		addColumn(tableName: "sequence_alignment") {
			column(name: "mapped_reads", type: "bigint")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1463766553938-14") {
		addColumn(tableName: "sequence_alignment") {
			column(name: "pe_histogram", type: "varchar(1000)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1463766553938-15") {
		addColumn(tableName: "sequence_alignment") {
			column(name: "seq_duplication_level", type: "float")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1463766553938-16") {
		addColumn(tableName: "sequence_alignment") {
			column(name: "std_dev_insert_size", type: "float")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1463766553938-17") {
		addColumn(tableName: "sequence_alignment") {
			column(name: "uniquely_mapped_reads", type: "bigint")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1463766553938-18") {
		addColumn(tableName: "sequencing_experiment") {
			column(name: "adapter_count", type: "bigint")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1463766553938-19") {
		addColumn(tableName: "sequencing_experiment") {
			column(name: "fastq_file", type: "varchar(1000)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1463766553938-20") {
		addColumn(tableName: "sequencing_experiment") {
			column(name: "fastqc_report", type: "varchar(1000)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1463766553938-21") {
		addColumn(tableName: "sequencing_experiment") {
			column(name: "index_mismatch", type: "integer")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1463766553938-22") {
		addColumn(tableName: "sequencing_experiment") {
			column(name: "total_reads", type: "bigint")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1463766553938-23") {
		modifyDataType(columnName: "cell_number", newDataType: "bigint", tableName: "sample")
	}

	changeSet(author: "dus73 (generated)", id: "1463766553938-24") {
		modifyDataType(columnName: "chromosome_amount", newDataType: "float", tableName: "sample")
	}

	changeSet(author: "dus73 (generated)", id: "1463766553938-25") {
		modifyDataType(columnName: "note", newDataType: "text", tableName: "sample")
	}

	changeSet(author: "dus73 (generated)", id: "1463766553938-26") {
		modifyDataType(columnName: "requested_tag_number", newDataType: "bigint", tableName: "sample")
	}

	changeSet(author: "dus73 (generated)", id: "1463766553938-27") {
		modifyDataType(columnName: "volume", newDataType: "float", tableName: "sample")
	}

	changeSet(author: "dus73 (generated)", id: "1463766553938-28") {
		dropForeignKeyConstraint(baseTableName: "core_pipeline", baseTableSchemaName: "pegr", constraintName: "FK_qhv857uuhkmcorb4pio2vhpy3")
	}

	changeSet(author: "dus73 (generated)", id: "1463766553938-29") {
		dropForeignKeyConstraint(baseTableName: "core_pipeline", baseTableSchemaName: "pegr", constraintName: "FK_mpgn6hl6vvbrcq3dd3tp130lx")
	}

	changeSet(author: "dus73 (generated)", id: "1463766553938-30") {
		dropForeignKeyConstraint(baseTableName: "core_pipeline", baseTableSchemaName: "pegr", constraintName: "FK_ceyrkr3xuabv7koe9ro018awy")
	}

	changeSet(author: "dus73 (generated)", id: "1463766553938-31") {
		dropForeignKeyConstraint(baseTableName: "core_pipeline", baseTableSchemaName: "pegr", constraintName: "FK_rm79jw9v0buyydkoy0nv05o52")
	}

	changeSet(author: "dus73 (generated)", id: "1463766553938-32") {
		dropForeignKeyConstraint(baseTableName: "file_metadata", baseTableSchemaName: "pegr", constraintName: "FK_8ei1cybnbixggmxfhwril3vac")
	}

	changeSet(author: "dus73 (generated)", id: "1463766553938-33") {
		dropForeignKeyConstraint(baseTableName: "file_metadata", baseTableSchemaName: "pegr", constraintName: "FK_p0dwk23v4mpuph7bvybfimjn9")
	}

	changeSet(author: "dus73 (generated)", id: "1463766553938-34") {
		dropForeignKeyConstraint(baseTableName: "sequence_alignment", baseTableSchemaName: "pegr", constraintName: "FK_i49xk3gljhqol3cvfkkumjksh")
	}

	changeSet(author: "dus73 (generated)", id: "1463766553938-35") {
		dropForeignKeyConstraint(baseTableName: "sequence_alignment", baseTableSchemaName: "pegr", constraintName: "FK_hc4f4br571qwgavrswy22w5wo")
	}

	changeSet(author: "dus73 (generated)", id: "1463766553938-36") {
		dropForeignKeyConstraint(baseTableName: "task", baseTableSchemaName: "pegr", constraintName: "FK_2ovu0g1rssdjbpgb6fj0l2vwy")
	}

	changeSet(author: "dus73 (generated)", id: "1463766553938-37") {
		dropForeignKeyConstraint(baseTableName: "task", baseTableSchemaName: "pegr", constraintName: "FK_89y9w7ykrtfvr9quf0acepu0k")
	}

	changeSet(author: "dus73 (generated)", id: "1463766553938-38") {
		dropForeignKeyConstraint(baseTableName: "task", baseTableSchemaName: "pegr", constraintName: "FK_4fmjedju7b35tb5cr71n3ntb0")
	}

	changeSet(author: "dus73 (generated)", id: "1463766553938-43") {
		dropIndex(indexName: "unique_name", tableName: "base_calling")
	}

	changeSet(author: "dus73 (generated)", id: "1463766553938-44") {
		dropIndex(indexName: "name_uniq_1456345093948", tableName: "computing_infrastructure")
	}

	changeSet(author: "dus73 (generated)", id: "1463766553938-45") {
		dropIndex(indexName: "unique_name", tableName: "core_pipeline")
	}

	changeSet(author: "dus73 (generated)", id: "1463766553938-46") {
		dropIndex(indexName: "name_uniq_1456345093952", tableName: "data_processing")
	}

	changeSet(author: "dus73 (generated)", id: "1463766553938-47") {
		dropIndex(indexName: "unique_name", tableName: "file_metadata")
	}

	changeSet(author: "dus73 (generated)", id: "1463766553938-48") {
		dropIndex(indexName: "name_uniq_1456345093956", tableName: "file_type")
	}

	changeSet(author: "dus73 (generated)", id: "1463766553938-49") {
		dropIndex(indexName: "name_uniq_1456345093966", tableName: "peak_finding")
	}

	changeSet(author: "dus73 (generated)", id: "1463766553938-50") {
		createIndex(indexName: "FK_8qg2onufaj9spg9xlnmdn6jk6", tableName: "analysis") {
			column(name: "pipeline_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1463766553938-51") {
		createIndex(indexName: "FK_jegylnmnp65k1q1698nu5sjjw", tableName: "analysis") {
			column(name: "alignment_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1463766553938-52") {
		createIndex(indexName: "FK_m6sxsi7lepp2alm9d8832uv26", tableName: "antibody") {
			column(name: "default_target_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1463766553938-53") {
		createIndex(indexName: "unique_name", tableName: "pipeline", unique: "true") {
			column(name: "pipeline_version")

			column(name: "name")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1463766553938-54") {
		createIndex(indexName: "FK_dhxymxy1936tgj756fntpg6xw", tableName: "sample") {
			column(name: "assay_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1463766553938-55") {
		dropColumn(columnName: "alignment_stats_id", tableName: "sequence_alignment")
	}

	changeSet(author: "dus73 (generated)", id: "1463766553938-56") {
		dropColumn(columnName: "bam_file_path", tableName: "sequence_alignment")
	}

	changeSet(author: "dus73 (generated)", id: "1463766553938-57") {
		dropColumn(columnName: "core_pipeline_id", tableName: "sequence_alignment")
	}

	changeSet(author: "dus73 (generated)", id: "1463766553938-58") {
		dropColumn(columnName: "fastq_file_path", tableName: "sequencing_experiment")
	}

	changeSet(author: "dus73 (generated)", id: "1463766553938-59") {
		dropTable(tableName: "alignment_stats")
	}

	changeSet(author: "dus73 (generated)", id: "1463766553938-60") {
		dropTable(tableName: "base_calling")
	}

	changeSet(author: "dus73 (generated)", id: "1463766553938-61") {
		dropTable(tableName: "computing_infrastructure")
	}

	changeSet(author: "dus73 (generated)", id: "1463766553938-62") {
		dropTable(tableName: "core_pipeline")
	}

	changeSet(author: "dus73 (generated)", id: "1463766553938-63") {
		dropTable(tableName: "data_processing")
	}

	changeSet(author: "dus73 (generated)", id: "1463766553938-64") {
		dropTable(tableName: "file_metadata")
	}

	changeSet(author: "dus73 (generated)", id: "1463766553938-65") {
		dropTable(tableName: "file_type")
	}

	changeSet(author: "dus73 (generated)", id: "1463766553938-66") {
		dropTable(tableName: "peak_finding")
	}

	changeSet(author: "dus73 (generated)", id: "1463766553938-67") {
		dropTable(tableName: "task")
	}

	changeSet(author: "dus73 (generated)", id: "1463766553938-39") {
		addForeignKeyConstraint(baseColumnNames: "alignment_id", baseTableName: "analysis", constraintName: "FK_jegylnmnp65k1q1698nu5sjjw", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sequence_alignment", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1463766553938-40") {
		addForeignKeyConstraint(baseColumnNames: "pipeline_id", baseTableName: "analysis", constraintName: "FK_8qg2onufaj9spg9xlnmdn6jk6", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "pipeline", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1463766553938-41") {
		addForeignKeyConstraint(baseColumnNames: "default_target_id", baseTableName: "antibody", constraintName: "FK_m6sxsi7lepp2alm9d8832uv26", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "target", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1463766553938-42") {
		addForeignKeyConstraint(baseColumnNames: "assay_id", baseTableName: "sample", constraintName: "FK_dhxymxy1936tgj756fntpg6xw", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "assay", referencesUniqueColumn: "false")
	}
}
