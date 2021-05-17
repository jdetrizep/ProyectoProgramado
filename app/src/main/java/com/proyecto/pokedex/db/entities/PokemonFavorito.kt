package com.proyecto.pokedex.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(primaryKeys = arrayOf("id", "usuarioNombre"))
data class PokemonFavorito(
    val id: Int,
    val usuarioNombre: String,
    @ColumnInfo(name = "genero_femenino") val generoFemenino: Boolean,
    @ColumnInfo(name = "genero_masculino") val generoMasculino: Boolean,
    @ColumnInfo(name = "name") val nombrePokemon: String,
    @ColumnInfo(name = "url") val urlPokemonFavorito: String
)