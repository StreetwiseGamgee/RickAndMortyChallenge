package com.example.rickandmorty.mmodel

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CastMemberData(
    @Json(name = "info")
    val info: Info? = null,
    @Json(name = "results")
    val results: List<CastMember>
)