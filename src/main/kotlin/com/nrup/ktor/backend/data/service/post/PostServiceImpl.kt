package com.nrup.ktor.backend.data.service.post

import com.nrup.ktor.backend.data.db.DatabaseFactory
import com.nrup.ktor.backend.data.db.schema.PostTable
import com.nrup.ktor.backend.routes.post.PostParams
import com.nrup.ktor.backend.routes.post.PostResponseData
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.statements.InsertStatement


class PostServiceImpl : PostService {

    // ==================== POST
    override suspend fun createPost(params: PostParams): PostResponseData? {
        var statement: InsertStatement<Number>? = null
        return DatabaseFactory.dbQuery {
            statement = PostTable.insert {
                it[title] = params.title
                it[imageUrl] = params.imageUrl
            }
            statement?.resultedValues?.singleOrNull()?.let {
                rowToPost(it)
            }
        }
    }

    private fun rowToPost(row: ResultRow?): PostResponseData? {
        return if (row == null) null
        else PostResponseData(
            id = row[PostTable.id],
            title = row[PostTable.title],
            imageUrl = row[PostTable.imageUrl],
            createdAt = row[PostTable.createdAt].toString(),
        )
    }

    override suspend fun getAllPost(): List<PostResponseData?> {
        val rows= DatabaseFactory.dbQuery {
            PostTable.selectAll().map { rowToPost(it) }
        }
        return rows
    }
}