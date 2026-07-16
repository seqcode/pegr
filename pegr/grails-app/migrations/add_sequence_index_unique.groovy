databaseChangeLog = {

    // Enforce UNIQUE(index_id) on sequence_index to match the domain constraint.
    // Production ALREADY has this index (named `index_id`, created long ago by
    // Hibernate auto-DDL) but it was never tracked in a migration. The
    // precondition detects the existing index and marks this changeset as run
    // WITHOUT executing DDL on prod, while still creating it on fresh/dev DBs
    // that lack it. Verified: no duplicate index_id values exist.

    changeSet(author: "danyingshao", id: "add-sequence-index-index-id-unique-1") {
        preConditions(onFail: "MARK_RAN") {
            not {
                indexExists(tableName: "sequence_index", columnNames: "index_id")
            }
        }
        addUniqueConstraint(columnNames: "index_id", constraintName: "UC_SEQUENCE_INDEXINDEX_ID_COL", tableName: "sequence_index")
    }

}
