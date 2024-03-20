package com.example.rickandmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.rickandmorty.api.RickAndMortyManager
import com.example.rickandmorty.ui.theme.RickAndMortyTheme
import com.example.rickandmorty.view.RickAndMortyScreen

sealed class Destination(val route: String) {
    object RickAndMortyDestination: Destination("rickmorty")
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMortyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                }
            }
        }
    }
}

@Composable
fun RickMortyScaffold(navController: NavHostController, rickMortyManager: RickAndMortyManager) {
    Scaffold(
        bottomBar = {
        }
    ) {
            paddingValues ->

        //NavHost
        NavHost(navController = navController, startDestination = Destination.RickAndMortyDestination.route){
            composable(Destination.RickAndMortyDestination.route) {
                RickAndMortyScreen(rickMortyManager, navController)
            }
        }
    }
}