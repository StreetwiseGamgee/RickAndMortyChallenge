package com.example.rickandmorty.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmorty.mmodel.Result

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCharacters(characters: List<Result>?)

    @Query("SELECT * FROM characters WHERE id = :id")
    fun getCharacterById(id: Int): Result?
}