package com.example.rickandmorty.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object Api {
    private val BASE_URL = "https://rickandmortyapi.com/api/"

    //moshi
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    //retrofit
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

    val retrofitService: RickAndMortyService by lazy {
        retrofit.create(RickAndMortyService::class.java)
    }
}