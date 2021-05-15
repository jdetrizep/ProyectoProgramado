package com.proyecto.pokedex.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.proyecto.pokedex.R
import com.proyecto.pokedex.adapter.PokemonAdapter
import com.proyecto.pokedex.databinding.FragmentFavoritosBinding
import com.proyecto.pokedex.models.Entrenador
import com.proyecto.pokedex.models.SharedViewModel
import kotlinx.android.synthetic.main.fragment_pokemon_cell.view.*

class FavoritosFragment : Fragment(R.layout.fragment_favoritos){
    private var _binding: FragmentFavoritosBinding? = null
    private val binding: FragmentFavoritosBinding get() = _binding!!

    //Manejo para pasar datos entre Fragments
    private val model: SharedViewModel by activityViewModels()
    private lateinit var user: Entrenador

    private val adapter = PokemonAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoritosBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Imagen de Favorito
        val imgTituloFav : String = "https://i1.sndcdn.com/avatars-000123079108-vuk0m6-t500x500.jpg"

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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}// Fin de la Clase FavoritosFragment