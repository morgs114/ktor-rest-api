// This file is where routes are defined that our server responds to - here we define/configure routes

package com.example

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.calllogging.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.slf4j.event.*
import routes.countries

fun Application.configureRouting() {
    routing {
        // call extension function countries() from countriesRoute.kt
        countries()
    }
}
