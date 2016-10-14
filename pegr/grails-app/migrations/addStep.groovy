databaseChangeLog = {

	changeSet(author: "danyingshao (generated)", id: "1476405265440-1") {
		addColumn(tableName: "analysis") {
			column(name: "step", type: "varchar(255)")
		}
	}
}
