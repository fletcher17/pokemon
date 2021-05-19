package com.decadev.pokemonapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.decadev.pokemonapp.R
import com.decadev.pokemonapp.datamodel.Type
import com.decadev.pokemonapp.decoration.CommonUse
import com.robertlevonyan.views.chip.Chip

class PokemonTypeAdapter(var context: Context, var listType: List<Type>) : RecyclerView.Adapter<PokemonTypeAdapter.PokemonTypeViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonTypeViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.chip_item, parent, false)
        return PokemonTypeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listType.size
    }

    override fun onBindViewHolder(holder: PokemonTypeViewHolder, position: Int) {
        holder.item_chip.chipText = listType[position].type.name
        holder.item_chip.changeBackgroundColor(CommonUse.getColorByType(listType[position].type.name))
    }

    inner class PokemonTypeViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
       var item_chip : Chip

       init {
           item_chip = itemView.findViewById(R.id.chip_recyclerview)
           item_chip.setOnChipClickListener { Toast.makeText(context, "Clicked", Toast.LENGTH_LONG).show() }
       }
    }
}