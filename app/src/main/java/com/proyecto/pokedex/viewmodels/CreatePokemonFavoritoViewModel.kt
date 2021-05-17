package com.proyecto.pokedex.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.proyecto.pokedex.db.entities.PokemonFavorito
import com.proyecto.pokedex.repositories.PokemonFavoritoRepository
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.launch

//M V VM

class CreatePokemonFavoritoViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: PokemonFavoritoRepository =
        PokemonFavoritoRepository(application.applicationContext)

    fun createPokemonFavorito(
        idPokemonFavorito: Int,
        nombreUsuario: String,
        generoFemenino: Boolean,
        generoMasculino: Boolean,
        nombrePokemonFavorito: String,
        urlPokemonFavorito: String
    ) {
        viewModelScope.launch {
            repository.insertPokemonFavorito(
                PokemonFavorito(
                    idPokemonFavorito,
                    nombreUsuario,
                    generoFemenino,
                    generoMasculino,
                    nombrePokemonFavorito,
                    urlPokemonFavorito
                )
            )
        }
    }

}//Fin de la clase CreatePokemonFavoritoViewModel