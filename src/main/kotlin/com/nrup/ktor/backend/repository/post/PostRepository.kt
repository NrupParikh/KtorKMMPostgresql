package com.nrup.ktor.backend.repository.post

import com.nrup.ktor.backend.base.BaseResponse
import com.nrup.ktor.backend.routes.post.PostListResponse
import com.nrup.ktor.backend.routes.post.PostParams
import com.nrup.ktor.backend.routes.post.PostResponse

interface PostRepository {
    suspend fun createNewPost(params: PostParams): BaseResponse<PostResponse>
    suspend fun getAllPost(): BaseResponse<PostListResponse>
}