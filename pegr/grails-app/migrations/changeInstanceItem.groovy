databaseChangeLog = {

	changeSet(author: "danyingshao (generated)", id: "1471396412843-1") {
		createTable(tableName: "item_antibody") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "item_antibodyPK")
			}

			column(name: "antibody_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "item_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1471396412843-2") {
		createTable(tableName: "item_sequence_indices") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "item_sequencePK")
			}

			column(name: "index_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "index_in_set", type: "integer")

			column(name: "item_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "set_id", type: "integer")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1471396412843-3") {
		addColumn(tableName: "protocol_instance_items") {
			column(name: "function", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1471396412843-9") {
		dropForeignKeyConstraint(baseTableName: "sample_bags", baseTableSchemaName: "pegr", constraintName: "FK_cnr1t3lrixla6a72js4vswgow")
	}

	changeSet(author: "danyingshao (generated)", id: "1471396412843-10") {
		dropForeignKeyConstraint(baseTableName: "sample_bags", baseTableSchemaName: "pegr", constraintName: "FK_m94py76hnab3k5yd83xstrenu")
	}

	changeSet(author: "danyingshao (generated)", id: "1471396412843-15") {
		dropIndex(indexName: "unique_protocol_instance_id", tableName: "protocol_instance_items")
	}

	changeSet(author: "danyingshao (generated)", id: "1471396412843-16") {
		createIndex(indexName: "FK_f68ojp3d1waxqqeoojl8j6nf5", tableName: "item_antibody") {
			column(name: "item_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1471396412843-17") {
		createIndex(indexName: "FK_jftvriocthroiuhj650ixyt1c", tableName: "item_antibody") {
			column(name: "antibody_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1471396412843-18") {
		createIndex(indexName: "unique_item_id", tableName: "item_antibody", unique: "true") {
			column(name: "antibody_id")

			column(name: "item_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1471396412843-19") {
		createIndex(indexName: "FK_8ip2o04e3gn7ulqvtxwpk2ycr", tableName: "item_sequence_indices") {
			column(name: "item_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1471396412843-20") {
		createIndex(indexName: "FK_fudqc8t4xmiaqk7ins2e4dsxs", tableName: "item_sequence_indices") {
			column(name: "index_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1471396412843-21") {
		dropTable(tableName: "sample_bags")
	}

	changeSet(author: "danyingshao (generated)", id: "1471396412843-11") {
		addForeignKeyConstraint(baseColumnNames: "antibody_id", baseTableName: "item_antibody", constraintName: "FK_jftvriocthroiuhj650ixyt1c", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "antibody", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1471396412843-12") {
		addForeignKeyConstraint(baseColumnNames: "item_id", baseTableName: "item_antibody", constraintName: "FK_f68ojp3d1waxqqeoojl8j6nf5", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "item", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1471396412843-13") {
		addForeignKeyConstraint(baseColumnNames: "index_id", baseTableName: "item_sequence_indices", constraintName: "FK_fudqc8t4xmiaqk7ins2e4dsxs", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sequence_index", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1471396412843-14") {
		addForeignKeyConstraint(baseColumnNames: "item_id", baseTableName: "item_sequence_indices", constraintName: "FK_8ip2o04e3gn7ulqvtxwpk2ycr", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "item", referencesUniqueColumn: "false")
	}
}
