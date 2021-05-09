package com.proyecto.pokedex.models

import com.google.gson.annotations.SerializedName

data class PokemonApi(
    @SerializedName("name") val name: String,
    @SerializedName("url") val pokemonUrl: String? = ""
)
