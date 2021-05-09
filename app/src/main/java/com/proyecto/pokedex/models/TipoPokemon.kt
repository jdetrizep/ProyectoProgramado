package com.proyecto.pokedex.models

import com.google.gson.annotations.SerializedName

data class TipoPokemon (@SerializedName("name") val nombre: String, @SerializedName("url") val urlTipo: String)