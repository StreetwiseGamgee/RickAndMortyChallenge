package com.example.rickandmorty.api

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.rickandmorty.db.AppDataBase
import com.example.rickandmorty.mmodel.CastMember
import com.example.rickandmorty.mmodel.CastMemberData
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RickAndMortyManager(database: AppDataBase) {
    private var _rickAndMortyResponse = mutableStateOf<List<CastMember>>(emptyList())
    //val rickAndMortyURL: String = "https://rickandmortyapi.com/api/character"

    val rickAndMortyResponse: MutableState<List<CastMember>>
        @Composable get() = remember {
            _rickAndMortyResponse
        }

    init {
        getRickAndMortyCharacters(database)
    }

    // grab api
    @OptIn(DelicateCoroutinesApi::class)
    private fun getRickAndMortyCharacters(database: AppDataBase) {
        val service = Api.retrofitService.getCharacter()

        service.enqueue(object : Callback<CastMemberData>{
            override fun onResponse(
                call: Call<CastMemberData>,
                response: Response<CastMemberData>
            ) {
                if (response.isSuccessful) {
                    Log.i("RickMortyManager", "API Response is successful")
                    _rickAndMortyResponse.value = response.body()?.results ?: emptyList()
                    val characters = response.body()?.results

                    if (characters != null) {
                        Log.i("RickMortyManager", "Number of characters received: ${characters.size}")
                        for (character in characters) {
                            Log.i("RickMortyManager", "Character Name: ${character.name}")
                        }
                        //_rickAndMortyResponse.value = characters
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

            override fun onFailure(call: Call<CastMemberData>, t: Throwable) {
                Log.e("RickMortyManager", "API call failed: ${t.message}")
            }

        })
    }

    private suspend fun saveDataToDatabase(database: AppDataBase, data: List<CastMember>) {
        database.dao().insertAllCharacters(data)
    }
}

