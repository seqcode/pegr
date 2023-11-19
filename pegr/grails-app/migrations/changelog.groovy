databaseChangeLog = {

    changeSet(author: "danyingshao (generated)", id: "1580764544352-1") {
        createTable(tableName: "ab_host") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "ab_hostPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "status", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-2") {
        createTable(tableName: "address") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "addressPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "line2", type: "VARCHAR(255)")

            column(name: "postal_code", type: "VARCHAR(10)") {
                constraints(nullable: "false")
            }

            column(name: "line1", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "city", type: "VARCHAR(50)") {
                constraints(nullable: "false")
            }

            column(name: "country", type: "VARCHAR(50)") {
                constraints(nullable: "false")
            }

            column(name: "state", type: "VARCHAR(50)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-3") {
        createTable(tableName: "align_type") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "align_typePK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "short_name", type: "VARCHAR(20)")

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-4") {
        createTable(tableName: "aligner") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "alignerPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "software", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "aligner_version", type: "VARCHAR(10)")
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-5") {
        createTable(tableName: "analysis") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "analysisPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "alignment_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "date", type: "datetime")

            column(name: "datasets", type: "CLOB")

            column(name: "tool", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "step_id", type: "VARCHAR(255)")

            column(name: "parameters", type: "CLOB")

            column(name: "user_id", type: "BIGINT")

            column(name: "category", type: "VARCHAR(255)")

            column(name: "note", type: "VARCHAR(255)")

            column(name: "statistics", type: "CLOB")

            column(name: "step", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-6") {
        createTable(tableName: "antibody") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "antibodyPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "concentration", type: "FLOAT")

            column(name: "catalog_number", type: "VARCHAR(255)")

            column(name: "immunogene", type: "VARCHAR(255)")

            column(name: "lot_number", type: "VARCHAR(255)")

            column(name: "ig_type_id", type: "BIGINT")

            column(name: "default_target_id", type: "BIGINT")

            column(name: "item_id", type: "BIGINT")

            column(name: "clonal", type: "VARCHAR(255)")

            column(name: "inventory_id", type: "VARCHAR(255)")

            column(name: "company_id", type: "BIGINT")

            column(name: "ab_host_id", type: "BIGINT")

            column(name: "note", type: "VARCHAR(255)")

            column(name: "external_id", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-7") {
        createTable(tableName: "assay") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "assayPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-8") {
        createTable(tableName: "batch_cell_sources") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "batch_cell_sourcesPK")
            }

            column(name: "cell_source_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "batch_id", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-9") {
        createTable(tableName: "cell_source") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "cell_sourcePK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "sex_id", type: "BIGINT")

            column(name: "prep_user_id", type: "BIGINT")

            column(name: "tissue_id", type: "BIGINT")

            column(name: "item_id", type: "BIGINT")

            column(name: "histology_id", type: "BIGINT")

            column(name: "provider_user_id", type: "BIGINT")

            column(name: "age", type: "VARCHAR(255)")

            column(name: "biological_source_id", type: "VARCHAR(255)")

            column(name: "inventory_id", type: "BIGINT")

            column(name: "status", type: "VARCHAR(255)")

            column(name: "note", type: "VARCHAR(255)")

            column(name: "strain_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "provider_lab_id", type: "BIGINT")
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-10") {
        createTable(tableName: "cell_source_batch") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "cell_source_batchPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "date", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "user_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "notes", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-11") {
        createTable(tableName: "cell_source_treatment") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "cell_source_treatmentPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "status", type: "VARCHAR(255)")

            column(name: "note", type: "CLOB")
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-12") {
        createTable(tableName: "chores") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "choresPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "value", type: "CLOB")
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-13") {
        createTable(tableName: "chrom_sequence") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "chrom_sequencePK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "sequence", type: "CLOB") {
                constraints(nullable: "false")
            }

            column(name: "chromosome_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "length", type: "INT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-14") {
        createTable(tableName: "chromosome") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "chromosomePK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "genome_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "note", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-15") {
        createTable(tableName: "control_sample") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "control_samplePK")
            }

            column(name: "sample_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "control_sample_id", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-16") {
        createTable(tableName: "definition") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "definitionPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "content", type: "VARCHAR(5000)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-17") {
        createTable(tableName: "funding") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "fundingPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "number", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-18") {
        createTable(tableName: "genome") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "genomePK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "url", type: "VARCHAR(255)")

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "species_id", type: "BIGINT")

            column(name: "status", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-19") {
        createTable(tableName: "growth_media") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "growth_mediaPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "species_id", type: "BIGINT")

            column(name: "status", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-20") {
        createTable(tableName: "histology") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "histologyPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "status", type: "VARCHAR(255)")

            column(name: "parent_id", type: "BIGINT")
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-21") {
        createTable(tableName: "history") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "historyPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "object_id", type: "INT") {
                constraints(nullable: "false")
            }

            column(name: "object_type", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "user_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "action", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "notes", type: "VARCHAR(255)")

            column(name: "project_id", type: "BIGINT")
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-22") {
        createTable(tableName: "ig_type") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "ig_typePK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "status", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-23") {
        createTable(tableName: "inventory") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "inventoryPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "receiving_user_id", type: "BIGINT")

            column(name: "location", type: "VARCHAR(255)")

            column(name: "notes", type: "VARCHAR(255)")

            column(name: "date_received", type: "datetime")

            column(name: "source_type", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-24") {
        createTable(tableName: "invoice") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "invoicePK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "date", type: "datetime")

            column(name: "invoice_num", type: "VARCHAR(255)")

            column(name: "service_id", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-25") {
        createTable(tableName: "item") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "itemPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "last_updated", type: "datetime")

            column(name: "location", type: "VARCHAR(255)")

            column(name: "notes", type: "VARCHAR(255)")

            column(name: "customized_fields", type: "CLOB")

            column(name: "barcode", type: "VARCHAR(255)")

            column(name: "name", type: "VARCHAR(255)")

            column(name: "user_id", type: "BIGINT")

            column(name: "type_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(defaultValue: "GOOD", name: "status", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "project_id", type: "BIGINT")

            column(name: "parent_id", type: "BIGINT")
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-26") {
        createTable(tableName: "item_antibody") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "item_antibodyPK")
            }

            column(name: "item_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "antibody_id", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-27") {
        createTable(tableName: "item_sequence_indices") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "item_sequence_indicesPK")
            }

            column(name: "item_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "index_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "set_id", type: "INT")

            column(name: "index_in_set", type: "INT")
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-28") {
        createTable(tableName: "item_type") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "item_typePK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "category_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "fields", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-29") {
        createTable(tableName: "item_type_category") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "item_type_categoryPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "super_category", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-30") {
        createTable(tableName: "organization") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "organizationPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "address_id", type: "BIGINT")

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "status", type: "VARCHAR(255)")

            column(name: "note", type: "VARCHAR(255)")

            column(name: "parent_id", type: "BIGINT")

            column(name: "website", type: "VARCHAR(255)")

            column(name: "class", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "pi_id", type: "BIGINT")

            column(name: "billing_contact_id", type: "BIGINT")
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-31") {
        createTable(tableName: "pipeline") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "pipelinePK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(defaultValue: "N/A", name: "workflow_id", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(defaultValue: "0.0.0", name: "pipeline_version", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "workflow_url", type: "VARCHAR(255)")

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "steps", type: "CLOB") {
                constraints(nullable: "false")
            }

            column(name: "note", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-32") {
        createTable(tableName: "pool_samples") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "pool_samplesPK")
            }

            column(name: "sample_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "pool_id", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-33") {
        createTable(tableName: "project") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "projectPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "last_updated", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "notes", type: "CLOB")

            column(name: "description", type: "VARCHAR(1000)")
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-34") {
        createTable(tableName: "project_bags") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "project_bagsPK")
            }

            column(name: "project_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "bag_id", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-35") {
        createTable(tableName: "project_funding") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "project_fundingPK")
            }

            column(name: "funding_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "project_id", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-36") {
        createTable(tableName: "project_samples") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "project_samplesPK")
            }

            column(name: "sample_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "project_id", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-37") {
        createTable(tableName: "project_user") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "project_userPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "project_role", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "user_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "project_id", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-38") {
        createTable(tableName: "protocol") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "protocolPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "short_name", type: "VARCHAR(255)")

            column(name: "file", type: "VARCHAR(255)")

            column(name: "assay_id", type: "BIGINT")

            column(name: "protocol_version", type: "VARCHAR(10)")

            column(name: "images", type: "VARCHAR(255)")

            column(name: "add_antibody", type: "BOOLEAN")

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "user_id", type: "BIGINT")

            column(name: "add_index", type: "BOOLEAN")

            column(name: "status", type: "VARCHAR(255)")

            column(name: "description", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-39") {
        createTable(tableName: "protocol_group") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "protocol_groupPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "user_id", type: "BIGINT")
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-40") {
        createTable(tableName: "protocol_group_protocols") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "protocol_group_protocolsPK")
            }

            column(name: "protocol_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "protocols_idx", type: "INT") {
                constraints(nullable: "false")
            }

            column(name: "protocol_group_id", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-41") {
        createTable(tableName: "protocol_instance") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "protocol_instancePK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "end_time", type: "datetime")

            column(name: "images", type: "VARCHAR(255)")

            column(name: "bag_id", type: "BIGINT")

            column(name: "protocol_id", type: "BIGINT")

            column(name: "bag_idx", type: "INT") {
                constraints(nullable: "false")
            }

            column(name: "start_time", type: "datetime")

            column(name: "user_id", type: "BIGINT")

            column(name: "status", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "note", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-42") {
        createTable(tableName: "protocol_instance_bag") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "protocol_instance_bagPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "end_time", type: "datetime")

            column(name: "protocol_group_id", type: "BIGINT")

            column(name: "start_time", type: "datetime")

            column(name: "name", type: "VARCHAR(255)")

            column(name: "status", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-43") {
        createTable(tableName: "protocol_instance_items") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "protocol_instance_itemsPK")
            }

            column(name: "item_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "protocol_instance_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "function", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-44") {
        createTable(tableName: "protocol_instance_summary") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "protocol_instance_summaryPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "protocol_id", type: "BIGINT")

            column(name: "end_time", type: "datetime")

            column(name: "start_time", type: "datetime")

            column(name: "user_id", type: "BIGINT")

            column(name: "note", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-45") {
        createTable(tableName: "protocol_item_types") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "protocol_item_typesPK")
            }

            column(name: "protocol_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "function", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "item_type_id", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-46") {
        createTable(tableName: "read_type") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "read_typePK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "short_name", type: "VARCHAR(10)") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "note", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-47") {
        createTable(tableName: "replicate_samples") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "replicate_samplesPK")
            }

            column(name: "sample_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "set_id", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-48") {
        createTable(tableName: "replicate_set") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "replicate_setPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "type", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "project_id", type: "BIGINT")
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-49") {
        createTable(tableName: "report_alignments") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "report_alignmentsPK")
            }

            column(name: "alignment_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "report_id", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-50") {
        createTable(tableName: "role") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "rolePK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "authority", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-51") {
        createTable(tableName: "role_group") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "role_groupPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-52") {
        createTable(tableName: "role_group_role") {
            column(name: "role_group_id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "role_group_rolePK")
            }

            column(name: "role_id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "role_group_rolePK")
            }
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-53") {
        createTable(tableName: "run_stats") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "run_statsPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "phixloaded", type: "FLOAT")

            column(name: "q_pcr_conc", type: "FLOAT")

            column(name: "sr_or_pe", type: "VARCHAR(255)")

            column(name: "read_pf", type: "FLOAT")

            column(name: "library_volume", type: "FLOAT")

            column(name: "pct_aligned_to_phix", type: "FLOAT")

            column(name: "q_pcr_date", type: "datetime")

            column(name: "notes", type: "VARCHAR(255)")

            column(name: "qubit_conc", type: "FLOAT")

            column(name: "qidx", type: "FLOAT")

            column(name: "cluster_num", type: "FLOAT")

            column(name: "library_stock", type: "FLOAT")

            column(name: "seq_ctrl", type: "VARCHAR(255)")

            column(name: "pct_pf", type: "FLOAT")

            column(name: "library_pool_archive_id", type: "VARCHAR(255)")

            column(name: "library_loaded_fmol", type: "FLOAT")

            column(name: "library_loaded_pm", type: "FLOAT")

            column(name: "pcr_cycles", type: "INT")

            column(name: "unmatched_indices", type: "FLOAT")

            column(name: "total_reads", type: "FLOAT")

            column(name: "pct_library_std_dev", type: "FLOAT")

            column(name: "cycles", type: "VARCHAR(255)")

            column(name: "library_std_dev", type: "FLOAT")

            column(name: "pctq30", type: "FLOAT")

            column(name: "technician_id", type: "BIGINT")

            column(name: "pct_unmatched_indices", type: "FLOAT")
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-54") {
        createTable(tableName: "sample") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "samplePK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "date", type: "datetime")

            column(name: "requested_genomes", type: "VARCHAR(255)")

            column(name: "assay_id", type: "BIGINT")

            column(name: "publication_reference", type: "VARCHAR(255)")

            column(name: "natural_id", type: "VARCHAR(255)")

            column(name: "antibody_notes", type: "VARCHAR(255)")

            column(name: "source", type: "VARCHAR(255)")

            column(name: "antibody_id", type: "BIGINT")

            column(name: "item_id", type: "BIGINT")

            column(name: "source_id", type: "VARCHAR(255)")

            column(name: "volume", type: "DOUBLE precision")

            column(name: "cell_source_id", type: "BIGINT")

            column(name: "prtcl_inst_summary_id", type: "BIGINT")

            column(name: "target_id", type: "BIGINT")

            column(name: "send_data_to_id", type: "BIGINT")

            column(name: "growth_media_id", type: "BIGINT")

            column(name: "chromosome_amount", type: "DOUBLE precision")

            column(name: "requested_tag_number", type: "DOUBLE precision")

            column(name: "cell_number", type: "DOUBLE precision")

            column(name: "spike_in_cell_source_id", type: "BIGINT")

            column(name: "status", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "note", type: "CLOB")

            column(name: "invoice_id", type: "BIGINT")

            column(name: "recommend", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-55") {
        createTable(tableName: "sample_in_run") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "sample_in_runPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "pool_date", type: "datetime")

            column(name: "sample_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "volume_to_pool", type: "FLOAT")

            column(name: "pool", type: "VARCHAR(255)")

            column(name: "params", type: "VARCHAR(255)")

            column(name: "run_id", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-56") {
        createTable(tableName: "sample_sequence_indices") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "sample_sequence_indicesPK")
            }

            column(name: "index_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "sample_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "set_id", type: "INT")

            column(name: "index_in_set", type: "INT")
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-57") {
        createTable(tableName: "sample_treatments") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "sample_treatmentsPK")
            }

            column(name: "sample_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "treatment_id", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-58") {
        createTable(tableName: "sequence_alignment") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "sequence_alignmentPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "read_db_id", type: "INT")

            column(name: "genome_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "date", type: "datetime")

            column(name: "dedup_uniquely_mapped_reads", type: "BIGINT")

            column(name: "pipeline_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "genome_coverage", type: "FLOAT")

            column(name: "sequencing_experiment_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "align_type_id", type: "BIGINT")

            column(name: "notes", type: "VARCHAR(255)")

            column(name: "aligner_id", type: "BIGINT")

            column(name: "bam_file", type: "VARCHAR(1000)")

            column(name: "std_dev_insert_size", type: "FLOAT")

            column(name: "mapped_reads", type: "BIGINT")

            column(defaultValueBoolean: "true", name: "is_preferred", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "avg_insert_size", type: "FLOAT")

            column(name: "history_url", type: "VARCHAR(255)")

            column(name: "pe_histogram", type: "VARCHAR(1000)")

            column(name: "seq_duplication_level", type: "FLOAT")

            column(name: "params", type: "VARCHAR(2000)")

            column(name: "history_id", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "uniquely_mapped_reads", type: "BIGINT")
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-59") {
        createTable(tableName: "sequence_index") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "sequence_indexPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "index_id", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "sequence", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "index_version", type: "VARCHAR(10)") {
                constraints(nullable: "false")
            }

            column(name: "oligo", type: "VARCHAR(255)")

            column(name: "status", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-60") {
        createTable(tableName: "sequence_run") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "sequence_runPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "run_num_alias", type: "VARCHAR(255)")

            column(name: "run_num", type: "INT") {
                constraints(nullable: "false")
            }

            column(name: "date", type: "datetime")

            column(name: "pool_item_id", type: "BIGINT")

            column(name: "lane", type: "INT")

            column(name: "run_stats_id", type: "BIGINT")

            column(name: "platform_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "directory_name", type: "VARCHAR(255)")

            column(name: "fc_id", type: "VARCHAR(255)")

            column(name: "user_id", type: "BIGINT")

            column(name: "status", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "note", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-61") {
        createTable(tableName: "sequencing_cohort") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "sequencing_cohortPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "VARCHAR(255)")

            column(name: "images", type: "CLOB")

            column(name: "notes", type: "CLOB")

            column(name: "report_id", type: "BIGINT")

            column(name: "run_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "project_id", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-62") {
        createTable(tableName: "sequencing_experiment") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "sequencing_experimentPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "fastqc_report", type: "VARCHAR(1000)")

            column(name: "fastq_file", type: "VARCHAR(1000)")

            column(name: "index_mismatch", type: "INT")

            column(name: "read_type_id", type: "BIGINT")

            column(name: "public_db_id", type: "VARCHAR(255)")

            column(name: "adapter_dimer_count", type: "BIGINT")

            column(name: "sample_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "sequence_run_id", type: "BIGINT")

            column(name: "cohort_id", type: "BIGINT")

            column(name: "total_reads", type: "BIGINT")

            column(name: "note", type: "VARCHAR(255)")

            column(name: "read_positions", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-63") {
        createTable(tableName: "sequencing_platform") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "sequencing_platformPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-64") {
        createTable(tableName: "sex") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "sexPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "status", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-65") {
        createTable(tableName: "species") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "speciesPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "status", type: "VARCHAR(255)")

            column(name: "note", type: "VARCHAR(255)")

            column(name: "genus_name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-66") {
        createTable(tableName: "strain") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "strainPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "genetic_modification", type: "VARCHAR(255)")

            column(name: "source_lab_id", type: "BIGINT")

            column(name: "name", type: "VARCHAR(255)")

            column(name: "genotype", type: "VARCHAR(255)")

            column(name: "species_id", type: "BIGINT")

            column(name: "status", type: "VARCHAR(255)")

            column(name: "note", type: "VARCHAR(255)")

            column(name: "parent_id", type: "BIGINT")
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-67") {
        createTable(tableName: "summary_report") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "summary_reportPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "date", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "type", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "status", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "note", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-68") {
        createTable(tableName: "target") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "targetPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "c_term_tag", type: "VARCHAR(255)")

            column(name: "name", type: "VARCHAR(255)")

            column(name: "target_type_id", type: "BIGINT")

            column(name: "status", type: "VARCHAR(255)")

            column(name: "note", type: "VARCHAR(255)")

            column(name: "n_term_tag", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-69") {
        createTable(tableName: "target_type") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "target_typePK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "description", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-70") {
        createTable(tableName: "tissue") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "tissuePK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "status", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-71") {
        createTable(tableName: "token") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "tokenPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "date", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "user_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "token", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-72") {
        createTable(tableName: "user") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "userPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "phone", type: "VARCHAR(20)")

            column(name: "password_expired", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "account_expired", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "full_name", type: "VARCHAR(255)")

            column(name: "address_id", type: "BIGINT")

            column(name: "username", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "account_locked", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "password", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "api_key", type: "VARCHAR(255)")

            column(name: "enabled", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "affiliation_id", type: "BIGINT")

            column(name: "email", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-73") {
        createTable(tableName: "user_role") {
            column(name: "user_id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "user_rolePK")
            }

            column(name: "role_id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "user_rolePK")
            }
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-74") {
        createTable(tableName: "user_role_group") {
            column(name: "role_group_id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "user_role_groupPK")
            }

            column(name: "user_id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "user_role_groupPK")
            }
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-75") {
        addUniqueConstraint(columnNames: "name", constraintName: "UC_AB_HOSTNAME_COL", tableName: "ab_host")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-76") {
        addUniqueConstraint(columnNames: "name", constraintName: "UC_ALIGN_TYPENAME_COL", tableName: "align_type")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-77") {
        addUniqueConstraint(columnNames: "short_name", constraintName: "UC_ALIGN_TYPESHORT_NAME_COL", tableName: "align_type")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-78") {
        addUniqueConstraint(columnNames: "name", constraintName: "UC_ASSAYNAME_COL", tableName: "assay")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-79") {
        addUniqueConstraint(columnNames: "name", constraintName: "UC_CELL_SOURCE_TREATMENTNAME_COL", tableName: "cell_source_treatment")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-80") {
        addUniqueConstraint(columnNames: "name", constraintName: "UC_CHORESNAME_COL", tableName: "chores")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-81") {
        addUniqueConstraint(columnNames: "name", constraintName: "UC_CHROMOSOMENAME_COL", tableName: "chromosome")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-82") {
        addUniqueConstraint(columnNames: "name", constraintName: "UC_DEFINITIONNAME_COL", tableName: "definition")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-83") {
        addUniqueConstraint(columnNames: "name", constraintName: "UC_GROWTH_MEDIANAME_COL", tableName: "growth_media")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-84") {
        addUniqueConstraint(columnNames: "name", constraintName: "UC_HISTOLOGYNAME_COL", tableName: "histology")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-85") {
        addUniqueConstraint(columnNames: "name", constraintName: "UC_IG_TYPENAME_COL", tableName: "ig_type")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-86") {
        addUniqueConstraint(columnNames: "barcode", constraintName: "UC_ITEMBARCODE_COL", tableName: "item")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-87") {
        addUniqueConstraint(columnNames: "name", constraintName: "UC_ITEM_TYPENAME_COL", tableName: "item_type")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-88") {
        addUniqueConstraint(columnNames: "name", constraintName: "UC_ITEM_TYPE_CATEGORYNAME_COL", tableName: "item_type_category")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-89") {
        addUniqueConstraint(columnNames: "name", constraintName: "UC_ORGANIZATIONNAME_COL", tableName: "organization")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-90") {
        addUniqueConstraint(columnNames: "workflow_id", constraintName: "UC_PIPELINEWORKFLOW_ID_COL", tableName: "pipeline")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-91") {
        addUniqueConstraint(columnNames: "name", constraintName: "UC_PROJECTNAME_COL", tableName: "project")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-92") {
        addUniqueConstraint(columnNames: "name", constraintName: "UC_PROTOCOL_GROUPNAME_COL", tableName: "protocol_group")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-93") {
        addUniqueConstraint(columnNames: "name", constraintName: "UC_READ_TYPENAME_COL", tableName: "read_type")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-94") {
        addUniqueConstraint(columnNames: "short_name", constraintName: "UC_READ_TYPESHORT_NAME_COL", tableName: "read_type")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-95") {
        addUniqueConstraint(columnNames: "authority", constraintName: "UC_ROLEAUTHORITY_COL", tableName: "role")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-96") {
        addUniqueConstraint(columnNames: "name", constraintName: "UC_ROLE_GROUPNAME_COL", tableName: "role_group")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-97") {
        addUniqueConstraint(columnNames: "name", constraintName: "UC_SEQUENCING_PLATFORMNAME_COL", tableName: "sequencing_platform")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-98") {
        addUniqueConstraint(columnNames: "name", constraintName: "UC_SEXNAME_COL", tableName: "sex")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-99") {
        addUniqueConstraint(columnNames: "name", constraintName: "UC_TARGET_TYPENAME_COL", tableName: "target_type")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-100") {
        addUniqueConstraint(columnNames: "name", constraintName: "UC_TISSUENAME_COL", tableName: "tissue")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-101") {
        addUniqueConstraint(columnNames: "username", constraintName: "UC_USERUSERNAME_COL", tableName: "user")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-102") {
        addUniqueConstraint(columnNames: "funding_id, project_id", constraintName: "UK16c0fb5f244d0452a2e49aed563f", tableName: "project_funding")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-103") {
        addUniqueConstraint(columnNames: "control_sample_id, sample_id", constraintName: "UK1a92d74db3df102928b5f605132a", tableName: "control_sample")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-104") {
        addUniqueConstraint(columnNames: "genus_name, name", constraintName: "UK3932af47b0357d2b4c5a261822ee", tableName: "species")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-105") {
        addUniqueConstraint(columnNames: "protocols_idx, protocol_group_id, protocol_id", constraintName: "UK413d0a510ff03d1f19c83af9ae36", tableName: "protocol_group_protocols")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-106") {
        addUniqueConstraint(columnNames: "treatment_id, sample_id", constraintName: "UK4b14a8d38495c6eec56d6123f6f5", tableName: "sample_treatments")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-107") {
        addUniqueConstraint(columnNames: "sample_id, set_id", constraintName: "UK68b1ef05059e7f9742c5997e6f74", tableName: "replicate_samples")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-108") {
        addUniqueConstraint(columnNames: "item_type_id, protocol_id", constraintName: "UK69c2d8246f1bc2e5e8d02ada96db", tableName: "protocol_item_types")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-109") {
        addUniqueConstraint(columnNames: "protocol_version, name", constraintName: "UK8d22b11cbab7be2f38b4c8c2dd1d", tableName: "protocol")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-110") {
        addUniqueConstraint(columnNames: "run_id, sample_id", constraintName: "UK8f7674b6c279c21eb5c882b40655", tableName: "sample_in_run")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-111") {
        addUniqueConstraint(columnNames: "bag_id, project_id", constraintName: "UKb4d526519821b65515d64f1ac54b", tableName: "project_bags")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-112") {
        addUniqueConstraint(columnNames: "aligner_version, software", constraintName: "UKb8ab847080355581b293e4a87bcb", tableName: "aligner")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-113") {
        addUniqueConstraint(columnNames: "pool_id, sample_id", constraintName: "UKba7773a538d4770149b421f84583", tableName: "pool_samples")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-114") {
        addUniqueConstraint(columnNames: "c_term_tag, n_term_tag, name", constraintName: "UKc01a900836fd7e6df0dbdc887877", tableName: "target")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-115") {
        addUniqueConstraint(columnNames: "name", constraintName: "UKc9cafa65fdeb3f03632764f330f8", tableName: "funding")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-116") {
        addUniqueConstraint(columnNames: "alignment_id, report_id", constraintName: "UKdc9d49ed85d397cd09bb505033ce", tableName: "report_alignments")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-117") {
        addUniqueConstraint(columnNames: "antibody_id, item_id", constraintName: "UKeac55f11282de8304404741a9798", tableName: "item_antibody")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-118") {
        addUniqueConstraint(columnNames: "pipeline_id, genome_id, sequencing_experiment_id, history_id", constraintName: "UKef3609f74c61b7bd7d0ec184c0b0", tableName: "sequence_alignment")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-119") {
        addUniqueConstraint(columnNames: "pipeline_version, name", constraintName: "UKf01a4847e4bdb6a7b8198123f4ae", tableName: "pipeline")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-120") {
        addUniqueConstraint(columnNames: "sample_id, project_id", constraintName: "UKf38f5325c5da49e7b9b5d51d5226", tableName: "project_samples")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-121") {
        addUniqueConstraint(columnNames: "user_id, project_id", constraintName: "UKfbf64ee15963f7e643e3a681061d", tableName: "project_user")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-122") {
        addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "cell_source_batch", constraintName: "FK14vul503srgn35l05e3v1yt51", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-123") {
        addForeignKeyConstraint(baseColumnNames: "sample_id", baseTableName: "pool_samples", constraintName: "FK1elc3dx8od4b22mfnj7ems7oa", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sample", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-124") {
        addForeignKeyConstraint(baseColumnNames: "protocol_group_id", baseTableName: "protocol_group_protocols", constraintName: "FK1iuch12ayvha1jsq3r49e8e2j", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "protocol_group", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-125") {
        addForeignKeyConstraint(baseColumnNames: "item_id", baseTableName: "sample", constraintName: "FK1rdptfp7u4obmb8eikuw5nnrf", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "item", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-126") {
        addForeignKeyConstraint(baseColumnNames: "species_id", baseTableName: "genome", constraintName: "FK1v0f049a0ljh0ohpc585xwb10", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "species", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-127") {
        addForeignKeyConstraint(baseColumnNames: "sequence_run_id", baseTableName: "sequencing_experiment", constraintName: "FK2a04ld6c4th452p48yqrqdhlq", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sequence_run", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-128") {
        addForeignKeyConstraint(baseColumnNames: "antibody_id", baseTableName: "sample", constraintName: "FK2hvogurfkgktbxxvdo2csoa8v", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "antibody", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-129") {
        addForeignKeyConstraint(baseColumnNames: "project_id", baseTableName: "sequencing_cohort", constraintName: "FK2kjc8ji0k7cxldfqvqqmr6qpq", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "project", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-130") {
        addForeignKeyConstraint(baseColumnNames: "parent_id", baseTableName: "strain", constraintName: "FK2ouetcuudlbctcmfse7fuuuo8", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "strain", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-131") {
        addForeignKeyConstraint(baseColumnNames: "assay_id", baseTableName: "protocol", constraintName: "FK2ugvxqbj45ie8pfijctcp5m44", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "assay", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-132") {
        addForeignKeyConstraint(baseColumnNames: "default_target_id", baseTableName: "antibody", constraintName: "FK2wi5vsoyxjfxl0mc5v1mm9dts", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "target", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-133") {
        addForeignKeyConstraint(baseColumnNames: "project_id", baseTableName: "project_bags", constraintName: "FK2xobskjd8bp5b128rh1duijt0", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "project", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-134") {
        addForeignKeyConstraint(baseColumnNames: "alignment_id", baseTableName: "report_alignments", constraintName: "FK33tihrorftk27epehcm1be8ci", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sequence_alignment", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-135") {
        addForeignKeyConstraint(baseColumnNames: "ig_type_id", baseTableName: "antibody", constraintName: "FK38l2x1xyohqre04awha89rr15", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "ig_type", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-136") {
        addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "protocol_instance", constraintName: "FK3kt03fn9v9icvw7iam3ri2q2e", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-137") {
        addForeignKeyConstraint(baseColumnNames: "funding_id", baseTableName: "project_funding", constraintName: "FK3sab04b4896qhtxtm9kmwb6p3", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "funding", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-138") {
        addForeignKeyConstraint(baseColumnNames: "provider_lab_id", baseTableName: "cell_source", constraintName: "FK49xxhx356gij34jl4uxnwknyw", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "organization", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-139") {
        addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "project_user", constraintName: "FK4jl2o131jivd80xsuw6pivnbx", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-140") {
        addForeignKeyConstraint(baseColumnNames: "role_group_id", baseTableName: "user_role_group", constraintName: "FK4u9p3asv1hflckgxegcafajkm", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "role_group", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-141") {
        addForeignKeyConstraint(baseColumnNames: "project_id", baseTableName: "project_user", constraintName: "FK4ug72llnm0n7yafwntgdswl3y", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "project", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-142") {
        addForeignKeyConstraint(baseColumnNames: "sample_id", baseTableName: "sample_in_run", constraintName: "FK50pk7cu1ix56x21n43qpmc9rs", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sample", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-143") {
        addForeignKeyConstraint(baseColumnNames: "item_id", baseTableName: "protocol_instance_items", constraintName: "FK521y62ybe1gxfnde38pvohr14", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "item", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-144") {
        addForeignKeyConstraint(baseColumnNames: "cell_source_id", baseTableName: "sample", constraintName: "FK579rlyfhja2i87deo6dgndfnj", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cell_source", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-145") {
        addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "sequence_run", constraintName: "FK5gbjvf2tscq7vyhwbadq2jbtx", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-146") {
        addForeignKeyConstraint(baseColumnNames: "sample_id", baseTableName: "control_sample", constraintName: "FK5luc1hgx7j675npfvs5k3jngq", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sample", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-147") {
        addForeignKeyConstraint(baseColumnNames: "species_id", baseTableName: "strain", constraintName: "FK6ikjjjvxicw58055xdk37tfj0", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "species", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-148") {
        addForeignKeyConstraint(baseColumnNames: "protocol_id", baseTableName: "protocol_instance_summary", constraintName: "FK6vn6v7g4c33vcd6pja47o9lxm", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "protocol", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-149") {
        addForeignKeyConstraint(baseColumnNames: "billing_contact_id", baseTableName: "organization", constraintName: "FK6xw1bsvdkcf10rvj1feetks4p", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-150") {
        addForeignKeyConstraint(baseColumnNames: "target_type_id", baseTableName: "target", constraintName: "FK7fd5o5g240p415qfh8ojmj9bo", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "target_type", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-151") {
        addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_role", constraintName: "FK859n2jvi8ivhui0rl0esws6o", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-152") {
        addForeignKeyConstraint(baseColumnNames: "strain_id", baseTableName: "cell_source", constraintName: "FK868cjl2jx1t1enrfst5ce1un9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "strain", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-153") {
        addForeignKeyConstraint(baseColumnNames: "index_id", baseTableName: "sample_sequence_indices", constraintName: "FK8dhe3xyoc3br0fr7uws3svyds", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sequence_index", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-154") {
        addForeignKeyConstraint(baseColumnNames: "protocol_instance_id", baseTableName: "protocol_instance_items", constraintName: "FK8pjccf4a3c9n2ja8k9cq6v38p", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "protocol_instance", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-155") {
        addForeignKeyConstraint(baseColumnNames: "report_id", baseTableName: "report_alignments", constraintName: "FK8ui05vjl12r7jwq8bdye2jsig", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "summary_report", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-156") {
        addForeignKeyConstraint(baseColumnNames: "project_id", baseTableName: "project_samples", constraintName: "FK95dx7fkv6r65xk5r9qcng8cin", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "project", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-157") {
        addForeignKeyConstraint(baseColumnNames: "address_id", baseTableName: "organization", constraintName: "FK97eigsvq2tsrd2bge4ox651wh", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "address", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-158") {
        addForeignKeyConstraint(baseColumnNames: "cell_source_id", baseTableName: "batch_cell_sources", constraintName: "FK99x1hjei6ogdcxm1w4fxvfr6q", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cell_source", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-159") {
        addForeignKeyConstraint(baseColumnNames: "tissue_id", baseTableName: "cell_source", constraintName: "FK9bw1i4huctjx4330y93axijm1", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "tissue", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-160") {
        addForeignKeyConstraint(baseColumnNames: "project_id", baseTableName: "project_funding", constraintName: "FK9l2tp6kpxf1us967h9092rsu3", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "project", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-161") {
        addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "user_role", constraintName: "FKa68196081fvovjhkek5m97n3y", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "role", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-162") {
        addForeignKeyConstraint(baseColumnNames: "genome_id", baseTableName: "chromosome", constraintName: "FKam69crakd25kjy7d2rqiy5108", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "genome", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-163") {
        addForeignKeyConstraint(baseColumnNames: "technician_id", baseTableName: "run_stats", constraintName: "FKar55v5cio0nmrqv7opgg1n8cl", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-164") {
        addForeignKeyConstraint(baseColumnNames: "bag_id", baseTableName: "project_bags", constraintName: "FKb4e1wnuhpvapbhnyjs6dqk8mo", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "protocol_instance_bag", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-165") {
        addForeignKeyConstraint(baseColumnNames: "item_type_id", baseTableName: "protocol_item_types", constraintName: "FKb5ck29pggprrfleshlkhx5qr8", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "item_type", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-166") {
        addForeignKeyConstraint(baseColumnNames: "project_id", baseTableName: "replicate_set", constraintName: "FKbesib3k7784q0fr1a2si9y576", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "project", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-167") {
        addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "role_group_role", constraintName: "FKbo8cffthrm8wxgtnhg0i8dcxw", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "role", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-168") {
        addForeignKeyConstraint(baseColumnNames: "send_data_to_id", baseTableName: "sample", constraintName: "FKbpci4r4ynak9f0hby2pmaw0bo", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-169") {
        addForeignKeyConstraint(baseColumnNames: "parent_id", baseTableName: "organization", constraintName: "FKc30yedjwp9qw1f3nn2ytda7tj", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "organization", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-170") {
        addForeignKeyConstraint(baseColumnNames: "sample_id", baseTableName: "sequencing_experiment", constraintName: "FKcb969swv4c6xbdb3kw93txww1", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sample", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-171") {
        addForeignKeyConstraint(baseColumnNames: "batch_id", baseTableName: "batch_cell_sources", constraintName: "FKcjurv5d0t0u6yx5tl79fh0ka4", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cell_source_batch", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-172") {
        addForeignKeyConstraint(baseColumnNames: "assay_id", baseTableName: "sample", constraintName: "FKcpc4hhf4ntlq02q576nu2ky44", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "assay", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-173") {
        addForeignKeyConstraint(baseColumnNames: "address_id", baseTableName: "user", constraintName: "FKddefmvbrws3hvl5t0hnnsv8ox", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "address", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-174") {
        addForeignKeyConstraint(baseColumnNames: "inventory_id", baseTableName: "cell_source", constraintName: "FKdspbw8rsnb9h8bttdb5a4qyq", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "inventory", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-175") {
        addForeignKeyConstraint(baseColumnNames: "sequencing_experiment_id", baseTableName: "sequence_alignment", constraintName: "FKdvg4g4a0pby4puygnvyof5cu4", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sequencing_experiment", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-176") {
        addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "token", constraintName: "FKe32ek7ixanakfqsdaokm4q9y2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-177") {
        addForeignKeyConstraint(baseColumnNames: "pi_id", baseTableName: "organization", constraintName: "FKepyd6uqewfcp3xjvkr28mksul", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-178") {
        addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_role_group", constraintName: "FKexdgxebi2id0avjqhenhkmem9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-179") {
        addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "analysis", constraintName: "FKey194jla0jtivm1mgn7tn7g0i", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-180") {
        addForeignKeyConstraint(baseColumnNames: "run_stats_id", baseTableName: "sequence_run", constraintName: "FKf27lk1j5r09jgtjl198kdtqje", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "run_stats", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-181") {
        addForeignKeyConstraint(baseColumnNames: "treatment_id", baseTableName: "sample_treatments", constraintName: "FKf2vfwolapiodnq3iy2hi4gs9a", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cell_source_treatment", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-182") {
        addForeignKeyConstraint(baseColumnNames: "project_id", baseTableName: "item", constraintName: "FKf60hnjyqgladtp0jw5o0n4e9u", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "project", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-183") {
        addForeignKeyConstraint(baseColumnNames: "receiving_user_id", baseTableName: "inventory", constraintName: "FKf7o8jsln5b13i6p3k5y75cgjo", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-184") {
        addForeignKeyConstraint(baseColumnNames: "sex_id", baseTableName: "cell_source", constraintName: "FKfc39not4lgs7gunubj62b5nsx", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sex", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-185") {
        addForeignKeyConstraint(baseColumnNames: "antibody_id", baseTableName: "item_antibody", constraintName: "FKg3hetijuop4bqe2dnhdqhgo51", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "antibody", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-186") {
        addForeignKeyConstraint(baseColumnNames: "type_id", baseTableName: "item", constraintName: "FKg9lymegmlbvqtjrwpvkcdo5gr", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "item_type", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-187") {
        addForeignKeyConstraint(baseColumnNames: "read_type_id", baseTableName: "sequencing_experiment", constraintName: "FKgbnh1t5mfymeuu3ltqk2q8kp0", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "read_type", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-188") {
        addForeignKeyConstraint(baseColumnNames: "protocol_group_id", baseTableName: "protocol_instance_bag", constraintName: "FKgf7cmwy4enta8934copgfm80b", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "protocol_group", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-189") {
        addForeignKeyConstraint(baseColumnNames: "affiliation_id", baseTableName: "user", constraintName: "FKgrefofj2fru3hqquh5ep0mviq", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "organization", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-190") {
        addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "item", constraintName: "FKh4epdoqikj4sfedlxcc9dwwnl", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-191") {
        addForeignKeyConstraint(baseColumnNames: "target_id", baseTableName: "sample", constraintName: "FKhflf768ds23ev1ywoqsp42p96", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "target", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-192") {
        addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "protocol_group", constraintName: "FKhin7nog9se54hev1i9697ux4t", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-193") {
        addForeignKeyConstraint(baseColumnNames: "item_id", baseTableName: "cell_source", constraintName: "FKhyjubhp4aikim11btnsynpfgd", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "item", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-194") {
        addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "protocol_instance_summary", constraintName: "FKi70wyau9uwahxc4rtkhwf3n23", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-195") {
        addForeignKeyConstraint(baseColumnNames: "index_id", baseTableName: "item_sequence_indices", constraintName: "FKjhtgywgjb12yr7ljvpfn5s8eq", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sequence_index", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-196") {
        addForeignKeyConstraint(baseColumnNames: "platform_id", baseTableName: "sequence_run", constraintName: "FKjjf30r4ua6ru9e0ls0qhbwiei", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sequencing_platform", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-197") {
        addForeignKeyConstraint(baseColumnNames: "run_id", baseTableName: "sample_in_run", constraintName: "FKjroa7jvf2q9kf1f3fasxiy735", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sequence_run", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-198") {
        addForeignKeyConstraint(baseColumnNames: "protocol_id", baseTableName: "protocol_item_types", constraintName: "FKjw224bhrckc7lnuqc9r8p9y1g", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "protocol", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-199") {
        addForeignKeyConstraint(baseColumnNames: "ab_host_id", baseTableName: "antibody", constraintName: "FKjwhhs6m573clakbtqb6ggmfn", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "ab_host", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-200") {
        addForeignKeyConstraint(baseColumnNames: "category_id", baseTableName: "item_type", constraintName: "FKk3eijjkf35q5w944pwbb412u6", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "item_type_category", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-201") {
        addForeignKeyConstraint(baseColumnNames: "sample_id", baseTableName: "project_samples", constraintName: "FKk7psrd21b6ftrh8y2xfm7yx7j", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sample", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-202") {
        addForeignKeyConstraint(baseColumnNames: "bag_id", baseTableName: "protocol_instance", constraintName: "FKl3j40ti2vyc6c4w1kbp6eb3u6", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "protocol_instance_bag", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-203") {
        addForeignKeyConstraint(baseColumnNames: "chromosome_id", baseTableName: "chrom_sequence", constraintName: "FKl7bmacg1b4eca57cotc7ils74", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "chromosome", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-204") {
        addForeignKeyConstraint(baseColumnNames: "role_group_id", baseTableName: "role_group_role", constraintName: "FKlu0ge9c3rhabcfu59589trt1m", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "role_group", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-205") {
        addForeignKeyConstraint(baseColumnNames: "item_id", baseTableName: "item_sequence_indices", constraintName: "FKm08yv7tx0ymrjv7un1wncej95", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "item", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-206") {
        addForeignKeyConstraint(baseColumnNames: "genome_id", baseTableName: "sequence_alignment", constraintName: "FKm3h3wvtgyqcbyp7kaac35wj57", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "genome", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-207") {
        addForeignKeyConstraint(baseColumnNames: "pool_item_id", baseTableName: "sequence_run", constraintName: "FKmcddrdwrtqwy0h5wfiplislxj", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "item", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-208") {
        addForeignKeyConstraint(baseColumnNames: "cohort_id", baseTableName: "sequencing_experiment", constraintName: "FKmqikne1qqkrnll81iu1sfre", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sequencing_cohort", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-209") {
        addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "history", constraintName: "FKn4gjyu69m6xa5f3bot571imbe", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-210") {
        addForeignKeyConstraint(baseColumnNames: "parent_id", baseTableName: "histology", constraintName: "FKn6wxriw2r0bt5lepofmjshgrc", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "histology", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-211") {
        addForeignKeyConstraint(baseColumnNames: "histology_id", baseTableName: "cell_source", constraintName: "FKndexf4a4taolqvll9kmbh8kkg", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "histology", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-212") {
        addForeignKeyConstraint(baseColumnNames: "project_id", baseTableName: "history", constraintName: "FKo85y510lpxvd8vyk32diab6qq", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "project", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-213") {
        addForeignKeyConstraint(baseColumnNames: "invoice_id", baseTableName: "sample", constraintName: "FKo86ao5favegpdv2p45ekxus9o", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "invoice", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-214") {
        addForeignKeyConstraint(baseColumnNames: "aligner_id", baseTableName: "sequence_alignment", constraintName: "FKok3dqhynp748nr1ur1firmt14", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "aligner", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-215") {
        addForeignKeyConstraint(baseColumnNames: "item_id", baseTableName: "item_antibody", constraintName: "FKoklrp1sxftr4iyjrxjhiykio2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "item", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-216") {
        addForeignKeyConstraint(baseColumnNames: "spike_in_cell_source_id", baseTableName: "sample", constraintName: "FKorx0c9myw8smxka4o3ir837ps", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cell_source", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-217") {
        addForeignKeyConstraint(baseColumnNames: "prtcl_inst_summary_id", baseTableName: "sample", constraintName: "FKp8cqy6861ds7dco3txn0ioh3c", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "protocol_instance_summary", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-218") {
        addForeignKeyConstraint(baseColumnNames: "report_id", baseTableName: "sequencing_cohort", constraintName: "FKq03aoroe14bkb93n2jpcmfpn", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "summary_report", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-219") {
        addForeignKeyConstraint(baseColumnNames: "pipeline_id", baseTableName: "sequence_alignment", constraintName: "FKq4d03x395u6vghiwp58qmqdf7", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "pipeline", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-220") {
        addForeignKeyConstraint(baseColumnNames: "run_id", baseTableName: "sequencing_cohort", constraintName: "FKqe8146g7qcr91mhbhlnkowoq5", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sequence_run", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-221") {
        addForeignKeyConstraint(baseColumnNames: "protocol_id", baseTableName: "protocol_instance", constraintName: "FKqhnl5itkka9paugla9ss836wb", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "protocol", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-222") {
        addForeignKeyConstraint(baseColumnNames: "pool_id", baseTableName: "pool_samples", constraintName: "FKqou16vog2akhov2b03410qna", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "item", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-223") {
        addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "protocol", constraintName: "FKqtg4kcmhhbl3evj87vvf71xvf", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-224") {
        addForeignKeyConstraint(baseColumnNames: "sample_id", baseTableName: "replicate_samples", constraintName: "FKqwoluiec2sxnxtbhuoj4e7858", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sample", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-225") {
        addForeignKeyConstraint(baseColumnNames: "parent_id", baseTableName: "item", constraintName: "FKqwonew7r6scr7si8k2cei8wjo", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "item", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-226") {
        addForeignKeyConstraint(baseColumnNames: "company_id", baseTableName: "antibody", constraintName: "FKr7hx5ykvqtjo1slxyedbeon0j", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "organization", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-227") {
        addForeignKeyConstraint(baseColumnNames: "prep_user_id", baseTableName: "cell_source", constraintName: "FKroomr52lbjofo8ly77vayrsuu", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-228") {
        addForeignKeyConstraint(baseColumnNames: "sample_id", baseTableName: "sample_treatments", constraintName: "FKrq3vygba0p5l99k8ej2msf758", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sample", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-229") {
        addForeignKeyConstraint(baseColumnNames: "set_id", baseTableName: "replicate_samples", constraintName: "FKrvwftgvok41hx8n02aym7slsp", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "replicate_set", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-230") {
        addForeignKeyConstraint(baseColumnNames: "source_lab_id", baseTableName: "strain", constraintName: "FKrwpjbguvhieg265qb4sjmofnr", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "organization", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-231") {
        addForeignKeyConstraint(baseColumnNames: "item_id", baseTableName: "antibody", constraintName: "FKrwq0pnfbecmygn4xofqr4qtw3", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "item", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-232") {
        addForeignKeyConstraint(baseColumnNames: "control_sample_id", baseTableName: "control_sample", constraintName: "FKs2ks4b5o1f4a670k9ynottyrp", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sample", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-233") {
        addForeignKeyConstraint(baseColumnNames: "provider_user_id", baseTableName: "cell_source", constraintName: "FKs656nbjgufpx0t833xbu78mck", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-234") {
        addForeignKeyConstraint(baseColumnNames: "alignment_id", baseTableName: "analysis", constraintName: "FKsmacedtxj270658jj6r8hh5qb", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sequence_alignment", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-235") {
        addForeignKeyConstraint(baseColumnNames: "protocol_id", baseTableName: "protocol_group_protocols", constraintName: "FKsp79j0667g22q9e72dp0617sd", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "protocol", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-236") {
        addForeignKeyConstraint(baseColumnNames: "align_type_id", baseTableName: "sequence_alignment", constraintName: "FKsw06tb5ys7obob4e2cpovuwr8", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "align_type", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-237") {
        addForeignKeyConstraint(baseColumnNames: "growth_media_id", baseTableName: "sample", constraintName: "FKswwuixwogcl48lwy3ucvvqaub", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "growth_media", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-238") {
        addForeignKeyConstraint(baseColumnNames: "sample_id", baseTableName: "sample_sequence_indices", constraintName: "FKtbax39c2nyeqk46yiwk6xj9l", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sample", validate: "true")
    }

    changeSet(author: "danyingshao (generated)", id: "1580764544352-239") {
        addForeignKeyConstraint(baseColumnNames: "species_id", baseTableName: "growth_media", constraintName: "FKte5f01yhaud29abdr878pjgsi", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "species", validate: "true")
    }
 //   include file: 'updateProtocol.groovy'
    include file: 'assay_description.groovy'
    include file: 'run_name.groovy'
    include file: 'delete_run_num.groovy'
    include file: 'add_feature.groovy'
    include file: 'item_active.groovy'
    include file: 'growth_media_species.groovy'
    include file: 'update_item.groovy'
    include file: 'rm_replicate_project.groovy'
    include file: 'add_project_links.groovy'
    include file: 'add_sex_notes.groovy'
    include file: 'add_bigwig.groovy'
    include file: 'add_read2.groovy'
}
