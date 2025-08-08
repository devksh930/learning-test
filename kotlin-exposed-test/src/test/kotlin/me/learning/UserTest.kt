package me.learning

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import java.time.LocalDate
import kotlin.test.AfterTest
import kotlin.test.Test


class UserTest {


    @AfterTest
    fun tearDown() {
        connect()
        transaction {
            addLogger(KotlinSqlLogger)
            SchemaUtils.drop(User)
        }
    }

    @Test
    fun `유저 생성 entity 사용`() {
        connect()

        transaction {
            addLogger(KotlinSqlLogger)
            SchemaUtils.create(User)

            UserEntity.new {
                name = "김성호"
                email = "test@test.com"
                birthDay = LocalDate.now()
            }

            val findById = UserEntity.findById(1L)
            println(findById)
        }
    }

    @Test
    fun `유저  생성 table 사용`() {
        connect()

        transaction {
            addLogger(KotlinSqlLogger)
            SchemaUtils.create(User)

            User.insert {
                it[name] = "김성호"
                it[email] = "test@test.com"
                it[birthDay] = LocalDate.now()
            } get User.id

            val findById = UserEntity.findById(1L)
            println(findById)

        }

    }

    @Test
    fun `entity 변경감지 업데이트`() {
        connect()
        transaction {
            addLogger(KotlinSqlLogger)
            SchemaUtils.create(User)

            val findById = UserEntity.findById(1L)
            findById?.changeName("김성호")

        }

    }

    @Test
    fun `table 업데이트`() {
        connect()
        transaction {
            addLogger(KotlinSqlLogger)
            SchemaUtils.create(User)

            User.update({ User.id eq 1L }) {
                it[name] = "홍길동"
            }

        }
    }

    @Test
    fun `entity 삭제`() {
        connect()
        transaction {
            addLogger(KotlinSqlLogger)
            SchemaUtils.create(User)

            val id = User.insert {
                it[name] = "김성호"
                it[email] = "test@test.com"
                it[birthDay] = LocalDate.now()
            } get User.id

            UserEntity.findById(id)?.delete()
        }
    }

    @Test
    fun `table 삭제`() {
        connect()
        transaction {
            addLogger(KotlinSqlLogger)
            SchemaUtils.create(User)

            User.deleteWhere { User.id eq 1L }
        }
    }


    private fun connect() {
        Database.connect(
            "jdbc:mysql://localhost:3306/learning",
            driver = "com.mysql.cj.jdbc.Driver",
            user = "root",
            password = "spring123"
        )
    }
}
