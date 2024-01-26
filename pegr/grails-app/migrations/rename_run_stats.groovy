databaseChangeLog = {

  changeSet(author: "danyingshao (generated)", id: "1620671544569-1") {
    renameColumn(tableName: "run_stats", oldColumnName: "cluster_num", newColumnName: "cluster_density", columnDataType: "FLOAT")
  }
    
  changeSet(author: "danyingshao (generated)", id: "1620671544569-2") {
    renameColumn(tableName: "run_stats", oldColumnName: "pct_pf", newColumnName: "pct_clusters_pass_filter", columnDataType: "FLOAT")
  }
    
  changeSet(author: "danyingshao (generated)", id: "1620671544569-3") {
    renameColumn(tableName: "run_stats", oldColumnName: "read_pf", newColumnName: "reads_pass_filter", columnDataType: "FLOAT")
  }
    
  changeSet(author: "danyingshao (generated)", id: "1620671544569-4") {
    renameColumn(tableName: "run_stats", oldColumnName: "unmatched_indices", newColumnName: "reads_failed_demultiplex", columnDataType: "FLOAT")
  }
    
  changeSet(author: "danyingshao (generated)", id: "1620671544569-5") {
    renameColumn(tableName: "run_stats", oldColumnName: "pct_unmatched_indices", newColumnName: "pct_reads_failed_demultiplex", columnDataType: "FLOAT")
  }
}