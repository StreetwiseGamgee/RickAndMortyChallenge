package com.example.rickandmorty.mmodel

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Location(
    @Json(name = "name")
    val name: String,
    @Json(name = "url")
    val url: String
)