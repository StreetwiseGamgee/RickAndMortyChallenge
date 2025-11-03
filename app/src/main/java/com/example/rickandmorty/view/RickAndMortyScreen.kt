package com.example.rickandmorty.view

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.rickandmorty.api.RickAndMortyManager
import com.example.rickandmorty.card.CharacterCard
import kotlinx.coroutines.launch

@Composable
fun RickAndMortyScreen(rickMortyManager: RickAndMortyManager, navController : NavHostController) {

    val rickmorty by rickMortyManager.rickAndMortyResponse
    val scope = rememberCoroutineScope()

    Log.i("RickMortyScreen", "RickMortyResponse size: ${rickmorty.size}")
    Log.i("RickMortyScreen", "First character name: ${rickmorty.firstOrNull()?.name}")

    //Text(text="test2")

    for (character in rickmorty){
        Log.i("name", "${character.name}")
        //Log.i("origin", "${character.origin}")
    }

    LazyColumn {
        items(rickmorty) { character ->
            CharacterCard(
                characterItem = character,
                navController = navController,
                onCharacterDeleted = {
                    scope.launch {
                        rickMortyManager.refreshCastMembers()
                    }
                }
            )
        }
    }
}
