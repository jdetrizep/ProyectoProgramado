package com.proyecto.pokedex.adapter

import android.database.Observable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.proyecto.pokedex.R
import com.proyecto.pokedex.extensions.mapToVisibility
import com.proyecto.pokedex.fragments.WelcomeFragmentDirections
import com.proyecto.pokedex.models.Pokemon
import com.proyecto.pokedex.models.PokemonDetail
import com.proyecto.pokedex.models.SharedViewModel
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_pokemon_cell.view.*

class PokemonAdapter : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    private lateinit var vPokemon: Pokemon

    private val clicksAcceptor: PublishSubject<Pokemon> = PublishSubject.create<Pokemon>()
    val pokemonClicked: @NonNull io.reactivex.rxjava3.core.Observable<Pokemon>? =
        clicksAcceptor.hide()

    var pokemonesApi: List<PokemonDetail> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(pokemonDetail: PokemonDetail) {

            vPokemon =
                Pokemon(
                    pokemonDetail.oPokemon.name,
                    pokemonDetail.oPokemon.pokemonUrl,
                    "",
                    "",
                    "",
                    0
                )

            vPokemon.imageURL =
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" +
                        vPokemon.getNumero().toString() + ".png"
            vPokemon.imageShinyURL =
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/" +
                        vPokemon.getNumero().toString() + ".png"
            vPokemon.numeroPokemon = vPokemon.getNumero()

            itemView.txtNombrePokemon.text = vPokemon.name
            itemView.txtDescripcionPokemon.text = "Número: " + vPokemon.numeroPokemon.toString()

            Glide.with(itemView.context)
                .load(vPokemon.imageURL)
                .circleCrop()
                .into(itemView.imgPokemon)

            itemView.setOnClickListener {
                vPokemon = Pokemon(
                    pokemonDetail.oPokemon.name,
                    pokemonDetail.oPokemon.pokemonUrl,
                    "",
                    "",
                    "",
                    0
                )
                vPokemon.imageURL =
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" +
                            vPokemon.getNumero().toString() + ".png"
                vPokemon.imageShinyURL =
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/" +
                            vPokemon.getNumero().toString() + ".png"
                vPokemon.numeroPokemon = vPokemon.getNumero()

                clicksAcceptor.onNext(vPokemon)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_pokemon_cell, parent, false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = pokemonesApi[position]
        holder.bind(pokemon)
    }

    override fun getItemCount(): Int = pokemonesApi.size


}//Fin de la Clase PokemonAdapter