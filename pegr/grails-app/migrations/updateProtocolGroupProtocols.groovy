databaseChangeLog = {

    changeSet(author: "danyingshao (generated)", id: "1576768506139-48") {
        addColumn(tableName: "protocol_group_protocols") {
            column(autoIncrement: "true", name: "id", type: "bigint") {
                constraints(nullable: "false",
                primaryKey: "true", primaryKeyName:"protocol_group_protocolsPK")
            }
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1576768506139-50") {
        addUniqueConstraint(columnNames: "protocols_idx, protocol_group_id, protocol_id", constraintName: "UK413d0a510ff03d1f19c83af9ae36", tableName: "protocol_group_protocols")
    }

    changeSet(author: "danyingshao (generated)", id: "1576768506139-1") {
        addNotNullConstraint(columnDataType: "int", columnName: "protocols_idx", tableName: "protocol_group_protocols")
    }
}
