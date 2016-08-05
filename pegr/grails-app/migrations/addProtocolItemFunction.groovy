databaseChangeLog = {

	changeSet(author: "danyingshao (generated)", id: "1470360755178-1") {
		addColumn(tableName: "protocol_instance_items") {
			column(name: "function", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1470360755178-7") {
		dropIndex(indexName: "unique_protocol_instance_id", tableName: "protocol_instance_items")
	}
}
