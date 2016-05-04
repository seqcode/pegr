databaseChangeLog = {

	changeSet(author: "dus73 (generated)", id: "1462372565771-1") {
		addColumn(tableName: "alignment_stats") {
			column(name: "bam_file", type: "varchar(1000)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1462372565771-2") {
		addColumn(tableName: "alignment_stats") {
			column(name: "composite_fig", type: "varchar(1000)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1462372565771-3") {
		addColumn(tableName: "alignment_stats") {
			column(name: "cwpair_file", type: "varchar(1000)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1462372565771-4") {
		addColumn(tableName: "alignment_stats") {
			column(name: "fastq_file", type: "varchar(1000)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1462372565771-5") {
		addColumn(tableName: "alignment_stats") {
			column(name: "fimo_file", type: "varchar(1000)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1462372565771-6") {
		addColumn(tableName: "alignment_stats") {
			column(name: "four_color_fig", type: "varchar(1000)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1462372565771-7") {
		renameColumn(tableName: "alignment_stats", oldColumnName: "peak_file_path", newColumnName: "genetrack_file", columnDataType: "varchar(1000)")
	}

	changeSet(author: "dus73 (generated)", id: "1462372565771-8") {
		addColumn(tableName: "alignment_stats") {
			column(name: "heatmap_fig", type: "varchar(1000)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1462372565771-9") {
		addColumn(tableName: "alignment_stats") {
			column(name: "meme_file", type: "varchar(1000)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1462372565771-10") {
		addColumn(tableName: "antibody") {
			column(name: "default_target_id", type: "bigint")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1462372565771-11") {
		addColumn(tableName: "inventory") {
			column(name: "location", type: "varchar(255)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1462372565771-12") {
		addColumn(tableName: "sample") {
			column(name: "antibody_notes", type: "varchar(255)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1462372565771-13") {
		modifyDataType(columnName: "note", newDataType: "text", tableName: "sample")
	}

	changeSet(author: "dus73 (generated)", id: "1462372565771-15") {
		createIndex(indexName: "FK_m6sxsi7lepp2alm9d8832uv26", tableName: "antibody") {
			column(name: "default_target_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1462372565771-16") {
		dropColumn(columnName: "galaxy_dataset_filepath", tableName: "alignment_stats")
	}

	changeSet(author: "dus73 (generated)", id: "1462372565771-18") {
		dropColumn(columnName: "bam_file_path", tableName: "sequence_alignment")
	}

	changeSet(author: "dus73 (generated)", id: "1462372565771-19") {
		dropColumn(columnName: "fastq_file_path", tableName: "sequencing_experiment")
	}

	changeSet(author: "dus73 (generated)", id: "1462372565771-14") {
		addForeignKeyConstraint(baseColumnNames: "default_target_id", baseTableName: "antibody", constraintName: "FK_m6sxsi7lepp2alm9d8832uv26", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "target", referencesUniqueColumn: "false")
	}
}
