package com.example.rickandmorty.api

import com.example.rickandmorty.mmodel.CastMember
import com.example.rickandmorty.mmodel.CastMemberData
import retrofit2.Call
import retrofit2.http.GET

interface RickAndMortyService {
    @GET("api/character")
    fun getCharacter(): Call<CastMemberData>
}