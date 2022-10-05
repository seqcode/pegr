databaseChangeLog = {
    changeSet(author: "danyingshao (generated)", id: "1620671544567-1") {
        dropForeignKeyConstraint(baseTableName: "replicate_set", constraintName: "FK_qg0x2qhnutq0rw27iglu1o49u")
    }
    
    changeSet(author: "danyingshao (generated)", id: "1620671544567-2") {
        dropColumn(columnName: "project_id", tableName: "replicate_set")
    }
    
}
