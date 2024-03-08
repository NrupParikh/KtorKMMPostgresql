package com.nrup.ktor.backend.data.service.auth

import com.nrup.ktor.backend.data.db.DatabaseFactory.dbQuery
import com.nrup.ktor.backend.data.db.schema.UserTable
import com.nrup.ktor.backend.data.models.User
import com.nrup.ktor.backend.security.hash
import com.nrup.ktor.backend.routes.auth.CreateUserParams
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.statements.InsertStatement

class AuthServiceImpl : AuthService {
    override suspend fun registerUser(params: CreateUserParams): User? {
        var statement: InsertStatement<Number>? = null
        dbQuery {
            statement = UserTable.insert {
                it[email] = params.email
                // Storing encrypted password
                it[password] = hash(params.password)
                it[fullName] = params.fullName
                it[avatar] = params.avatar
            }
        }
        return rowToUser(statement?.resultedValues?.get(0))
    }

    override suspend fun findUserByEmail(emailId: String): User? {
        val user = dbQuery {
            UserTable.select {
                UserTable.email.eq(emailId)
            }.map {
                rowToUser(it)
            }.singleOrNull()
        }
        return user
    }

    override suspend fun loginUser(email: String, password: String): User? {
        val hashedPassword = hash(password)
        val userRow = dbQuery { UserTable.select { UserTable.email eq email and (UserTable.password eq hashedPassword) }.firstOrNull() }
        return rowToUser(userRow)
    }


    // Return the response
    private fun rowToUser(row: ResultRow?): User? {
        return if (row == null) null
        else User(
            id = row[UserTable.id],
            fullName = row[UserTable.fullName],
            avatar = row[UserTable.avatar],
            email = row[UserTable.email],
            createdAt = row[UserTable.createdAt].toString(),
        )
    }
}