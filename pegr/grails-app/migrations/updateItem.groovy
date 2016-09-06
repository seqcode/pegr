databaseChangeLog = {

	changeSet(author: "danyingshao (generated)", id: "1473096571923-1") {
		addColumn(tableName: "item") {
			column(name: "customized_fields", type: "longtext")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1473096571923-2") {
		dropIndex(indexName: "unique_barcode", tableName: "item")
	}

	changeSet(author: "danyingshao (generated)", id: "1473096571923-3") {
		createIndex(indexName: "barcode_uniq_1473096571187", tableName: "item", unique: "true") {
			column(name: "barcode")
		}
	}
}
