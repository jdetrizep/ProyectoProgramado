package com.proyecto.pokedex.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    val selected = MutableLiveData<Entrenador>()

    fun select(item: Entrenador) {
        selected.value = item
    }
}