package com.example.rickandmorty.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmorty.mmodel.CastMember

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCharacters(characters: List<CastMember>)

    @Query("SELECT * FROM tbl_cast_members")
    suspend fun getAllCharacters(): List<CastMember>

    @Query("SELECT * FROM tbl_cast_members WHERE id = :id")
    suspend fun getCharacterById(id: Int): CastMember?

    @Query("DELETE FROM tbl_cast_members WHERE id = :id")
    suspend fun purgeCharacter(id: Int)
}