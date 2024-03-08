package com.nrup.ktor


import com.nrup.ktor.backend.config.configureContentNegotiation
import com.nrup.ktor.backend.config.configureDatabase
import com.nrup.ktor.backend.config.configureRouting
import io.ktor.server.application.*
import com.nrup.ktor.backend.data.db.DatabaseFactory
import com.nrup.ktor.backend.repository.auth.AuthRepository
import com.nrup.ktor.backend.repository.auth.AuthRepositoryImpl
import com.nrup.ktor.backend.security.configureSecurity
import com.nrup.ktor.backend.data.service.auth.AuthService
import com.nrup.ktor.backend.data.service.auth.AuthServiceImpl
import com.nrup.ktor.backend.routes.auth.authRoutes
import com.nrup.ktor.plugins.configureMonitoring
import com.nrup.ktor.plugins.configureSerialization
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


fun main(args: Array<String>): Unit = EngineMain.main(args)
@Suppress("unused")
fun Application.module() {
    configureDatabase()
    configureContentNegotiation()
    configureSecurity()
    configureRouting()
}