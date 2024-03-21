package com.example.rickandmorty.api

import androidx.compose.runtime.mutableStateOf
import com.example.rickandmorty.mmodel.CastMember

class RickAndMortyViewModel {
    private val _characters = mutableStateOf<List<CastMember>>(emptyList())

    val characters: List<CastMember>
        get() = _characters.value

    fun updateCharacters(newCharacters: List<CastMember>) {
        _characters.value = newCharacters
    }
}