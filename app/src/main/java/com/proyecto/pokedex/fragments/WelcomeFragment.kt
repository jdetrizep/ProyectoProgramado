package com.proyecto.pokedex.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Spinner
import android.widget.TextView
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.proyecto.pokedex.R
import com.proyecto.pokedex.adapter.PokemonAdapter
import com.proyecto.pokedex.databinding.FragmentLoginBinding
import com.proyecto.pokedex.databinding.FragmentWelcomeBinding
import com.proyecto.pokedex.models.Entrenador
import com.proyecto.pokedex.models.Pokemon
import com.proyecto.pokedex.models.SharedViewModel
import com.proyecto.pokedex.models.TipoPokemon
import com.proyecto.pokedex.viewmodels.PokemonListViewModel
import kotlinx.android.synthetic.main.fragment_welcome.view.*

class WelcomeFragment : Fragment(R.layout.fragment_welcome) {
    private var _binding: FragmentWelcomeBinding? = null
    private val binding: FragmentWelcomeBinding get() = _binding!!

    //Se inicializa el viewModel
    private val viewModel: PokemonListViewModel by viewModels()

    //Manejo para pasar datos entre Fragments
    private val model: SharedViewModel by activityViewModels()
    private lateinit var user: Entrenador

    private val adapter = PokemonAdapter()

    private fun getDummyPokemones(): List<Pokemon> {
        return mutableListOf(
            Pokemon(
                "Pikachu",
                "https://assets.stickpng.com/thumbs/580b57fcd9996e24bc43c325.png",
                "Pokemon Electrico"
            ),
            Pokemon(
                "Squirtle",
                "https://assets.stickpng.com/thumbs/580b57fcd9996e24bc43c32a.png",
                "Pokemon Agua"
            ),
            Pokemon(
                "Bulbasaur",
                "https://assets.stickpng.com/images/580b57fcd9996e24bc43c31a.png",
                "Pokemon Planta"
            ),
            Pokemon(
                "Charmander",
                "https://c3.klipartz.com/pngpicture/447/935/sticker-png-pokemon-charmander-character.png",
                "Pokemon Fuego"
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.makeAPIRequestListaPokemones()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Carga el Parametro enviado desde otro Fragment
        user = model.selected.value!!

        //Genera el Grid en Pantalla
        adapter.pokemones = getDummyPokemones()
        binding.pokemonesRecyclerView.adapter = adapter

        binding.pokemonesRecyclerView.addItemDecoration(
            DividerItemDecoration(
                view.context,
                VERTICAL
            )
        )

        //Asignación de Datos en navegación
        if (user.generoMasculino) {
            binding.txtTitulo.text = "Bienvenido Entrenador"
        } else if (user.generoFemenino) {
            binding.txtTitulo.text = "Bienvenida Entrenadora"
        }
        binding.txtNombre.text = user.name

        viewModel.getPokemonList().observe(viewLifecycleOwner) {
            var items = mutableListOf<String>()
            var listaTipos = listOf<TipoPokemon>()

            for (argm in it) {
                listaTipos = argm.listaTiposPokemones
                for (arguments in listaTipos) {
                    items.add(arguments.nombre.toUpperCase())
                }
            }

            val adapterListaTipoPokemon =
                ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, items)
            view.spinnerCombo.adapter = adapterListaTipoPokemon

        }
        /*val items = listOf("Material", "Design", "Components", "Android")
        val adapter = ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, items)
        view.spinnerCombo.adapter = adapter*/


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}