package me.learning

import org.jetbrains.exposed.sql.javatime.date

object User : BaseTable("users") {
    val name = varchar("name", length = 50)
    val email = varchar("email", length = 50)
    val birthDay = date("birthdate")
    val gender = customEnumeration(
        name = "gender",
        sql = "ENUM('MALE', 'FEMALE')",
        fromDb = { value -> Gender.valueOf(value as String) },
        toDb = { it.name }
    )
    init{
        index("idx_name_email", true, name, email)
    }
}