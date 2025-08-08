package me.learning

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDate
import kotlin.test.Test

class UserTest {

    @Test
    fun `유저 생성`() {
        Database.connect(
            "jdbc:mysql://localhost:3306/learning",
            driver = "com.mysql.cj.jdbc.Driver",
            user = "root",
            password = "spring123"
        )

        transaction {
            addLogger(StdOutSqlLogger)
            User.insert {
                it[name] = "김성호"
                it[email] = "test@test.com"
                it[birthDay] = LocalDate.now()
            }get User.id

        }
    }

    @Test
    fun `유저 조회`(){
        Database.connect(
            "jdbc:mysql://localhost:3306/learning",
            driver = "com.mysql.cj.jdbc.Driver",
            user = "root",
            password = "spring123"
        )

        transaction {
            addLogger(StdOutSqlLogger)
            val user = User.selectAll().limit(1)
            println("User: $user")
        }
    }
}