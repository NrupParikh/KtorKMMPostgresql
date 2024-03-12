package com.nrup.ktor.backend.data.service.auth

import AuthResponseData
import SignUpParams
import com.nrup.ktor.backend.data.db.DatabaseFactory.dbQuery
import com.nrup.ktor.backend.data.db.schema.UserTable
import com.nrup.ktor.backend.security.hash
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.statements.InsertStatement

class AuthServiceImpl : AuthService {
    override suspend fun registerUser(params: SignUpParams): AuthResponseData? {
        var statement: InsertStatement<Number>? = null
        return dbQuery {
            statement = UserTable.insert {
                it[email] = params.email
                // Storing encrypted password
                it[password] = hash(params.password)
                it[fullName] = params.fullName
            }
            statement?.resultedValues?.singleOrNull()?.let {
                rowToUser(it)
            }
        }
    }

    override suspend fun findUserByEmail(params: String): AuthResponseData? {
        return dbQuery {
            UserTable.select {
                UserTable.email.eq(params)
            }.map {
                rowToUser(it)
            }.singleOrNull()
        }
    }

    override suspend fun loginUser(email: String, password: String): AuthResponseData? {
        val hashedPassword = hash(password)
        val userRow = dbQuery {
            UserTable.select { UserTable.email eq email and (UserTable.password eq hashedPassword) }.firstOrNull()
        }
        return rowToUser(userRow)
    }


    // Return the response
    private fun rowToUser(row: ResultRow?): AuthResponseData? {
        return if (row == null) null
        else AuthResponseData(
            id = row[UserTable.id],
            fullName = row[UserTable.fullName],
            email = row[UserTable.email],
            createdAt = row[UserTable.createdAt].toString(),
        )
    }
}