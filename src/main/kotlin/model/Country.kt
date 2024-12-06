package model

import kotlinx.serialization.Serializable

// @Serializable annotation enables us to parse the content of the Country data class into JSON data and send it over the network
@Serializable
data class Country(
    val id: String,
    val name: String,
    val continent: String,
    val capital: String
)
