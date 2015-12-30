databaseChangeLog = {

	changeSet(author: "danyingshao (generated)", id: "1451508121298-1") {
		createTable(tableName: "ab_host") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "ab_hostPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(30)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-2") {
		createTable(tableName: "address") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "addressPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "city", type: "varchar(30)") {
				constraints(nullable: "false")
			}

			column(name: "country", type: "varchar(20)") {
				constraints(nullable: "false")
			}

			column(name: "line1", type: "varchar(100)") {
				constraints(nullable: "false")
			}

			column(name: "line2", type: "varchar(255)")

			column(name: "postal_code", type: "varchar(10)") {
				constraints(nullable: "false")
			}

			column(name: "state", type: "varchar(20)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-3") {
		createTable(tableName: "align_type") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "align_typePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(30)") {
				constraints(nullable: "false")
			}

			column(name: "short_name", type: "varchar(20)")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-4") {
		createTable(tableName: "aligner") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "alignerPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "aligner_version", type: "varchar(10)")

			column(name: "software", type: "varchar(30)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-5") {
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

	changeSet(author: "danyingshao (generated)", id: "1451508121298-6") {
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

	changeSet(author: "danyingshao (generated)", id: "1451508121298-7") {
		createTable(tableName: "biological_replicate_set") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "biological_rePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-8") {
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

	changeSet(author: "danyingshao (generated)", id: "1451508121298-9") {
		createTable(tableName: "cell_source_cell_source_treatments") {
			column(name: "cell_source_treatment_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "cell_source_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-10") {
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

	changeSet(author: "danyingshao (generated)", id: "1451508121298-11") {
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

	changeSet(author: "danyingshao (generated)", id: "1451508121298-12") {
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

	changeSet(author: "danyingshao (generated)", id: "1451508121298-13") {
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

	changeSet(author: "danyingshao (generated)", id: "1451508121298-14") {
		createTable(tableName: "core_pipeline") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "core_pipelinePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "base_calling_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "core_pipeline_version", type: "varchar(10)")

			column(name: "data_processing_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "downstream_analysis_id", type: "bigint")

			column(name: "name", type: "varchar(30)") {
				constraints(nullable: "false")
			}

			column(name: "note", type: "varchar(255)")

			column(name: "peak_finding_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-15") {
		createTable(tableName: "data_processing") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "data_processiPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(30)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-16") {
		createTable(tableName: "definition") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "definitionPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "content", type: "varchar(5000)") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-17") {
		createTable(tableName: "downstream_analysis") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "downstream_anPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(30)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-18") {
		createTable(tableName: "file_metadata") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "file_metadataPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "file_type_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "insertion_size", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "md5check_sum", type: "varchar(50)") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(30)") {
				constraints(nullable: "false")
			}

			column(name: "note", type: "varchar(255)")

			column(name: "sequence_alignment_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "standard_deviation", type: "float") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-19") {
		createTable(tableName: "file_type") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "file_typePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "varchar(200)") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(30)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-20") {
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

	changeSet(author: "danyingshao (generated)", id: "1451508121298-21") {
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

	changeSet(author: "danyingshao (generated)", id: "1451508121298-22") {
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

	changeSet(author: "danyingshao (generated)", id: "1451508121298-23") {
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

	changeSet(author: "danyingshao (generated)", id: "1451508121298-24") {
		createTable(tableName: "history") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "historyPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "action", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "log", type: "varchar(255)")

			column(name: "object_id", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "object_type", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "project_id", type: "bigint")

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-25") {
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

	changeSet(author: "danyingshao (generated)", id: "1451508121298-26") {
		createTable(tableName: "item") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "itemPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "barcode", type: "varchar(255)")

			column(name: "image_path", type: "varchar(255)")

			column(name: "location", type: "varchar(255)")

			column(name: "notes", type: "varchar(255)")

			column(name: "protocol_instance_id", type: "bigint")

			column(name: "reference_id", type: "bigint")

			column(name: "type_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-27") {
		createTable(tableName: "item_type") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "item_typePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "fields", type: "varchar(255)")

			column(name: "name", type: "varchar(30)") {
				constraints(nullable: "false")
			}

			column(name: "object_type", type: "varchar(30)")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-28") {
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

			column(name: "billing_contact_id", type: "bigint")

			column(name: "pi_id", type: "bigint")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-29") {
		createTable(tableName: "peak_finding") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "peak_findingPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(30)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-30") {
		createTable(tableName: "pool") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "poolPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-31") {
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

	changeSet(author: "danyingshao (generated)", id: "1451508121298-32") {
		createTable(tableName: "project_samples") {
			column(name: "sample_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "project_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-33") {
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

	changeSet(author: "danyingshao (generated)", id: "1451508121298-34") {
		createTable(tableName: "protocol") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "protocolPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "varchar(255)")

			column(name: "details", type: "text")

			column(name: "name", type: "varchar(30)") {
				constraints(nullable: "false")
			}

			column(name: "protocol_version", type: "varchar(10)")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-35") {
		createTable(tableName: "protocol_group") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "protocol_grouPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(30)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-36") {
		createTable(tableName: "protocol_group_protocols") {
			column(name: "protocol_group_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "protocol_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "protocols_idx", type: "integer")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-37") {
		createTable(tableName: "protocol_instance") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "protocol_instPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "note", type: "varchar(255)")

			column(name: "prior_id", type: "bigint")

			column(name: "protocol_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-38") {
		createTable(tableName: "read_type") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "read_typePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(20)") {
				constraints(nullable: "false")
			}

			column(name: "note", type: "varchar(255)")

			column(name: "short_name", type: "varchar(10)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-39") {
		createTable(tableName: "requestmap") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "requestmapPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "config_attribute", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "http_method", type: "varchar(255)")

			column(name: "url", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-40") {
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

	changeSet(author: "danyingshao (generated)", id: "1451508121298-41") {
		createTable(tableName: "sample") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "samplePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "antibody_id", type: "bigint")

			column(name: "biological_replicate_set_id", type: "bigint")

			column(name: "cell_number", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "cell_source_id", type: "bigint")

			column(name: "chromosome_amount", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "concentration", type: "float") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "note", type: "varchar(255)")

			column(name: "protocol_group_id", type: "bigint")

			column(name: "publication_reference", type: "varchar(30)")

			column(name: "quantity_received", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "requested_tag_number", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "spike_in_cell_source_id", type: "bigint")

			column(name: "status_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "target_id", type: "bigint")

			column(name: "technical_replicate_set_id", type: "bigint")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-42") {
		createTable(tableName: "sample_in_pool") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "sample_in_pooPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "concentration", type: "float") {
				constraints(nullable: "false")
			}

			column(name: "pool_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "pool_dilution", type: "float") {
				constraints(nullable: "false")
			}

			column(name: "qubit_dilution_factor", type: "float") {
				constraints(nullable: "false")
			}

			column(name: "qubit_reading", type: "float") {
				constraints(nullable: "false")
			}

			column(name: "sample_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "volume_to_pool", type: "float") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-43") {
		createTable(tableName: "sample_sequence_indices") {
			column(name: "sequence_index_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "sample_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-44") {
		createTable(tableName: "sample_status") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "sample_statusPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(20)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-45") {
		createTable(tableName: "sequence_alignment") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "sequence_aligPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "align_type_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "aligner_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "alignment_params", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "bam_file_path", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "core_pipeline_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "genome_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "idx_file_path", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "is_preferred_version", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "number_pairs", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "number_tags1", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "number_tags2", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "read_db_id", type: "integer")

			column(name: "sequencing_experiment_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "total_pair_weight", type: "float") {
				constraints(nullable: "false")
			}

			column(name: "total_type1weight", type: "float") {
				constraints(nullable: "false")
			}

			column(name: "total_type2weight", type: "float") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-46") {
		createTable(tableName: "sequence_index") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "sequence_indePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "index_id", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "index_version", type: "varchar(10)") {
				constraints(nullable: "false")
			}

			column(name: "oligo", type: "varchar(255)")

			column(name: "sequence", type: "varchar(30)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-47") {
		createTable(tableName: "sequence_run") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "sequence_runPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "directory_name", type: "varchar(255)")

			column(name: "fc_id", type: "varchar(255)")

			column(defaultValue: "12", name: "index_cycle", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "lane", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "note", type: "varchar(255)")

			column(name: "platform_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "pool_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(defaultValue: "40", name: "read1cycle", type: "integer") {
				constraints(nullable: "false")
			}

			column(defaultValue: "40", name: "read2cycle", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "run_num", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-48") {
		createTable(tableName: "sequencing_experiment") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "sequencing_exPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "fastqcreport_paths", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "fastq_file_paths", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "note", type: "varchar(255)")

			column(name: "number_reads", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "public_db_id", type: "varchar(255)")

			column(name: "read_type_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "sample_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "seq_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "sequence_run_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-49") {
		createTable(tableName: "sequencing_platform") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "sequencing_plPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(30)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-50") {
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

	changeSet(author: "danyingshao (generated)", id: "1451508121298-51") {
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

	changeSet(author: "danyingshao (generated)", id: "1451508121298-52") {
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

	changeSet(author: "danyingshao (generated)", id: "1451508121298-53") {
		createTable(tableName: "strain_genetic_modifications") {
			column(name: "strain_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "genetic_modification_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-54") {
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

	changeSet(author: "danyingshao (generated)", id: "1451508121298-55") {
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

	changeSet(author: "danyingshao (generated)", id: "1451508121298-56") {
		createTable(tableName: "task") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "taskPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "computing_infrastructure_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "core_pipe_line_id", type: "bigint")

			column(name: "end_time", type: "datetime")

			column(name: "input_file_paths", type: "varchar(255)")

			column(name: "job_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "output_file_paths", type: "varchar(255)")

			column(name: "script_file_path", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "start_time", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "status", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-57") {
		createTable(tableName: "technical_replicate_set") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "technical_repPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-58") {
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

	changeSet(author: "danyingshao (generated)", id: "1451508121298-59") {
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

	changeSet(author: "danyingshao (generated)", id: "1451508121298-60") {
		createTable(tableName: "user_role") {
			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "role_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-61") {
		addPrimaryKey(columnNames: "cell_source_id, cell_source_treatment_id", tableName: "cell_source_cell_source_treatments")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-62") {
		addPrimaryKey(columnNames: "project_id, sample_id", tableName: "project_samples")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-63") {
		addPrimaryKey(columnNames: "sample_id, sequence_index_id", tableName: "sample_sequence_indices")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-64") {
		addPrimaryKey(columnNames: "strain_id, genetic_modification_id", tableName: "strain_genetic_modifications")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-65") {
		addPrimaryKey(columnNames: "user_id, role_id", constraintName: "user_rolePK", tableName: "user_role")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-143") {
		createIndex(indexName: "name_uniq_1451508121151", tableName: "ab_host", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-144") {
		createIndex(indexName: "name_uniq_1451508121156", tableName: "align_type", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-145") {
		createIndex(indexName: "short_name_uniq_1451508121156", tableName: "align_type", unique: "true") {
			column(name: "short_name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-146") {
		createIndex(indexName: "unique_software", tableName: "aligner", unique: "true") {
			column(name: "aligner_version")

			column(name: "software")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-147") {
		createIndex(indexName: "FK_1n4i1c14220wwmiyv15uddx1r", tableName: "antibody") {
			column(name: "target_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-148") {
		createIndex(indexName: "FK_5bpi6ah0csjh4g0n8ln93wx3h", tableName: "antibody") {
			column(name: "ig_type_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-149") {
		createIndex(indexName: "FK_qjuxd91ddd77fufu1em86n2gb", tableName: "antibody") {
			column(name: "ab_host_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-150") {
		createIndex(indexName: "FK_rhc520eoim7txw9d7kqpb90fw", tableName: "antibody") {
			column(name: "company_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-151") {
		createIndex(indexName: "name_uniq_1451508121170", tableName: "antibody", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-152") {
		createIndex(indexName: "unique_name", tableName: "base_calling", unique: "true") {
			column(name: "base_calling_version")

			column(name: "name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-153") {
		createIndex(indexName: "FK_318alqytpqfjhixa0xumta27", tableName: "cell_source") {
			column(name: "provider_lab_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-154") {
		createIndex(indexName: "FK_3j4empi718wbs6weggsvx0vxf", tableName: "cell_source") {
			column(name: "sex_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-155") {
		createIndex(indexName: "FK_7i3q0e2js5j13ybv0fpn6wsdj", tableName: "cell_source") {
			column(name: "strain_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-156") {
		createIndex(indexName: "FK_abm2plajs5iecdn4xw32iiq98", tableName: "cell_source") {
			column(name: "histology_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-157") {
		createIndex(indexName: "FK_fvxkssmfdikktj0688ybb3tbh", tableName: "cell_source") {
			column(name: "tissue_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-158") {
		createIndex(indexName: "FK_js2v7lu898c0p8ortg5xgxb0q", tableName: "cell_source") {
			column(name: "provider_user_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-159") {
		createIndex(indexName: "FK_1ni2dp2onaacc5jhsifyiohop", tableName: "cell_source_cell_source_treatments") {
			column(name: "cell_source_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-160") {
		createIndex(indexName: "FK_ip0egm28ey9b78jnqyoke624h", tableName: "cell_source_cell_source_treatments") {
			column(name: "cell_source_treatment_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-161") {
		createIndex(indexName: "name_uniq_1451508121180", tableName: "cell_source_treatment", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-162") {
		createIndex(indexName: "FK_lese17egs9gkqmyudn2wh5il2", tableName: "chrom_sequence") {
			column(name: "chromosome_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-163") {
		createIndex(indexName: "FK_qtx5yloqyq7hc1efournhys5u", tableName: "chromosome") {
			column(name: "genome_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-164") {
		createIndex(indexName: "name_uniq_1451508121183", tableName: "chromosome", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-165") {
		createIndex(indexName: "name_uniq_1451508121184", tableName: "computing_infrastructure", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-166") {
		createIndex(indexName: "FK_ceyrkr3xuabv7koe9ro018awy", tableName: "core_pipeline") {
			column(name: "downstream_analysis_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-167") {
		createIndex(indexName: "FK_mpgn6hl6vvbrcq3dd3tp130lx", tableName: "core_pipeline") {
			column(name: "data_processing_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-168") {
		createIndex(indexName: "FK_qhv857uuhkmcorb4pio2vhpy3", tableName: "core_pipeline") {
			column(name: "base_calling_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-169") {
		createIndex(indexName: "FK_rm79jw9v0buyydkoy0nv05o52", tableName: "core_pipeline") {
			column(name: "peak_finding_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-170") {
		createIndex(indexName: "unique_name", tableName: "core_pipeline", unique: "true") {
			column(name: "core_pipeline_version")

			column(name: "name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-171") {
		createIndex(indexName: "name_uniq_1451508121187", tableName: "data_processing", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-172") {
		createIndex(indexName: "name_uniq_1451508121189", tableName: "definition", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-173") {
		createIndex(indexName: "name_uniq_1451508121190", tableName: "downstream_analysis", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-174") {
		createIndex(indexName: "FK_8ei1cybnbixggmxfhwril3vac", tableName: "file_metadata") {
			column(name: "file_type_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-175") {
		createIndex(indexName: "FK_p0dwk23v4mpuph7bvybfimjn9", tableName: "file_metadata") {
			column(name: "sequence_alignment_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-176") {
		createIndex(indexName: "unique_name", tableName: "file_metadata", unique: "true") {
			column(name: "file_type_id")

			column(name: "name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-177") {
		createIndex(indexName: "name_uniq_1451508121193", tableName: "file_type", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-178") {
		createIndex(indexName: "name_uniq_1451508121193", tableName: "genetic_modification", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-179") {
		createIndex(indexName: "FK_8qyh94lw1ucpnn89d4dl4e5dr", tableName: "genome") {
			column(name: "species_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-180") {
		createIndex(indexName: "FK_ag1m8bhk8on25x3thx4edhu34", tableName: "genotype") {
			column(name: "species_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-181") {
		createIndex(indexName: "name_uniq_1451508121196", tableName: "genotype", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-182") {
		createIndex(indexName: "FK_jrq8s9u4ujdq8durk95ilfebo", tableName: "histology") {
			column(name: "parent_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-183") {
		createIndex(indexName: "name_uniq_1451508121197", tableName: "histology", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-184") {
		createIndex(indexName: "FK_fej8h317q2acuy144kav1oyxc", tableName: "history") {
			column(name: "project_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-185") {
		createIndex(indexName: "FK_fuutexvtx28fs971iq0kbfbmp", tableName: "history") {
			column(name: "user_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-186") {
		createIndex(indexName: "FK_qxnbu16tlqfmub9pgfj3h2e41", tableName: "item") {
			column(name: "type_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-187") {
		createIndex(indexName: "FK_rv13l1tnj5m3x3r61t7yq97ue", tableName: "item") {
			column(name: "protocol_instance_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-188") {
		createIndex(indexName: "unique_barcode", tableName: "item", unique: "true") {
			column(name: "type_id")

			column(name: "barcode")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-189") {
		createIndex(indexName: "name_uniq_1451508121201", tableName: "item_type", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-190") {
		createIndex(indexName: "FK_57byxcy430qbl2gl7liup0py1", tableName: "organization") {
			column(name: "parent_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-191") {
		createIndex(indexName: "FK_6w4r8gx2jdvy193esigbpxxx6", tableName: "organization") {
			column(name: "billing_contact_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-192") {
		createIndex(indexName: "FK_l070gahmlj4g2sqbm72btw64e", tableName: "organization") {
			column(name: "address_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-193") {
		createIndex(indexName: "FK_shncwjk67uss09ivcrpvlv8xg", tableName: "organization") {
			column(name: "pi_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-194") {
		createIndex(indexName: "name_uniq_1451508121202", tableName: "organization", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-195") {
		createIndex(indexName: "name_uniq_1451508121203", tableName: "peak_finding", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-196") {
		createIndex(indexName: "FK_m6wrsc7nbw6p4ulsp0cg65jhi", tableName: "pool") {
			column(name: "user_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-197") {
		createIndex(indexName: "name_uniq_1451508121204", tableName: "project", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-198") {
		createIndex(indexName: "FK_bcuquxjs68mn5vyq7d0yphn8p", tableName: "project_samples") {
			column(name: "project_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-199") {
		createIndex(indexName: "FK_qfu2n2hwoukibpvvx51eftp9y", tableName: "project_samples") {
			column(name: "sample_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-200") {
		createIndex(indexName: "FK_d6kfrxuqknbxrlxhwmn66a3kg", tableName: "project_user") {
			column(name: "user_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-201") {
		createIndex(indexName: "FK_ptwhmsh2vocln8sffhyvr2ohm", tableName: "project_user") {
			column(name: "project_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-202") {
		createIndex(indexName: "unique_name", tableName: "protocol", unique: "true") {
			column(name: "protocol_version")

			column(name: "name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-203") {
		createIndex(indexName: "name_uniq_1451508121207", tableName: "protocol_group", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-204") {
		createIndex(indexName: "FK_6i86p4hqvh803hqeam63pf0fo", tableName: "protocol_group_protocols") {
			column(name: "protocol_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-205") {
		createIndex(indexName: "FK_6sjm1imt3f4b8rd492wm9vt7e", tableName: "protocol_group_protocols") {
			column(name: "protocol_group_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-206") {
		createIndex(indexName: "FK_d4v22o39k3xu8m216w8r1nyx1", tableName: "protocol_instance") {
			column(name: "protocol_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-207") {
		createIndex(indexName: "FK_p9g9nnnknkaftxrkf1gktn8x6", tableName: "protocol_instance") {
			column(name: "prior_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-208") {
		createIndex(indexName: "FK_sgj3vhj59cxyb10md7ybwmtm7", tableName: "protocol_instance") {
			column(name: "user_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-209") {
		createIndex(indexName: "name_uniq_1451508121208", tableName: "read_type", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-210") {
		createIndex(indexName: "short_name_uniq_1451508121209", tableName: "read_type", unique: "true") {
			column(name: "short_name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-211") {
		createIndex(indexName: "unique_url", tableName: "requestmap", unique: "true") {
			column(name: "http_method")

			column(name: "url")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-212") {
		createIndex(indexName: "authority_uniq_1451508121210", tableName: "role", unique: "true") {
			column(name: "authority")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-213") {
		createIndex(indexName: "FK_1d2270u07cw6ytkajov7d2wqs", tableName: "sample") {
			column(name: "status_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-214") {
		createIndex(indexName: "FK_4lb993olybjsa6bqlbt90as3c", tableName: "sample") {
			column(name: "antibody_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-215") {
		createIndex(indexName: "FK_a1ro7uobsoyc87a51ssw7n5sw", tableName: "sample") {
			column(name: "cell_source_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-216") {
		createIndex(indexName: "FK_cfxg067kqgsk55rjibh2pbnso", tableName: "sample") {
			column(name: "target_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-217") {
		createIndex(indexName: "FK_hd6wcvumdm4eysvsdnro5hd2f", tableName: "sample") {
			column(name: "technical_replicate_set_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-218") {
		createIndex(indexName: "FK_iab90bxgihv46lqg7ib51kmwn", tableName: "sample") {
			column(name: "spike_in_cell_source_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-219") {
		createIndex(indexName: "FK_libuiv9iymg5okp79f1maxt3", tableName: "sample") {
			column(name: "protocol_group_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-220") {
		createIndex(indexName: "FK_lkd1fyshpt94t6hik30q046a3", tableName: "sample") {
			column(name: "biological_replicate_set_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-221") {
		createIndex(indexName: "FK_41xrmt2o01xwuxfs7lg976piw", tableName: "sample_in_pool") {
			column(name: "sample_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-222") {
		createIndex(indexName: "FK_96fu40wr3u20fqapuj2gdcm6m", tableName: "sample_in_pool") {
			column(name: "pool_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-223") {
		createIndex(indexName: "FK_qh0akbm1a3893iwxbwqps9xmn", tableName: "sample_sequence_indices") {
			column(name: "sample_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-224") {
		createIndex(indexName: "FK_ruriwyvbrja8cfrayq2ux8c1u", tableName: "sample_sequence_indices") {
			column(name: "sequence_index_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-225") {
		createIndex(indexName: "name_uniq_1451508121214", tableName: "sample_status", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-226") {
		createIndex(indexName: "FK_5tdpednm424bwlyaygxb91cj8", tableName: "sequence_alignment") {
			column(name: "genome_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-227") {
		createIndex(indexName: "FK_8gcis40hwme6s7ywn4jikodd2", tableName: "sequence_alignment") {
			column(name: "align_type_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-228") {
		createIndex(indexName: "FK_fee47iwmejna7h55jp8dpkowd", tableName: "sequence_alignment") {
			column(name: "aligner_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-229") {
		createIndex(indexName: "FK_hc4f4br571qwgavrswy22w5wo", tableName: "sequence_alignment") {
			column(name: "core_pipeline_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-230") {
		createIndex(indexName: "FK_js0whth80fyna834brwbbo8v0", tableName: "sequence_alignment") {
			column(name: "sequencing_experiment_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-231") {
		createIndex(indexName: "unique_index_id", tableName: "sequence_index", unique: "true") {
			column(name: "index_version")

			column(name: "index_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-232") {
		createIndex(indexName: "FK_gerfjuiu4n7m0iukeud2wrux7", tableName: "sequence_run") {
			column(name: "user_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-233") {
		createIndex(indexName: "FK_jrr4ojwkpl4jryj9ippkg55ed", tableName: "sequence_run") {
			column(name: "pool_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-234") {
		createIndex(indexName: "FK_l1ofj1csp7k439e52l32soay9", tableName: "sequence_run") {
			column(name: "platform_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-235") {
		createIndex(indexName: "FK_3mx03u80x4ynhsx1i65yrrxcq", tableName: "sequencing_experiment") {
			column(name: "sequence_run_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-236") {
		createIndex(indexName: "FK_3s9e144d345uyhay28toy8jwg", tableName: "sequencing_experiment") {
			column(name: "read_type_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-237") {
		createIndex(indexName: "FK_7gsumwrx4h3nji2g2q5htn098", tableName: "sequencing_experiment") {
			column(name: "sample_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-238") {
		createIndex(indexName: "name_uniq_1451508121221", tableName: "sequencing_platform", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-239") {
		createIndex(indexName: "name_uniq_1451508121221", tableName: "sex", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-240") {
		createIndex(indexName: "name_uniq_1451508121221", tableName: "species", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-241") {
		createIndex(indexName: "FK_4shdjcf8qcywolsnrmi3djhmu", tableName: "strain") {
			column(name: "parent_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-242") {
		createIndex(indexName: "FK_bd07vlra9sn9vs8po73rw89wg", tableName: "strain") {
			column(name: "source_lab_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-243") {
		createIndex(indexName: "FK_ob705eavs6i60ngs0imotqc5n", tableName: "strain") {
			column(name: "genotype_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-244") {
		createIndex(indexName: "name_uniq_1451508121222", tableName: "strain", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-245") {
		createIndex(indexName: "FK_bybku3lrkjee3jdtq8sixm2if", tableName: "strain_genetic_modifications") {
			column(name: "genetic_modification_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-246") {
		createIndex(indexName: "FK_tfjl1k0206qdhf4x28xx5fxal", tableName: "strain_genetic_modifications") {
			column(name: "strain_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-247") {
		createIndex(indexName: "FK_e5wo7j1f9so6mcll9vwtn6005", tableName: "target") {
			column(name: "target_type_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-248") {
		createIndex(indexName: "name_uniq_1451508121223", tableName: "target", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-249") {
		createIndex(indexName: "name_uniq_1451508121224", tableName: "target_type", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-250") {
		createIndex(indexName: "FK_2ovu0g1rssdjbpgb6fj0l2vwy", tableName: "task") {
			column(name: "computing_infrastructure_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-251") {
		createIndex(indexName: "FK_4fmjedju7b35tb5cr71n3ntb0", tableName: "task") {
			column(name: "user_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-252") {
		createIndex(indexName: "FK_89y9w7ykrtfvr9quf0acepu0k", tableName: "task") {
			column(name: "core_pipe_line_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-253") {
		createIndex(indexName: "name_uniq_1451508121226", tableName: "tissue", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-254") {
		createIndex(indexName: "FK_5dvidjwskvh1r6ev4f5m5n0j7", tableName: "user") {
			column(name: "lab_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-255") {
		createIndex(indexName: "FK_dhlcfg8h1drrgu0irs1ro3ohb", tableName: "user") {
			column(name: "address_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-256") {
		createIndex(indexName: "username_uniq_1451508121227", tableName: "user", unique: "true") {
			column(name: "username")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-257") {
		createIndex(indexName: "FK_apcc8lxk2xnug8377fatvbn04", tableName: "user_role") {
			column(name: "user_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-258") {
		createIndex(indexName: "FK_it77eq964jhfqtu54081ebtio", tableName: "user_role") {
			column(name: "role_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-66") {
		addForeignKeyConstraint(baseColumnNames: "ab_host_id", baseTableName: "antibody", constraintName: "FK_qjuxd91ddd77fufu1em86n2gb", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "ab_host", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-67") {
		addForeignKeyConstraint(baseColumnNames: "company_id", baseTableName: "antibody", constraintName: "FK_rhc520eoim7txw9d7kqpb90fw", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "organization", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-68") {
		addForeignKeyConstraint(baseColumnNames: "ig_type_id", baseTableName: "antibody", constraintName: "FK_5bpi6ah0csjh4g0n8ln93wx3h", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "ig_type", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-69") {
		addForeignKeyConstraint(baseColumnNames: "target_id", baseTableName: "antibody", constraintName: "FK_1n4i1c14220wwmiyv15uddx1r", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "target", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-70") {
		addForeignKeyConstraint(baseColumnNames: "histology_id", baseTableName: "cell_source", constraintName: "FK_abm2plajs5iecdn4xw32iiq98", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "histology", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-71") {
		addForeignKeyConstraint(baseColumnNames: "provider_lab_id", baseTableName: "cell_source", constraintName: "FK_318alqytpqfjhixa0xumta27", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "organization", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-72") {
		addForeignKeyConstraint(baseColumnNames: "provider_user_id", baseTableName: "cell_source", constraintName: "FK_js2v7lu898c0p8ortg5xgxb0q", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-73") {
		addForeignKeyConstraint(baseColumnNames: "sex_id", baseTableName: "cell_source", constraintName: "FK_3j4empi718wbs6weggsvx0vxf", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sex", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-74") {
		addForeignKeyConstraint(baseColumnNames: "strain_id", baseTableName: "cell_source", constraintName: "FK_7i3q0e2js5j13ybv0fpn6wsdj", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "strain", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-75") {
		addForeignKeyConstraint(baseColumnNames: "tissue_id", baseTableName: "cell_source", constraintName: "FK_fvxkssmfdikktj0688ybb3tbh", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "tissue", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-76") {
		addForeignKeyConstraint(baseColumnNames: "cell_source_id", baseTableName: "cell_source_cell_source_treatments", constraintName: "FK_1ni2dp2onaacc5jhsifyiohop", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cell_source", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-77") {
		addForeignKeyConstraint(baseColumnNames: "cell_source_treatment_id", baseTableName: "cell_source_cell_source_treatments", constraintName: "FK_ip0egm28ey9b78jnqyoke624h", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cell_source_treatment", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-78") {
		addForeignKeyConstraint(baseColumnNames: "chromosome_id", baseTableName: "chrom_sequence", constraintName: "FK_lese17egs9gkqmyudn2wh5il2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "chromosome", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-79") {
		addForeignKeyConstraint(baseColumnNames: "genome_id", baseTableName: "chromosome", constraintName: "FK_qtx5yloqyq7hc1efournhys5u", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "genome", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-80") {
		addForeignKeyConstraint(baseColumnNames: "base_calling_id", baseTableName: "core_pipeline", constraintName: "FK_qhv857uuhkmcorb4pio2vhpy3", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "base_calling", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-81") {
		addForeignKeyConstraint(baseColumnNames: "data_processing_id", baseTableName: "core_pipeline", constraintName: "FK_mpgn6hl6vvbrcq3dd3tp130lx", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "data_processing", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-82") {
		addForeignKeyConstraint(baseColumnNames: "downstream_analysis_id", baseTableName: "core_pipeline", constraintName: "FK_ceyrkr3xuabv7koe9ro018awy", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "downstream_analysis", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-83") {
		addForeignKeyConstraint(baseColumnNames: "peak_finding_id", baseTableName: "core_pipeline", constraintName: "FK_rm79jw9v0buyydkoy0nv05o52", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "peak_finding", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-84") {
		addForeignKeyConstraint(baseColumnNames: "file_type_id", baseTableName: "file_metadata", constraintName: "FK_8ei1cybnbixggmxfhwril3vac", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "file_type", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-85") {
		addForeignKeyConstraint(baseColumnNames: "sequence_alignment_id", baseTableName: "file_metadata", constraintName: "FK_p0dwk23v4mpuph7bvybfimjn9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sequence_alignment", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-86") {
		addForeignKeyConstraint(baseColumnNames: "species_id", baseTableName: "genome", constraintName: "FK_8qyh94lw1ucpnn89d4dl4e5dr", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "species", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-87") {
		addForeignKeyConstraint(baseColumnNames: "species_id", baseTableName: "genotype", constraintName: "FK_ag1m8bhk8on25x3thx4edhu34", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "species", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-88") {
		addForeignKeyConstraint(baseColumnNames: "parent_id", baseTableName: "histology", constraintName: "FK_jrq8s9u4ujdq8durk95ilfebo", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "histology", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-89") {
		addForeignKeyConstraint(baseColumnNames: "project_id", baseTableName: "history", constraintName: "FK_fej8h317q2acuy144kav1oyxc", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "project", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-90") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "history", constraintName: "FK_fuutexvtx28fs971iq0kbfbmp", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-91") {
		addForeignKeyConstraint(baseColumnNames: "protocol_instance_id", baseTableName: "item", constraintName: "FK_rv13l1tnj5m3x3r61t7yq97ue", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "protocol_instance", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-92") {
		addForeignKeyConstraint(baseColumnNames: "type_id", baseTableName: "item", constraintName: "FK_qxnbu16tlqfmub9pgfj3h2e41", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "item_type", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-93") {
		addForeignKeyConstraint(baseColumnNames: "address_id", baseTableName: "organization", constraintName: "FK_l070gahmlj4g2sqbm72btw64e", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "address", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-94") {
		addForeignKeyConstraint(baseColumnNames: "billing_contact_id", baseTableName: "organization", constraintName: "FK_6w4r8gx2jdvy193esigbpxxx6", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-95") {
		addForeignKeyConstraint(baseColumnNames: "parent_id", baseTableName: "organization", constraintName: "FK_57byxcy430qbl2gl7liup0py1", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "organization", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-96") {
		addForeignKeyConstraint(baseColumnNames: "pi_id", baseTableName: "organization", constraintName: "FK_shncwjk67uss09ivcrpvlv8xg", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-97") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "pool", constraintName: "FK_m6wrsc7nbw6p4ulsp0cg65jhi", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-98") {
		addForeignKeyConstraint(baseColumnNames: "project_id", baseTableName: "project_samples", constraintName: "FK_bcuquxjs68mn5vyq7d0yphn8p", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "project", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-99") {
		addForeignKeyConstraint(baseColumnNames: "sample_id", baseTableName: "project_samples", constraintName: "FK_qfu2n2hwoukibpvvx51eftp9y", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sample", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-100") {
		addForeignKeyConstraint(baseColumnNames: "project_id", baseTableName: "project_user", constraintName: "FK_ptwhmsh2vocln8sffhyvr2ohm", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "project", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-101") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "project_user", constraintName: "FK_d6kfrxuqknbxrlxhwmn66a3kg", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-102") {
		addForeignKeyConstraint(baseColumnNames: "protocol_group_id", baseTableName: "protocol_group_protocols", constraintName: "FK_6sjm1imt3f4b8rd492wm9vt7e", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "protocol_group", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-103") {
		addForeignKeyConstraint(baseColumnNames: "protocol_id", baseTableName: "protocol_group_protocols", constraintName: "FK_6i86p4hqvh803hqeam63pf0fo", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "protocol", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-104") {
		addForeignKeyConstraint(baseColumnNames: "prior_id", baseTableName: "protocol_instance", constraintName: "FK_p9g9nnnknkaftxrkf1gktn8x6", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "protocol_instance", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-105") {
		addForeignKeyConstraint(baseColumnNames: "protocol_id", baseTableName: "protocol_instance", constraintName: "FK_d4v22o39k3xu8m216w8r1nyx1", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "protocol", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-106") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "protocol_instance", constraintName: "FK_sgj3vhj59cxyb10md7ybwmtm7", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-107") {
		addForeignKeyConstraint(baseColumnNames: "antibody_id", baseTableName: "sample", constraintName: "FK_4lb993olybjsa6bqlbt90as3c", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "antibody", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-108") {
		addForeignKeyConstraint(baseColumnNames: "biological_replicate_set_id", baseTableName: "sample", constraintName: "FK_lkd1fyshpt94t6hik30q046a3", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "biological_replicate_set", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-109") {
		addForeignKeyConstraint(baseColumnNames: "cell_source_id", baseTableName: "sample", constraintName: "FK_a1ro7uobsoyc87a51ssw7n5sw", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cell_source", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-110") {
		addForeignKeyConstraint(baseColumnNames: "protocol_group_id", baseTableName: "sample", constraintName: "FK_libuiv9iymg5okp79f1maxt3", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "protocol_group", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-111") {
		addForeignKeyConstraint(baseColumnNames: "spike_in_cell_source_id", baseTableName: "sample", constraintName: "FK_iab90bxgihv46lqg7ib51kmwn", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cell_source", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-112") {
		addForeignKeyConstraint(baseColumnNames: "status_id", baseTableName: "sample", constraintName: "FK_1d2270u07cw6ytkajov7d2wqs", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sample_status", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-113") {
		addForeignKeyConstraint(baseColumnNames: "target_id", baseTableName: "sample", constraintName: "FK_cfxg067kqgsk55rjibh2pbnso", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "target", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-114") {
		addForeignKeyConstraint(baseColumnNames: "technical_replicate_set_id", baseTableName: "sample", constraintName: "FK_hd6wcvumdm4eysvsdnro5hd2f", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "technical_replicate_set", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-115") {
		addForeignKeyConstraint(baseColumnNames: "pool_id", baseTableName: "sample_in_pool", constraintName: "FK_96fu40wr3u20fqapuj2gdcm6m", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "pool", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-116") {
		addForeignKeyConstraint(baseColumnNames: "sample_id", baseTableName: "sample_in_pool", constraintName: "FK_41xrmt2o01xwuxfs7lg976piw", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sample", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-117") {
		addForeignKeyConstraint(baseColumnNames: "sample_id", baseTableName: "sample_sequence_indices", constraintName: "FK_qh0akbm1a3893iwxbwqps9xmn", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sample", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-118") {
		addForeignKeyConstraint(baseColumnNames: "sequence_index_id", baseTableName: "sample_sequence_indices", constraintName: "FK_ruriwyvbrja8cfrayq2ux8c1u", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sequence_index", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-119") {
		addForeignKeyConstraint(baseColumnNames: "align_type_id", baseTableName: "sequence_alignment", constraintName: "FK_8gcis40hwme6s7ywn4jikodd2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "align_type", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-120") {
		addForeignKeyConstraint(baseColumnNames: "aligner_id", baseTableName: "sequence_alignment", constraintName: "FK_fee47iwmejna7h55jp8dpkowd", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "aligner", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-121") {
		addForeignKeyConstraint(baseColumnNames: "core_pipeline_id", baseTableName: "sequence_alignment", constraintName: "FK_hc4f4br571qwgavrswy22w5wo", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "core_pipeline", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-122") {
		addForeignKeyConstraint(baseColumnNames: "genome_id", baseTableName: "sequence_alignment", constraintName: "FK_5tdpednm424bwlyaygxb91cj8", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "genome", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-123") {
		addForeignKeyConstraint(baseColumnNames: "sequencing_experiment_id", baseTableName: "sequence_alignment", constraintName: "FK_js0whth80fyna834brwbbo8v0", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sequencing_experiment", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-124") {
		addForeignKeyConstraint(baseColumnNames: "platform_id", baseTableName: "sequence_run", constraintName: "FK_l1ofj1csp7k439e52l32soay9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sequencing_platform", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-125") {
		addForeignKeyConstraint(baseColumnNames: "pool_id", baseTableName: "sequence_run", constraintName: "FK_jrr4ojwkpl4jryj9ippkg55ed", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "pool", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-126") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "sequence_run", constraintName: "FK_gerfjuiu4n7m0iukeud2wrux7", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-127") {
		addForeignKeyConstraint(baseColumnNames: "read_type_id", baseTableName: "sequencing_experiment", constraintName: "FK_3s9e144d345uyhay28toy8jwg", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "read_type", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-128") {
		addForeignKeyConstraint(baseColumnNames: "sample_id", baseTableName: "sequencing_experiment", constraintName: "FK_7gsumwrx4h3nji2g2q5htn098", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sample", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-129") {
		addForeignKeyConstraint(baseColumnNames: "sequence_run_id", baseTableName: "sequencing_experiment", constraintName: "FK_3mx03u80x4ynhsx1i65yrrxcq", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sequence_run", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-130") {
		addForeignKeyConstraint(baseColumnNames: "genotype_id", baseTableName: "strain", constraintName: "FK_ob705eavs6i60ngs0imotqc5n", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "genotype", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-131") {
		addForeignKeyConstraint(baseColumnNames: "parent_id", baseTableName: "strain", constraintName: "FK_4shdjcf8qcywolsnrmi3djhmu", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "strain", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-132") {
		addForeignKeyConstraint(baseColumnNames: "source_lab_id", baseTableName: "strain", constraintName: "FK_bd07vlra9sn9vs8po73rw89wg", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "organization", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-133") {
		addForeignKeyConstraint(baseColumnNames: "genetic_modification_id", baseTableName: "strain_genetic_modifications", constraintName: "FK_bybku3lrkjee3jdtq8sixm2if", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "genetic_modification", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-134") {
		addForeignKeyConstraint(baseColumnNames: "strain_id", baseTableName: "strain_genetic_modifications", constraintName: "FK_tfjl1k0206qdhf4x28xx5fxal", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "strain", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-135") {
		addForeignKeyConstraint(baseColumnNames: "target_type_id", baseTableName: "target", constraintName: "FK_e5wo7j1f9so6mcll9vwtn6005", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "target_type", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-136") {
		addForeignKeyConstraint(baseColumnNames: "computing_infrastructure_id", baseTableName: "task", constraintName: "FK_2ovu0g1rssdjbpgb6fj0l2vwy", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "computing_infrastructure", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-137") {
		addForeignKeyConstraint(baseColumnNames: "core_pipe_line_id", baseTableName: "task", constraintName: "FK_89y9w7ykrtfvr9quf0acepu0k", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "core_pipeline", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-138") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "task", constraintName: "FK_4fmjedju7b35tb5cr71n3ntb0", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-139") {
		addForeignKeyConstraint(baseColumnNames: "address_id", baseTableName: "user", constraintName: "FK_dhlcfg8h1drrgu0irs1ro3ohb", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "address", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-140") {
		addForeignKeyConstraint(baseColumnNames: "lab_id", baseTableName: "user", constraintName: "FK_5dvidjwskvh1r6ev4f5m5n0j7", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "organization", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-141") {
		addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "user_role", constraintName: "FK_it77eq964jhfqtu54081ebtio", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "role", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1451508121298-142") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_role", constraintName: "FK_apcc8lxk2xnug8377fatvbn04", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

}
