package com.proyecto.pokedex.api

import com.proyecto.pokedex.models.ListaCantidadResponse
import com.proyecto.pokedex.models.PokemonResponse
import com.proyecto.pokedex.models.TipoPokemonResponse
import com.proyecto.pokedex.models.TipoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {
    //Lista todos los Tipos
    @GET("type")
    fun getListaTipoPokemones(): Call<TipoResponse>

    //Lista un Tipo Especifico
    @GET("type/{id}")
    fun getTiposPokemones(@Path("id") idTipoPokemon: Int): Call<TipoPokemonResponse>

    //Lista una cantidad de Pokemones
    @GET("pokemon")
    fun getListaPokemones(
        @Query("limit") cantidad: Int,
        @Query("offset") posicion: Int
    ): Call<ListaCantidadResponse>

    //Lista un pokemon por Nombre
    @GET("pokemon/{nombre}")
    fun getPokemonNombre(@Path("nombre") nombrePokemon: String): Call<PokemonResponse>

    //Lista un pokemon por Id
    @GET("pokemon/{id}")
    fun getPokemonId(@Path("id") idPokemon: Int): Call<PokemonResponse>

}