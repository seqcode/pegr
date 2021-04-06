databaseChangeLog = {

    changeSet(author: "danyingshao (generated)", id: "1617641836355-49") {
        addColumn(tableName: "item") {
            column(name: "active", type: "bit") {
                constraints(nullable: "false")
            }
        }
    }
    
}
