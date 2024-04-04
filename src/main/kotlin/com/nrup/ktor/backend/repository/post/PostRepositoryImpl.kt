package com.nrup.ktor.backend.repository.post

import com.nrup.ktor.backend.base.BaseResponse
import com.nrup.ktor.backend.constants.APIConstants
import com.nrup.ktor.backend.data.service.post.PostService
import com.nrup.ktor.backend.routes.post.PostListResponse
import com.nrup.ktor.backend.routes.post.PostParams
import com.nrup.ktor.backend.routes.post.PostResponse
import com.nrup.ktor.backend.routes.post.PostResponseData
import io.ktor.http.*

@Suppress("UNCHECKED_CAST")
class PostRepositoryImpl(
    private val postService: PostService
) : PostRepository {
    override suspend fun createNewPost(params: PostParams): BaseResponse<PostResponse> {
        val post = postService.createPost(params)
        return if (post != null) {
            BaseResponse.SuccessResponse(data = PostResponse(post))
        } else {
            BaseResponse.ErrorResponse(
                code = HttpStatusCode.InternalServerError,
                data = PostResponse(errorMessage = APIConstants.msgSomethingWentWrong)
            )
        }
    }

    override suspend fun getAllPost(): BaseResponse<PostListResponse> {
        val postList = postService.getAllPost()
        return if (postList.isNotEmpty()) {
            BaseResponse.SuccessResponse(
                data = PostListResponse(
                    data = postList as List<PostResponseData>,
                    errorMessage = null
                )
            )
        } else {
            BaseResponse.ErrorResponse(
                code = HttpStatusCode.OK,
                data = PostListResponse(data = emptyList(), errorMessage = APIConstants.msgNoPostFound)
            )

        }
    }
}