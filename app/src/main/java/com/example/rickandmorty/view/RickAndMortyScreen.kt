package com.example.rickandmorty.view

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.rickandmorty.api.RickAndMortyManager
import com.example.rickandmorty.card.CharacterCard

@Composable
fun RickAndMortyScreen(rickMortyManager: RickAndMortyManager, navController : NavHostController) {

    val rickmorty = rickMortyManager.rickAndMortyResponse.value
    Log.i("RickMortyScreen", "RickMortyResponse size: ${rickmorty.size}")
    Log.i("RickMortyScreen", "First character name: ${rickmorty.firstOrNull()?.name}")

    //Text(text="test2")

    for (character in rickmorty){
        Log.i("name", "${character.name}")
        //Log.i("origin", "${character.origin}")
    }

    LazyColumn {
        items(rickmorty) { character ->
            CharacterCard(characterItem = character, navController)
        }
    }
}
