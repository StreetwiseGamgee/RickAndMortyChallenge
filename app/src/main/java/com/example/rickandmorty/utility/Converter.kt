package com.example.rickandmorty.utility

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.example.rickandmorty.mmodel.Origin

class Converter {
    private val moshi: Moshi = Moshi.Builder().build()

    @TypeConverter
    fun fromString(value: String?): List<Int>? {
        if (value == null) return null

        val listType = Types.newParameterizedType(List::class.java, Integer::class.javaObjectType)
        val adapter: JsonAdapter<List<Int>> = moshi.adapter(listType)

        return adapter.fromJson(value)
    }

    //
    // create converter for Origin
    @TypeConverter
    fun fromOrigin(value: Origin?): String? {
        if (value == null) return null

//        val type = Types.newParameterizedType(List::class.java, Origin::class.java)
//        val adapter: JsonAdapter<List<Origin>> = moshi.adapter(type)
        val adapter: JsonAdapter<Origin> = moshi.adapter(Origin::class.java)
        return adapter.toJson(value)
    }
}