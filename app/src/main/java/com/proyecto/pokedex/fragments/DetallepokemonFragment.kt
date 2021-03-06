package com.proyecto.pokedex.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.jakewharton.rxbinding4.widget.checkedChanges
import com.proyecto.pokedex.R
import com.proyecto.pokedex.adapter.PokemonAdapter
import com.proyecto.pokedex.databinding.FragmentDetallepokemonBinding
import com.proyecto.pokedex.db.entities.PokemonFavorito
import com.proyecto.pokedex.models.Entrenador
import com.proyecto.pokedex.models.SharedViewModel
import com.proyecto.pokedex.viewmodels.CreatePokemonFavoritoViewModel
import com.proyecto.pokedex.viewmodels.DeletePokemonFavoritoViewModel
import com.proyecto.pokedex.viewmodels.PokemonFavoritoListViewModel
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class DetallepokemonFragment : Fragment(R.layout.fragment_detallepokemon) {
    val arguments: DetallepokemonFragmentArgs by navArgs()
    private var _binding: FragmentDetallepokemonBinding? = null
    private val binding: FragmentDetallepokemonBinding get() = _binding!!
    val viewModel: PokemonFavoritoListViewModel by viewModels()

    val disposables = CompositeDisposable()

    val viewModelCreate: CreatePokemonFavoritoViewModel by viewModels()
    val viewModelDelete: DeletePokemonFavoritoViewModel by viewModels()

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

        //Carga el Parametro enviado desde otro Fragment
        user = model.selected.value!!

        //Asignaci??n de datos en la Navegaci??n
        binding.txtNombrePoke.text =
            arguments.pokemon.numeroPokemon.toString() + " " + arguments.pokemon.name.toUpperCase()

        //var vPokemonFav: Boolean = esPokemonFavorito(arguments.pokemon.numeroPokemon,user.name)

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

        //RX
        disposables.add(
            viewModel.getConsultaPokemonFavorito(arguments.pokemon.numeroPokemon!!, user.name)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    binding.chkFavorito.isChecked = it.isNotEmpty()
                }
        )
        /*binding.chkFavorito.checkedChanges()
            .subscribe()*/

        binding.chkFavorito.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                arguments.pokemon.pokemonUrl?.let {
                    arguments.pokemon.numeroPokemon?.let { it1 ->
                        viewModelCreate.createPokemonFavorito(
                            it1,
                            user.name,
                            user.generoFemenino,
                            user.generoMasculino,
                            arguments.pokemon.name,
                            it
                        )
                    }
                }
            }else{
                arguments.pokemon.pokemonUrl?.let {
                    arguments.pokemon.numeroPokemon?.let { it1 ->
                        viewModelDelete.deletePokemonFavorito(
                            it1,
                            user.name,
                            user.generoFemenino,
                            user.generoMasculino,
                            arguments.pokemon.name,
                            it
                        )
                    }
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposables.clear()
        _binding = null
    }

}//Fin de clase DetallepokemonFragment