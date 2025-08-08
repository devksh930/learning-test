package me.learning

import org.jetbrains.exposed.sql.javatime.date

object User : BaseTable("users") {
    val name = varchar("name", length = 50)
    val email = varchar("email", length = 50)
    val birthDay = date("birthdate")
}