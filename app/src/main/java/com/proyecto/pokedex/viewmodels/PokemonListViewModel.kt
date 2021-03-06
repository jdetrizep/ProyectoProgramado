package com.proyecto.pokedex.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.proyecto.pokedex.api.APIService
import com.proyecto.pokedex.models.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonListViewModel : ViewModel() {
    private val pokemonList = MutableLiveData<List<PokemonDetail>>()
    private lateinit var service: APIService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(APIService::class.java)
    }

    fun makeAPIRequestListaPokemones(nombreTipo: String) {
        service.getTiposPokemones(nombreTipo).enqueue(object : Callback<TipoPokemonResponse> {
            override fun onResponse(
                call: Call<TipoPokemonResponse>,
                response: Response<TipoPokemonResponse>
            ) {
                response.body()?.let {
                    pokemonList.postValue(it.listaPokemones)
                }
            }

            override fun onFailure(call: Call<TipoPokemonResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    fun getPokemonList(): LiveData<List<PokemonDetail>> {
        return pokemonList
    }
}//Fin de Clase PokemonListViewModel