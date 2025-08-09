package me.learning

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDate
import kotlin.test.AfterTest
import kotlin.test.Test

class BoardTest {
    @AfterTest
    fun tearDown() {
        connect()
        transaction {
            addLogger(KotlinSqlLogger)
            SchemaUtils.drop(User, Board)
        }
    }

    @Test
    fun `테이블 join`() {
        connect()
        transaction {
            addLogger(KotlinSqlLogger)
            SchemaUtils.create(User, Board)

            val new = UserEntity.new {
                name = "김성호"
                email = "test@test.com"
                birthDay = LocalDate.now()
                gender = Gender.MALE
            }
            BoardEntity.new {
                title = "제목 입니다"
                content = "내용 입니다."
                author = new.id
            }

            val selectAll = Board.join(
                User,
                JoinType.INNER,
                onColumn = Board.author,
                otherColumn = User.id
            ).select(
                Board.id,
                User.name,
                Board.title,
                Board.content,
            ).where(Board.id eq 1L)

            println(selectAll.toList())

        }
    }

    @Test
    fun `테이블 insert`() {
        connect()
        transaction {
            addLogger(KotlinSqlLogger)
            SchemaUtils.create(User, Board)

            val new = UserEntity.new {
                name = "김성호"
                email = "test@test.com"
                birthDay = LocalDate.now()
                gender = Gender.MALE
            }

            val boarId = Board.insertAndGetId {
                it[author] = new.id
                it[title] = "제목 입니다"
                it[content] = " 내용입니다"
            }
            println(boarId)

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