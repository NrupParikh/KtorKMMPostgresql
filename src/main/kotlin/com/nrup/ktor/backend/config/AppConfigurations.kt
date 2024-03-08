package com.nrup.ktor.backend.config

import com.nrup.ktor.backend.data.db.DatabaseFactory
import com.nrup.ktor.backend.di.RepositoryProvider
import com.nrup.ktor.backend.routes.auth.authRoutes
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*

fun configureDatabase(){
    // Doing exposed.sql Database initialization with Hikari pooled DataSource
    DatabaseFactory.init()
}

fun Application.configureContentNegotiation(){
    /*  ContentNegotiation : Negotiating media types between the client and server.
         Serializing/deserializing the content in a specific format
         Ktor supports : JSON, Jackson, XML, CBOR and ProtoBuf
     */

    install(ContentNegotiation) {
        // We are using jackson for serialization and deserialization
        jackson()
    }
}

fun Application.configureRouting(){
    authRoutes(RepositoryProvider.provideAuthRepository())
}