databaseChangeLog = {

	changeSet(author: "dus73 (generated)", id: "1472145065343-1") {
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

	changeSet(author: "dus73 (generated)", id: "1472145065343-2") {
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

	changeSet(author: "dus73 (generated)", id: "1472145065343-3") {
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

	changeSet(author: "dus73 (generated)", id: "1472145065343-4") {
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

	changeSet(author: "dus73 (generated)", id: "1472145065343-5") {
		addColumn(tableName: "analysis") {
			column(name: "user_id", type: "bigint")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1472145065343-6") {
		addColumn(tableName: "item_type") {
			column(name: "category_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "dus73 (generated)", id: "1472145065343-7") {
		addColumn(tableName: "pipeline") {
			column(name: "steps", type: "longtext") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "dus73 (generated)", id: "1472145065343-8") {
		addColumn(tableName: "pipeline") {
			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "dus73 (generated)", id: "1472145065343-9") {
		addColumn(tableName: "pipeline") {
			column(defaultValue: "N/A", name: "workflow_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "dus73 (generated)", id: "1472145065343-10") {
		addColumn(tableName: "sequencing_experiment") {
			column(name: "cohort_id", type: "bigint")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1472145065343-11") {
		addColumn(tableName: "summary_report") {
			column(name: "date", type: "datetime") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "dus73 (generated)", id: "1472145065343-12") {
		addColumn(tableName: "summary_report") {
			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "dus73 (generated)", id: "1472145065343-13") {
		addColumn(tableName: "summary_report") {
			column(name: "note", type: "varchar(255)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1472145065343-14") {
		addColumn(tableName: "summary_report") {
			column(name: "status", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "dus73 (generated)", id: "1472145065343-15") {
		addColumn(tableName: "summary_report") {
			column(name: "type", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "dus73 (generated)", id: "1472145065343-16") {
		addColumn(tableName: "summary_report") {
			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "dus73 (generated)", id: "1472145065343-17") {
		addColumn(tableName: "summary_report") {
			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "dus73 (generated)", id: "1472145065343-18") {
		addColumn(tableName: "user") {
			column(name: "api_key", type: "varchar(255)")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1472145065343-19") {
		modifyDataType(columnName: "notes", newDataType: "text", tableName: "item")
	}
    
    changeSet(author: "dus73 (generated)", id: "1472145065343-64") {
		addColumn(tableName: "project") {
			column(name: "notes", type: "longtext") {
				constraints(nullable: "true")
			}
		}
	}

	changeSet(author: "dus73 (generated)", id: "1472145065343-24") {
        sql("update pipeline set pipeline_version = '0.0.0' where pipeline_version is null;")
		addNotNullConstraint(columnDataType: "varchar(255)", columnName: "pipeline_version", tableName: "pipeline")
	}

	changeSet(author: "dus73 (generated)", id: "1472145065343-26") {
		dropForeignKeyConstraint(baseTableName: "summary_report", baseTableSchemaName: "pegr", constraintName: "FK_3avk8v4ooy5p6pgj95viim6uy")
	}

	changeSet(author: "dus73 (generated)", id: "1472145065343-27") {
		dropForeignKeyConstraint(baseTableName: "summary_report", baseTableSchemaName: "pegr", constraintName: "FK_bu7egp9o5u8ktnkkplehxxsjf")
	}

	changeSet(author: "dus73 (generated)", id: "1472145065343-38") {
		dropIndex(indexName: "unique_name", tableName: "pipeline")
	}

	changeSet(author: "dus73 (generated)", id: "1472145065343-39") {
		dropIndex(indexName: "unique_project_id", tableName: "summary_report")
	}

	changeSet(author: "dus73 (generated)", id: "1472145065343-40") {
		createIndex(indexName: "FK_iw1m6hhffn4kgjwh96ejux11m", tableName: "analysis") {
			column(name: "user_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1472145065343-41") {
		createIndex(indexName: "unique_name", tableName: "funding", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1472145065343-42") {
        sql("insert into item_type_category (version, name, super_category) values (0, 'Antibody', 'ANTIBODY');")
        sql("update item_type set category_id = 1 where category_id = 0;")
		createIndex(indexName: "FK_94ymay19alxgutio16kgowv93", tableName: "item_type") {
			column(name: "category_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1472145065343-43") {
		createIndex(indexName: "name_uniq_1472145064479", tableName: "item_type_category", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1472145065343-44") {
		createIndex(indexName: "FK_t42f6y8d1i2o2eu786cplcj7u", tableName: "pipeline") {
			column(name: "user_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1472145065343-45") {
		createIndex(indexName: "unique_name", tableName: "pipeline", unique: "true") {
			column(name: "pipeline_version")

			column(name: "user_id")

			column(name: "name")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1472145065343-46") {
		createIndex(indexName: "unique_workflow_id", tableName: "pipeline", unique: "true") {
			column(name: "user_id")

			column(name: "workflow_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1472145065343-47") {
		createIndex(indexName: "FK_6e9lgt30h05twwt72veu2hrt1", tableName: "project_funding") {
			column(name: "funding_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1472145065343-48") {
		createIndex(indexName: "FK_clw28uyswyfeglm9v1kdx0pmy", tableName: "project_funding") {
			column(name: "project_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1472145065343-49") {
		createIndex(indexName: "unique_project_id", tableName: "project_funding", unique: "true") {
			column(name: "funding_id")

			column(name: "project_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1472145065343-50") {
		createIndex(indexName: "FK_724ffp5jxmqpde8496in7kggu", tableName: "sequencing_cohort") {
			column(name: "run_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1472145065343-51") {
		createIndex(indexName: "FK_8ae2rfhbp4pr7xk6kqpa38818", tableName: "sequencing_cohort") {
			column(name: "project_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1472145065343-52") {
		createIndex(indexName: "FK_ptpiv5b92sigq6bm3l854sgj8", tableName: "sequencing_cohort") {
			column(name: "report_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1472145065343-53") {
		createIndex(indexName: "unique_project_id", tableName: "sequencing_cohort", unique: "true") {
			column(name: "run_id")

			column(name: "project_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1472145065343-54") {
		createIndex(indexName: "FK_91odwapnmtkkmkbfserv13a80", tableName: "sequencing_experiment") {
			column(name: "cohort_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1472145065343-55") {
		createIndex(indexName: "FK_7w5sgwlh01jveie4ok14g0mew", tableName: "summary_report") {
			column(name: "user_id")
		}
	}

	changeSet(author: "dus73 (generated)", id: "1472145065343-56") {
		dropColumn(columnName: "user", tableName: "analysis")
	}

	changeSet(author: "dus73 (generated)", id: "1472145065343-57") {
		dropColumn(columnName: "genome_version", tableName: "genome")
	}

	changeSet(author: "dus73 (generated)", id: "1472145065343-58") {
		dropColumn(columnName: "category", tableName: "item_type")
	}

	changeSet(author: "dus73 (generated)", id: "1472145065343-59") {
		dropColumn(columnName: "funding", tableName: "project")
	}

	changeSet(author: "dus73 (generated)", id: "1472145065343-60") {
		dropColumn(columnName: "workflow_id", tableName: "sequence_alignment")
	}

	changeSet(author: "dus73 (generated)", id: "1472145065343-61") {
		dropColumn(columnName: "project_id", tableName: "summary_report")
	}

	changeSet(author: "dus73 (generated)", id: "1472145065343-62") {
		dropColumn(columnName: "run_id", tableName: "summary_report")
	}

	changeSet(author: "dus73 (generated)", id: "1472145065343-63") {
		dropTable(tableName: "api_user")
	}

	changeSet(author: "dus73 (generated)", id: "1472145065343-28") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "analysis", constraintName: "FK_iw1m6hhffn4kgjwh96ejux11m", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1472145065343-29") {
		addForeignKeyConstraint(baseColumnNames: "category_id", baseTableName: "item_type", constraintName: "FK_94ymay19alxgutio16kgowv93", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "item_type_category", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1472145065343-30") {
        sql("update pipeline set user_id = 1 where user_id =0;")
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "pipeline", constraintName: "FK_t42f6y8d1i2o2eu786cplcj7u", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1472145065343-31") {
		addForeignKeyConstraint(baseColumnNames: "funding_id", baseTableName: "project_funding", constraintName: "FK_6e9lgt30h05twwt72veu2hrt1", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "funding", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1472145065343-32") {
		addForeignKeyConstraint(baseColumnNames: "project_id", baseTableName: "project_funding", constraintName: "FK_clw28uyswyfeglm9v1kdx0pmy", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "project", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1472145065343-33") {
		addForeignKeyConstraint(baseColumnNames: "project_id", baseTableName: "sequencing_cohort", constraintName: "FK_8ae2rfhbp4pr7xk6kqpa38818", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "project", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1472145065343-34") {
		addForeignKeyConstraint(baseColumnNames: "report_id", baseTableName: "sequencing_cohort", constraintName: "FK_ptpiv5b92sigq6bm3l854sgj8", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "summary_report", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1472145065343-35") {
		addForeignKeyConstraint(baseColumnNames: "run_id", baseTableName: "sequencing_cohort", constraintName: "FK_724ffp5jxmqpde8496in7kggu", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sequence_run", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1472145065343-36") {
		addForeignKeyConstraint(baseColumnNames: "cohort_id", baseTableName: "sequencing_experiment", constraintName: "FK_91odwapnmtkkmkbfserv13a80", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sequencing_cohort", referencesUniqueColumn: "false")
	}

	changeSet(author: "dus73 (generated)", id: "1472145065343-37") {
        sql("delete from report_alignments;")
        sql("delete from summary_report;")
        addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "summary_report", constraintName: "FK_7w5sgwlh01jveie4ok14g0mew", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}
}
