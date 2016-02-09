databaseChangeLog = {

	changeSet(author: "danyingshao (generated)", id: "1454990409083-1") {
		addColumn(tableName: "sample") {
			column(name: "date", type: "datetime") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1454990409083-2") {
		modifyDataType(columnName: "details", newDataType: "text", tableName: "protocol")
	}

	changeSet(author: "danyingshao (generated)", id: "1454990409083-3") {
		dropColumn(columnName: "last_updated", tableName: "sample")
	}
}
