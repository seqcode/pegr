databaseChangeLog = {

    // Enforce at the DB level the unique constraints that the domain classes
    // intended but never applied (the `unqiue:` typo in Genome/ReferenceFeature
    // meant GORM silently ignored them). Verified: no duplicate values exist in
    // genome.name or reference_feature.filename, so these apply cleanly.
    // (pipeline.steps needs no change — the column is already NOT NULL.)

    changeSet(author: "danyingshao", id: "add-genome-name-unique-1") {
        addUniqueConstraint(columnNames: "name", constraintName: "UC_GENOMENAME_COL", tableName: "genome")
    }

    changeSet(author: "danyingshao", id: "add-reference-feature-filename-unique-1") {
        addUniqueConstraint(columnNames: "filename", constraintName: "UC_REFERENCE_FEATUREFILENAME_COL", tableName: "reference_feature")
    }

}
