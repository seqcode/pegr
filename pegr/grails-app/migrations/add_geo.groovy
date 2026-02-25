databaseChangeLog = {

    changeSet(author: "danyingshao (generated)", id: "1620671544575-3") {
        addColumn(tableName: "sample") {
            column(name: "geo_accession", type: "varchar(255)")
        }
    }
    
}
