package com.proyecto.pokedex.extensions

import android.view.View

fun Boolean.mapToVisibility() : Int = if (this) View.VISIBLE else View.GONE