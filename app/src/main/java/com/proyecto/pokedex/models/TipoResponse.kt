package com.proyecto.pokedex.models

import com.google.gson.annotations.SerializedName

data class TipoResponse(@SerializedName("results") val listaTiposPokemones: List<TipoPokemon>)