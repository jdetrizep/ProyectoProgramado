package com.proyecto.pokedex.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.proyecto.pokedex.db.entities.PokemonFavorito
import com.proyecto.pokedex.repositories.PokemonFavoritoRepository
import io.reactivex.Observable

class PokemonFavoritoListViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = PokemonFavoritoRepository(application.applicationContext)

    fun getAllPokemonFavorito(userName : String): Observable<List<PokemonFavorito>>{
        return repository.getAllPokemonFavorito(userName)
    }

    fun getConsultaPokemonFavorito(idPokemonFavorito: Int, userName: String): Observable<List<PokemonFavorito>>{
        return repository.getConsultaPokemonFavorito(idPokemonFavorito, userName)
    }

}//Fin de la clase PokemonFavoritoListViewModel