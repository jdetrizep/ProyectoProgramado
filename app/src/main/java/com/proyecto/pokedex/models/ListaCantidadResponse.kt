package com.proyecto.pokedex.models

import com.google.gson.annotations.SerializedName

data class ListaCantidadResponse(@SerializedName("results") val listaPokemones: List<Pokemon>)
