package com.proyecto.pokedex.models

import com.google.gson.annotations.SerializedName

data class TipoPokemonResponse(@SerializedName("pokemon") val listaPokemones: List<PokemonDetail>)
