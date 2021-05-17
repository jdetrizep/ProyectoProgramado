package com.proyecto.pokedex.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.proyecto.pokedex.db.dao.PokemonFavoritoDAO
import com.proyecto.pokedex.db.entities.PokemonFavorito

@Database(entities = arrayOf(PokemonFavorito::class), version = 1, exportSchema = false)
abstract class PokedexDataBase : RoomDatabase() {
    abstract fun pokemonFavoritoDAO(): PokemonFavoritoDAO

    companion object {
        @Volatile
        private var INSTANCE: PokedexDataBase? = null

        fun getDatabase(context: Context): PokedexDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PokedexDataBase::class.java,
                    "pokemonfavorito_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}//Fin de la clase PokedexDataBase