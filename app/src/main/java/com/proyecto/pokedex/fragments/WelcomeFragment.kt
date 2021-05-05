package com.proyecto.pokedex.fragments

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.proyecto.pokedex.R
import com.proyecto.pokedex.adapter.PokemonAdapter
import com.proyecto.pokedex.models.Entrenador
import com.proyecto.pokedex.models.Pokemon
import com.proyecto.pokedex.models.SharedViewModel

class WelcomeFragment : Fragment(R.layout.fragment_welcome) {
    //val arguments : WelcomeFragmentArgs by navArgs()

    //Manejo para pasar datos entre Fragments
    private val model: SharedViewModel by activityViewModels()
    private lateinit var user: Entrenador

    private val adapter = PokemonAdapter()
    private lateinit var pokemonRecycler : RecyclerView
    private lateinit var tituloPrin : TextView
    private lateinit var nombreEntrenador : TextView


    private fun getDummyPokemones() : List<Pokemon> {
        return mutableListOf(
            Pokemon("Pikachu", "https://assets.stickpng.com/thumbs/580b57fcd9996e24bc43c325.png", "Pokemon Electrico"),
            Pokemon("Squirtle", "https://assets.stickpng.com/thumbs/580b57fcd9996e24bc43c32a.png", "Pokemon Agua"),
            Pokemon("Bulbasaur", "https://assets.stickpng.com/images/580b57fcd9996e24bc43c31a.png", "Pokemon Planta"),
            Pokemon("Charmander", "https://c3.klipartz.com/pngpicture/447/935/sticker-png-pokemon-charmander-character.png", "Pokemon Fuego")
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Carga el Parametro enviado desde otro Fragment
        user = model.selected.value!!

        //Genera el Grid en Pantalla
        adapter.pokemones = getDummyPokemones()
        pokemonRecycler = view.findViewById(R.id.pokemonesRecyclerView)
        pokemonRecycler.adapter =adapter
        pokemonRecycler.addItemDecoration(DividerItemDecoration(view.context, VERTICAL))

        //Asignación de Datos en navegación
        tituloPrin = view.findViewById(R.id.txtTitulo)
        if (user.generoMasculino){
            tituloPrin.text = "Bienvenido Entrenador"
        }else if (user.generoFemenino){
            tituloPrin.text = "Bienvenida Entrenadora"
        }
        nombreEntrenador = view.findViewById(R.id.txtNombre)
        nombreEntrenador.text = user.name

    }
}