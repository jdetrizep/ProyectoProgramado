package com.proyecto.pokedex.models

import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("name") val name: String,
    @SerializedName("url") val imageURL: String,
    val description: String? = ""
)