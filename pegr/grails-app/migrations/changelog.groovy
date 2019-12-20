databaseChangeLog = {

	changeSet(author: "dus73 (generated)", id: "1470678902832-1") {
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

	changeSet(author: "dus73 (generated)", id: "1470678902832-2") {
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

	changeSet(author: "dus73 (generated)", id: "1470678902832-3") {
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

	changeSet(author: "dus73 (generated)", id: "1470678902832-4") {
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

	changeSet(author: "dus73 (generated)", id: "1470678902832-5") {
		createTable(tableName: "analysis") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "analysisPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "alignment_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "category", type: "varchar(255)")

			column(name: "datasets", type: "text")

			column(name: "history_id", type: "varchar(255)")

			column(name: "note", type: "varchar(255)")

			column(name: "parameters", type: "text")

			column(name: "pipeline_id", type: "bigint")

			column(name: "statistics", type: "text")

			column(name: "step_id", type: "varchar(255)")

			column(name: "tool", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "user", type: "varchar(255)")

			column(name: "workflow_id", type: "varchar(255)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-6") {
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

			column(name: "default_target_id", type: "bigint")

			column(name: "external_id", type: "varchar(255)")

			column(name: "ig_type_id", type: "bigint")

			column(name: "immunogene", type: "varchar(255)")

			column(name: "inventory_id", type: "varchar(255)")

			column(name: "item_id", type: "bigint")

			column(name: "lot_number", type: "varchar(255)")

			column(name: "note", type: "varchar(255)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-7") {
		createTable(tableName: "api_user") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "api_userPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "api_key", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-8") {
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

	changeSet(author: "dus73 (generated)", id: "1470678902832-9") {
		createTable(tableName: "cell_source") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "cell_sourcePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "age", type: "varchar(255)")

			column(name: "biological_source_id", type: "varchar(255)")

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

	changeSet(author: "dus73 (generated)", id: "1470678902832-10") {
		createTable(tableName: "cell_source_treatment") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "cell_source_tPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "note", type: "text")

			column(name: "status", type: "varchar(255)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-11") {
		createTable(tableName: "chores") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "choresPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "value", type: "varchar(255)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-12") {
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

	changeSet(author: "dus73 (generated)", id: "1470678902832-13") {
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

	changeSet(author: "dus73 (generated)", id: "1470678902832-14") {
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

	changeSet(author: "dus73 (generated)", id: "1470678902832-15") {
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

	changeSet(author: "dus73 (generated)", id: "1470678902832-16") {
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

	changeSet(author: "dus73 (generated)", id: "1470678902832-17") {
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

	changeSet(author: "dus73 (generated)", id: "1470678902832-18") {
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

	changeSet(author: "dus73 (generated)", id: "1470678902832-19") {
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

	changeSet(author: "dus73 (generated)", id: "1470678902832-20") {
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

	changeSet(author: "dus73 (generated)", id: "1470678902832-21") {
		createTable(tableName: "inventory") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "inventoryPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "date_received", type: "datetime")

			column(name: "location", type: "varchar(255)")

			column(name: "notes", type: "varchar(255)")

			column(name: "receiving_user_id", type: "bigint")

			column(name: "source_type", type: "varchar(255)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-22") {
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

	changeSet(author: "dus73 (generated)", id: "1470678902832-23") {
		createTable(tableName: "item") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "itemPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "barcode", type: "varchar(255)")

			column(name: "location", type: "varchar(255)")

			column(name: "name", type: "varchar(255)")

			column(name: "notes", type: "varchar(255)")

			column(name: "parent_id", type: "bigint")

			column(name: "type_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-24") {
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

	changeSet(author: "dus73 (generated)", id: "1470678902832-25") {
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

	changeSet(author: "dus73 (generated)", id: "1470678902832-26") {
		createTable(tableName: "pipeline") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "pipelinePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "note", type: "varchar(255)")

			column(name: "pipeline_version", type: "varchar(255)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-27") {
		createTable(tableName: "pool_samples") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "pool_samplesPK")
			}

			column(name: "pool_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "sample_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-28") {
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

	changeSet(author: "dus73 (generated)", id: "1470678902832-29") {
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

	changeSet(author: "dus73 (generated)", id: "1470678902832-30") {
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

	changeSet(author: "dus73 (generated)", id: "1470678902832-31") {
		createTable(tableName: "protocol") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "protocolPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "add_antibody", type: "bit")

			column(name: "add_index", type: "bit")

			column(name: "assay_id", type: "bigint")

			column(name: "description", type: "varchar(255)")

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "protocol_version", type: "varchar(10)")

			column(name: "short_name", type: "varchar(255)")

			column(name: "status", type: "varchar(255)")

			column(name: "user_id", type: "bigint")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-32") {
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

	changeSet(author: "dus73 (generated)", id: "1470678902832-33") {
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

	changeSet(author: "dus73 (generated)", id: "1470678902832-34") {
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

	changeSet(author: "dus73 (generated)", id: "1470678902832-35") {
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

	changeSet(author: "dus73 (generated)", id: "1470678902832-36") {
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

	changeSet(author: "dus73 (generated)", id: "1470678902832-37") {
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

	changeSet(author: "dus73 (generated)", id: "1470678902832-38") {
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

	changeSet(author: "dus73 (generated)", id: "1470678902832-39") {
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

	changeSet(author: "dus73 (generated)", id: "1470678902832-40") {
		createTable(tableName: "replicate_samples") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "replicate_samPK")
			}

			column(name: "sample_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "set_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-41") {
		createTable(tableName: "replicate_set") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "replicate_setPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "project_id", type: "bigint")

			column(name: "type", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-42") {
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

	changeSet(author: "dus73 (generated)", id: "1470678902832-43") {
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

	changeSet(author: "dus73 (generated)", id: "1470678902832-44") {
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

	changeSet(author: "dus73 (generated)", id: "1470678902832-45") {
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

	changeSet(author: "dus73 (generated)", id: "1470678902832-46") {
		createTable(tableName: "sample") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "samplePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "antibody_id", type: "bigint")

			column(name: "antibody_notes", type: "varchar(255)")

			column(name: "assay_id", type: "bigint")

			column(name: "audit_id", type: "bigint")

			column(name: "cell_number", type: "double precision")

			column(name: "cell_source_id", type: "bigint")

			column(name: "chromosome_amount", type: "double precision")

			column(name: "date", type: "datetime")

			column(name: "growth_media_id", type: "bigint")

			column(name: "invoice_id", type: "bigint")

			column(name: "item_id", type: "bigint")

			column(name: "note", type: "text")

			column(name: "prtcl_inst_summary_id", type: "bigint")

			column(name: "publication_reference", type: "varchar(255)")

			column(name: "requested_genomes", type: "varchar(255)")

			column(name: "requested_tag_number", type: "double precision")

			column(name: "send_data_to_id", type: "bigint")

			column(name: "source", type: "varchar(255)")

			column(name: "source_id", type: "varchar(255)")

			column(name: "spike_in_cell_source_id", type: "bigint")

			column(name: "status", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "target_id", type: "bigint")

			column(name: "volume", type: "double precision")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-47") {
		createTable(tableName: "sample_audit") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "sample_auditPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "contaminated", type: "bit")

			column(name: "notes", type: "varchar(255)")

			column(name: "warning", type: "bit")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-48") {
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

	changeSet(author: "dus73 (generated)", id: "1470678902832-49") {
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

	changeSet(author: "dus73 (generated)", id: "1470678902832-50") {
		createTable(tableName: "sample_sequence_indices") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "sample_sequenPK")
			}

			column(name: "index_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "index_in_set", type: "integer")

			column(name: "sample_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "set_id", type: "integer")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-51") {
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

	changeSet(author: "dus73 (generated)", id: "1470678902832-52") {
		createTable(tableName: "sequence_alignment") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "sequence_aligPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "align_type_id", type: "bigint")

			column(name: "aligner_id", type: "bigint")

			column(name: "avg_insert_size", type: "float")

			column(name: "bam_file", type: "varchar(1000)")

			column(name: "date", type: "datetime")

			column(name: "dedup_uniquely_mapped_reads", type: "bigint")

			column(name: "genome_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "genome_coverage", type: "float")

			column(defaultValue: "true", name: "is_preferred", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "mapped_reads", type: "bigint")

			column(name: "params", type: "varchar(2000)")

			column(name: "pe_histogram", type: "varchar(1000)")

			column(name: "read_db_id", type: "integer")

			column(name: "seq_duplication_level", type: "float")

			column(name: "sequencing_experiment_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "std_dev_insert_size", type: "float")

			column(name: "uniquely_mapped_reads", type: "bigint")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-53") {
		createTable(tableName: "sequence_index") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "sequence_indePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "index_id", type: "varchar(255)") {
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

	changeSet(author: "dus73 (generated)", id: "1470678902832-54") {
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

			column(name: "pool_item_id", type: "bigint")

			column(name: "run_num", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "run_stats_id", type: "bigint")

			column(name: "status", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-55") {
		createTable(tableName: "sequencing_experiment") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "sequencing_exPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "adapter_dimer_count", type: "bigint")

			column(name: "fastq_file", type: "varchar(1000)")

			column(name: "fastqc_report", type: "varchar(1000)")

			column(name: "index_mismatch", type: "integer")

			column(name: "note", type: "varchar(255)")

			column(name: "public_db_id", type: "varchar(255)")

			column(name: "read_positions", type: "varchar(255)")

			column(name: "read_type_id", type: "bigint")

			column(name: "sample_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "sequence_run_id", type: "bigint")

			column(name: "total_reads", type: "bigint")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-56") {
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

	changeSet(author: "dus73 (generated)", id: "1470678902832-57") {
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

	changeSet(author: "dus73 (generated)", id: "1470678902832-58") {
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

	changeSet(author: "dus73 (generated)", id: "1470678902832-59") {
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

	changeSet(author: "dus73 (generated)", id: "1470678902832-60") {
		createTable(tableName: "summary_report") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "summary_reporPK")
			}

			column(name: "project_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "run_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-61") {
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

	changeSet(author: "dus73 (generated)", id: "1470678902832-62") {
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

	changeSet(author: "dus73 (generated)", id: "1470678902832-63") {
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

	changeSet(author: "dus73 (generated)", id: "1470678902832-64") {
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

	changeSet(author: "dus73 (generated)", id: "1470678902832-65") {
		createTable(tableName: "user_role") {
			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "role_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-66") {
		addPrimaryKey(columnNames: "user_id, role_id", constraintName: "user_rolePK", tableName: "user_role")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-166") {
		createIndex(indexName: "name_uniq_1470678902654", tableName: "ab_host", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-167") {
		createIndex(indexName: "name_uniq_1470678902659", tableName: "align_type", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-168") {
		createIndex(indexName: "short_name_uniq_1470678902660", tableName: "align_type", unique: "true") {
			column(name: "short_name")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-169") {
		createIndex(indexName: "unique_software", tableName: "aligner", unique: "true") {
			column(name: "aligner_version")

			column(name: "software")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-170") {
		createIndex(indexName: "FK_8qg2onufaj9spg9xlnmdn6jk6", tableName: "analysis") {
			column(name: "pipeline_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-171") {
		createIndex(indexName: "FK_jegylnmnp65k1q1698nu5sjjw", tableName: "analysis") {
			column(name: "alignment_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-172") {
		createIndex(indexName: "FK_54ep9wcwdrl5kkuq231173s2f", tableName: "antibody") {
			column(name: "item_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-173") {
		createIndex(indexName: "FK_5bpi6ah0csjh4g0n8ln93wx3h", tableName: "antibody") {
			column(name: "ig_type_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-174") {
		createIndex(indexName: "FK_m6sxsi7lepp2alm9d8832uv26", tableName: "antibody") {
			column(name: "default_target_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-175") {
		createIndex(indexName: "FK_qjuxd91ddd77fufu1em86n2gb", tableName: "antibody") {
			column(name: "ab_host_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-176") {
		createIndex(indexName: "FK_rhc520eoim7txw9d7kqpb90fw", tableName: "antibody") {
			column(name: "company_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-177") {
		createIndex(indexName: "name_uniq_1470678902672", tableName: "assay", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-178") {
		createIndex(indexName: "FK_318alqytpqfjhixa0xumta27", tableName: "cell_source") {
			column(name: "provider_lab_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-179") {
		createIndex(indexName: "FK_3j4empi718wbs6weggsvx0vxf", tableName: "cell_source") {
			column(name: "sex_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-180") {
		createIndex(indexName: "FK_71fd478uakc4ikgvf7jkqrbic", tableName: "cell_source") {
			column(name: "inventory_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-181") {
		createIndex(indexName: "FK_7i3q0e2js5j13ybv0fpn6wsdj", tableName: "cell_source") {
			column(name: "strain_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-182") {
		createIndex(indexName: "FK_abm2plajs5iecdn4xw32iiq98", tableName: "cell_source") {
			column(name: "histology_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-183") {
		createIndex(indexName: "FK_fvxkssmfdikktj0688ybb3tbh", tableName: "cell_source") {
			column(name: "tissue_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-184") {
		createIndex(indexName: "FK_js2v7lu898c0p8ortg5xgxb0q", tableName: "cell_source") {
			column(name: "provider_user_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-185") {
		createIndex(indexName: "FK_mrpb1l0o1f7urfo78pf1dn2sl", tableName: "cell_source") {
			column(name: "item_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-186") {
		createIndex(indexName: "FK_osoanky7172r4r5skdfatpr2n", tableName: "cell_source") {
			column(name: "prep_user_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-187") {
		createIndex(indexName: "name_uniq_1470678902686", tableName: "cell_source_treatment", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-188") {
		createIndex(indexName: "name_uniq_1470678902687", tableName: "chores", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-189") {
		createIndex(indexName: "FK_lese17egs9gkqmyudn2wh5il2", tableName: "chrom_sequence") {
			column(name: "chromosome_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-190") {
		createIndex(indexName: "FK_qtx5yloqyq7hc1efournhys5u", tableName: "chromosome") {
			column(name: "genome_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-191") {
		createIndex(indexName: "name_uniq_1470678902689", tableName: "chromosome", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-192") {
		createIndex(indexName: "FK_n5xni9ruiej5e6p37qkxc0ha8", tableName: "control_sample") {
			column(name: "sample_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-193") {
		createIndex(indexName: "FK_pmyy4lhr2rnht0taqgm4rpfq3", tableName: "control_sample") {
			column(name: "control_sample_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-194") {
		createIndex(indexName: "unique_sample_id", tableName: "control_sample", unique: "true") {
			column(name: "control_sample_id")

			column(name: "sample_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-195") {
		createIndex(indexName: "name_uniq_1470678902690", tableName: "definition", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-196") {
		createIndex(indexName: "FK_8qyh94lw1ucpnn89d4dl4e5dr", tableName: "genome") {
			column(name: "species_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-197") {
		createIndex(indexName: "FK_d67vskwe5phnwub9cqiokalkx", tableName: "growth_media") {
			column(name: "species_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-198") {
		createIndex(indexName: "name_uniq_1470678902693", tableName: "growth_media", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-199") {
		createIndex(indexName: "FK_jrq8s9u4ujdq8durk95ilfebo", tableName: "histology") {
			column(name: "parent_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-200") {
		createIndex(indexName: "name_uniq_1470678902694", tableName: "histology", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-201") {
		createIndex(indexName: "FK_fej8h317q2acuy144kav1oyxc", tableName: "history") {
			column(name: "project_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-202") {
		createIndex(indexName: "FK_fuutexvtx28fs971iq0kbfbmp", tableName: "history") {
			column(name: "user_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-203") {
		createIndex(indexName: "name_uniq_1470678902696", tableName: "ig_type", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-204") {
		createIndex(indexName: "FK_mnah8ybq73nyl126do8agmk08", tableName: "inventory") {
			column(name: "receiving_user_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-205") {
		createIndex(indexName: "FK_br92r4wqm19mvpcyhxn5lg7m7", tableName: "item") {
			column(name: "user_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-206") {
		createIndex(indexName: "FK_ccldfsomwnlcfqys42su71de3", tableName: "item") {
			column(name: "parent_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-207") {
		createIndex(indexName: "FK_qxnbu16tlqfmub9pgfj3h2e41", tableName: "item") {
			column(name: "type_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-208") {
		createIndex(indexName: "unique_barcode", tableName: "item", unique: "true") {
			column(name: "type_id")

			column(name: "barcode")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-209") {
		createIndex(indexName: "name_uniq_1470678902700", tableName: "item_type", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-210") {
		createIndex(indexName: "FK_57byxcy430qbl2gl7liup0py1", tableName: "organization") {
			column(name: "parent_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-211") {
		createIndex(indexName: "FK_6w4r8gx2jdvy193esigbpxxx6", tableName: "organization") {
			column(name: "billing_contact_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-212") {
		createIndex(indexName: "FK_l070gahmlj4g2sqbm72btw64e", tableName: "organization") {
			column(name: "address_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-213") {
		createIndex(indexName: "FK_shncwjk67uss09ivcrpvlv8xg", tableName: "organization") {
			column(name: "pi_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-214") {
		createIndex(indexName: "name_uniq_1470678902701", tableName: "organization", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-215") {
		createIndex(indexName: "unique_name", tableName: "pipeline", unique: "true") {
			column(name: "pipeline_version")

			column(name: "name")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-216") {
		createIndex(indexName: "FK_ad5h1h1ndrx123chi4kvxkijg", tableName: "pool_samples") {
			column(name: "sample_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-217") {
		createIndex(indexName: "FK_ek00bovrrt47tc8bpgn5g2gdg", tableName: "pool_samples") {
			column(name: "pool_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-218") {
		createIndex(indexName: "unique_sample_id", tableName: "pool_samples", unique: "true") {
			column(name: "pool_id")

			column(name: "sample_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-219") {
		createIndex(indexName: "name_uniq_1470678902705", tableName: "project", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-220") {
		createIndex(indexName: "FK_bcuquxjs68mn5vyq7d0yphn8p", tableName: "project_samples") {
			column(name: "project_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-221") {
		createIndex(indexName: "FK_qfu2n2hwoukibpvvx51eftp9y", tableName: "project_samples") {
			column(name: "sample_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-222") {
		createIndex(indexName: "unique_project_id", tableName: "project_samples", unique: "true") {
			column(name: "sample_id")

			column(name: "project_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-223") {
		createIndex(indexName: "FK_d6kfrxuqknbxrlxhwmn66a3kg", tableName: "project_user") {
			column(name: "user_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-224") {
		createIndex(indexName: "FK_ptwhmsh2vocln8sffhyvr2ohm", tableName: "project_user") {
			column(name: "project_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-225") {
		createIndex(indexName: "unique_project_id", tableName: "project_user", unique: "true") {
			column(name: "user_id")

			column(name: "project_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-226") {
		createIndex(indexName: "FK_f2u9282jbe9eiy0fggmrvrt16", tableName: "protocol") {
			column(name: "assay_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-227") {
		createIndex(indexName: "FK_mpt4h4wrqjngorh2706rvsqk2", tableName: "protocol") {
			column(name: "user_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-228") {
		createIndex(indexName: "unique_name", tableName: "protocol", unique: "true") {
			column(name: "protocol_version")

			column(name: "name")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-229") {
		createIndex(indexName: "FK_mm7fvjt84dbnbsnsbe9ik36xh", tableName: "protocol_group") {
			column(name: "user_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-230") {
		createIndex(indexName: "name_uniq_1470678902708", tableName: "protocol_group", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-231") {
		createIndex(indexName: "FK_6i86p4hqvh803hqeam63pf0fo", tableName: "protocol_group_protocols") {
			column(name: "protocol_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-232") {
		createIndex(indexName: "FK_6sjm1imt3f4b8rd492wm9vt7e", tableName: "protocol_group_protocols") {
			column(name: "protocol_group_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-233") {
		createIndex(indexName: "FK_d4v22o39k3xu8m216w8r1nyx1", tableName: "protocol_instance") {
			column(name: "protocol_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-234") {
		createIndex(indexName: "FK_j952d9eb9yjue4b2xggu8ehib", tableName: "protocol_instance") {
			column(name: "bag_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-235") {
		createIndex(indexName: "FK_sgj3vhj59cxyb10md7ybwmtm7", tableName: "protocol_instance") {
			column(name: "user_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-236") {
		createIndex(indexName: "FK_r1uxlmgfs4nieck8kad4wo79q", tableName: "protocol_instance_bag") {
			column(name: "protocol_group_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-237") {
		createIndex(indexName: "FK_2ehrfoqmnyg7wc3sv0owshi8l", tableName: "protocol_instance_items") {
			column(name: "item_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-238") {
		createIndex(indexName: "FK_dmt3slumwfqinb5qicjdvrilr", tableName: "protocol_instance_items") {
			column(name: "protocol_instance_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-239") {
		createIndex(indexName: "unique_protocol_instance_id", tableName: "protocol_instance_items", unique: "true") {
			column(name: "item_id")

			column(name: "protocol_instance_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-240") {
		createIndex(indexName: "FK_5v0brjc04upo9ksqnq3bivxni", tableName: "protocol_instance_summary") {
			column(name: "protocol_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-241") {
		createIndex(indexName: "FK_8idyxguxexanjiixc15ylqewv", tableName: "protocol_instance_summary") {
			column(name: "user_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-242") {
		createIndex(indexName: "FK_mi8fvx3awmigchnxb1s8keinu", tableName: "protocol_item_types") {
			column(name: "item_type_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-243") {
		createIndex(indexName: "FK_p1p3edd8llir9a0qp24sj5cql", tableName: "protocol_item_types") {
			column(name: "protocol_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-244") {
		createIndex(indexName: "unique_protocol_id", tableName: "protocol_item_types", unique: "true") {
			column(name: "item_type_id")

			column(name: "protocol_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-245") {
		createIndex(indexName: "name_uniq_1470678902716", tableName: "read_type", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-246") {
		createIndex(indexName: "short_name_uniq_1470678902716", tableName: "read_type", unique: "true") {
			column(name: "short_name")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-247") {
		createIndex(indexName: "FK_b7fffn7tv5cj5s4eslmipmujb", tableName: "replicate_samples") {
			column(name: "set_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-248") {
		createIndex(indexName: "FK_ced3uejqos7cnb2vaupk3qk1h", tableName: "replicate_samples") {
			column(name: "sample_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-249") {
		createIndex(indexName: "unique_set_id", tableName: "replicate_samples", unique: "true") {
			column(name: "sample_id")

			column(name: "set_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-250") {
		createIndex(indexName: "FK_qg0x2qhnutq0rw27iglu1o49u", tableName: "replicate_set") {
			column(name: "project_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-251") {
		createIndex(indexName: "FK_invrh9of0ndhq9wetgqupqjuu", tableName: "report_alignments") {
			column(name: "alignment_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-252") {
		createIndex(indexName: "FK_jgmi242smigukk2otj453sld8", tableName: "report_alignments") {
			column(name: "report_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-253") {
		createIndex(indexName: "unique_report_id", tableName: "report_alignments", unique: "true") {
			column(name: "alignment_id")

			column(name: "report_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-254") {
		createIndex(indexName: "unique_url", tableName: "requestmap", unique: "true") {
			column(name: "http_method")

			column(name: "url")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-255") {
		createIndex(indexName: "authority_uniq_1470678902719", tableName: "role", unique: "true") {
			column(name: "authority")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-256") {
		createIndex(indexName: "FK_4lb993olybjsa6bqlbt90as3c", tableName: "sample") {
			column(name: "antibody_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-257") {
		createIndex(indexName: "FK_9l7trxp5onscu1yk6fcvenjq6", tableName: "sample") {
			column(name: "item_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-258") {
		createIndex(indexName: "FK_a1ro7uobsoyc87a51ssw7n5sw", tableName: "sample") {
			column(name: "cell_source_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-259") {
		createIndex(indexName: "FK_bcdouyjpgn9hqcd947o1f28jq", tableName: "sample") {
			column(name: "prtcl_inst_summary_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-260") {
		createIndex(indexName: "FK_cfxg067kqgsk55rjibh2pbnso", tableName: "sample") {
			column(name: "target_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-261") {
		createIndex(indexName: "FK_dhxymxy1936tgj756fntpg6xw", tableName: "sample") {
			column(name: "assay_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-262") {
		createIndex(indexName: "FK_iab90bxgihv46lqg7ib51kmwn", tableName: "sample") {
			column(name: "spike_in_cell_source_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-263") {
		createIndex(indexName: "FK_ppsd5p8eqaveimasoymlfgcny", tableName: "sample") {
			column(name: "growth_media_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-264") {
		createIndex(indexName: "FK_psm0kim6hte7fy0577fwsbdi", tableName: "sample") {
			column(name: "send_data_to_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-265") {
		createIndex(indexName: "FK_qbpe56xpt5u1qewbly5ivorrx", tableName: "sample") {
			column(name: "audit_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-266") {
		createIndex(indexName: "FK_tqw4ka9uvmywhb4gw0ib7nxy9", tableName: "sample") {
			column(name: "invoice_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-267") {
		createIndex(indexName: "FK_cnr1t3lrixla6a72js4vswgow", tableName: "sample_bags") {
			column(name: "protocol_instance_bag_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-268") {
		createIndex(indexName: "FK_m94py76hnab3k5yd83xstrenu", tableName: "sample_bags") {
			column(name: "sample_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-269") {
		createIndex(indexName: "FK_993bhxmuyjlul88wourwo5eyw", tableName: "sample_in_run") {
			column(name: "run_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-270") {
		createIndex(indexName: "FK_fsayjgiqhlt7cctq8n1escwvk", tableName: "sample_in_run") {
			column(name: "sample_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-271") {
		createIndex(indexName: "unique_sample_id", tableName: "sample_in_run", unique: "true") {
			column(name: "run_id")

			column(name: "sample_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-272") {
		createIndex(indexName: "FK_flne19kx2tprlhpbxh9a3xk8r", tableName: "sample_sequence_indices") {
			column(name: "index_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-273") {
		createIndex(indexName: "FK_qh0akbm1a3893iwxbwqps9xmn", tableName: "sample_sequence_indices") {
			column(name: "sample_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-274") {
		createIndex(indexName: "FK_cldug6dnvxfcnd89wfrgg9e7i", tableName: "sample_treatments") {
			column(name: "sample_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-275") {
		createIndex(indexName: "FK_ehfppi0f0g2lojkytdeaij3lp", tableName: "sample_treatments") {
			column(name: "treatment_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-276") {
		createIndex(indexName: "unique_sample_id", tableName: "sample_treatments", unique: "true") {
			column(name: "treatment_id")

			column(name: "sample_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-277") {
		createIndex(indexName: "FK_5tdpednm424bwlyaygxb91cj8", tableName: "sequence_alignment") {
			column(name: "genome_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-278") {
		createIndex(indexName: "FK_8gcis40hwme6s7ywn4jikodd2", tableName: "sequence_alignment") {
			column(name: "align_type_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-279") {
		createIndex(indexName: "FK_fee47iwmejna7h55jp8dpkowd", tableName: "sequence_alignment") {
			column(name: "aligner_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-280") {
		createIndex(indexName: "FK_js0whth80fyna834brwbbo8v0", tableName: "sequence_alignment") {
			column(name: "sequencing_experiment_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-281") {
		createIndex(indexName: "FK_5cc4mivg8mvxoipvbrl09geqj", tableName: "sequence_run") {
			column(name: "run_stats_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-282") {
		createIndex(indexName: "FK_gerfjuiu4n7m0iukeud2wrux7", tableName: "sequence_run") {
			column(name: "user_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-283") {
		createIndex(indexName: "FK_l1ofj1csp7k439e52l32soay9", tableName: "sequence_run") {
			column(name: "platform_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-284") {
		createIndex(indexName: "FK_nb6cvqg7y8ruphnbfber6qjsb", tableName: "sequence_run") {
			column(name: "pool_item_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-285") {
		createIndex(indexName: "FK_3mx03u80x4ynhsx1i65yrrxcq", tableName: "sequencing_experiment") {
			column(name: "sequence_run_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-286") {
		createIndex(indexName: "FK_3s9e144d345uyhay28toy8jwg", tableName: "sequencing_experiment") {
			column(name: "read_type_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-287") {
		createIndex(indexName: "FK_7gsumwrx4h3nji2g2q5htn098", tableName: "sequencing_experiment") {
			column(name: "sample_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-288") {
		createIndex(indexName: "name_uniq_1470678902736", tableName: "sequencing_platform", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-289") {
		createIndex(indexName: "name_uniq_1470678902736", tableName: "sex", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-290") {
		createIndex(indexName: "unique_name", tableName: "species", unique: "true") {
			column(name: "genus_name")

			column(name: "name")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-291") {
		createIndex(indexName: "FK_4shdjcf8qcywolsnrmi3djhmu", tableName: "strain") {
			column(name: "parent_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-292") {
		createIndex(indexName: "FK_bd07vlra9sn9vs8po73rw89wg", tableName: "strain") {
			column(name: "source_lab_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-293") {
		createIndex(indexName: "FK_c5gb45n5okxh7bqig1nouw76t", tableName: "strain") {
			column(name: "species_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-294") {
		createIndex(indexName: "FK_3avk8v4ooy5p6pgj95viim6uy", tableName: "summary_report") {
			column(name: "project_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-295") {
		createIndex(indexName: "FK_bu7egp9o5u8ktnkkplehxxsjf", tableName: "summary_report") {
			column(name: "run_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-296") {
		createIndex(indexName: "unique_project_id", tableName: "summary_report", unique: "true") {
			column(name: "run_id")

			column(name: "project_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-297") {
		createIndex(indexName: "FK_e5wo7j1f9so6mcll9vwtn6005", tableName: "target") {
			column(name: "target_type_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-298") {
		createIndex(indexName: "unique_name", tableName: "target", unique: "true") {
			column(name: "c_term_tag")

			column(name: "n_term_tag")

			column(name: "name")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-299") {
		createIndex(indexName: "name_uniq_1470678902739", tableName: "target_type", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-300") {
		createIndex(indexName: "name_uniq_1470678902740", tableName: "tissue", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-301") {
		createIndex(indexName: "FK_dhlcfg8h1drrgu0irs1ro3ohb", tableName: "user") {
			column(name: "address_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-302") {
		createIndex(indexName: "FK_qdusuaq6oge31t7nlq10wm6ku", tableName: "user") {
			column(name: "affiliation_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-303") {
		createIndex(indexName: "username_uniq_1470678902741", tableName: "user", unique: "true") {
			column(name: "username")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-304") {
		createIndex(indexName: "FK_apcc8lxk2xnug8377fatvbn04", tableName: "user_role") {
			column(name: "user_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-305") {
		createIndex(indexName: "FK_it77eq964jhfqtu54081ebtio", tableName: "user_role") {
			column(name: "role_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-67") {
		addForeignKeyConstraint(baseColumnNames: "alignment_id", baseTableName: "analysis", constraintName: "FK_jegylnmnp65k1q1698nu5sjjw", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sequence_alignment", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-68") {
		addForeignKeyConstraint(baseColumnNames: "pipeline_id", baseTableName: "analysis", constraintName: "FK_8qg2onufaj9spg9xlnmdn6jk6", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "pipeline", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-69") {
		addForeignKeyConstraint(baseColumnNames: "ab_host_id", baseTableName: "antibody", constraintName: "FK_qjuxd91ddd77fufu1em86n2gb", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "ab_host", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-70") {
		addForeignKeyConstraint(baseColumnNames: "company_id", baseTableName: "antibody", constraintName: "FK_rhc520eoim7txw9d7kqpb90fw", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "organization", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-71") {
		addForeignKeyConstraint(baseColumnNames: "default_target_id", baseTableName: "antibody", constraintName: "FK_m6sxsi7lepp2alm9d8832uv26", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "target", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-72") {
		addForeignKeyConstraint(baseColumnNames: "ig_type_id", baseTableName: "antibody", constraintName: "FK_5bpi6ah0csjh4g0n8ln93wx3h", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "ig_type", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-73") {
		addForeignKeyConstraint(baseColumnNames: "item_id", baseTableName: "antibody", constraintName: "FK_54ep9wcwdrl5kkuq231173s2f", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "item", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-74") {
		addForeignKeyConstraint(baseColumnNames: "histology_id", baseTableName: "cell_source", constraintName: "FK_abm2plajs5iecdn4xw32iiq98", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "histology", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-75") {
		addForeignKeyConstraint(baseColumnNames: "inventory_id", baseTableName: "cell_source", constraintName: "FK_71fd478uakc4ikgvf7jkqrbic", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "inventory", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-76") {
		addForeignKeyConstraint(baseColumnNames: "item_id", baseTableName: "cell_source", constraintName: "FK_mrpb1l0o1f7urfo78pf1dn2sl", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "item", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-77") {
		addForeignKeyConstraint(baseColumnNames: "prep_user_id", baseTableName: "cell_source", constraintName: "FK_osoanky7172r4r5skdfatpr2n", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-78") {
		addForeignKeyConstraint(baseColumnNames: "provider_lab_id", baseTableName: "cell_source", constraintName: "FK_318alqytpqfjhixa0xumta27", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "organization", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-79") {
		addForeignKeyConstraint(baseColumnNames: "provider_user_id", baseTableName: "cell_source", constraintName: "FK_js2v7lu898c0p8ortg5xgxb0q", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-80") {
		addForeignKeyConstraint(baseColumnNames: "sex_id", baseTableName: "cell_source", constraintName: "FK_3j4empi718wbs6weggsvx0vxf", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sex", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-81") {
		addForeignKeyConstraint(baseColumnNames: "strain_id", baseTableName: "cell_source", constraintName: "FK_7i3q0e2js5j13ybv0fpn6wsdj", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "strain", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-82") {
		addForeignKeyConstraint(baseColumnNames: "tissue_id", baseTableName: "cell_source", constraintName: "FK_fvxkssmfdikktj0688ybb3tbh", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "tissue", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-83") {
		addForeignKeyConstraint(baseColumnNames: "chromosome_id", baseTableName: "chrom_sequence", constraintName: "FK_lese17egs9gkqmyudn2wh5il2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "chromosome", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-84") {
		addForeignKeyConstraint(baseColumnNames: "genome_id", baseTableName: "chromosome", constraintName: "FK_qtx5yloqyq7hc1efournhys5u", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "genome", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-85") {
		addForeignKeyConstraint(baseColumnNames: "control_sample_id", baseTableName: "control_sample", constraintName: "FK_pmyy4lhr2rnht0taqgm4rpfq3", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sample", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-86") {
		addForeignKeyConstraint(baseColumnNames: "sample_id", baseTableName: "control_sample", constraintName: "FK_n5xni9ruiej5e6p37qkxc0ha8", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sample", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-87") {
		addForeignKeyConstraint(baseColumnNames: "species_id", baseTableName: "genome", constraintName: "FK_8qyh94lw1ucpnn89d4dl4e5dr", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "species", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-88") {
		addForeignKeyConstraint(baseColumnNames: "species_id", baseTableName: "growth_media", constraintName: "FK_d67vskwe5phnwub9cqiokalkx", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "species", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-89") {
		addForeignKeyConstraint(baseColumnNames: "parent_id", baseTableName: "histology", constraintName: "FK_jrq8s9u4ujdq8durk95ilfebo", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "histology", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-90") {
		addForeignKeyConstraint(baseColumnNames: "project_id", baseTableName: "history", constraintName: "FK_fej8h317q2acuy144kav1oyxc", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "project", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-91") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "history", constraintName: "FK_fuutexvtx28fs971iq0kbfbmp", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-92") {
		addForeignKeyConstraint(baseColumnNames: "receiving_user_id", baseTableName: "inventory", constraintName: "FK_mnah8ybq73nyl126do8agmk08", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-93") {
		addForeignKeyConstraint(baseColumnNames: "parent_id", baseTableName: "item", constraintName: "FK_ccldfsomwnlcfqys42su71de3", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "item", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-94") {
		addForeignKeyConstraint(baseColumnNames: "type_id", baseTableName: "item", constraintName: "FK_qxnbu16tlqfmub9pgfj3h2e41", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "item_type", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-95") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "item", constraintName: "FK_br92r4wqm19mvpcyhxn5lg7m7", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-96") {
		addForeignKeyConstraint(baseColumnNames: "address_id", baseTableName: "organization", constraintName: "FK_l070gahmlj4g2sqbm72btw64e", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "address", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-97") {
		addForeignKeyConstraint(baseColumnNames: "billing_contact_id", baseTableName: "organization", constraintName: "FK_6w4r8gx2jdvy193esigbpxxx6", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-98") {
		addForeignKeyConstraint(baseColumnNames: "parent_id", baseTableName: "organization", constraintName: "FK_57byxcy430qbl2gl7liup0py1", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "organization", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-99") {
		addForeignKeyConstraint(baseColumnNames: "pi_id", baseTableName: "organization", constraintName: "FK_shncwjk67uss09ivcrpvlv8xg", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-100") {
		addForeignKeyConstraint(baseColumnNames: "pool_id", baseTableName: "pool_samples", constraintName: "FK_ek00bovrrt47tc8bpgn5g2gdg", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "item", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-101") {
		addForeignKeyConstraint(baseColumnNames: "sample_id", baseTableName: "pool_samples", constraintName: "FK_ad5h1h1ndrx123chi4kvxkijg", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sample", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-102") {
		addForeignKeyConstraint(baseColumnNames: "project_id", baseTableName: "project_samples", constraintName: "FK_bcuquxjs68mn5vyq7d0yphn8p", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "project", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-103") {
		addForeignKeyConstraint(baseColumnNames: "sample_id", baseTableName: "project_samples", constraintName: "FK_qfu2n2hwoukibpvvx51eftp9y", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sample", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-104") {
		addForeignKeyConstraint(baseColumnNames: "project_id", baseTableName: "project_user", constraintName: "FK_ptwhmsh2vocln8sffhyvr2ohm", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "project", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-105") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "project_user", constraintName: "FK_d6kfrxuqknbxrlxhwmn66a3kg", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-106") {
		addForeignKeyConstraint(baseColumnNames: "assay_id", baseTableName: "protocol", constraintName: "FK_f2u9282jbe9eiy0fggmrvrt16", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "assay", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-107") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "protocol", constraintName: "FK_mpt4h4wrqjngorh2706rvsqk2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-108") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "protocol_group", constraintName: "FK_mm7fvjt84dbnbsnsbe9ik36xh", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-109") {
		addForeignKeyConstraint(baseColumnNames: "protocol_group_id", baseTableName: "protocol_group_protocols", constraintName: "FK_6sjm1imt3f4b8rd492wm9vt7e", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "protocol_group", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-110") {
		addForeignKeyConstraint(baseColumnNames: "protocol_id", baseTableName: "protocol_group_protocols", constraintName: "FK_6i86p4hqvh803hqeam63pf0fo", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "protocol", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-111") {
		addForeignKeyConstraint(baseColumnNames: "bag_id", baseTableName: "protocol_instance", constraintName: "FK_j952d9eb9yjue4b2xggu8ehib", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "protocol_instance_bag", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-112") {
		addForeignKeyConstraint(baseColumnNames: "protocol_id", baseTableName: "protocol_instance", constraintName: "FK_d4v22o39k3xu8m216w8r1nyx1", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "protocol", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-113") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "protocol_instance", constraintName: "FK_sgj3vhj59cxyb10md7ybwmtm7", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-114") {
		addForeignKeyConstraint(baseColumnNames: "protocol_group_id", baseTableName: "protocol_instance_bag", constraintName: "FK_r1uxlmgfs4nieck8kad4wo79q", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "protocol_group", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-115") {
		addForeignKeyConstraint(baseColumnNames: "item_id", baseTableName: "protocol_instance_items", constraintName: "FK_2ehrfoqmnyg7wc3sv0owshi8l", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "item", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-116") {
		addForeignKeyConstraint(baseColumnNames: "protocol_instance_id", baseTableName: "protocol_instance_items", constraintName: "FK_dmt3slumwfqinb5qicjdvrilr", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "protocol_instance", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-117") {
		addForeignKeyConstraint(baseColumnNames: "protocol_id", baseTableName: "protocol_instance_summary", constraintName: "FK_5v0brjc04upo9ksqnq3bivxni", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "protocol", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-118") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "protocol_instance_summary", constraintName: "FK_8idyxguxexanjiixc15ylqewv", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-119") {
		addForeignKeyConstraint(baseColumnNames: "item_type_id", baseTableName: "protocol_item_types", constraintName: "FK_mi8fvx3awmigchnxb1s8keinu", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "item_type", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-120") {
		addForeignKeyConstraint(baseColumnNames: "protocol_id", baseTableName: "protocol_item_types", constraintName: "FK_p1p3edd8llir9a0qp24sj5cql", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "protocol", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-121") {
		addForeignKeyConstraint(baseColumnNames: "sample_id", baseTableName: "replicate_samples", constraintName: "FK_ced3uejqos7cnb2vaupk3qk1h", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sample", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-122") {
		addForeignKeyConstraint(baseColumnNames: "set_id", baseTableName: "replicate_samples", constraintName: "FK_b7fffn7tv5cj5s4eslmipmujb", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "replicate_set", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-123") {
		addForeignKeyConstraint(baseColumnNames: "project_id", baseTableName: "replicate_set", constraintName: "FK_qg0x2qhnutq0rw27iglu1o49u", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "project", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-124") {
		addForeignKeyConstraint(baseColumnNames: "alignment_id", baseTableName: "report_alignments", constraintName: "FK_invrh9of0ndhq9wetgqupqjuu", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sequence_alignment", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-125") {
		addForeignKeyConstraint(baseColumnNames: "report_id", baseTableName: "report_alignments", constraintName: "FK_jgmi242smigukk2otj453sld8", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "summary_report", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-126") {
		addForeignKeyConstraint(baseColumnNames: "antibody_id", baseTableName: "sample", constraintName: "FK_4lb993olybjsa6bqlbt90as3c", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "antibody", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-127") {
		addForeignKeyConstraint(baseColumnNames: "assay_id", baseTableName: "sample", constraintName: "FK_dhxymxy1936tgj756fntpg6xw", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "assay", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-128") {
		addForeignKeyConstraint(baseColumnNames: "audit_id", baseTableName: "sample", constraintName: "FK_qbpe56xpt5u1qewbly5ivorrx", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sample_audit", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-129") {
		addForeignKeyConstraint(baseColumnNames: "cell_source_id", baseTableName: "sample", constraintName: "FK_a1ro7uobsoyc87a51ssw7n5sw", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cell_source", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-130") {
		addForeignKeyConstraint(baseColumnNames: "growth_media_id", baseTableName: "sample", constraintName: "FK_ppsd5p8eqaveimasoymlfgcny", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "growth_media", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-131") {
		addForeignKeyConstraint(baseColumnNames: "invoice_id", baseTableName: "sample", constraintName: "FK_tqw4ka9uvmywhb4gw0ib7nxy9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "invoice", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-132") {
		addForeignKeyConstraint(baseColumnNames: "item_id", baseTableName: "sample", constraintName: "FK_9l7trxp5onscu1yk6fcvenjq6", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "item", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-133") {
		addForeignKeyConstraint(baseColumnNames: "prtcl_inst_summary_id", baseTableName: "sample", constraintName: "FK_bcdouyjpgn9hqcd947o1f28jq", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "protocol_instance_summary", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-134") {
		addForeignKeyConstraint(baseColumnNames: "send_data_to_id", baseTableName: "sample", constraintName: "FK_psm0kim6hte7fy0577fwsbdi", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-135") {
		addForeignKeyConstraint(baseColumnNames: "spike_in_cell_source_id", baseTableName: "sample", constraintName: "FK_iab90bxgihv46lqg7ib51kmwn", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cell_source", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-136") {
		addForeignKeyConstraint(baseColumnNames: "target_id", baseTableName: "sample", constraintName: "FK_cfxg067kqgsk55rjibh2pbnso", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "target", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-137") {
		addForeignKeyConstraint(baseColumnNames: "protocol_instance_bag_id", baseTableName: "sample_bags", constraintName: "FK_cnr1t3lrixla6a72js4vswgow", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "protocol_instance_bag", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-138") {
		addForeignKeyConstraint(baseColumnNames: "sample_id", baseTableName: "sample_bags", constraintName: "FK_m94py76hnab3k5yd83xstrenu", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sample", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-139") {
		addForeignKeyConstraint(baseColumnNames: "run_id", baseTableName: "sample_in_run", constraintName: "FK_993bhxmuyjlul88wourwo5eyw", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sequence_run", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-140") {
		addForeignKeyConstraint(baseColumnNames: "sample_id", baseTableName: "sample_in_run", constraintName: "FK_fsayjgiqhlt7cctq8n1escwvk", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sample", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-141") {
		addForeignKeyConstraint(baseColumnNames: "index_id", baseTableName: "sample_sequence_indices", constraintName: "FK_flne19kx2tprlhpbxh9a3xk8r", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sequence_index", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-142") {
		addForeignKeyConstraint(baseColumnNames: "sample_id", baseTableName: "sample_sequence_indices", constraintName: "FK_qh0akbm1a3893iwxbwqps9xmn", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sample", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-143") {
		addForeignKeyConstraint(baseColumnNames: "sample_id", baseTableName: "sample_treatments", constraintName: "FK_cldug6dnvxfcnd89wfrgg9e7i", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sample", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-144") {
		addForeignKeyConstraint(baseColumnNames: "treatment_id", baseTableName: "sample_treatments", constraintName: "FK_ehfppi0f0g2lojkytdeaij3lp", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cell_source_treatment", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-145") {
		addForeignKeyConstraint(baseColumnNames: "align_type_id", baseTableName: "sequence_alignment", constraintName: "FK_8gcis40hwme6s7ywn4jikodd2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "align_type", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-146") {
		addForeignKeyConstraint(baseColumnNames: "aligner_id", baseTableName: "sequence_alignment", constraintName: "FK_fee47iwmejna7h55jp8dpkowd", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "aligner", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-147") {
		addForeignKeyConstraint(baseColumnNames: "genome_id", baseTableName: "sequence_alignment", constraintName: "FK_5tdpednm424bwlyaygxb91cj8", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "genome", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-148") {
		addForeignKeyConstraint(baseColumnNames: "sequencing_experiment_id", baseTableName: "sequence_alignment", constraintName: "FK_js0whth80fyna834brwbbo8v0", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sequencing_experiment", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-149") {
		addForeignKeyConstraint(baseColumnNames: "platform_id", baseTableName: "sequence_run", constraintName: "FK_l1ofj1csp7k439e52l32soay9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sequencing_platform", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-150") {
		addForeignKeyConstraint(baseColumnNames: "pool_item_id", baseTableName: "sequence_run", constraintName: "FK_nb6cvqg7y8ruphnbfber6qjsb", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "item", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-151") {
		addForeignKeyConstraint(baseColumnNames: "run_stats_id", baseTableName: "sequence_run", constraintName: "FK_5cc4mivg8mvxoipvbrl09geqj", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "run_stats", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-152") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "sequence_run", constraintName: "FK_gerfjuiu4n7m0iukeud2wrux7", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-153") {
		addForeignKeyConstraint(baseColumnNames: "read_type_id", baseTableName: "sequencing_experiment", constraintName: "FK_3s9e144d345uyhay28toy8jwg", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "read_type", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-154") {
		addForeignKeyConstraint(baseColumnNames: "sample_id", baseTableName: "sequencing_experiment", constraintName: "FK_7gsumwrx4h3nji2g2q5htn098", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sample", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-155") {
		addForeignKeyConstraint(baseColumnNames: "sequence_run_id", baseTableName: "sequencing_experiment", constraintName: "FK_3mx03u80x4ynhsx1i65yrrxcq", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sequence_run", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-156") {
		addForeignKeyConstraint(baseColumnNames: "parent_id", baseTableName: "strain", constraintName: "FK_4shdjcf8qcywolsnrmi3djhmu", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "strain", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-157") {
		addForeignKeyConstraint(baseColumnNames: "source_lab_id", baseTableName: "strain", constraintName: "FK_bd07vlra9sn9vs8po73rw89wg", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "organization", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-158") {
		addForeignKeyConstraint(baseColumnNames: "species_id", baseTableName: "strain", constraintName: "FK_c5gb45n5okxh7bqig1nouw76t", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "species", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-159") {
		addForeignKeyConstraint(baseColumnNames: "project_id", baseTableName: "summary_report", constraintName: "FK_3avk8v4ooy5p6pgj95viim6uy", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "project", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-160") {
		addForeignKeyConstraint(baseColumnNames: "run_id", baseTableName: "summary_report", constraintName: "FK_bu7egp9o5u8ktnkkplehxxsjf", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sequence_run", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-161") {
		addForeignKeyConstraint(baseColumnNames: "target_type_id", baseTableName: "target", constraintName: "FK_e5wo7j1f9so6mcll9vwtn6005", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "target_type", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-162") {
		addForeignKeyConstraint(baseColumnNames: "address_id", baseTableName: "user", constraintName: "FK_dhlcfg8h1drrgu0irs1ro3ohb", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "address", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-163") {
		addForeignKeyConstraint(baseColumnNames: "affiliation_id", baseTableName: "user", constraintName: "FK_qdusuaq6oge31t7nlq10wm6ku", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "organization", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-164") {
		addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "user_role", constraintName: "FK_it77eq964jhfqtu54081ebtio", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "role", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1470678902832-165") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_role", constraintName: "FK_apcc8lxk2xnug8377fatvbn04", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	include file: 'addHistoryIdToAlignment.groovy'

	include file: 'removeHistoryIdFromAnalysis.groovy'

	include file: 'changeInstanceItem.groovy'

	include file: 'changeChores.groovy'

	include file: 'changeGenome.groovy'

	include file: 'addCohort.groovy'

	include file: 'updateItem.groovy'

	include file: 'addProjectBag.groovy'

	include file: 'addProjectItem.groovy'

	include file: 'addStep.groovy'

	include file: 'addCohortImages.groovy'

	include file: 'addCellSourceStatus.groovy'

	include file: 'addCohortNotes.groovy'

	include file: 'addGroupRole.groovy'

	include file: 'addLaneStats.groovy'

	include file: 'addSampleNaturalId.groovy'

	include file: 'addCellSourceBatch.groovy'

	include file: 'addItemStatus.groovy'

	include file: 'removeCohortUnique.groovy'

	include file: 'addProtocolImages.groovy'

	include file: 'addBatchNotes.groovy'

	include file: 'batchNotesNullable.groovy'

	include file: 'addSampleRecommend.groovy'
    include file: 'historyAndWorkflowUrl.groovy'
    include file: 'updateProtocolGroupProtocols.groovy'
}
