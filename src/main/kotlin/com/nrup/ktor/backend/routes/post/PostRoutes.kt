package com.nrup.ktor.backend.routes.post

import com.nrup.ktor.backend.repository.post.PostRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.postRoutes(repository: PostRepository) {
    val log = this.log
    routing {
        route("/post") {

            // =========== CREATE POST
            post("/createPost") {
                log.info("Received create post request: $call")
                val params = call.receiveNullable<PostParams>()
                if (params == null) {
                    call.respond(
                        status = HttpStatusCode.BadRequest,
                        message = PostResponse(
                            errorMessage = "Invalid credentials"
                        )
                    )
                    return@post
                }
                val result = repository.createNewPost(params)
                call.respond(status = result.statusCode, message = result.data)
            }

            // =========== GET ALL POST
            authenticate {
                get("/getAllPost") {
                    log.info("Received get all post request: $call")
                    val result = repository.getAllPost()
                    call.respond(status = result.statusCode, message = result.data)
                }
            }
        }
    }
}