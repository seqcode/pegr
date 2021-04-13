databaseChangeLog = {

    changeSet(author: "danyingshao (generated)", id: "1618278662989-49") {
        dropForeignKeyConstraint(baseTableName: "growth_media", constraintName: "FK_d67vskwe5phnwub9cqiokalkx")
    }

    changeSet(author: "danyingshao (generated)", id: "1618278662989-50") {
        dropColumn(columnName: "species_id", tableName: "growth_media")
    }

}
