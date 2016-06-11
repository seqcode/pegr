databaseChangeLog = {
    
	changeSet(author: "dus73 (generated)", id: "1465502382011-2") {
        addColumn(tableName: "biological_replicate_set") {
            column(name: "type", type: "varchar(255)") {
                constraints(nullable: "false")
            }
        }
	}
    
    changeSet(author: "dus73 (generated)", id: "1465502382011-3") {
        renameTable(oldTableName: "biological_replicate_set", newTableName: "replicate_set")
    }
    
    changeSet(author: "dus73 (generated)", id: "1465502382011-4") {
        renameTable(oldTableName: "biological_replicate_samples", newTableName: "replicate_samples")
	}
    
	changeSet(author: "dus73 (generated)", id: "1465502382011-7") {
		dropForeignKeyConstraint(baseTableName: "technical_replicate_samples", baseTableSchemaName: "pegr", constraintName: "FK_pgfytkmfc7o8hujxpm27nrupx")
	}

	changeSet(author: "dus73 (generated)", id: "1465502382011-8") {
		dropForeignKeyConstraint(baseTableName: "technical_replicate_samples", baseTableSchemaName: "pegr", constraintName: "FK_bxc886ht20h9dyioee4mvtuhg")
	}

	changeSet(author: "dus73 (generated)", id: "1465502382011-9") {
		dropForeignKeyConstraint(baseTableName: "technical_replicate_set", baseTableSchemaName: "pegr", constraintName: "FK_9qiur0k1ju7xinurh5sy074fr")
	}

	changeSet(author: "dus73 (generated)", id: "1465502382011-12") {
		dropIndex(indexName: "unique_set_id", tableName: "technical_replicate_samples")
	}

	changeSet(author: "dus73 (generated)", id: "1465502382011-16") {
		dropTable(tableName: "technical_replicate_samples")
	}

	changeSet(author: "dus73 (generated)", id: "1465502382011-17") {
		dropTable(tableName: "technical_replicate_set")
	}
}
