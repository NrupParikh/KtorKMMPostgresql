package com.nrup.ktor


import io.ktor.server.application.*
import com.nrup.ktor.plugins.*
import com.nrup.ktor.backend.db.DatabaseFactory
import com.nrup.ktor.backend.repository.UserRepository
import com.nrup.ktor.backend.repository.UserRepositoryImpl
import com.nrup.ktor.backend.service.UserService
import com.nrup.ktor.backend.service.UserServiceImpl
import io.ktor.serialization.jackson.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*

/*fun main(args: Array<String>): Unit =
    EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    configureSerialization()
    configureMonitoring()
    configureRouting()

}*/

fun main() {
    embeddedServer(Netty, port = 8080, host = "127.0.01") {
        DatabaseFactory.init()
        install(ContentNegotiation) {
           jackson()
        }
        val service: UserService = UserServiceImpl()
        val repository: UserRepository = UserRepositoryImpl(service)

        authRoutes(repository)
    }.start(wait = true)

}
