databaseChangeLog = {

	changeSet(author: "dus73 (generated)", id: "1458836925498-1") {
		createTable(tableName: "peak_statistics") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "peak_statistiPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "genome_coverage", type: "float")

			column(name: "median_tag_singletons", type: "integer")

			column(name: "peak_file_path", type: "varchar(255)")

			column(name: "peak_mean", type: "integer")

			column(name: "peak_mean_std", type: "integer")

			column(name: "peak_median", type: "integer")

			column(name: "peak_median_std", type: "integer")

			column(name: "peak_pair_nos", type: "integer")

			column(name: "peak_pair_wis", type: "integer")

			column(name: "peaks", type: "integer")

			column(name: "repeated_regions", type: "integer")

			column(name: "seq_duplication_level", type: "float")

			column(name: "singletons", type: "integer")

			column(name: "tss_distal", type: "integer")

			column(name: "tss_proximal", type: "integer")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1458836925498-2") {
		addColumn(tableName: "sequence_alignment") {
			column(name: "avg_insert_size", type: "integer")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1458836925498-3") {
		addColumn(tableName: "sequence_alignment") {
			column(name: "bam_file_path", type: "varchar(1000)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1458836925498-4") {
		addColumn(tableName: "sequence_alignment") {
			column(name: "dedup_read_count", type: "integer")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1458836925498-5") {
		addColumn(tableName: "sequence_alignment") {
			column(name: "mapped_read_count", type: "integer")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1458836925498-6") {
		addColumn(tableName: "sequence_alignment") {
			column(name: "peak_statistics_id", type: "bigint")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1458836925498-7") {
		addColumn(tableName: "sequence_alignment") {
			column(name: "unique_mapped_read_count", type: "integer")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1458836925498-8") {
		addColumn(tableName: "sequence_run") {
			column(name: "total_num_reads", type: "integer")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1458836925498-9") {
		addColumn(tableName: "sequencing_experiment") {
			column(name: "adapter_count", type: "integer")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1458836925498-10") {
		addColumn(tableName: "sequencing_experiment") {
			column(name: "index_mismatch", type: "integer")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1458836925498-11") {
		addColumn(tableName: "sequencing_experiment") {
			column(name: "fastq_file_path", type: "varchar(1000)")
		}
	}
    
    changeSet(author: "dus73 (generated)", id: "1458836925498-15") {
        dropColumn(columnName: "file_paths", tableName: "sequencing_experiment") 
	}

	changeSet(author: "dus73 (generated)", id: "1458836925498-13") {
		createIndex(indexName: "FK_93wy818byq00fooh0rmsr8ui7", tableName: "sequence_alignment") {
			column(name: "peak_statistics_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1458836925498-14") {
		dropColumn(columnName: "file_paths", tableName: "sequence_alignment")
	}

	changeSet(author: "dus73 (generated)", id: "1458836925498-12") {
		addForeignKeyConstraint(baseColumnNames: "peak_statistics_id", baseTableName: "sequence_alignment", constraintName: "FK_93wy818byq00fooh0rmsr8ui7", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "peak_statistics", referencesUniqueColumn: "false")
	}
}
