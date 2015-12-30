databaseChangeLog = {

	changeSet(author: "danyingshao (generated)", id: "1451501350493-1") {
		createTable(tableName: "antibody") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "antibodyPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "ab_host_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "catalog_number", type: "varchar(255)")

			column(name: "clonal", type: "varchar(255)")

			column(name: "company_id", type: "bigint")

			column(name: "concentration", type: "float") {
				constraints(nullable: "false")
			}

			column(name: "encode_id", type: "varchar(255)")

			column(name: "ig_type_id", type: "bigint")

			column(name: "immunogene", type: "varchar(255)")

			column(name: "inventory_id", type: "varchar(255)")

			column(name: "lot_number", type: "varchar(255)")

			column(name: "name", type: "varchar(30)") {
				constraints(nullable: "false")
			}

			column(name: "note", type: "varchar(255)")

			column(name: "target_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451501350493-2") {
		createTable(tableName: "ig_type") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "ig_typePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(30)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451501350493-3") {
		createTable(tableName: "organization") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "organizationPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "address_id", type: "bigint")

			column(name: "name", type: "varchar(50)") {
				constraints(nullable: "false")
			}

			column(name: "note", type: "varchar(255)")

			column(name: "parent_id", type: "bigint")

			column(name: "website", type: "varchar(255)")

			column(name: "class", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451501350493-4") {
		createTable(tableName: "target") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "targetPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "c_term_tag", type: "varchar(255)")

			column(name: "n_term_tag", type: "varchar(255)")

			column(name: "name", type: "varchar(30)") {
				constraints(nullable: "false")
			}

			column(name: "note", type: "varchar(255)")

			column(name: "target_type_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451501350493-5") {
		createTable(tableName: "target_type") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "target_typePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "varchar(255)")

			column(name: "name", type: "varchar(30)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451501350493-13") {
		createIndex(indexName: "FK_1n4i1c14220wwmiyv15uddx1r", tableName: "antibody") {
			column(name: "target_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451501350493-14") {
		createIndex(indexName: "FK_5bpi6ah0csjh4g0n8ln93wx3h", tableName: "antibody") {
			column(name: "ig_type_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451501350493-15") {
		createIndex(indexName: "FK_qjuxd91ddd77fufu1em86n2gb", tableName: "antibody") {
			column(name: "ab_host_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451501350493-16") {
		createIndex(indexName: "FK_rhc520eoim7txw9d7kqpb90fw", tableName: "antibody") {
			column(name: "company_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451501350493-17") {
		createIndex(indexName: "name_uniq_1451501350383", tableName: "antibody", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451501350493-18") {
		createIndex(indexName: "FK_57byxcy430qbl2gl7liup0py1", tableName: "organization") {
			column(name: "parent_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451501350493-19") {
		createIndex(indexName: "FK_l070gahmlj4g2sqbm72btw64e", tableName: "organization") {
			column(name: "address_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451501350493-20") {
		createIndex(indexName: "name_uniq_1451501350387", tableName: "organization", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451501350493-21") {
		createIndex(indexName: "FK_e5wo7j1f9so6mcll9vwtn6005", tableName: "target") {
			column(name: "target_type_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451501350493-22") {
		createIndex(indexName: "name_uniq_1451501350390", tableName: "target", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451501350493-23") {
		createIndex(indexName: "name_uniq_1451501350392", tableName: "target_type", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451501350493-6") {
		addForeignKeyConstraint(baseColumnNames: "ab_host_id", baseTableName: "antibody", constraintName: "FK_qjuxd91ddd77fufu1em86n2gb", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "ab_host", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451501350493-7") {
		addForeignKeyConstraint(baseColumnNames: "company_id", baseTableName: "antibody", constraintName: "FK_rhc520eoim7txw9d7kqpb90fw", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "organization", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451501350493-8") {
		addForeignKeyConstraint(baseColumnNames: "ig_type_id", baseTableName: "antibody", constraintName: "FK_5bpi6ah0csjh4g0n8ln93wx3h", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "ig_type", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451501350493-9") {
		addForeignKeyConstraint(baseColumnNames: "target_id", baseTableName: "antibody", constraintName: "FK_1n4i1c14220wwmiyv15uddx1r", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "target", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451501350493-10") {
		addForeignKeyConstraint(baseColumnNames: "address_id", baseTableName: "organization", constraintName: "FK_l070gahmlj4g2sqbm72btw64e", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "address", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451501350493-11") {
		addForeignKeyConstraint(baseColumnNames: "parent_id", baseTableName: "organization", constraintName: "FK_57byxcy430qbl2gl7liup0py1", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "organization", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451501350493-12") {
		addForeignKeyConstraint(baseColumnNames: "target_type_id", baseTableName: "target", constraintName: "FK_e5wo7j1f9so6mcll9vwtn6005", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "target_type", referencesUniqueColumn: "false")
	}
}
