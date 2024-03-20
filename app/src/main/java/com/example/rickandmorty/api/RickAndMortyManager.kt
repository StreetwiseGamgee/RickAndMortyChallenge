package com.example.rickandmorty.api

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rickandmorty.db.AppDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.rickandmorty.mmodel.Result
import com.example.rickandmorty.mmodel.RickAndMorty

class RickAndMortyManager(database: AppDataBase) {
    private var _rickAndMorttyResponse = mutableStateOf<List<Result>>(emptyList())
    val rickAndMortyURL: String = "https://rickandmortyapi.com/api/character"

    val rickAndMortyResponse: MutableState<List<Result>>
        @Composable get() = remember {
            _rickAndMorttyResponse
        }

    init {
        getRickAndMortyCharacters(database)

    }
    // grab api
    private fun getRickAndMortyCharacters(database: AppDataBase){
        val service = Api.retrofitService.getCharacter()

        service.enqueue(object : Callback<RickAndMorty> {
            override fun onResponse(
                call: Call<RickAndMorty>, response: Response<RickAndMorty>
            ) {
                if (response.isSuccessful) {
                    Log.i("RickMortyManager", "API Response is successful")
                    val characters = response.body()?.results
                    if (characters != null) {
                        Log.i("RickMortyManager", "Number of characters received: ${characters.size}")
                        for (character in characters) {
                            Log.i("RickMortyManager", "Character Name: ${character.name}")
                        }
                        _rickAndMorttyResponse.value = characters
                        GlobalScope.launch {
                            saveDataToDatabase(database, characters)
                        }
                    } else {
                        Log.e("RickMortyManager", "Response body is null or empty")
                    }
                } else {
                    Log.e("RickMortyManager", "Unsuccessful response: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<RickAndMorty>, t: Throwable) {
                Log.e("RickMortyManager", "API call failed: ${t.message}")
            }
        })
    }

    private suspend fun saveDataToDatabase(database: AppDataBase, data: List<Result>) {
        database.dao().insertAllCharacters(data)
    }

}
