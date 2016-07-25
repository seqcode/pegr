databaseChangeLog = {

	changeSet(author: "dus73 (generated)", id: "1469476064537-1") {
		createTable(tableName: "control_sample") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "control_samplPK")
			}

			column(name: "control_sample_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "sample_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "dus73 (generated)", id: "1469476064537-2") {
		createTable(tableName: "report_alignments") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "report_alignmPK")
			}

			column(name: "alignment_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "report_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "dus73 (generated)", id: "1469476064537-3") {
		createTable(tableName: "sample_treatments") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "sample_treatmPK")
			}

			column(name: "sample_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "treatment_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "dus73 (generated)", id: "1469476064537-4") {
		addColumn(tableName: "analysis") {
			column(name: "step_id", type: "varchar(255)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1469476064537-5") {
		addColumn(tableName: "protocol") {
			column(name: "short_name", type: "varchar(255)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1469476064537-6") {
		addColumn(tableName: "sample") {
			column(name: "growth_media_id", type: "bigint")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1469476064537-12") {
		dropForeignKeyConstraint(baseTableName: "sequence_alignment", baseTableSchemaName: "pegr", constraintName: "FK_305x86nih993ksbixfnq599oc")
	}

	changeSet(author: "dus73 (generated)", id: "1469476064537-20") {
		createIndex(indexName: "FK_n5xni9ruiej5e6p37qkxc0ha8", tableName: "control_sample") {
			column(name: "sample_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1469476064537-21") {
		createIndex(indexName: "FK_pmyy4lhr2rnht0taqgm4rpfq3", tableName: "control_sample") {
			column(name: "control_sample_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1469476064537-22") {
		createIndex(indexName: "unique_sample_id", tableName: "control_sample", unique: "true") {
			column(name: "control_sample_id")

			column(name: "sample_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1469476064537-23") {
		createIndex(indexName: "FK_invrh9of0ndhq9wetgqupqjuu", tableName: "report_alignments") {
			column(name: "alignment_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1469476064537-24") {
		createIndex(indexName: "FK_jgmi242smigukk2otj453sld8", tableName: "report_alignments") {
			column(name: "report_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1469476064537-25") {
		createIndex(indexName: "unique_report_id", tableName: "report_alignments", unique: "true") {
			column(name: "alignment_id")

			column(name: "report_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1469476064537-26") {
		createIndex(indexName: "FK_ppsd5p8eqaveimasoymlfgcny", tableName: "sample") {
			column(name: "growth_media_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1469476064537-27") {
		createIndex(indexName: "FK_cldug6dnvxfcnd89wfrgg9e7i", tableName: "sample_treatments") {
			column(name: "sample_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1469476064537-28") {
		createIndex(indexName: "FK_ehfppi0f0g2lojkytdeaij3lp", tableName: "sample_treatments") {
			column(name: "treatment_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1469476064537-29") {
		createIndex(indexName: "unique_sample_id", tableName: "sample_treatments", unique: "true") {
			column(name: "treatment_id")

			column(name: "sample_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1469476064537-30") {
		dropColumn(columnName: "summary_report_id", tableName: "sequence_alignment")
	}

	changeSet(author: "dus73 (generated)", id: "1469476064537-13") {
		addForeignKeyConstraint(baseColumnNames: "control_sample_id", baseTableName: "control_sample", constraintName: "FK_pmyy4lhr2rnht0taqgm4rpfq3", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sample", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1469476064537-14") {
		addForeignKeyConstraint(baseColumnNames: "sample_id", baseTableName: "control_sample", constraintName: "FK_n5xni9ruiej5e6p37qkxc0ha8", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sample", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1469476064537-15") {
		addForeignKeyConstraint(baseColumnNames: "alignment_id", baseTableName: "report_alignments", constraintName: "FK_invrh9of0ndhq9wetgqupqjuu", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sequence_alignment", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1469476064537-16") {
		addForeignKeyConstraint(baseColumnNames: "report_id", baseTableName: "report_alignments", constraintName: "FK_jgmi242smigukk2otj453sld8", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "summary_report", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1469476064537-17") {
		addForeignKeyConstraint(baseColumnNames: "growth_media_id", baseTableName: "sample", constraintName: "FK_ppsd5p8eqaveimasoymlfgcny", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "growth_media", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1469476064537-18") {
		addForeignKeyConstraint(baseColumnNames: "sample_id", baseTableName: "sample_treatments", constraintName: "FK_cldug6dnvxfcnd89wfrgg9e7i", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sample", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1469476064537-19") {
		addForeignKeyConstraint(baseColumnNames: "treatment_id", baseTableName: "sample_treatments", constraintName: "FK_ehfppi0f0g2lojkytdeaij3lp", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cell_source_treatment", referencesUniqueColumn: "false")
	}
}
