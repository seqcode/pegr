databaseChangeLog = {

	changeSet(author: "danyingshao (generated)", id: "1451502199891-1") {
		createTable(tableName: "base_calling") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "base_callingPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "base_calling_version", type: "varchar(10)")

			column(name: "name", type: "varchar(30)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451502199891-2") {
		createTable(tableName: "chrom_sequence") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "chrom_sequencPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "chromosome_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "length", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "sequence", type: "longtext") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451502199891-3") {
		createTable(tableName: "chromosome") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "chromosomePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "genome_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(30)") {
				constraints(nullable: "false")
			}

			column(name: "note", type: "varchar(255)")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451502199891-4") {
		createTable(tableName: "computing_infrastructure") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "computing_infPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(20)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451502199891-5") {
		createTable(tableName: "genome") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "genomePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "genome_build", type: "varchar(30)") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(30)") {
				constraints(nullable: "false")
			}

			column(name: "species_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451502199891-6") {
		createTable(tableName: "species") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "speciesPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "genus_name", type: "varchar(50)") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(50)") {
				constraints(nullable: "false")
			}

			column(name: "note", type: "varchar(255)")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451502199891-10") {
		createIndex(indexName: "unique_name", tableName: "base_calling", unique: "true") {
			column(name: "base_calling_version")

			column(name: "name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451502199891-11") {
		createIndex(indexName: "FK_lese17egs9gkqmyudn2wh5il2", tableName: "chrom_sequence") {
			column(name: "chromosome_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451502199891-12") {
		createIndex(indexName: "FK_qtx5yloqyq7hc1efournhys5u", tableName: "chromosome") {
			column(name: "genome_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451502199891-13") {
		createIndex(indexName: "name_uniq_1451502199730", tableName: "chromosome", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451502199891-14") {
		createIndex(indexName: "name_uniq_1451502199731", tableName: "computing_infrastructure", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451502199891-15") {
		createIndex(indexName: "FK_8qyh94lw1ucpnn89d4dl4e5dr", tableName: "genome") {
			column(name: "species_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451502199891-16") {
		createIndex(indexName: "name_uniq_1451502199737", tableName: "species", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451502199891-7") {
		addForeignKeyConstraint(baseColumnNames: "chromosome_id", baseTableName: "chrom_sequence", constraintName: "FK_lese17egs9gkqmyudn2wh5il2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "chromosome", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451502199891-8") {
		addForeignKeyConstraint(baseColumnNames: "genome_id", baseTableName: "chromosome", constraintName: "FK_qtx5yloqyq7hc1efournhys5u", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "genome", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451502199891-9") {
		addForeignKeyConstraint(baseColumnNames: "species_id", baseTableName: "genome", constraintName: "FK_8qyh94lw1ucpnn89d4dl4e5dr", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "species", referencesUniqueColumn: "false")
	}
}
