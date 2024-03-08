package com.nrup.ktor


import io.ktor.server.application.*
import com.nrup.ktor.plugins.*
import com.nrup.ktor.backend.db.DatabaseFactory
import com.nrup.ktor.backend.repository.UserRepository
import com.nrup.ktor.backend.repository.UserRepositoryImpl
import com.nrup.ktor.backend.security.configureSecurity
import com.nrup.ktor.backend.service.UserService
import com.nrup.ktor.backend.service.UserServiceImpl
import io.ktor.serialization.jackson.*
import io.ktor.server.auth.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

/*fun main(args: Array<String>): Unit =
    EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    configureSerialization()
    configureMonitoring()
    configureRouting()

}*/

fun main() {

    // Netty : From io.ktor.server.netty run on port 8080 hosted on 127.0.01 machine

    embeddedServer(Netty, port = 8080, host = "127.0.01") {

        // Doing exposed.sql Database initialization with Hikari pooled DataSource
        DatabaseFactory.init()

        /*  ContentNegotiation : Negotiating media types between the client and server.
            Serializing/deserializing the content in a specific format
            Ktor supports : JSON, Jackson, XML, CBOR and ProtoBuf
        */

        install(ContentNegotiation) {
            // We are using jackson for serialization and deserialization
            jackson()
        }

        // SECURITY WITH JWT TOKEN
        configureSecurity()

        val service: UserService = UserServiceImpl()
        val repository: UserRepository = UserRepositoryImpl(service)

        // NAVIGATION
        authRoutes(repository)

        // ==================== just for testing that auth token working or not
        // Put all APIs which required Auth Token will go inside this authenticate block
        routing {
            authenticate {
                get("/testurl") {
                    call.respond("working fine")
                }
            }
        }
    }.start(wait = true)

}
