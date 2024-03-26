package com.nrup.ktor.backend.data.db

import com.nrup.ktor.backend.data.db.schema.UserTable
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    fun init() {
        // Connecting database with hikari data source
        Database.connect(hikari())

        // Creating table
        transaction {
            SchemaUtils.create(UserTable)
        }
    }

    // HikariDataSource is a connection pool implementation for Java applications
    // that simplifies managing database connections

    private fun hikari(): HikariDataSource {
        val config = HikariConfig()
        config.driverClassName = "org.postgresql.Driver"

        // mydb is our database name
        config.jdbcUrl = "jdbc:postgresql:mydb"

        // Define credential for my pgAdmin [postgres]
        config.username = "postgres"
        config.password = "1234"
        config.maximumPoolSize = 3
        config.isAutoCommit = false
        config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        config.validate()
        return HikariDataSource(config)
    }

    suspend fun <T> dbQuery(block: () -> T): T = withContext(Dispatchers.IO) {
        transaction {
            block()
        }
    }
}