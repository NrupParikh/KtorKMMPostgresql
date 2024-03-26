package com.nrup.ktor.backend.config

import com.nrup.ktor.backend.data.db.DatabaseFactory
import com.nrup.ktor.backend.di.RepositoryProvider
import com.nrup.ktor.backend.routes.auth.authRoutes
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.plugins.contentnegotiation.*
import org.slf4j.event.Level

// Doing exposed.sql Database initialization with Hikari pooled DataSource
fun configureDatabase() {
    DatabaseFactory.init()
}

fun Application.configureMonitoring() {
    install(CallLogging) {
        level = Level.INFO
    }
}

/*  ContentNegotiation : Negotiating media types between the client and server.
     Serializing/deserializing the content in a specific format
     Ktor supports : JSON, Jackson, XML, CBOR and ProtoBuf
 */
fun Application.configureContentNegotiation() {
    install(ContentNegotiation) {
        jackson()
    }
}

fun Application.configureRouting() {
    authRoutes(RepositoryProvider.provideAuthRepository())
}