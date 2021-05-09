package com.proyecto.pokedex.models

import com.google.gson.annotations.SerializedName

data class TipoPokemonDetail(@SerializedName("pokemon") val tipoPokemon: List<TipoPokemon>)
