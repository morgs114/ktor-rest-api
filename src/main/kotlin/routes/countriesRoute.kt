package routes

import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import model.Country
import kotlin.text.get

// mutable list of Country objects, as our data does not originate from a database
private val countries = mutableListOf<Country>(
    Country("1", "Japan", "Asia", "Tokyo"),
    Country("2", "Egypt", "Africa", "Cairo"),
    Country("3", "Canada", "North America", "Ottawa"),
    Country("4", "Brazil", "South America", "Brasília"),
    Country("5", "New Zealand", "Oceania", "Wellington"),
    Country("6", "England", "Europe", "London"),
)

// extension function of route class in kotlin - way to group related routes
fun Route.countries() {
    // sets up route group called countries with path /countries - all routes defined within this block will have /countries prepended to their path
    route("/countries") {
        // when a GET request is made to /countries, the server responds with a list of countries & ok status code
        get {
            call.respond(
                HttpStatusCode.OK,
                countries
            )
        }

        // when a GET request is made to /countries/{id}, the server stores the id parameter in a variable
        // if id is not provided, server responds with bad request status code
        get("{id?}") {
            val id = call.parameters["id"]?: return@get call.respondText(
                "missing id",
                status = HttpStatusCode.BadRequest
            )

            // the server searches for a country with the id provided in the list of countries
            // if the country with that id is not found, server responds with not found status code
            val country = countries.find { it.id == id }?:return@get call.respondText(
                "country with id $id not found",
                status = HttpStatusCode.NotFound
            )

            // if the country with the id is found, server responds with the country object
            call.respond(country)
        }

        // post request expects a Country object in the request body - adds the country to the list of countries and responds with created status code
        post {
            val country = call.receive<Country>()
            countries.add(country)
            call.respondText(
                "Country added ",
                status = HttpStatusCode.Created
            )
        }
    }
}