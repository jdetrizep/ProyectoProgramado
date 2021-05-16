package com.proyecto.pokedex.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.proyecto.pokedex.R
import com.proyecto.pokedex.adapter.PokemonAdapter
import com.proyecto.pokedex.databinding.FragmentDetallepokemonBinding
import com.proyecto.pokedex.models.Entrenador
import com.proyecto.pokedex.models.SharedViewModel
import kotlinx.android.synthetic.main.fragment_pokemon_cell.view.*

class DetallepokemonFragment : Fragment(R.layout.fragment_detallepokemon) {
    val arguments: DetallepokemonFragmentArgs by navArgs()
    private var _binding: FragmentDetallepokemonBinding? = null
    private val binding: FragmentDetallepokemonBinding get() = _binding!!

    //Manejo para pasar datos entre Fragments
    private val model: SharedViewModel by activityViewModels()
    private lateinit var user: Entrenador

    private val adapter = PokemonAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetallepokemonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Asignación de datos en la Navegación
        binding.txtNombrePoke.text =
            arguments.pokemon.numeroPokemon.toString() + " " + arguments.pokemon.name.toUpperCase()

        Glide.with(view.context)
            .load(arguments.pokemon.imageURL)
            .circleCrop()
            .into(binding.imgPokeFrente)

        Glide.with(view.context)
            .load(arguments.pokemon.imageShinyURL)
            .circleCrop()
            .into(binding.imgPokeShini)

        //Cargan la lista del RecyclerView
        binding.pokemonesEvoRecyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}//Fin de clase DetallepokemonFragment