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

class PokemonAdapter : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {
    private lateinit var nombrePokemon: TextView
    private lateinit var descripcionPokemon: TextView
    private lateinit var imagenPokemon: ImageView

    var pokemones: List<Pokemon> = emptyList()
    set(value){
        field = value
        notifyDataSetChanged()
    }

    inner class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(pokemon: Pokemon) {
            nombrePokemon = itemView.findViewById(R.id.txtNombrePokemon)
            descripcionPokemon = itemView.findViewById(R.id.txtDescripcionPokemon)
            imagenPokemon = itemView.findViewById(R.id.imgPokemon)

            nombrePokemon.text = pokemon.name
            descripcionPokemon.text = pokemon.description

            itemView.setOnClickListener{
                descripcionPokemon.visibility = descripcionPokemon.isVisible.not().mapToVisibility()
            }

            Glide.with(itemView.context)
                .load(pokemon.imageURL)
                .circleCrop()
                .into(imagenPokemon)

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