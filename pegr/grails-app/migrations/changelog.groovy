databaseChangeLog = {

	changeSet(author: "dus73 (generated)", id: "1456345094061-1") {
		createTable(tableName: "ab_host") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "ab_hostPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "status", type: "varchar(255)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-2") {
		createTable(tableName: "address") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "addressPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "city", type: "varchar(50)") {
				constraints(nullable: "false")
			}

			column(name: "country", type: "varchar(50)") {
				constraints(nullable: "false")
			}

			column(name: "line1", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "line2", type: "varchar(255)")

			column(name: "postal_code", type: "varchar(10)") {
				constraints(nullable: "false")
			}

			column(name: "state", type: "varchar(50)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-3") {
		createTable(tableName: "align_type") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "align_typePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "short_name", type: "varchar(20)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-4") {
		createTable(tableName: "aligner") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "alignerPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "aligner_version", type: "varchar(10)")

			column(name: "software", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-5") {
		createTable(tableName: "antibody") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "antibodyPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "ab_host_id", type: "bigint")

			column(name: "catalog_number", type: "varchar(255)")

			column(name: "clonal", type: "varchar(255)")

			column(name: "company_id", type: "bigint")

			column(name: "concentration", type: "float")

			column(name: "external_id", type: "varchar(255)")

			column(name: "ig_type_id", type: "bigint")

			column(name: "immunogene", type: "varchar(255)")

			column(name: "inventory_id", type: "varchar(255)")

			column(name: "item_id", type: "bigint")

			column(name: "lot_number", type: "varchar(255)")

			column(name: "note", type: "varchar(255)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-6") {
		createTable(tableName: "assay") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "assayPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-7") {
		createTable(tableName: "base_calling") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "base_callingPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "base_calling_version", type: "varchar(10)")

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-8") {
		createTable(tableName: "biological_replicate_samples") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "biological_rePK")
			}

			column(name: "sample_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "set_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-9") {
		createTable(tableName: "biological_replicate_set") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "biological_rePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "project_id", type: "bigint")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-10") {
		createTable(tableName: "cell_source") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "cell_sourcePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "age", type: "varchar(255)")

			column(name: "biological_source_id", type: "varchar(255)")

			column(name: "growth_media_id", type: "bigint")

			column(name: "histology_id", type: "bigint")

			column(name: "inventory_id", type: "bigint")

			column(name: "item_id", type: "bigint")

			column(name: "note", type: "varchar(255)")

			column(name: "prep_user_id", type: "bigint")

			column(name: "provider_lab_id", type: "bigint")

			column(name: "provider_user_id", type: "bigint")

			column(name: "sex_id", type: "bigint")

			column(name: "strain_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "tissue_id", type: "bigint")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-11") {
		createTable(tableName: "cell_source_treatment") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "cell_source_tPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "compound", type: "varchar(255)")

			column(name: "duration", type: "varchar(255)")

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "quantity", type: "varchar(255)")

			column(name: "status", type: "varchar(255)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-12") {
		createTable(tableName: "cell_source_treatments") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "cell_source_tPK")
			}

			column(name: "cell_source_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "treatment_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-13") {
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

	changeSet(author: "dus73 (generated)", id: "1456345094061-14") {
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

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "note", type: "varchar(255)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-15") {
		createTable(tableName: "computing_infrastructure") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "computing_infPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-16") {
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

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "note", type: "varchar(255)")

			column(name: "peak_finding_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-17") {
		createTable(tableName: "data_processing") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "data_processiPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-18") {
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

	changeSet(author: "dus73 (generated)", id: "1456345094061-19") {
		createTable(tableName: "downstream_analysis") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "downstream_anPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-20") {
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

			column(name: "md5check_sum", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
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

	changeSet(author: "dus73 (generated)", id: "1456345094061-21") {
		createTable(tableName: "file_type") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "file_typePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(30)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-22") {
		createTable(tableName: "genome") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "genomePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "species_id", type: "bigint")

			column(name: "status", type: "varchar(255)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-23") {
		createTable(tableName: "growth_media") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "growth_mediaPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "species_id", type: "bigint")

			column(name: "status", type: "varchar(255)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-24") {
		createTable(tableName: "histology") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "histologyPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "parent_id", type: "bigint")

			column(name: "status", type: "varchar(255)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-25") {
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

	changeSet(author: "dus73 (generated)", id: "1456345094061-26") {
		createTable(tableName: "ig_type") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "ig_typePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "status", type: "varchar(255)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-27") {
		createTable(tableName: "inventory") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "inventoryPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "date_received", type: "datetime")

			column(name: "notes", type: "varchar(255)")

			column(name: "receiving_user_id", type: "bigint")

			column(name: "source_type", type: "varchar(255)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-28") {
		createTable(tableName: "invoice") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "invoicePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "date", type: "datetime")

			column(name: "invoice_num", type: "varchar(255)")

			column(name: "service_id", type: "varchar(255)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-29") {
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

			column(name: "name", type: "varchar(255)")

			column(name: "notes", type: "varchar(255)")

			column(name: "parent_id", type: "bigint")

			column(name: "type_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-30") {
		createTable(tableName: "item_type") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "item_typePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "category", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "fields", type: "varchar(255)")

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-31") {
		createTable(tableName: "organization") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "organizationPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "address_id", type: "bigint")

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "note", type: "varchar(255)")

			column(name: "parent_id", type: "bigint")

			column(name: "status", type: "varchar(255)")

			column(name: "website", type: "varchar(255)")

			column(name: "class", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "billing_contact_id", type: "bigint")

			column(name: "pi_id", type: "bigint")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-32") {
		createTable(tableName: "peak_finding") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "peak_findingPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-33") {
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

			column(name: "funding", type: "varchar(255)")

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-34") {
		createTable(tableName: "project_samples") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "project_samplPK")
			}

			column(name: "project_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "sample_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-35") {
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

	changeSet(author: "dus73 (generated)", id: "1456345094061-36") {
		createTable(tableName: "protocol") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "protocolPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "assay_id", type: "bigint")

			column(name: "description", type: "varchar(255)")

			column(name: "details", type: "text")

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "protocol_version", type: "varchar(10)")

			column(name: "user_id", type: "bigint")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-37") {
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

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-38") {
		createTable(tableName: "protocol_group_protocols") {
			column(name: "protocol_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "protocol_group_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "protocols_idx", type: "integer")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-39") {
		createTable(tableName: "protocol_instance") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "protocol_instPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "bag_id", type: "bigint")

			column(name: "bag_idx", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "end_time", type: "datetime")

			column(name: "note", type: "varchar(255)")

			column(name: "protocol_id", type: "bigint")

			column(name: "start_time", type: "datetime")

			column(name: "status", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-40") {
		createTable(tableName: "protocol_instance_bag") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "protocol_instPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "end_time", type: "datetime")

			column(name: "name", type: "varchar(255)")

			column(name: "protocol_group_id", type: "bigint")

			column(name: "start_time", type: "datetime")

			column(name: "status", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-41") {
		createTable(tableName: "protocol_instance_items") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "protocol_instPK")
			}

			column(name: "item_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "protocol_instance_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-42") {
		createTable(tableName: "protocol_instance_summary") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "protocol_instPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "end_time", type: "datetime")

			column(name: "note", type: "varchar(255)")

			column(name: "protocol_id", type: "bigint")

			column(name: "start_time", type: "datetime")

			column(name: "user_id", type: "bigint")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-43") {
		createTable(tableName: "protocol_item_types") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "protocol_itemPK")
			}

			column(name: "function", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "item_type_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "protocol_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-44") {
		createTable(tableName: "read_type") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "read_typePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "note", type: "varchar(255)")

			column(name: "short_name", type: "varchar(10)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-45") {
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

	changeSet(author: "dus73 (generated)", id: "1456345094061-46") {
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

	changeSet(author: "dus73 (generated)", id: "1456345094061-47") {
		createTable(tableName: "sample") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "samplePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "antibody_id", type: "bigint")

			column(name: "cell_number", type: "integer")

			column(name: "cell_source_id", type: "bigint")

			column(name: "chromosome_amount", type: "integer")

			column(name: "date", type: "datetime")

			column(name: "invoice_id", type: "bigint")

			column(name: "item_id", type: "bigint")

			column(name: "note", type: "text")

			column(name: "prtcl_inst_summary_id", type: "bigint")

			column(name: "publication_reference", type: "varchar(255)")

			column(name: "requested_tag_number", type: "integer")

			column(name: "send_data_to_id", type: "bigint")

			column(name: "spike_in_cell_source_id", type: "bigint")

			column(name: "status", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "target_id", type: "bigint")

			column(name: "volume", type: "integer")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-48") {
		createTable(tableName: "sample_bags") {
			column(name: "sample_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "protocol_instance_bag_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "bags_idx", type: "integer")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-49") {
		createTable(tableName: "sample_in_run") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "sample_in_runPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "params", type: "varchar(255)")

			column(name: "pool", type: "varchar(255)")

			column(name: "pool_date", type: "datetime")

			column(name: "run_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "sample_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "volume_to_pool", type: "float")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-50") {
		createTable(tableName: "sample_sequence_indices") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "sample_sequenPK")
			}

			column(name: "index_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "sample_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-51") {
		createTable(tableName: "sequence_alignment") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "sequence_aligPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "align_type_id", type: "bigint")

			column(name: "aligner_id", type: "bigint")

			column(name: "core_pipeline_id", type: "bigint")

			column(name: "date", type: "datetime")

			column(name: "file_paths", type: "varchar(1000)")

			column(name: "genome_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "is_preferred", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "params", type: "varchar(2000)")

			column(name: "read_db_id", type: "integer")

			column(name: "sequencing_experiment_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-52") {
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

			column(name: "sequence", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "status", type: "varchar(255)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-53") {
		createTable(tableName: "sequence_run") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "sequence_runPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "date", type: "datetime")

			column(name: "directory_name", type: "varchar(255)")

			column(name: "fc_id", type: "varchar(255)")

			column(name: "lane", type: "integer")

			column(name: "note", type: "varchar(255)")

			column(name: "platform_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "run_num", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "status", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-54") {
		createTable(tableName: "sequencing_experiment") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "sequencing_exPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "file_paths", type: "varchar(500)")

			column(name: "note", type: "varchar(255)")

			column(name: "number_reads", type: "integer")

			column(name: "public_db_id", type: "varchar(255)")

			column(name: "read_positions", type: "varchar(255)")

			column(name: "read_type_id", type: "bigint")

			column(name: "sample_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "seq_id", type: "varchar(255)")

			column(name: "sequence_run_id", type: "bigint")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-55") {
		createTable(tableName: "sequencing_platform") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "sequencing_plPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-56") {
		createTable(tableName: "sex") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "sexPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "status", type: "varchar(255)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-57") {
		createTable(tableName: "species") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "speciesPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "genus_name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "note", type: "varchar(255)")

			column(name: "status", type: "varchar(255)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-58") {
		createTable(tableName: "strain") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "strainPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "genetic_modification", type: "varchar(255)")

			column(name: "genotype", type: "varchar(255)")

			column(name: "name", type: "varchar(255)")

			column(name: "note", type: "varchar(255)")

			column(name: "parent_id", type: "bigint")

			column(name: "source_lab_id", type: "bigint")

			column(name: "species_id", type: "bigint")

			column(name: "status", type: "varchar(255)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-59") {
		createTable(tableName: "target") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "targetPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "c_term_tag", type: "varchar(255)")

			column(name: "n_term_tag", type: "varchar(255)")

			column(name: "name", type: "varchar(255)")

			column(name: "note", type: "varchar(255)")

			column(name: "status", type: "varchar(255)")

			column(name: "target_type_id", type: "bigint")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-60") {
		createTable(tableName: "target_type") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "target_typePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "varchar(255)")

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-61") {
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

	changeSet(author: "dus73 (generated)", id: "1456345094061-62") {
		createTable(tableName: "technical_replicate_samples") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "technical_repPK")
			}

			column(name: "sample_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "set_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-63") {
		createTable(tableName: "technical_replicate_set") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "technical_repPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "project_id", type: "bigint")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-64") {
		createTable(tableName: "tissue") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "tissuePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "status", type: "varchar(255)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-65") {
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

			column(name: "affiliation_id", type: "bigint")

			column(name: "email", type: "varchar(255)")

			column(name: "enabled", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "full_name", type: "varchar(255)")

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

	changeSet(author: "dus73 (generated)", id: "1456345094061-66") {
		createTable(tableName: "user_role") {
			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "role_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-67") {
		addPrimaryKey(columnNames: "user_id, role_id", constraintName: "user_rolePK", tableName: "user_role")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-164") {
		createIndex(indexName: "name_uniq_1456345093922", tableName: "ab_host", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-165") {
		createIndex(indexName: "name_uniq_1456345093927", tableName: "align_type", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-166") {
		createIndex(indexName: "short_name_uniq_1456345093929", tableName: "align_type", unique: "true") {
			column(name: "short_name")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-167") {
		createIndex(indexName: "unique_software", tableName: "aligner", unique: "true") {
			column(name: "aligner_version")

			column(name: "software")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-168") {
		createIndex(indexName: "FK_54ep9wcwdrl5kkuq231173s2f", tableName: "antibody") {
			column(name: "item_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-169") {
		createIndex(indexName: "FK_5bpi6ah0csjh4g0n8ln93wx3h", tableName: "antibody") {
			column(name: "ig_type_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-170") {
		createIndex(indexName: "FK_qjuxd91ddd77fufu1em86n2gb", tableName: "antibody") {
			column(name: "ab_host_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-171") {
		createIndex(indexName: "FK_rhc520eoim7txw9d7kqpb90fw", tableName: "antibody") {
			column(name: "company_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-172") {
		createIndex(indexName: "name_uniq_1456345093940", tableName: "assay", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-173") {
		createIndex(indexName: "unique_name", tableName: "base_calling", unique: "true") {
			column(name: "base_calling_version")

			column(name: "name")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-174") {
		createIndex(indexName: "FK_5cfsjvfvqbjtcx7t6ftka65pu", tableName: "biological_replicate_samples") {
			column(name: "set_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-175") {
		createIndex(indexName: "FK_hlaud14xiftoss51nv2k9mp9n", tableName: "biological_replicate_samples") {
			column(name: "sample_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-176") {
		createIndex(indexName: "unique_set_id", tableName: "biological_replicate_samples", unique: "true") {
			column(name: "sample_id")

			column(name: "set_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-177") {
		createIndex(indexName: "FK_giwv376p7duup0ii9vm5a41vt", tableName: "biological_replicate_set") {
			column(name: "project_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-178") {
		createIndex(indexName: "FK_318alqytpqfjhixa0xumta27", tableName: "cell_source") {
			column(name: "provider_lab_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-179") {
		createIndex(indexName: "FK_3j4empi718wbs6weggsvx0vxf", tableName: "cell_source") {
			column(name: "sex_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-180") {
		createIndex(indexName: "FK_71fd478uakc4ikgvf7jkqrbic", tableName: "cell_source") {
			column(name: "inventory_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-181") {
		createIndex(indexName: "FK_7i3q0e2js5j13ybv0fpn6wsdj", tableName: "cell_source") {
			column(name: "strain_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-182") {
		createIndex(indexName: "FK_abm2plajs5iecdn4xw32iiq98", tableName: "cell_source") {
			column(name: "histology_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-183") {
		createIndex(indexName: "FK_dyakrl2jrduv34rm8abrpdpea", tableName: "cell_source") {
			column(name: "growth_media_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-184") {
		createIndex(indexName: "FK_fvxkssmfdikktj0688ybb3tbh", tableName: "cell_source") {
			column(name: "tissue_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-185") {
		createIndex(indexName: "FK_js2v7lu898c0p8ortg5xgxb0q", tableName: "cell_source") {
			column(name: "provider_user_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-186") {
		createIndex(indexName: "FK_mrpb1l0o1f7urfo78pf1dn2sl", tableName: "cell_source") {
			column(name: "item_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-187") {
		createIndex(indexName: "FK_osoanky7172r4r5skdfatpr2n", tableName: "cell_source") {
			column(name: "prep_user_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-188") {
		createIndex(indexName: "name_uniq_1456345093945", tableName: "cell_source_treatment", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-189") {
		createIndex(indexName: "FK_jggm0quygtml4729ct1v2li8q", tableName: "cell_source_treatments") {
			column(name: "treatment_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-190") {
		createIndex(indexName: "FK_xht7d6039ax9kbh4gmdceh5k", tableName: "cell_source_treatments") {
			column(name: "cell_source_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-191") {
		createIndex(indexName: "unique_cell_source_id", tableName: "cell_source_treatments", unique: "true") {
			column(name: "treatment_id")

			column(name: "cell_source_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-192") {
		createIndex(indexName: "FK_lese17egs9gkqmyudn2wh5il2", tableName: "chrom_sequence") {
			column(name: "chromosome_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-193") {
		createIndex(indexName: "FK_qtx5yloqyq7hc1efournhys5u", tableName: "chromosome") {
			column(name: "genome_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-194") {
		createIndex(indexName: "name_uniq_1456345093947", tableName: "chromosome", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-195") {
		createIndex(indexName: "name_uniq_1456345093948", tableName: "computing_infrastructure", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-196") {
		createIndex(indexName: "FK_ceyrkr3xuabv7koe9ro018awy", tableName: "core_pipeline") {
			column(name: "downstream_analysis_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-197") {
		createIndex(indexName: "FK_mpgn6hl6vvbrcq3dd3tp130lx", tableName: "core_pipeline") {
			column(name: "data_processing_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-198") {
		createIndex(indexName: "FK_qhv857uuhkmcorb4pio2vhpy3", tableName: "core_pipeline") {
			column(name: "base_calling_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-199") {
		createIndex(indexName: "FK_rm79jw9v0buyydkoy0nv05o52", tableName: "core_pipeline") {
			column(name: "peak_finding_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-200") {
		createIndex(indexName: "unique_name", tableName: "core_pipeline", unique: "true") {
			column(name: "core_pipeline_version")

			column(name: "name")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-201") {
		createIndex(indexName: "name_uniq_1456345093952", tableName: "data_processing", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-202") {
		createIndex(indexName: "name_uniq_1456345093953", tableName: "definition", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-203") {
		createIndex(indexName: "name_uniq_1456345093953", tableName: "downstream_analysis", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-204") {
		createIndex(indexName: "FK_8ei1cybnbixggmxfhwril3vac", tableName: "file_metadata") {
			column(name: "file_type_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-205") {
		createIndex(indexName: "FK_p0dwk23v4mpuph7bvybfimjn9", tableName: "file_metadata") {
			column(name: "sequence_alignment_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-206") {
		createIndex(indexName: "unique_name", tableName: "file_metadata", unique: "true") {
			column(name: "file_type_id")

			column(name: "name")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-207") {
		createIndex(indexName: "name_uniq_1456345093956", tableName: "file_type", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-208") {
		createIndex(indexName: "FK_8qyh94lw1ucpnn89d4dl4e5dr", tableName: "genome") {
			column(name: "species_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-209") {
		createIndex(indexName: "FK_d67vskwe5phnwub9cqiokalkx", tableName: "growth_media") {
			column(name: "species_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-210") {
		createIndex(indexName: "name_uniq_1456345093957", tableName: "growth_media", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-211") {
		createIndex(indexName: "FK_jrq8s9u4ujdq8durk95ilfebo", tableName: "histology") {
			column(name: "parent_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-212") {
		createIndex(indexName: "name_uniq_1456345093958", tableName: "histology", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-213") {
		createIndex(indexName: "FK_fej8h317q2acuy144kav1oyxc", tableName: "history") {
			column(name: "project_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-214") {
		createIndex(indexName: "FK_fuutexvtx28fs971iq0kbfbmp", tableName: "history") {
			column(name: "user_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-215") {
		createIndex(indexName: "name_uniq_1456345093960", tableName: "ig_type", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-216") {
		createIndex(indexName: "FK_mnah8ybq73nyl126do8agmk08", tableName: "inventory") {
			column(name: "receiving_user_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-217") {
		createIndex(indexName: "FK_ccldfsomwnlcfqys42su71de3", tableName: "item") {
			column(name: "parent_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-218") {
		createIndex(indexName: "FK_qxnbu16tlqfmub9pgfj3h2e41", tableName: "item") {
			column(name: "type_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-219") {
		createIndex(indexName: "unique_barcode", tableName: "item", unique: "true") {
			column(name: "type_id")

			column(name: "barcode")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-220") {
		createIndex(indexName: "name_uniq_1456345093964", tableName: "item_type", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-221") {
		createIndex(indexName: "FK_57byxcy430qbl2gl7liup0py1", tableName: "organization") {
			column(name: "parent_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-222") {
		createIndex(indexName: "FK_6w4r8gx2jdvy193esigbpxxx6", tableName: "organization") {
			column(name: "billing_contact_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-223") {
		createIndex(indexName: "FK_l070gahmlj4g2sqbm72btw64e", tableName: "organization") {
			column(name: "address_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-224") {
		createIndex(indexName: "FK_shncwjk67uss09ivcrpvlv8xg", tableName: "organization") {
			column(name: "pi_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-225") {
		createIndex(indexName: "name_uniq_1456345093964", tableName: "organization", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-226") {
		createIndex(indexName: "name_uniq_1456345093966", tableName: "peak_finding", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-227") {
		createIndex(indexName: "name_uniq_1456345093967", tableName: "project", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-228") {
		createIndex(indexName: "FK_bcuquxjs68mn5vyq7d0yphn8p", tableName: "project_samples") {
			column(name: "project_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-229") {
		createIndex(indexName: "FK_qfu2n2hwoukibpvvx51eftp9y", tableName: "project_samples") {
			column(name: "sample_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-230") {
		createIndex(indexName: "unique_project_id", tableName: "project_samples", unique: "true") {
			column(name: "sample_id")

			column(name: "project_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-231") {
		createIndex(indexName: "FK_d6kfrxuqknbxrlxhwmn66a3kg", tableName: "project_user") {
			column(name: "user_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-232") {
		createIndex(indexName: "FK_ptwhmsh2vocln8sffhyvr2ohm", tableName: "project_user") {
			column(name: "project_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-233") {
		createIndex(indexName: "unique_project_id", tableName: "project_user", unique: "true") {
			column(name: "user_id")

			column(name: "project_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-234") {
		createIndex(indexName: "FK_f2u9282jbe9eiy0fggmrvrt16", tableName: "protocol") {
			column(name: "assay_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-235") {
		createIndex(indexName: "FK_mpt4h4wrqjngorh2706rvsqk2", tableName: "protocol") {
			column(name: "user_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-236") {
		createIndex(indexName: "unique_name", tableName: "protocol", unique: "true") {
			column(name: "protocol_version")

			column(name: "name")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-237") {
		createIndex(indexName: "FK_mm7fvjt84dbnbsnsbe9ik36xh", tableName: "protocol_group") {
			column(name: "user_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-238") {
		createIndex(indexName: "name_uniq_1456345093970", tableName: "protocol_group", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-239") {
		createIndex(indexName: "FK_6i86p4hqvh803hqeam63pf0fo", tableName: "protocol_group_protocols") {
			column(name: "protocol_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-240") {
		createIndex(indexName: "FK_6sjm1imt3f4b8rd492wm9vt7e", tableName: "protocol_group_protocols") {
			column(name: "protocol_group_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-241") {
		createIndex(indexName: "FK_d4v22o39k3xu8m216w8r1nyx1", tableName: "protocol_instance") {
			column(name: "protocol_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-242") {
		createIndex(indexName: "FK_j952d9eb9yjue4b2xggu8ehib", tableName: "protocol_instance") {
			column(name: "bag_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-243") {
		createIndex(indexName: "FK_sgj3vhj59cxyb10md7ybwmtm7", tableName: "protocol_instance") {
			column(name: "user_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-244") {
		createIndex(indexName: "FK_r1uxlmgfs4nieck8kad4wo79q", tableName: "protocol_instance_bag") {
			column(name: "protocol_group_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-245") {
		createIndex(indexName: "FK_2ehrfoqmnyg7wc3sv0owshi8l", tableName: "protocol_instance_items") {
			column(name: "item_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-246") {
		createIndex(indexName: "FK_dmt3slumwfqinb5qicjdvrilr", tableName: "protocol_instance_items") {
			column(name: "protocol_instance_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-247") {
		createIndex(indexName: "unique_protocol_instance_id", tableName: "protocol_instance_items", unique: "true") {
			column(name: "item_id")

			column(name: "protocol_instance_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-248") {
		createIndex(indexName: "FK_5v0brjc04upo9ksqnq3bivxni", tableName: "protocol_instance_summary") {
			column(name: "protocol_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-249") {
		createIndex(indexName: "FK_8idyxguxexanjiixc15ylqewv", tableName: "protocol_instance_summary") {
			column(name: "user_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-250") {
		createIndex(indexName: "FK_mi8fvx3awmigchnxb1s8keinu", tableName: "protocol_item_types") {
			column(name: "item_type_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-251") {
		createIndex(indexName: "FK_p1p3edd8llir9a0qp24sj5cql", tableName: "protocol_item_types") {
			column(name: "protocol_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-252") {
		createIndex(indexName: "unique_protocol_id", tableName: "protocol_item_types", unique: "true") {
			column(name: "item_type_id")

			column(name: "protocol_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-253") {
		createIndex(indexName: "name_uniq_1456345093978", tableName: "read_type", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-254") {
		createIndex(indexName: "short_name_uniq_1456345093978", tableName: "read_type", unique: "true") {
			column(name: "short_name")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-255") {
		createIndex(indexName: "unique_url", tableName: "requestmap", unique: "true") {
			column(name: "http_method")

			column(name: "url")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-256") {
		createIndex(indexName: "authority_uniq_1456345093979", tableName: "role", unique: "true") {
			column(name: "authority")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-257") {
		createIndex(indexName: "FK_4lb993olybjsa6bqlbt90as3c", tableName: "sample") {
			column(name: "antibody_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-258") {
		createIndex(indexName: "FK_9l7trxp5onscu1yk6fcvenjq6", tableName: "sample") {
			column(name: "item_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-259") {
		createIndex(indexName: "FK_a1ro7uobsoyc87a51ssw7n5sw", tableName: "sample") {
			column(name: "cell_source_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-260") {
		createIndex(indexName: "FK_bcdouyjpgn9hqcd947o1f28jq", tableName: "sample") {
			column(name: "prtcl_inst_summary_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-261") {
		createIndex(indexName: "FK_cfxg067kqgsk55rjibh2pbnso", tableName: "sample") {
			column(name: "target_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-262") {
		createIndex(indexName: "FK_iab90bxgihv46lqg7ib51kmwn", tableName: "sample") {
			column(name: "spike_in_cell_source_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-263") {
		createIndex(indexName: "FK_psm0kim6hte7fy0577fwsbdi", tableName: "sample") {
			column(name: "send_data_to_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-264") {
		createIndex(indexName: "FK_tqw4ka9uvmywhb4gw0ib7nxy9", tableName: "sample") {
			column(name: "invoice_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-265") {
		createIndex(indexName: "FK_cnr1t3lrixla6a72js4vswgow", tableName: "sample_bags") {
			column(name: "protocol_instance_bag_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-266") {
		createIndex(indexName: "FK_m94py76hnab3k5yd83xstrenu", tableName: "sample_bags") {
			column(name: "sample_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-267") {
		createIndex(indexName: "FK_993bhxmuyjlul88wourwo5eyw", tableName: "sample_in_run") {
			column(name: "run_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-268") {
		createIndex(indexName: "FK_fsayjgiqhlt7cctq8n1escwvk", tableName: "sample_in_run") {
			column(name: "sample_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-269") {
		createIndex(indexName: "unique_sample_id", tableName: "sample_in_run", unique: "true") {
			column(name: "run_id")

			column(name: "sample_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-270") {
		createIndex(indexName: "FK_flne19kx2tprlhpbxh9a3xk8r", tableName: "sample_sequence_indices") {
			column(name: "index_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-271") {
		createIndex(indexName: "FK_qh0akbm1a3893iwxbwqps9xmn", tableName: "sample_sequence_indices") {
			column(name: "sample_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-272") {
		createIndex(indexName: "unique_sample_id", tableName: "sample_sequence_indices", unique: "true") {
			column(name: "index_id")

			column(name: "sample_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-273") {
		createIndex(indexName: "FK_5tdpednm424bwlyaygxb91cj8", tableName: "sequence_alignment") {
			column(name: "genome_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-274") {
		createIndex(indexName: "FK_8gcis40hwme6s7ywn4jikodd2", tableName: "sequence_alignment") {
			column(name: "align_type_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-275") {
		createIndex(indexName: "FK_fee47iwmejna7h55jp8dpkowd", tableName: "sequence_alignment") {
			column(name: "aligner_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-276") {
		createIndex(indexName: "FK_hc4f4br571qwgavrswy22w5wo", tableName: "sequence_alignment") {
			column(name: "core_pipeline_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-277") {
		createIndex(indexName: "FK_js0whth80fyna834brwbbo8v0", tableName: "sequence_alignment") {
			column(name: "sequencing_experiment_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-278") {
		createIndex(indexName: "FK_gerfjuiu4n7m0iukeud2wrux7", tableName: "sequence_run") {
			column(name: "user_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-279") {
		createIndex(indexName: "FK_l1ofj1csp7k439e52l32soay9", tableName: "sequence_run") {
			column(name: "platform_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-280") {
		createIndex(indexName: "FK_3mx03u80x4ynhsx1i65yrrxcq", tableName: "sequencing_experiment") {
			column(name: "sequence_run_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-281") {
		createIndex(indexName: "FK_3s9e144d345uyhay28toy8jwg", tableName: "sequencing_experiment") {
			column(name: "read_type_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-282") {
		createIndex(indexName: "FK_7gsumwrx4h3nji2g2q5htn098", tableName: "sequencing_experiment") {
			column(name: "sample_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-283") {
		createIndex(indexName: "name_uniq_1456345093990", tableName: "sequencing_platform", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-284") {
		createIndex(indexName: "name_uniq_1456345093990", tableName: "sex", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-285") {
		createIndex(indexName: "unique_name", tableName: "species", unique: "true") {
			column(name: "genus_name")

			column(name: "name")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-286") {
		createIndex(indexName: "FK_4shdjcf8qcywolsnrmi3djhmu", tableName: "strain") {
			column(name: "parent_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-287") {
		createIndex(indexName: "FK_bd07vlra9sn9vs8po73rw89wg", tableName: "strain") {
			column(name: "source_lab_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-288") {
		createIndex(indexName: "FK_c5gb45n5okxh7bqig1nouw76t", tableName: "strain") {
			column(name: "species_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-289") {
		createIndex(indexName: "FK_e5wo7j1f9so6mcll9vwtn6005", tableName: "target") {
			column(name: "target_type_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-290") {
		createIndex(indexName: "unique_name", tableName: "target", unique: "true") {
			column(name: "c_term_tag")

			column(name: "n_term_tag")

			column(name: "name")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-291") {
		createIndex(indexName: "name_uniq_1456345093994", tableName: "target_type", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-292") {
		createIndex(indexName: "FK_2ovu0g1rssdjbpgb6fj0l2vwy", tableName: "task") {
			column(name: "computing_infrastructure_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-293") {
		createIndex(indexName: "FK_4fmjedju7b35tb5cr71n3ntb0", tableName: "task") {
			column(name: "user_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-294") {
		createIndex(indexName: "FK_89y9w7ykrtfvr9quf0acepu0k", tableName: "task") {
			column(name: "core_pipe_line_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-295") {
		createIndex(indexName: "FK_bxc886ht20h9dyioee4mvtuhg", tableName: "technical_replicate_samples") {
			column(name: "set_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-296") {
		createIndex(indexName: "FK_pgfytkmfc7o8hujxpm27nrupx", tableName: "technical_replicate_samples") {
			column(name: "sample_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-297") {
		createIndex(indexName: "unique_set_id", tableName: "technical_replicate_samples", unique: "true") {
			column(name: "sample_id")

			column(name: "set_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-298") {
		createIndex(indexName: "FK_9qiur0k1ju7xinurh5sy074fr", tableName: "technical_replicate_set") {
			column(name: "project_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-299") {
		createIndex(indexName: "name_uniq_1456345093996", tableName: "tissue", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-300") {
		createIndex(indexName: "FK_dhlcfg8h1drrgu0irs1ro3ohb", tableName: "user") {
			column(name: "address_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-301") {
		createIndex(indexName: "FK_qdusuaq6oge31t7nlq10wm6ku", tableName: "user") {
			column(name: "affiliation_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-302") {
		createIndex(indexName: "username_uniq_1456345093997", tableName: "user", unique: "true") {
			column(name: "username")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-303") {
		createIndex(indexName: "FK_apcc8lxk2xnug8377fatvbn04", tableName: "user_role") {
			column(name: "user_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-304") {
		createIndex(indexName: "FK_it77eq964jhfqtu54081ebtio", tableName: "user_role") {
			column(name: "role_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-68") {
		addForeignKeyConstraint(baseColumnNames: "ab_host_id", baseTableName: "antibody", constraintName: "FK_qjuxd91ddd77fufu1em86n2gb", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "ab_host", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-69") {
		addForeignKeyConstraint(baseColumnNames: "company_id", baseTableName: "antibody", constraintName: "FK_rhc520eoim7txw9d7kqpb90fw", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "organization", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-70") {
		addForeignKeyConstraint(baseColumnNames: "ig_type_id", baseTableName: "antibody", constraintName: "FK_5bpi6ah0csjh4g0n8ln93wx3h", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "ig_type", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-71") {
		addForeignKeyConstraint(baseColumnNames: "item_id", baseTableName: "antibody", constraintName: "FK_54ep9wcwdrl5kkuq231173s2f", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "item", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-72") {
		addForeignKeyConstraint(baseColumnNames: "sample_id", baseTableName: "biological_replicate_samples", constraintName: "FK_hlaud14xiftoss51nv2k9mp9n", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sample", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-73") {
		addForeignKeyConstraint(baseColumnNames: "set_id", baseTableName: "biological_replicate_samples", constraintName: "FK_5cfsjvfvqbjtcx7t6ftka65pu", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "biological_replicate_set", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-74") {
		addForeignKeyConstraint(baseColumnNames: "project_id", baseTableName: "biological_replicate_set", constraintName: "FK_giwv376p7duup0ii9vm5a41vt", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "project", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-75") {
		addForeignKeyConstraint(baseColumnNames: "growth_media_id", baseTableName: "cell_source", constraintName: "FK_dyakrl2jrduv34rm8abrpdpea", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "growth_media", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-76") {
		addForeignKeyConstraint(baseColumnNames: "histology_id", baseTableName: "cell_source", constraintName: "FK_abm2plajs5iecdn4xw32iiq98", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "histology", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-77") {
		addForeignKeyConstraint(baseColumnNames: "inventory_id", baseTableName: "cell_source", constraintName: "FK_71fd478uakc4ikgvf7jkqrbic", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "inventory", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-78") {
		addForeignKeyConstraint(baseColumnNames: "item_id", baseTableName: "cell_source", constraintName: "FK_mrpb1l0o1f7urfo78pf1dn2sl", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "item", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-79") {
		addForeignKeyConstraint(baseColumnNames: "prep_user_id", baseTableName: "cell_source", constraintName: "FK_osoanky7172r4r5skdfatpr2n", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-80") {
		addForeignKeyConstraint(baseColumnNames: "provider_lab_id", baseTableName: "cell_source", constraintName: "FK_318alqytpqfjhixa0xumta27", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "organization", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-81") {
		addForeignKeyConstraint(baseColumnNames: "provider_user_id", baseTableName: "cell_source", constraintName: "FK_js2v7lu898c0p8ortg5xgxb0q", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-82") {
		addForeignKeyConstraint(baseColumnNames: "sex_id", baseTableName: "cell_source", constraintName: "FK_3j4empi718wbs6weggsvx0vxf", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sex", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-83") {
		addForeignKeyConstraint(baseColumnNames: "strain_id", baseTableName: "cell_source", constraintName: "FK_7i3q0e2js5j13ybv0fpn6wsdj", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "strain", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-84") {
		addForeignKeyConstraint(baseColumnNames: "tissue_id", baseTableName: "cell_source", constraintName: "FK_fvxkssmfdikktj0688ybb3tbh", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "tissue", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-85") {
		addForeignKeyConstraint(baseColumnNames: "cell_source_id", baseTableName: "cell_source_treatments", constraintName: "FK_xht7d6039ax9kbh4gmdceh5k", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cell_source", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-86") {
		addForeignKeyConstraint(baseColumnNames: "treatment_id", baseTableName: "cell_source_treatments", constraintName: "FK_jggm0quygtml4729ct1v2li8q", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cell_source_treatment", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-87") {
		addForeignKeyConstraint(baseColumnNames: "chromosome_id", baseTableName: "chrom_sequence", constraintName: "FK_lese17egs9gkqmyudn2wh5il2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "chromosome", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-88") {
		addForeignKeyConstraint(baseColumnNames: "genome_id", baseTableName: "chromosome", constraintName: "FK_qtx5yloqyq7hc1efournhys5u", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "genome", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-89") {
		addForeignKeyConstraint(baseColumnNames: "base_calling_id", baseTableName: "core_pipeline", constraintName: "FK_qhv857uuhkmcorb4pio2vhpy3", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "base_calling", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-90") {
		addForeignKeyConstraint(baseColumnNames: "data_processing_id", baseTableName: "core_pipeline", constraintName: "FK_mpgn6hl6vvbrcq3dd3tp130lx", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "data_processing", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-91") {
		addForeignKeyConstraint(baseColumnNames: "downstream_analysis_id", baseTableName: "core_pipeline", constraintName: "FK_ceyrkr3xuabv7koe9ro018awy", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "downstream_analysis", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-92") {
		addForeignKeyConstraint(baseColumnNames: "peak_finding_id", baseTableName: "core_pipeline", constraintName: "FK_rm79jw9v0buyydkoy0nv05o52", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "peak_finding", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-93") {
		addForeignKeyConstraint(baseColumnNames: "file_type_id", baseTableName: "file_metadata", constraintName: "FK_8ei1cybnbixggmxfhwril3vac", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "file_type", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-94") {
		addForeignKeyConstraint(baseColumnNames: "sequence_alignment_id", baseTableName: "file_metadata", constraintName: "FK_p0dwk23v4mpuph7bvybfimjn9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sequence_alignment", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-95") {
		addForeignKeyConstraint(baseColumnNames: "species_id", baseTableName: "genome", constraintName: "FK_8qyh94lw1ucpnn89d4dl4e5dr", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "species", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-96") {
		addForeignKeyConstraint(baseColumnNames: "species_id", baseTableName: "growth_media", constraintName: "FK_d67vskwe5phnwub9cqiokalkx", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "species", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-97") {
		addForeignKeyConstraint(baseColumnNames: "parent_id", baseTableName: "histology", constraintName: "FK_jrq8s9u4ujdq8durk95ilfebo", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "histology", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-98") {
		addForeignKeyConstraint(baseColumnNames: "project_id", baseTableName: "history", constraintName: "FK_fej8h317q2acuy144kav1oyxc", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "project", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-99") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "history", constraintName: "FK_fuutexvtx28fs971iq0kbfbmp", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-100") {
		addForeignKeyConstraint(baseColumnNames: "receiving_user_id", baseTableName: "inventory", constraintName: "FK_mnah8ybq73nyl126do8agmk08", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-101") {
		addForeignKeyConstraint(baseColumnNames: "parent_id", baseTableName: "item", constraintName: "FK_ccldfsomwnlcfqys42su71de3", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "item", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-102") {
		addForeignKeyConstraint(baseColumnNames: "type_id", baseTableName: "item", constraintName: "FK_qxnbu16tlqfmub9pgfj3h2e41", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "item_type", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-103") {
		addForeignKeyConstraint(baseColumnNames: "address_id", baseTableName: "organization", constraintName: "FK_l070gahmlj4g2sqbm72btw64e", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "address", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-104") {
		addForeignKeyConstraint(baseColumnNames: "billing_contact_id", baseTableName: "organization", constraintName: "FK_6w4r8gx2jdvy193esigbpxxx6", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-105") {
		addForeignKeyConstraint(baseColumnNames: "parent_id", baseTableName: "organization", constraintName: "FK_57byxcy430qbl2gl7liup0py1", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "organization", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-106") {
		addForeignKeyConstraint(baseColumnNames: "pi_id", baseTableName: "organization", constraintName: "FK_shncwjk67uss09ivcrpvlv8xg", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-107") {
		addForeignKeyConstraint(baseColumnNames: "project_id", baseTableName: "project_samples", constraintName: "FK_bcuquxjs68mn5vyq7d0yphn8p", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "project", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-108") {
		addForeignKeyConstraint(baseColumnNames: "sample_id", baseTableName: "project_samples", constraintName: "FK_qfu2n2hwoukibpvvx51eftp9y", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sample", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-109") {
		addForeignKeyConstraint(baseColumnNames: "project_id", baseTableName: "project_user", constraintName: "FK_ptwhmsh2vocln8sffhyvr2ohm", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "project", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-110") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "project_user", constraintName: "FK_d6kfrxuqknbxrlxhwmn66a3kg", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-111") {
		addForeignKeyConstraint(baseColumnNames: "assay_id", baseTableName: "protocol", constraintName: "FK_f2u9282jbe9eiy0fggmrvrt16", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "assay", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-112") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "protocol", constraintName: "FK_mpt4h4wrqjngorh2706rvsqk2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-113") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "protocol_group", constraintName: "FK_mm7fvjt84dbnbsnsbe9ik36xh", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-114") {
		addForeignKeyConstraint(baseColumnNames: "protocol_group_id", baseTableName: "protocol_group_protocols", constraintName: "FK_6sjm1imt3f4b8rd492wm9vt7e", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "protocol_group", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-115") {
		addForeignKeyConstraint(baseColumnNames: "protocol_id", baseTableName: "protocol_group_protocols", constraintName: "FK_6i86p4hqvh803hqeam63pf0fo", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "protocol", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-116") {
		addForeignKeyConstraint(baseColumnNames: "bag_id", baseTableName: "protocol_instance", constraintName: "FK_j952d9eb9yjue4b2xggu8ehib", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "protocol_instance_bag", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-117") {
		addForeignKeyConstraint(baseColumnNames: "protocol_id", baseTableName: "protocol_instance", constraintName: "FK_d4v22o39k3xu8m216w8r1nyx1", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "protocol", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-118") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "protocol_instance", constraintName: "FK_sgj3vhj59cxyb10md7ybwmtm7", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-119") {
		addForeignKeyConstraint(baseColumnNames: "protocol_group_id", baseTableName: "protocol_instance_bag", constraintName: "FK_r1uxlmgfs4nieck8kad4wo79q", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "protocol_group", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-120") {
		addForeignKeyConstraint(baseColumnNames: "item_id", baseTableName: "protocol_instance_items", constraintName: "FK_2ehrfoqmnyg7wc3sv0owshi8l", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "item", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-121") {
		addForeignKeyConstraint(baseColumnNames: "protocol_instance_id", baseTableName: "protocol_instance_items", constraintName: "FK_dmt3slumwfqinb5qicjdvrilr", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "protocol_instance", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-122") {
		addForeignKeyConstraint(baseColumnNames: "protocol_id", baseTableName: "protocol_instance_summary", constraintName: "FK_5v0brjc04upo9ksqnq3bivxni", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "protocol", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-123") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "protocol_instance_summary", constraintName: "FK_8idyxguxexanjiixc15ylqewv", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-124") {
		addForeignKeyConstraint(baseColumnNames: "item_type_id", baseTableName: "protocol_item_types", constraintName: "FK_mi8fvx3awmigchnxb1s8keinu", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "item_type", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-125") {
		addForeignKeyConstraint(baseColumnNames: "protocol_id", baseTableName: "protocol_item_types", constraintName: "FK_p1p3edd8llir9a0qp24sj5cql", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "protocol", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-126") {
		addForeignKeyConstraint(baseColumnNames: "antibody_id", baseTableName: "sample", constraintName: "FK_4lb993olybjsa6bqlbt90as3c", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "antibody", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-127") {
		addForeignKeyConstraint(baseColumnNames: "cell_source_id", baseTableName: "sample", constraintName: "FK_a1ro7uobsoyc87a51ssw7n5sw", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cell_source", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-128") {
		addForeignKeyConstraint(baseColumnNames: "invoice_id", baseTableName: "sample", constraintName: "FK_tqw4ka9uvmywhb4gw0ib7nxy9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "invoice", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-129") {
		addForeignKeyConstraint(baseColumnNames: "item_id", baseTableName: "sample", constraintName: "FK_9l7trxp5onscu1yk6fcvenjq6", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "item", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-130") {
		addForeignKeyConstraint(baseColumnNames: "prtcl_inst_summary_id", baseTableName: "sample", constraintName: "FK_bcdouyjpgn9hqcd947o1f28jq", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "protocol_instance_summary", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-131") {
		addForeignKeyConstraint(baseColumnNames: "send_data_to_id", baseTableName: "sample", constraintName: "FK_psm0kim6hte7fy0577fwsbdi", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-132") {
		addForeignKeyConstraint(baseColumnNames: "spike_in_cell_source_id", baseTableName: "sample", constraintName: "FK_iab90bxgihv46lqg7ib51kmwn", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cell_source", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-133") {
		addForeignKeyConstraint(baseColumnNames: "target_id", baseTableName: "sample", constraintName: "FK_cfxg067kqgsk55rjibh2pbnso", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "target", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-134") {
		addForeignKeyConstraint(baseColumnNames: "protocol_instance_bag_id", baseTableName: "sample_bags", constraintName: "FK_cnr1t3lrixla6a72js4vswgow", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "protocol_instance_bag", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-135") {
		addForeignKeyConstraint(baseColumnNames: "sample_id", baseTableName: "sample_bags", constraintName: "FK_m94py76hnab3k5yd83xstrenu", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sample", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-136") {
		addForeignKeyConstraint(baseColumnNames: "run_id", baseTableName: "sample_in_run", constraintName: "FK_993bhxmuyjlul88wourwo5eyw", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sequence_run", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-137") {
		addForeignKeyConstraint(baseColumnNames: "sample_id", baseTableName: "sample_in_run", constraintName: "FK_fsayjgiqhlt7cctq8n1escwvk", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sample", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-138") {
		addForeignKeyConstraint(baseColumnNames: "index_id", baseTableName: "sample_sequence_indices", constraintName: "FK_flne19kx2tprlhpbxh9a3xk8r", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sequence_index", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-139") {
		addForeignKeyConstraint(baseColumnNames: "sample_id", baseTableName: "sample_sequence_indices", constraintName: "FK_qh0akbm1a3893iwxbwqps9xmn", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sample", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-140") {
		addForeignKeyConstraint(baseColumnNames: "align_type_id", baseTableName: "sequence_alignment", constraintName: "FK_8gcis40hwme6s7ywn4jikodd2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "align_type", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-141") {
		addForeignKeyConstraint(baseColumnNames: "aligner_id", baseTableName: "sequence_alignment", constraintName: "FK_fee47iwmejna7h55jp8dpkowd", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "aligner", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-142") {
		addForeignKeyConstraint(baseColumnNames: "core_pipeline_id", baseTableName: "sequence_alignment", constraintName: "FK_hc4f4br571qwgavrswy22w5wo", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "core_pipeline", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-143") {
		addForeignKeyConstraint(baseColumnNames: "genome_id", baseTableName: "sequence_alignment", constraintName: "FK_5tdpednm424bwlyaygxb91cj8", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "genome", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-144") {
		addForeignKeyConstraint(baseColumnNames: "sequencing_experiment_id", baseTableName: "sequence_alignment", constraintName: "FK_js0whth80fyna834brwbbo8v0", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sequencing_experiment", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-145") {
		addForeignKeyConstraint(baseColumnNames: "platform_id", baseTableName: "sequence_run", constraintName: "FK_l1ofj1csp7k439e52l32soay9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sequencing_platform", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-146") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "sequence_run", constraintName: "FK_gerfjuiu4n7m0iukeud2wrux7", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-147") {
		addForeignKeyConstraint(baseColumnNames: "read_type_id", baseTableName: "sequencing_experiment", constraintName: "FK_3s9e144d345uyhay28toy8jwg", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "read_type", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-148") {
		addForeignKeyConstraint(baseColumnNames: "sample_id", baseTableName: "sequencing_experiment", constraintName: "FK_7gsumwrx4h3nji2g2q5htn098", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sample", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-149") {
		addForeignKeyConstraint(baseColumnNames: "sequence_run_id", baseTableName: "sequencing_experiment", constraintName: "FK_3mx03u80x4ynhsx1i65yrrxcq", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sequence_run", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-150") {
		addForeignKeyConstraint(baseColumnNames: "parent_id", baseTableName: "strain", constraintName: "FK_4shdjcf8qcywolsnrmi3djhmu", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "strain", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-151") {
		addForeignKeyConstraint(baseColumnNames: "source_lab_id", baseTableName: "strain", constraintName: "FK_bd07vlra9sn9vs8po73rw89wg", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "organization", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-152") {
		addForeignKeyConstraint(baseColumnNames: "species_id", baseTableName: "strain", constraintName: "FK_c5gb45n5okxh7bqig1nouw76t", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "species", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-153") {
		addForeignKeyConstraint(baseColumnNames: "target_type_id", baseTableName: "target", constraintName: "FK_e5wo7j1f9so6mcll9vwtn6005", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "target_type", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-154") {
		addForeignKeyConstraint(baseColumnNames: "computing_infrastructure_id", baseTableName: "task", constraintName: "FK_2ovu0g1rssdjbpgb6fj0l2vwy", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "computing_infrastructure", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-155") {
		addForeignKeyConstraint(baseColumnNames: "core_pipe_line_id", baseTableName: "task", constraintName: "FK_89y9w7ykrtfvr9quf0acepu0k", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "core_pipeline", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-156") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "task", constraintName: "FK_4fmjedju7b35tb5cr71n3ntb0", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-157") {
		addForeignKeyConstraint(baseColumnNames: "sample_id", baseTableName: "technical_replicate_samples", constraintName: "FK_pgfytkmfc7o8hujxpm27nrupx", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sample", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-158") {
		addForeignKeyConstraint(baseColumnNames: "set_id", baseTableName: "technical_replicate_samples", constraintName: "FK_bxc886ht20h9dyioee4mvtuhg", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "technical_replicate_set", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-159") {
		addForeignKeyConstraint(baseColumnNames: "project_id", baseTableName: "technical_replicate_set", constraintName: "FK_9qiur0k1ju7xinurh5sy074fr", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "project", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-160") {
		addForeignKeyConstraint(baseColumnNames: "address_id", baseTableName: "user", constraintName: "FK_dhlcfg8h1drrgu0irs1ro3ohb", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "address", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-161") {
		addForeignKeyConstraint(baseColumnNames: "affiliation_id", baseTableName: "user", constraintName: "FK_qdusuaq6oge31t7nlq10wm6ku", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "organization", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-162") {
		addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "user_role", constraintName: "FK_it77eq964jhfqtu54081ebtio", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "role", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1456345094061-163") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_role", constraintName: "FK_apcc8lxk2xnug8377fatvbn04", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}
}
