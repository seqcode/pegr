databaseChangeLog = {

	changeSet(author: "danyingshao (generated)", id: "1457897783309-1") {
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

			column(name: "qidx", type: "float")

			column(name: "qubit_conc", type: "float")

			column(name: "read_pf", type: "float")

			column(name: "seq_ctrl", type: "varchar(255)")

			column(name: "sr_or_pe", type: "varchar(255)")

			column(name: "total_reads", type: "float")

			column(name: "unmatched_indices", type: "float")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1457897783309-2") {
		addColumn(tableName: "protocol") {
			column(name: "add_antibody", type: "bit")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1457897783309-3") {
		addColumn(tableName: "protocol") {
			column(name: "add_index", type: "bit")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1457897783309-4") {
		addColumn(tableName: "protocol") {
			column(name: "status", type: "varchar(255)")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1457897783309-5") {
		addColumn(tableName: "sequence_run") {
			column(name: "pool_item_id", type: "bigint")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1457897783309-6") {
		addColumn(tableName: "sequence_run") {
			column(name: "run_stats_id", type: "bigint")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1457897783309-7") {
		modifyDataType(columnName: "note", newDataType: "text", tableName: "sample")
	}

	changeSet(author: "danyingshao (generated)", id: "1457897783309-8") {
		dropForeignKeyConstraint(baseTableName: "cell_source_cell_source_treatments", baseTableSchemaName: "pegr", constraintName: "FK_1ni2dp2onaacc5jhsifyiohop")
	}

	changeSet(author: "danyingshao (generated)", id: "1457897783309-9") {
		dropForeignKeyConstraint(baseTableName: "cell_source_cell_source_treatments", baseTableSchemaName: "pegr", constraintName: "FK_ip0egm28ey9b78jnqyoke624h")
	}

	changeSet(author: "danyingshao (generated)", id: "1457897783309-10") {
		dropForeignKeyConstraint(baseTableName: "genotype", baseTableSchemaName: "pegr", constraintName: "FK_ag1m8bhk8on25x3thx4edhu34")
	}

	changeSet(author: "danyingshao (generated)", id: "1457897783309-11") {
		dropForeignKeyConstraint(baseTableName: "strain_genetic_modifications", baseTableSchemaName: "pegr", constraintName: "FK_bybku3lrkjee3jdtq8sixm2if")
	}

	changeSet(author: "danyingshao (generated)", id: "1457897783309-12") {
		dropForeignKeyConstraint(baseTableName: "strain_genetic_modifications", baseTableSchemaName: "pegr", constraintName: "FK_tfjl1k0206qdhf4x28xx5fxal")
	}

	changeSet(author: "danyingshao (generated)", id: "1457897783309-15") {
		dropIndex(indexName: "name_uniq_1451941640190", tableName: "genetic_modification")
	}

	changeSet(author: "danyingshao (generated)", id: "1457897783309-16") {
		dropIndex(indexName: "name_uniq_1451941640191", tableName: "genotype")
	}

	changeSet(author: "danyingshao (generated)", id: "1457897783309-17") {
		createIndex(indexName: "FK_5cc4mivg8mvxoipvbrl09geqj", tableName: "sequence_run") {
			column(name: "run_stats_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1457897783309-18") {
		createIndex(indexName: "FK_nb6cvqg7y8ruphnbfber6qjsb", tableName: "sequence_run") {
			column(name: "pool_item_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1457897783309-19") {
		dropTable(tableName: "cell_source_cell_source_treatments")
	}

	changeSet(author: "danyingshao (generated)", id: "1457897783309-20") {
		dropTable(tableName: "genetic_modification")
	}

	changeSet(author: "danyingshao (generated)", id: "1457897783309-21") {
		dropTable(tableName: "genotype")
	}

	changeSet(author: "danyingshao (generated)", id: "1457897783309-22") {
		dropTable(tableName: "strain_genetic_modifications")
	}

	changeSet(author: "danyingshao (generated)", id: "1457897783309-13") {
		addForeignKeyConstraint(baseColumnNames: "pool_item_id", baseTableName: "sequence_run", constraintName: "FK_nb6cvqg7y8ruphnbfber6qjsb", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "item", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1457897783309-14") {
		addForeignKeyConstraint(baseColumnNames: "run_stats_id", baseTableName: "sequence_run", constraintName: "FK_5cc4mivg8mvxoipvbrl09geqj", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "run_stats", referencesUniqueColumn: "false")
	}
}
