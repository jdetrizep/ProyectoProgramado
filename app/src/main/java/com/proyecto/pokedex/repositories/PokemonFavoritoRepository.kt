package com.proyecto.pokedex.repositories

import android.content.Context
import com.proyecto.pokedex.db.PokedexDataBase
import com.proyecto.pokedex.db.entities.PokemonFavorito
import io.reactivex.Observable

class PokemonFavoritoRepository(context: Context) {
    var db: PokedexDataBase = PokedexDataBase.getDatabase(context)

    fun getAllPokemonFavorito(userName: String): Observable<List<PokemonFavorito>> =
        db.pokemonFavoritoDAO().getAllPokemonFavorito(userName)

    fun getConsultaPokemonFavorito(
        idPokemonFavorito: Int,
        userName: String
    ): Observable<List<PokemonFavorito>> =
        db.pokemonFavoritoDAO().getConsultaPokemonFavorito(idPokemonFavorito, userName)

    suspend fun deletePokemonFavorito(pokemonFavorito: PokemonFavorito) {
        db.pokemonFavoritoDAO().deletePokemonFavorito(pokemonFavorito)
    }

    suspend fun insertPokemonFavorito(pokemonFavorito: PokemonFavorito) {
        db.pokemonFavoritoDAO().insertPokemonFavorito(pokemonFavorito)
    }

}//Fin de la clase PokemonFavoritoRepository