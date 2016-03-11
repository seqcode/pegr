databaseChangeLog = {

	changeSet(author: "dus73 (generated)", id: "1457733706601-1") {
		createTable(tableName: "run_stats") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "run_statsPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "cluster_num", type: "float")

			column(name: "cycles", type: "integer")

			column(name: "library_loaded_fmol", type: "float")

			column(name: "library_loaded_pm", type: "float")

			column(name: "library_std_dev", type: "float")

			column(name: "library_stock", type: "float")

			column(name: "library_volume", type: "float")

			column(name: "pcr_cycles", type: "integer")

			column(name: "pct_aligned_to_phix", type: "float")

			column(name: "pct_library_std_dev", type: "float")

			column(name: "pct_pf", type: "float")

			column(name: "pctq30", type: "float")

			column(name: "pct_unmatched_indices", type: "float")

			column(name: "phixloaded", type: "float")

			column(name: "q_pcr_conc", type: "float")

			column(name: "q_pcr_date", type: "datetime")

			column(name: "q_pcr_technician_id", type: "bigint")

			column(name: "qidx", type: "float")

			column(name: "qubit_conc", type: "float")

			column(name: "read_pf", type: "float")

			column(name: "seq_ctrl", type: "varchar(255)")

			column(name: "sr_or_pe", type: "varchar(255)")

			column(name: "total_reads", type: "float")

			column(name: "unmatched_indices", type: "float")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1457733706601-2") {
		addColumn(tableName: "sequence_run") {
			column(name: "pool_item_id", type: "bigint")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1457733706601-3") {
		addColumn(tableName: "sequence_run") {
			column(name: "run_stats_id", type: "bigint")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1457733706601-4") {
		modifyDataType(columnName: "note", newDataType: "text", tableName: "sample")
	}

	changeSet(author: "dus73 (generated)", id: "1457733706601-5") {
		dropForeignKeyConstraint(baseTableName: "cell_source_cell_source_treatments", baseTableSchemaName: "pegr", constraintName: "FK_1ni2dp2onaacc5jhsifyiohop")
	}

	changeSet(author: "dus73 (generated)", id: "1457733706601-6") {
		dropForeignKeyConstraint(baseTableName: "cell_source_cell_source_treatments", baseTableSchemaName: "pegr", constraintName: "FK_ip0egm28ey9b78jnqyoke624h")
	}

	changeSet(author: "dus73 (generated)", id: "1457733706601-7") {
		dropForeignKeyConstraint(baseTableName: "genotype", baseTableSchemaName: "pegr", constraintName: "FK_ag1m8bhk8on25x3thx4edhu34")
	}

	changeSet(author: "dus73 (generated)", id: "1457733706601-8") {
		dropForeignKeyConstraint(baseTableName: "strain_genetic_modifications", baseTableSchemaName: "pegr", constraintName: "FK_bybku3lrkjee3jdtq8sixm2if")
	}

	changeSet(author: "dus73 (generated)", id: "1457733706601-9") {
		dropForeignKeyConstraint(baseTableName: "strain_genetic_modifications", baseTableSchemaName: "pegr", constraintName: "FK_tfjl1k0206qdhf4x28xx5fxal")
	}

	changeSet(author: "dus73 (generated)", id: "1457733706601-13") {
		dropIndex(indexName: "name_uniq_1451941640190", tableName: "genetic_modification")
	}

	changeSet(author: "dus73 (generated)", id: "1457733706601-14") {
		dropIndex(indexName: "name_uniq_1451941640191", tableName: "genotype")
	}

	changeSet(author: "dus73 (generated)", id: "1457733706601-15") {
		createIndex(indexName: "FK_8ss3vfgml8vxn31ksj0dvd0fa", tableName: "run_stats") {
			column(name: "q_pcr_technician_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1457733706601-16") {
		createIndex(indexName: "FK_5cc4mivg8mvxoipvbrl09geqj", tableName: "sequence_run") {
			column(name: "run_stats_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1457733706601-17") {
		createIndex(indexName: "FK_nb6cvqg7y8ruphnbfber6qjsb", tableName: "sequence_run") {
			column(name: "pool_item_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1457733706601-18") {
		dropTable(tableName: "cell_source_cell_source_treatments")
	}

	changeSet(author: "dus73 (generated)", id: "1457733706601-19") {
		dropTable(tableName: "genetic_modification")
	}

	changeSet(author: "dus73 (generated)", id: "1457733706601-20") {
		dropTable(tableName: "genotype")
	}

	changeSet(author: "dus73 (generated)", id: "1457733706601-21") {
		dropTable(tableName: "strain_genetic_modifications")
	}

	changeSet(author: "dus73 (generated)", id: "1457733706601-10") {
		addForeignKeyConstraint(baseColumnNames: "q_pcr_technician_id", baseTableName: "run_stats", constraintName: "FK_8ss3vfgml8vxn31ksj0dvd0fa", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1457733706601-11") {
		addForeignKeyConstraint(baseColumnNames: "pool_item_id", baseTableName: "sequence_run", constraintName: "FK_nb6cvqg7y8ruphnbfber6qjsb", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "item", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1457733706601-12") {
		addForeignKeyConstraint(baseColumnNames: "run_stats_id", baseTableName: "sequence_run", constraintName: "FK_5cc4mivg8mvxoipvbrl09geqj", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "run_stats", referencesUniqueColumn: "false")
	}
}
