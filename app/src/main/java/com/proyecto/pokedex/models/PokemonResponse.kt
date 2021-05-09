package com.proyecto.pokedex.models

import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("sprites") val arte: IlustracionPokemon
)
