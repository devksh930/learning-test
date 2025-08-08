package me.learning

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime


abstract class BaseTable(name: String) : LongIdTable(name) {
    val createdAt: Column<LocalDateTime> =
        datetime("created_at").clientDefault { LocalDateTime.now() }

    val updatedAt: Column<LocalDateTime> =
        datetime("updated_at").clientDefault { LocalDateTime.now() }
}