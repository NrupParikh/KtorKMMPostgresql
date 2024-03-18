package com.nrup.ktor.backend.data.service.post

import com.nrup.ktor.backend.routes.post.PostParams
import com.nrup.ktor.backend.routes.post.PostResponseData

interface PostService {
    suspend fun createPost(params: PostParams): PostResponseData?
    suspend fun getAllPost(): List<PostResponseData?>
}
