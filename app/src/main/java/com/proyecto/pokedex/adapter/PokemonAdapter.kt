package com.proyecto.pokedex.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.proyecto.pokedex.R
import com.proyecto.pokedex.extensions.mapToVisibility
import com.proyecto.pokedex.models.Pokemon
import com.proyecto.pokedex.models.PokemonDetail
import kotlinx.android.synthetic.main.fragment_pokemon_cell.view.*

class PokemonAdapter : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    private lateinit var vPokemon: Pokemon

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
                    0
                )
            itemView.txtNombrePokemon.text = vPokemon.name
            itemView.txtDescripcionPokemon.text = vPokemon.description

            itemView.setOnClickListener {
                itemView.txtDescripcionPokemon.visibility =
                    itemView.txtDescripcionPokemon.isVisible.not().mapToVisibility()
            }

            vPokemon.imageURL =
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" +
                        vPokemon.getNumero().toString() + ".png"

            Glide.with(itemView.context)
                .load(vPokemon.imageURL)
                .circleCrop()
                .into(itemView.imgPokemon)

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