plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlinSerialization)
    application
}

group = "online.muhammadali.kite"
version = "1.0.0"
application {
    mainClass.set("online.muhammadali.kite.ApplicationKt")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=${extra["development"] ?: "false"}")
}

dependencies {
    implementation(projects.shared)
    implementation(libs.logback)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)

    //  Monogo DB
    implementation(libs.mongodb.driver.kotlin.coroutine)
    implementation(libs.bson.kotlinx)

    //  Kotlin Serialization
    implementation(libs.kotlinx.serialization.json)

    testImplementation(libs.ktor.server.tests)
    testImplementation(libs.kotlin.test.junit)
}