package com.decadev.pokemonapp.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import com.decadev.pokemonapp.datamodel.Result
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.decadev.pokemonapp.R
import com.decadev.pokemonapp.itemclicklistener.ItemClickListener

class PokemonListAdapter(var context: Context, var pokemonDataBase: List<Result>, var inter : ItemClickListener) : RecyclerView.Adapter<PokemonListAdapter.PokemonListViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_list_item, parent, false)

        return PokemonListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return pokemonDataBase.size
    }

    override fun onBindViewHolder(holder: PokemonListViewHolder, position: Int) {
        var pos = position + 1
        holder.name_pokemon.text = pokemonDataBase[position].name
       // holder.name_pokemon.setOnClickListener { inter.onNode(pos) }
        Glide.with(context).load("https://pokeres.bastionbot.org/images/pokemon/${pos}.png").into(holder.image_pokemon)
    }


    inner class PokemonListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener {
        var name_pokemon : TextView
        var image_pokemon : ImageView

        init {
            name_pokemon = itemView.findViewById(R.id.pokemon_name)
            image_pokemon = itemView.findViewById(R.id.pokemon_image)
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
           inter.onNode(adapterPosition + 1)
        }
    }
}