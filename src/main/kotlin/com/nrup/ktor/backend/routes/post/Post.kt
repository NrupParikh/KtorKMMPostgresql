package com.nrup.ktor.backend.routes.post

import kotlinx.serialization.Serializable


@Serializable
data class PostParams(
    val title: String,
    val imageUrl: String,
)

@Serializable
data class PostResponse(
    val data: PostResponseData? = null,
    val errorMessage: String? = null
)

@Serializable
data class PostResponseData(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val createdAt: String
)

// ==== post list

@Serializable
data class PostListResponse(
    val data: List<PostResponseData> = listOf(),
    val errorMessage: String? = null
)