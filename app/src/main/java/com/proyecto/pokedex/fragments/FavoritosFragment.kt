package com.proyecto.pokedex.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.proyecto.pokedex.R
import com.proyecto.pokedex.adapter.PokemonAdapter
import com.proyecto.pokedex.databinding.FragmentFavoritosBinding
import com.proyecto.pokedex.db.entities.PokemonFavorito
import com.proyecto.pokedex.models.Entrenador
import com.proyecto.pokedex.models.PokemonApi
import com.proyecto.pokedex.models.PokemonDetail
import com.proyecto.pokedex.models.SharedViewModel
import com.proyecto.pokedex.viewmodels.PokemonFavoritoListViewModel
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class FavoritosFragment : Fragment(R.layout.fragment_favoritos) {
    private var _binding: FragmentFavoritosBinding? = null
    private val binding: FragmentFavoritosBinding get() = _binding!!
    val viewModel: PokemonFavoritoListViewModel by viewModels()

    private val disposables = CompositeDisposable()

    //Manejo para pasar datos entre Fragments
    private val model: SharedViewModel by activityViewModels()
    private lateinit var user: Entrenador
    private var pokemonesApi = mutableListOf<PokemonDetail>()

    private val adapter = PokemonAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoritosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Imagen de Favorito
        val imgTituloFav: String = "https://i1.sndcdn.com/avatars-000123079108-vuk0m6-t500x500.jpg"

        //Carga el Parametro enviado desde otro Fragment
        user = model.selected.value!!

        binding.txtTituloFav.text = "Pokemones Favoritos de"
        binding.txtEntrenadorFav.text = user.name.toUpperCase()

        //Carga la imagen de Favoritos
        Glide.with(view.context)
            .load(imgTituloFav)
            .circleCrop()
            .into(binding.imgPrincipalFav)

        //Cargan la lista del RecyclerView
        binding.pokemonesFavRecyclerView.adapter = adapter

        var layoutManager = GridLayoutManager(view.context, 3)
        binding.pokemonesFavRecyclerView.layoutManager = layoutManager

        disposables.add(
            viewModel.getAllPokemonFavorito(user.name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    for (oPokemonFav in it) {
                        pokemonesApi.add(
                            PokemonDetail(
                                PokemonApi(
                                    oPokemonFav.nombrePokemon,
                                    oPokemonFav.urlPokemonFavorito
                                )
                            )
                        )
                    }
                    adapter.pokemonesApi = pokemonesApi
                }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        disposables.clear()
    }


}// Fin de la Clase FavoritosFragment