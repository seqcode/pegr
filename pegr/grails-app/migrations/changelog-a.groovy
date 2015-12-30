databaseChangeLog = {

	changeSet(author: "danyingshao (generated)", id: "1451500947124-1") {
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

	changeSet(author: "danyingshao (generated)", id: "1451500947124-2") {
		createTable(tableName: "address") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "addressPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "city", type: "varchar(30)") {
				constraints(nullable: "false")
			}

			column(name: "country", type: "varchar(20)") {
				constraints(nullable: "false")
			}

			column(name: "line1", type: "varchar(100)") {
				constraints(nullable: "false")
			}

			column(name: "line2", type: "varchar(255)")

			column(name: "postal_code", type: "varchar(10)") {
				constraints(nullable: "false")
			}

			column(name: "state", type: "varchar(20)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451500947124-3") {
		createTable(tableName: "align_type") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "align_typePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(30)") {
				constraints(nullable: "false")
			}

			column(name: "short_name", type: "varchar(20)")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451500947124-4") {
		createTable(tableName: "aligner") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "alignerPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "aligner_version", type: "varchar(10)")

			column(name: "software", type: "varchar(30)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451500947124-5") {
		createIndex(indexName: "name_uniq_1451500947055", tableName: "ab_host", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451500947124-6") {
		createIndex(indexName: "name_uniq_1451500947065", tableName: "align_type", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451500947124-7") {
		createIndex(indexName: "short_name_uniq_1451500947066", tableName: "align_type", unique: "true") {
			column(name: "short_name")
		}
	}

	changeSet(author: "danyingshao (generated)", id: "1451500947124-8") {
		createIndex(indexName: "unique_software", tableName: "aligner", unique: "true") {
			column(name: "aligner_version")

			column(name: "software")
		}
	}
}
