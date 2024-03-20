package com.example.rickandmorty.api

import com.example.rickandmorty.mmodel.RickAndMorty
import retrofit2.Call
import retrofit2.http.GET

interface RickAndMortyService {
    @GET("api/character")
    fun getCharacter(): Call<RickAndMorty>
}