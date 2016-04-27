databaseChangeLog = {

	changeSet(author: "dus73 (generated)", id: "1461774894669-1") {
        renameColumn(tableName: "alignment_stats", oldColumnName: "index_count", newColumnName: "total_reads", columnDataType: "integer")
	}

	changeSet(author: "dus73 (generated)", id: "1461774894669-2") {
        renameColumn(tableName: "alignment_stats", oldColumnName: "mapped_read_count", newColumnName: "mapped_reads", columnDataType: "integer")
	}
    
    changeSet(author: "dus73 (generated)", id: "1461774894669-3") {
        renameColumn(tableName: "alignment_stats", oldColumnName: "unique_mapped_read_count", newColumnName: "uniquely_mapped_reads", columnDataType: "integer")
	}
    
    changeSet(author: "dus73 (generated)", id: "1461774894669-4") {
        renameColumn(tableName: "alignment_stats", oldColumnName: "dedup_read_count", newColumnName: "dedup_uniquely_mapped_reads", columnDataType: "integer")
	}
    
    changeSet(author: "dus73 (generated)", id: "1461774894669-5") {
        addColumn(tableName: "alignment_stats") {
			column(name: "galaxy_dataset_filepath", type: "varchar(1000)")
		}
    }
}
