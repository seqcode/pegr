databaseChangeLog = {

    changeSet(author: "danyingshao (generated)", id: "1620671544568-3") {
        addColumn(tableName: "sequence_alignment") {
            column(name: "bigwig_forward_file", type: "varchar(255)")
        }
    }
    
    changeSet(author: "danyingshao (generated)", id: "1620671544568-4") {
        addColumn(tableName: "sequence_alignment") {
            column(name: "bigwig_reverse_file", type: "varchar(255)")
        }
    }
}
