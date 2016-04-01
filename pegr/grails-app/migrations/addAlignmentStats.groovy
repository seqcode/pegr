databaseChangeLog = {

	changeSet(author: "dus73 (generated)", id: "1459541828624-1") {
		createTable(tableName: "alignment_stats") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "alignment_staPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "adapter_count", type: "integer")

			column(name: "avg_insert_size", type: "integer")

			column(name: "dedup_read_count", type: "integer")

			column(name: "genome_coverage", type: "float")

			column(name: "index_count", type: "integer")

			column(name: "index_mismatch", type: "integer")

			column(name: "ip_strength", type: "float")

			column(name: "mapped_read_count", type: "integer")

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

			column(name: "spike_in_count", type: "integer")

			column(name: "tss_distal", type: "integer")

			column(name: "tss_proximal", type: "integer")

			column(name: "unique_mapped_read_count", type: "integer")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1459541828624-2") {
		addColumn(tableName: "sequence_alignment") {
			column(name: "alignment_stats_id", type: "bigint")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1459541828624-3") {
		addColumn(tableName: "sequence_alignment") {
			column(name: "bam_file_path", type: "varchar(1000)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1459541828624-4") {
		addColumn(tableName: "sequencing_experiment") {
			column(name: "fastq_file_path", type: "varchar(500)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1459541828624-5") {
		modifyDataType(columnName: "note", newDataType: "text", tableName: "sample")
	}

	changeSet(author: "dus73 (generated)", id: "1459541828624-7") {
		createIndex(indexName: "FK_i49xk3gljhqol3cvfkkumjksh", tableName: "sequence_alignment") {
			column(name: "alignment_stats_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1459541828624-8") {
		dropColumn(columnName: "file_paths", tableName: "sequence_alignment")
	}

	changeSet(author: "dus73 (generated)", id: "1459541828624-9") {
		dropColumn(columnName: "file_paths", tableName: "sequencing_experiment")
	}

	changeSet(author: "dus73 (generated)", id: "1459541828624-10") {
		dropColumn(columnName: "number_reads", tableName: "sequencing_experiment")
	}

	changeSet(author: "dus73 (generated)", id: "1459541828624-6") {
		addForeignKeyConstraint(baseColumnNames: "alignment_stats_id", baseTableName: "sequence_alignment", constraintName: "FK_i49xk3gljhqol3cvfkkumjksh", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "alignment_stats", referencesUniqueColumn: "false")
	}
}
