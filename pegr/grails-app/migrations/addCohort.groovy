databaseChangeLog = {

	changeSet(author: "danyingshao (generated)", id: "1472352936325-1") {
		createTable(tableName: "funding") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "fundingPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "number", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1472352936325-2") {
		createTable(tableName: "item_type_category") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "item_type_catPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "super_category", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1472352936325-3") {
		createTable(tableName: "project_funding") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "project_fundiPK")
			}

			column(name: "funding_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "project_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1472352936325-4") {
		createTable(tableName: "sequencing_cohort") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "sequencing_coPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "project_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "report_id", type: "bigint")

			column(name: "run_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1472352936325-5") {
		addColumn(tableName: "analysis") {
			column(name: "user_id", type: "bigint")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1472352936325-6") {
		addColumn(tableName: "item_type") {
			column(name: "category_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1472352936325-7") {
		addColumn(tableName: "pipeline") {
			column(name: "steps", type: "longtext") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1472352936325-8") {
		addColumn(tableName: "pipeline") {
			column(defaultValue: "N/A", name: "workflow_id", type: "varchar(255)") {
				constraints(nullable: "false", unique: "true")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1472352936325-9") {
		addColumn(tableName: "project") {
			column(name: "notes", type: "longtext")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1472352936325-10") {
		addColumn(tableName: "sequencing_experiment") {
			column(name: "cohort_id", type: "bigint")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1472352936325-11") {
		addColumn(tableName: "summary_report") {
			column(name: "date", type: "datetime") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1472352936325-12") {
		addColumn(tableName: "summary_report") {
			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1472352936325-13") {
		addColumn(tableName: "summary_report") {
			column(name: "note", type: "varchar(255)")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1472352936325-14") {
		addColumn(tableName: "summary_report") {
			column(name: "status", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1472352936325-15") {
		addColumn(tableName: "summary_report") {
			column(name: "type", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1472352936325-16") {
		addColumn(tableName: "summary_report") {
			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1472352936325-17") {
		addColumn(tableName: "user") {
			column(name: "api_key", type: "varchar(255)")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1472352936325-18") {
        sql("update pipeline set pipeline_version = '0.0.0' where pipeline_version is null;")
		addNotNullConstraint(columnDataType: "varchar(255)", columnName: "pipeline_version", tableName: "pipeline")
	}

	changeSet(author: "danyingshao (generated)", id: "1472352936325-19") {
		dropForeignKeyConstraint(baseTableName: "summary_report", baseTableSchemaName: "pegr", constraintName: "FK_3avk8v4ooy5p6pgj95viim6uy")
	}

	changeSet(author: "danyingshao (generated)", id: "1472352936325-20") {
		dropForeignKeyConstraint(baseTableName: "summary_report", baseTableSchemaName: "pegr", constraintName: "FK_bu7egp9o5u8ktnkkplehxxsjf")
	}

	changeSet(author: "danyingshao (generated)", id: "1472352936325-29") {
		dropIndex(indexName: "unique_project_id", tableName: "summary_report")
	}

	changeSet(author: "danyingshao (generated)", id: "1472352936325-30") {
		createIndex(indexName: "FK_iw1m6hhffn4kgjwh96ejux11m", tableName: "analysis") {
			column(name: "user_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1472352936325-31") {
		createIndex(indexName: "unique_name", tableName: "funding", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1472352936325-32") {
        sql("insert into item_type_category (version, name, super_category) values (0, 'Antibody', 'ANTIBODY');")
        sql("update item_type set category_id = 1 where category_id = 0;")
		createIndex(indexName: "FK_94ymay19alxgutio16kgowv93", tableName: "item_type") {
			column(name: "category_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1472352936325-33") {
		createIndex(indexName: "name_uniq_1472352933587", tableName: "item_type_category", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1472352936325-34") {
		createIndex(indexName: "workflow_id_uniq_1472352933591", tableName: "pipeline", unique: "true") {
			column(name: "workflow_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1472352936325-35") {
		createIndex(indexName: "FK_6e9lgt30h05twwt72veu2hrt1", tableName: "project_funding") {
			column(name: "funding_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1472352936325-36") {
		createIndex(indexName: "FK_clw28uyswyfeglm9v1kdx0pmy", tableName: "project_funding") {
			column(name: "project_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1472352936325-37") {
		createIndex(indexName: "unique_project_id", tableName: "project_funding", unique: "true") {
			column(name: "funding_id")

			column(name: "project_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1472352936325-38") {
		createIndex(indexName: "FK_724ffp5jxmqpde8496in7kggu", tableName: "sequencing_cohort") {
			column(name: "run_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1472352936325-39") {
		createIndex(indexName: "FK_8ae2rfhbp4pr7xk6kqpa38818", tableName: "sequencing_cohort") {
			column(name: "project_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1472352936325-40") {
		createIndex(indexName: "FK_ptpiv5b92sigq6bm3l854sgj8", tableName: "sequencing_cohort") {
			column(name: "report_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1472352936325-41") {
		createIndex(indexName: "unique_project_id", tableName: "sequencing_cohort", unique: "true") {
			column(name: "run_id")

			column(name: "project_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1472352936325-42") {
		createIndex(indexName: "FK_91odwapnmtkkmkbfserv13a80", tableName: "sequencing_experiment") {
			column(name: "cohort_id")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1472352936325-43") {
		dropColumn(columnName: "user", tableName: "analysis")
	}

	changeSet(author: "danyingshao (generated)", id: "1472352936325-44") {
		dropColumn(columnName: "genome_version", tableName: "genome")
	}

	changeSet(author: "danyingshao (generated)", id: "1472352936325-45") {
		dropColumn(columnName: "category", tableName: "item_type")
	}

	changeSet(author: "danyingshao (generated)", id: "1472352936325-46") {
		dropColumn(columnName: "funding", tableName: "project")
	}

	changeSet(author: "danyingshao (generated)", id: "1472352936325-47") {
		dropColumn(columnName: "workflow_id", tableName: "sequence_alignment")
	}

	changeSet(author: "danyingshao (generated)", id: "1472352936325-48") {
		dropColumn(columnName: "project_id", tableName: "summary_report")
	}

	changeSet(author: "danyingshao (generated)", id: "1472352936325-49") {
		dropColumn(columnName: "run_id", tableName: "summary_report")
	}

	changeSet(author: "danyingshao (generated)", id: "1472352936325-50") {
		dropTable(tableName: "api_user")
	}

	changeSet(author: "danyingshao (generated)", id: "1472352936325-21") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "analysis", constraintName: "FK_iw1m6hhffn4kgjwh96ejux11m", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1472352936325-22") {
		addForeignKeyConstraint(baseColumnNames: "category_id", baseTableName: "item_type", constraintName: "FK_94ymay19alxgutio16kgowv93", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "item_type_category", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1472352936325-23") {
		addForeignKeyConstraint(baseColumnNames: "funding_id", baseTableName: "project_funding", constraintName: "FK_6e9lgt30h05twwt72veu2hrt1", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "funding", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1472352936325-24") {
		addForeignKeyConstraint(baseColumnNames: "project_id", baseTableName: "project_funding", constraintName: "FK_clw28uyswyfeglm9v1kdx0pmy", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "project", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1472352936325-25") {
		addForeignKeyConstraint(baseColumnNames: "project_id", baseTableName: "sequencing_cohort", constraintName: "FK_8ae2rfhbp4pr7xk6kqpa38818", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "project", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1472352936325-26") {
		addForeignKeyConstraint(baseColumnNames: "report_id", baseTableName: "sequencing_cohort", constraintName: "FK_ptpiv5b92sigq6bm3l854sgj8", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "summary_report", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1472352936325-27") {
		addForeignKeyConstraint(baseColumnNames: "run_id", baseTableName: "sequencing_cohort", constraintName: "FK_724ffp5jxmqpde8496in7kggu", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sequence_run", referencesUniqueColumn: "false")
	}

	changeSet(author: "danyingshao (generated)", id: "1472352936325-28") {
		addForeignKeyConstraint(baseColumnNames: "cohort_id", baseTableName: "sequencing_experiment", constraintName: "FK_91odwapnmtkkmkbfserv13a80", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sequencing_cohort", referencesUniqueColumn: "false")
	}
}
