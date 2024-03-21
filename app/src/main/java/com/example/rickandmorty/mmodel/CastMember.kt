package com.example.rickandmorty.mmodel

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "tbl_cast_members")
@JsonClass(generateAdapter = true)
data class CastMember(

    @Json(name = "id")
    //@ColumnInfo(name="id")
    @PrimaryKey val id: Int,

    @Json(name = "image")
    //@ColumnInfo(name="image")
    val image: String,

    @Json(name = "name")
    //@ColumnInfo(name="name")
    var name: String,

    @Json(name = "species")
    //@ColumnInfo(name="species")
    var species: String,

    @Json(name = "gender")
    //@ColumnInfo(name="gender")
    var gender: String,

    // not in db?
    @Json(name = "created")
    @Ignore val created: String?,

    @Json(name = "status")
    @Ignore val status: String?,

    @Json(name = "type")
    @Ignore val type: String?,

    @Json(name = "url")
    @Ignore val url: String?,

    // complex types
    @Json(name = "episode")
    @Ignore val episode: List<String>?,

    @Json(name = "location")
    @Ignore val location: Location?,

    @Json(name = "origin")
    @Ignore val origin: Origin?,


    ){
    constructor(id:Int, image:String, name:String, species:String, gender: String)
            : this(id,image,name,species,gender,"","","",null,null,null,null)
}