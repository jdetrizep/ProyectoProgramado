package com.proyecto.pokedex.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.proyecto.pokedex.R
import com.proyecto.pokedex.adapter.PokemonAdapter
import com.proyecto.pokedex.databinding.FragmentWelcomeBinding
import com.proyecto.pokedex.models.Entrenador
import com.proyecto.pokedex.models.Pokemon
import com.proyecto.pokedex.models.SharedViewModel
import com.proyecto.pokedex.models.TipoPokemon
import com.proyecto.pokedex.viewmodels.PokemonListViewModel
import com.proyecto.pokedex.viewmodels.TipoPokemonListViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_welcome.view.*

class WelcomeFragment : Fragment(R.layout.fragment_welcome) {
    private var _binding: FragmentWelcomeBinding? = null
    private val binding: FragmentWelcomeBinding get() = _binding!!

    //Se inicializa el viewModelCreate
    private val viewModelTipo: TipoPokemonListViewModel by viewModels()
    private val viemModelPokemones: PokemonListViewModel by viewModels()

    //Manejo para pasar datos entre Fragments
    private val model: SharedViewModel by activityViewModels()
    private lateinit var user: Entrenador

    private val adapter = PokemonAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelTipo.makeAPIRequestListaTipoPokemones()

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

        viemModelPokemones.getPokemonList().observe(viewLifecycleOwner){
            //Genera el Grid en Pantalla
            adapter.pokemonesApi = it

        }

        binding.pokemonesRecyclerView.adapter = adapter

        var layoutManager = GridLayoutManager(view.context, 3)
        binding.pokemonesRecyclerView.layoutManager = layoutManager

        //Asignación de Datos en navegación
        if (user.generoMasculino) {
            binding.txtTitulo.text = "Bienvenido Entrenador"
        } else if (user.generoFemenino) {
            binding.txtTitulo.text = "Bienvenida Entrenadora"
        }
        binding.txtNombre.text = user.name

        viewModelTipo.getPokemonList().observe(viewLifecycleOwner) {
            var items = mutableListOf<String>()


            for (arguments in it) {
                items.add(arguments.nombre.toUpperCase())
            }

            val adapterListaTipoPokemon =
                ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, items)
            view.spinnerCombo.adapter = adapterListaTipoPokemon

        }

        binding.spinnerCombo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                var itemSeleccionado = parent?.getItemAtPosition(position).toString().toLowerCase()
                viemModelPokemones.makeAPIRequestListaPokemones(itemSeleccionado)
            }
        }

        //Escuchamos por RX el click en Adapter
        adapter.pokemonClicked
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe {
                var action = WelcomeFragmentDirections.actionWelcomeFragment2ToDetallepokemonFragment(it)
                findNavController().navigate(action)
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}