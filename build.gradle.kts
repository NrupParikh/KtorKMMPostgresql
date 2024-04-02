val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val exposed_version: String by project
val postgrey_sql_version: String by project
val hikaricp_version: String by project

plugins {
    kotlin("jvm") version "1.8.22"
    id("io.ktor.plugin") version "2.3.2"
                id("org.jetbrains.kotlin.plugin.serialization") version "1.8.22"
}

group = "com.nrup.ktor"
version = "0.0.1"
application {
    mainClass.set("com.nrup.ktor.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    // Ktor
    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    // Netty Engine
    implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")

    // Content Negotiation
    implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktor_version")

    // Call logging
    implementation("io.ktor:ktor-server-call-logging-jvm:$ktor_version")

    // For serialization / deserialization of JSON objects using Jackson
    implementation("io.ktor:ktor-serialization-jackson:$ktor_version")

    // For JWT For User Authentication
    implementation("io.ktor:ktor-server-auth:$ktor_version")
    implementation("io.ktor:ktor-server-auth-jwt:$ktor_version")

    // EXPOSED (Kotlin DSL)
    implementation("org.jetbrains.exposed:exposed-core:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-dao:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-java-time:$exposed_version")

    // Hikari : Creating JDBC Database Connection Pool
    implementation("com.zaxxer:HikariCP:$hikaricp_version")

    // Postgrey SQL (RDBMS)
    implementation("org.postgresql:postgresql:$postgrey_sql_version")

    implementation("io.ktor:ktor-server-host-common-jvm:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}