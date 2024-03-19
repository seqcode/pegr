databaseChangeLog = {
    changeSet(author: "danyingshao (generated)", id: "1620671544570-1") {
        sql("""alter table sequence_run modify column lane varchar(10);""")
    }
}