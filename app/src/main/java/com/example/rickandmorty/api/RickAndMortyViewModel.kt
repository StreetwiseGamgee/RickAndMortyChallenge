package com.example.rickandmorty.api

import androidx.compose.runtime.mutableStateOf
import com.example.rickandmorty.mmodel.Result

class RickAndMortyViewModel {
    private val _characters = mutableStateOf<List<Result>>(emptyList())

    val characters: List<Result>
        get() = _characters.value

    fun updateCharacters(newCharacters: List<Result>) {
        _characters.value = newCharacters
    }
}