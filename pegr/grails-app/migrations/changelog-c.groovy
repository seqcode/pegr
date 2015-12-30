databaseChangeLog = {

	changeSet(author: "danyingshao (generated)", id: "1451503071193-1") {
		createTable(tableName: "cell_source") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "cell_sourcePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "age", type: "varchar(30)")

			column(name: "biological_source_id", type: "varchar(50)")

			column(name: "histology_id", type: "bigint")

			column(name: "note", type: "varchar(255)")

			column(name: "provider_lab_id", type: "bigint")

			column(name: "provider_user_id", type: "bigint")

			column(name: "sex_id", type: "bigint")

			column(name: "strain_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "tissue_id", type: "bigint")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-2") {
		createTable(tableName: "cell_source_cell_source_treatments") {
			column(name: "cell_source_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "cell_source_treatment_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-3") {
		createTable(tableName: "cell_source_treatment") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "cell_source_tPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "compound", type: "varchar(30)") {
				constraints(nullable: "false")
			}

			column(name: "duration", type: "varchar(20)")

			column(name: "name", type: "varchar(30)") {
				constraints(nullable: "false")
			}

			column(name: "quantity", type: "varchar(20)")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-4") {
		createTable(tableName: "genetic_modification") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "genetic_modifPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(20)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-5") {
		createTable(tableName: "genotype") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "genotypePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(30)") {
				constraints(nullable: "false")
			}

			column(name: "note", type: "varchar(255)")

			column(name: "species_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-6") {
		createTable(tableName: "histology") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "histologyPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(30)") {
				constraints(nullable: "false")
			}

			column(name: "parent_id", type: "bigint")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-7") {
		createTable(tableName: "project") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "projectPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "varchar(1000)")

			column(name: "funding", type: "varchar(50)")

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-8") {
		createTable(tableName: "project_user") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "project_userPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "project_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "project_role", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-9") {
		createTable(tableName: "role") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "rolePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "authority", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-10") {
		createTable(tableName: "sex") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "sexPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(20)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-11") {
		createTable(tableName: "strain") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "strainPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "genotype_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "note", type: "varchar(255)")

			column(name: "parent_id", type: "bigint")

			column(name: "source_lab_id", type: "bigint")

			column(name: "tag", type: "varchar(255)")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-12") {
		createTable(tableName: "strain_genetic_modifications") {
			column(name: "genetic_modification_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "strain_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-13") {
		createTable(tableName: "tissue") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "tissuePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(30)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-14") {
		createTable(tableName: "user") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "userPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "account_expired", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "account_locked", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "address_id", type: "bigint")

			column(name: "email", type: "varchar(255)")

			column(name: "enabled", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "full_name", type: "varchar(50)")

			column(name: "lab_id", type: "bigint")

			column(name: "password", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "password_expired", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "phone", type: "varchar(20)")

			column(name: "username", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-15") {
		addColumn(tableName: "organization") {
			column(name: "billing_contact_id", type: "bigint")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-16") {
		addColumn(tableName: "organization") {
			column(name: "pi_id", type: "bigint")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-17") {
		addPrimaryKey(columnNames: "cell_source_id, cell_source_treatment_id", tableName: "cell_source_cell_source_treatments")
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-18") {
		addPrimaryKey(columnNames: "strain_id, genetic_modification_id", tableName: "strain_genetic_modifications")
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-40") {
		createIndex(indexName: "FK_318alqytpqfjhixa0xumta27", tableName: "cell_source") {
			column(name: "provider_lab_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-41") {
		createIndex(indexName: "FK_3j4empi718wbs6weggsvx0vxf", tableName: "cell_source") {
			column(name: "sex_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-42") {
		createIndex(indexName: "FK_7i3q0e2js5j13ybv0fpn6wsdj", tableName: "cell_source") {
			column(name: "strain_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-43") {
		createIndex(indexName: "FK_abm2plajs5iecdn4xw32iiq98", tableName: "cell_source") {
			column(name: "histology_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-44") {
		createIndex(indexName: "FK_fvxkssmfdikktj0688ybb3tbh", tableName: "cell_source") {
			column(name: "tissue_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-45") {
		createIndex(indexName: "FK_js2v7lu898c0p8ortg5xgxb0q", tableName: "cell_source") {
			column(name: "provider_user_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-46") {
		createIndex(indexName: "FK_1ni2dp2onaacc5jhsifyiohop", tableName: "cell_source_cell_source_treatments") {
			column(name: "cell_source_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-47") {
		createIndex(indexName: "FK_ip0egm28ey9b78jnqyoke624h", tableName: "cell_source_cell_source_treatments") {
			column(name: "cell_source_treatment_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-48") {
		createIndex(indexName: "name_uniq_1451503070989", tableName: "cell_source_treatment", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-49") {
		createIndex(indexName: "name_uniq_1451503070992", tableName: "genetic_modification", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-50") {
		createIndex(indexName: "FK_ag1m8bhk8on25x3thx4edhu34", tableName: "genotype") {
			column(name: "species_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-51") {
		createIndex(indexName: "name_uniq_1451503070994", tableName: "genotype", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-52") {
		createIndex(indexName: "FK_jrq8s9u4ujdq8durk95ilfebo", tableName: "histology") {
			column(name: "parent_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-53") {
		createIndex(indexName: "name_uniq_1451503070997", tableName: "histology", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-54") {
		createIndex(indexName: "FK_6w4r8gx2jdvy193esigbpxxx6", tableName: "organization") {
			column(name: "billing_contact_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-55") {
		createIndex(indexName: "FK_shncwjk67uss09ivcrpvlv8xg", tableName: "organization") {
			column(name: "pi_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-56") {
		createIndex(indexName: "name_uniq_1451503071000", tableName: "project", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-57") {
		createIndex(indexName: "FK_d6kfrxuqknbxrlxhwmn66a3kg", tableName: "project_user") {
			column(name: "user_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-58") {
		createIndex(indexName: "FK_ptwhmsh2vocln8sffhyvr2ohm", tableName: "project_user") {
			column(name: "project_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-59") {
		createIndex(indexName: "authority_uniq_1451503071002", tableName: "role", unique: "true") {
			column(name: "authority")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-60") {
		createIndex(indexName: "name_uniq_1451503071002", tableName: "sex", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-61") {
		createIndex(indexName: "FK_4shdjcf8qcywolsnrmi3djhmu", tableName: "strain") {
			column(name: "parent_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-62") {
		createIndex(indexName: "FK_bd07vlra9sn9vs8po73rw89wg", tableName: "strain") {
			column(name: "source_lab_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-63") {
		createIndex(indexName: "FK_ob705eavs6i60ngs0imotqc5n", tableName: "strain") {
			column(name: "genotype_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-64") {
		createIndex(indexName: "name_uniq_1451503071005", tableName: "strain", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-65") {
		createIndex(indexName: "FK_bybku3lrkjee3jdtq8sixm2if", tableName: "strain_genetic_modifications") {
			column(name: "genetic_modification_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-66") {
		createIndex(indexName: "FK_tfjl1k0206qdhf4x28xx5fxal", tableName: "strain_genetic_modifications") {
			column(name: "strain_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-67") {
		createIndex(indexName: "name_uniq_1451503071008", tableName: "tissue", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-68") {
		createIndex(indexName: "FK_5dvidjwskvh1r6ev4f5m5n0j7", tableName: "user") {
			column(name: "lab_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-69") {
		createIndex(indexName: "FK_dhlcfg8h1drrgu0irs1ro3ohb", tableName: "user") {
			column(name: "address_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-70") {
		createIndex(indexName: "username_uniq_1451503071010", tableName: "user", unique: "true") {
			column(name: "username")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-19") {
		addForeignKeyConstraint(baseColumnNames: "histology_id", baseTableName: "cell_source", constraintName: "FK_abm2plajs5iecdn4xw32iiq98", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "histology", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-20") {
		addForeignKeyConstraint(baseColumnNames: "provider_lab_id", baseTableName: "cell_source", constraintName: "FK_318alqytpqfjhixa0xumta27", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "organization", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-21") {
		addForeignKeyConstraint(baseColumnNames: "provider_user_id", baseTableName: "cell_source", constraintName: "FK_js2v7lu898c0p8ortg5xgxb0q", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-22") {
		addForeignKeyConstraint(baseColumnNames: "sex_id", baseTableName: "cell_source", constraintName: "FK_3j4empi718wbs6weggsvx0vxf", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sex", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-23") {
		addForeignKeyConstraint(baseColumnNames: "strain_id", baseTableName: "cell_source", constraintName: "FK_7i3q0e2js5j13ybv0fpn6wsdj", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "strain", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-24") {
		addForeignKeyConstraint(baseColumnNames: "tissue_id", baseTableName: "cell_source", constraintName: "FK_fvxkssmfdikktj0688ybb3tbh", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "tissue", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-25") {
		addForeignKeyConstraint(baseColumnNames: "cell_source_id", baseTableName: "cell_source_cell_source_treatments", constraintName: "FK_1ni2dp2onaacc5jhsifyiohop", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cell_source", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-26") {
		addForeignKeyConstraint(baseColumnNames: "cell_source_treatment_id", baseTableName: "cell_source_cell_source_treatments", constraintName: "FK_ip0egm28ey9b78jnqyoke624h", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cell_source_treatment", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-27") {
		addForeignKeyConstraint(baseColumnNames: "species_id", baseTableName: "genotype", constraintName: "FK_ag1m8bhk8on25x3thx4edhu34", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "species", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-28") {
		addForeignKeyConstraint(baseColumnNames: "parent_id", baseTableName: "histology", constraintName: "FK_jrq8s9u4ujdq8durk95ilfebo", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "histology", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-29") {
		addForeignKeyConstraint(baseColumnNames: "billing_contact_id", baseTableName: "organization", constraintName: "FK_6w4r8gx2jdvy193esigbpxxx6", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-30") {
		addForeignKeyConstraint(baseColumnNames: "pi_id", baseTableName: "organization", constraintName: "FK_shncwjk67uss09ivcrpvlv8xg", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-31") {
		addForeignKeyConstraint(baseColumnNames: "project_id", baseTableName: "project_user", constraintName: "FK_ptwhmsh2vocln8sffhyvr2ohm", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "project", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-32") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "project_user", constraintName: "FK_d6kfrxuqknbxrlxhwmn66a3kg", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-33") {
		addForeignKeyConstraint(baseColumnNames: "genotype_id", baseTableName: "strain", constraintName: "FK_ob705eavs6i60ngs0imotqc5n", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "genotype", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-34") {
		addForeignKeyConstraint(baseColumnNames: "parent_id", baseTableName: "strain", constraintName: "FK_4shdjcf8qcywolsnrmi3djhmu", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "strain", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-35") {
		addForeignKeyConstraint(baseColumnNames: "source_lab_id", baseTableName: "strain", constraintName: "FK_bd07vlra9sn9vs8po73rw89wg", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "organization", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-36") {
		addForeignKeyConstraint(baseColumnNames: "genetic_modification_id", baseTableName: "strain_genetic_modifications", constraintName: "FK_bybku3lrkjee3jdtq8sixm2if", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "genetic_modification", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-37") {
		addForeignKeyConstraint(baseColumnNames: "strain_id", baseTableName: "strain_genetic_modifications", constraintName: "FK_tfjl1k0206qdhf4x28xx5fxal", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "strain", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-38") {
		addForeignKeyConstraint(baseColumnNames: "address_id", baseTableName: "user", constraintName: "FK_dhlcfg8h1drrgu0irs1ro3ohb", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "address", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451503071193-39") {
		addForeignKeyConstraint(baseColumnNames: "lab_id", baseTableName: "user", constraintName: "FK_5dvidjwskvh1r6ev4f5m5n0j7", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "organization", referencesUniqueColumn: "false")
	}
}
