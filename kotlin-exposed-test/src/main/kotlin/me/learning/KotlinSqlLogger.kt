package me.learning

import io.github.oshai.kotlinlogging.KotlinLogging
import org.jetbrains.exposed.sql.SqlLogger
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.statements.StatementContext

private val logger = KotlinLogging.logger {}

object KotlinSqlLogger : SqlLogger {
    override fun log(context: StatementContext, transaction: Transaction) {
        logger.info { "SQL: ${context.statement}" }
    }
}
