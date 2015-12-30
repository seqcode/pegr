databaseChangeLog = {

	changeSet(author: "danyingshao (generated)", id: "1451495337330-1") {
		createTable(tableName: "ab_host") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "ab_hostPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(30)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451495337330-2") {
		createIndex(indexName: "name_uniq_1451495337310", tableName: "ab_host", unique: "true") {
			column(name: "name")
		}
	}

	include file: 'changelog-a.groovy'

	include file: 'changelog-a2.groovy'

	include file: 'changelog-b.groovy'

	include file: 'changelog-c.groovy'

	include file: 'changelog-c2.groovy'

	include file: 'changelog-d.groovy'

	include file: 'changelog-o.groovy'

	include file: 'changelog-u.groovy'
}
