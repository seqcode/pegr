databaseChangeLog = {

    changeSet(author: "danyingshao (generated)", id: "1620671544568-5") {
        addColumn(tableName: "sequencing_experiment") {
            column(name: "total_readsr2", type: "BIGINT")
        }
    }
    
    changeSet(author: "danyingshao (generated)", id: "1620671544568-6") {
        addColumn(tableName: "sequencing_experiment") {
            column(name: "adapter_dimer_countr2", type: "BIGINT")
        }
    }
    
    changeSet(author: "danyingshao (generated)", id: "1620671544568-7") {
        addColumn(tableName: "sequence_alignment") {
            column(name: "mapped_readsr2", type: "BIGINT")
        }
    }
    
    changeSet(author: "danyingshao (generated)", id: "1620671544568-8") {
        addColumn(tableName: "sequence_alignment") {
            column(name: "uniquely_mapped_readsr2", type: "BIGINT")
        }
    }
    
    changeSet(author: "danyingshao (generated)", id: "1620671544568-9") {
        addColumn(tableName: "sequence_alignment") {
            column(name: "dedup_uniquely_mapped_readsr2", type: "BIGINT")
        }
    }
    
    changeSet(author: "danyingshao (generated)", id: "1620671544568-10") {
        addColumn(tableName: "sequence_alignment") {
            column(name: "median_insert_size", type: "FLOAT")
        }
    }
    
    changeSet(author: "danyingshao (generated)", id: "1620671544568-11") {
        addColumn(tableName: "sequence_alignment") {
            column(name: "mode_insert_size", type: "FLOAT")
        }
    }
}
