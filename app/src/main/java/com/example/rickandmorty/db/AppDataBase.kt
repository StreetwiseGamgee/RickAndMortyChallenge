package com.example.rickandmorty.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.rickandmorty.utility.Converter
import com.example.rickandmorty.db.Dao

@Database(entities = [Result::class], version = 4, exportSchema = false)
@TypeConverters(Converter::class)
abstract class AppDataBase : RoomDatabase(){

    abstract fun dao() : Dao

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase?= null

        fun getInstance(context: Context): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "Rick and Morty database"
                ).addMigrations(MIGRATION_3_4)
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                instance
            }
        }

        val MIGRATION_3_4 = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {

                database.execSQL("DROP TABLE IF EXISTS characters")
                database.execSQL(
                    """
                        CREATE TABLE IF NOT EXISTS characters (
                            id INTEGER PRIMARY KEY,
                            name TEXT,
                            status TEXT,
                            species TEXT,
                            type TEXT,
                            gender TEXT,
                            origin_name TEXT,
                            origin_url TEXT,
                            location_name TEXT,
                            location_url TEXT,
                            image TEXT,
                            episode TEXT,
                            url TEXT,
                            created TEXT
                        )
                        """
                ) // Define your new schema
            }
        }
    }
}