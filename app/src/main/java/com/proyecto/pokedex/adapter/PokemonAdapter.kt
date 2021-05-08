package com.proyecto.pokedex.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.proyecto.pokedex.R
import com.proyecto.pokedex.extensions.mapToVisibility
import com.proyecto.pokedex.models.Pokemon
import kotlinx.android.synthetic.main.fragment_pokemon_cell.view.*

class PokemonAdapter : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    var pokemones: List<Pokemon> = emptyList()
    set(value){
        field = value
        notifyDataSetChanged()
    }

    inner class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(pokemon: Pokemon) {
            itemView.txtNombrePokemon.text = pokemon.name
            itemView.txtDescripcionPokemon.text = pokemon.description

            itemView.setOnClickListener{
                itemView.txtDescripcionPokemon.visibility = itemView.txtDescripcionPokemon.isVisible.not().mapToVisibility()
            }

            Glide.with(itemView.context)
                .load(pokemon.imageURL)
                .circleCrop()
                .into(itemView.imgPokemon)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_pokemon_cell, parent, false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = pokemones[position]
        holder.bind(pokemon)
    }

    override fun getItemCount(): Int = pokemones.size

}//Fin de la Clase PokemonAdapter