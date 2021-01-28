databaseChangeLog = {

    changeSet(author: "danyingshao (generated)", id: "1611863993806-49") {
        createTable(tableName: "reference_feature") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "reference_featurePK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "genome_id", type: "BIGINT")

            column(name: "url", type: "VARCHAR(255)")

            column(name: "summary", type: "VARCHAR(255)")

            column(name: "filename", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1611863993806-50") {
        addForeignKeyConstraint(baseColumnNames: "genome_id", baseTableName: "reference_feature", constraintName: "FKcy4t95j1gcwtgimf4oxyyjm46", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "genome", validate: "true")
    }

}
