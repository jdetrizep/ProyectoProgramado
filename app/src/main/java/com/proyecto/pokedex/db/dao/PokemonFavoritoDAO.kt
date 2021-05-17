package com.proyecto.pokedex.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.proyecto.pokedex.db.entities.PokemonFavorito
import io.reactivex.Observable

@Dao
interface PokemonFavoritoDAO {

    @Query("SELECT * FROM pokemonfavorito WHERE usuarioNombre = :userName")
    fun getAllPokemonFavorito(userName:String) : Observable<List<PokemonFavorito>>

    @Insert
    suspend fun insertPokemonFavorito(pokemonFavorito: PokemonFavorito)

    @Delete
    suspend fun deletePokemonFavorito(pokemonFavorito: PokemonFavorito)

}//Fin de la interface PokemonFavoritoDAO