databaseChangeLog = {

    changeSet(author: "danyingshao (generated)", id: "1605124372433-47") {
        addColumn(tableName: "protocol") {
            column(name: "url", type: "varchar(255)")
        }
    }

    changeSet(author: "danyingshao (generated)", id: "1605124372433-48") {
        addUniqueConstraint(columnNames: "function, item_type_id, protocol_id", constraintName: "UKccd9281ba8d5bd121364d7b5eb3c", tableName: "protocol_item_types")
    }

    changeSet(author: "danyingshao (generated)", id: "1605124372433-49") {
        dropUniqueConstraint(constraintName: "unique_protocol_id", tableName: "protocol_item_types")
    }

}
