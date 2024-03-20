package com.example.rickandmorty.mmodel

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "results")
@JsonClass(generateAdapter = true)
data class Result(
    @Json(name = "id")
    @PrimaryKey val id: Int,

    @Json(name = "created")
    val created: String? = null,

    //@Json(name = "episode")
    //val episode: List<String>? = null,

    @Json(name = "gender")
    val gender: String?,

    @Json(name = "image")
    val image: String?,

    //@Json(name = "location")
    //val location: Location?,

    @Json(name = "name")
    val name: String?,

    //@Json(name = "origin")
    //val origin: Origin? = null,

    @Json(name = "species")
    val species: String?,
    @Json(name = "status")
    val status: String?,
    @Json(name = "type")
    val type: String?,
    @Json(name = "url")
    val url: String?
){
    constructor(id:Int, image:String, name:String, species:String, gender: String)
            : this(id,image,name,species,gender, "", "", "", "")
}