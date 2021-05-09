package com.proyecto.pokedex.models

import com.google.gson.annotations.SerializedName

data class IlustracionPokemon(
    @SerializedName("back_default") val back_default: String,
    @SerializedName("back_female") val back_female: String? = null,
    @SerializedName("back_shiny") val back_shiny: String? = null,
    @SerializedName("back_shiny_female") val back_shiny_female: String? = null,
    @SerializedName("front_default") val front_default: String? = null,
    @SerializedName("front_female") val front_female: String? = null,
    @SerializedName("front_shiny") val front_shiny: String? = null,
    @SerializedName("front_shiny_female") val front_shiny_female: String? = null,
)
