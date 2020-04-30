databaseChangeLog = {

    changeSet(author: "danyingshao (generated)", id: "1588270150057-45") {
        dropForeignKeyConstraint(baseTableName: "chrom_sequence", constraintName: "FK_lese17egs9gkqmyudn2wh5il2")
    }

    changeSet(author: "danyingshao (generated)", id: "1588270150057-46") {
        dropForeignKeyConstraint(baseTableName: "chromosome", constraintName: "FK_qtx5yloqyq7hc1efournhys5u")
    }

    changeSet(author: "danyingshao (generated)", id: "1588270150057-47") {
        dropUniqueConstraint(constraintName: "name_uniq_1470678902689", tableName: "chromosome")
    }

    changeSet(author: "danyingshao (generated)", id: "1588270150057-48") {
        dropTable(tableName: "chrom_sequence")
    }

    changeSet(author: "danyingshao (generated)", id: "1588270150057-49") {
        dropTable(tableName: "chromosome")
    }

}
