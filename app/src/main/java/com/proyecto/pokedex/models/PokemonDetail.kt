package com.proyecto.pokedex.models

import com.google.gson.annotations.SerializedName

data class PokemonDetail(@SerializedName("pokemon") val oPokemon: PokemonApi)