package com.proyecto.pokedex.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.proyecto.pokedex.db.entities.PokemonFavorito
import com.proyecto.pokedex.repositories.PokemonFavoritoRepository
import kotlinx.coroutines.launch

class DeletePokemonFavoritoViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: PokemonFavoritoRepository =
        PokemonFavoritoRepository(application.applicationContext)

    fun deletePokemonFavorito(
        idPokemonFavorito: Int,
        nombreUsuario: String,
        generoFemenino: Boolean,
        generoMasculino: Boolean,
        nombrePokemonFavorito: String,
        urlPokemonFavorito: String
    ){
        viewModelScope.launch {
            repository.deletePokemonFavorito(
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
}