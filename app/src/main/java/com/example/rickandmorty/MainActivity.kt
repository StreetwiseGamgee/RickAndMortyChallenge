package com.example.rickandmorty

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rickandmorty.api.RickAndMortyManager
import com.example.rickandmorty.db.AppDataBase
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
                    var context: Context = LocalContext.current
                    val db = AppDataBase.getInstance(context)
                    val navController = rememberNavController()
                    val rickAndMortyManager: RickAndMortyManager = RickAndMortyManager(db)
                    RickMortyScaffold(navController = navController, rickMortyManager = rickAndMortyManager)
                }
            }
        }
    }
}

@Composable
fun RickMortyScaffold(navController: NavHostController, rickMortyManager: RickAndMortyManager) {
    Scaffold(
        bottomBar = {
        }, content = { innerPadding ->
            //NavHost
            NavHost(
                navController = navController,
                startDestination = Destination.RickAndMortyDestination.route,
                modifier = Modifier.padding(innerPadding)
                    ){
                composable(Destination.RickAndMortyDestination.route) {
                    RickAndMortyScreen(rickMortyManager, navController)
                }
            }
        }
    )
}