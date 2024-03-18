package com.nrup.ktor.backend.data.db.schema

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object PostTable : Table("post") {
    val id = integer("id").autoIncrement()
    val title = varchar("title", 256)
    val imageUrl = varchar("image_url", 256)
    val createdAt = datetime("created_at").clientDefault { LocalDateTime.now() }

    override val primaryKey = PrimaryKey(id)
}