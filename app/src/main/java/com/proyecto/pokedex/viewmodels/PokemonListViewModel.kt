package com.proyecto.pokedex.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.proyecto.pokedex.api.APIService
import com.proyecto.pokedex.models.TipoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonListViewModel : ViewModel() {
    private val listaPokemones = MutableLiveData<List<TipoResponse>>()
    private lateinit var service: APIService

    init {

        // TODO Inicializamos retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(APIService::class.java)
    }

    fun makeAPIRequestListaPokemones(){
        service.getListaTipoPokemones().enqueue(object: Callback<TipoResponse>{
            override fun onResponse(
                call: Call<TipoResponse>,
                response: Response<TipoResponse>
            ) {
                response.body()?.let {
                    listaPokemones.postValue(listOf(it))
                }
            }

            override fun onFailure(call: Call<TipoResponse>, t: Throwable) {
                val a = "Ocurrio un Error!"
            }
        })
    }

    fun getPokemonList(): LiveData<List<TipoResponse>> {
        return listaPokemones
    }
}