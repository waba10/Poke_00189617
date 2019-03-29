package com.ayala.pokedex


import android.content.Intent
import android.support.v7.view.menu.ActionMenuItemView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.ayala.pokedex.models.Pokemon
import com.ayala.pokedex.utilities.AppConstant
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_element_pokemon.view.*


class PokemonAdapter(val items: List<Pokemon>): RecyclerView.Adapter<PokemonAdapter.ViewHolder>
    (){
    private var countViews: Int=0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_element_pokemon, parent, false)


        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])

        var aux:Int= position+1

        Glide.with(holder.itemView.context)
            .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$aux.png")
            .into(holder.itemView.iv_pokemon)



    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(item:Pokemon)= with(itemView){
            tv_pokemon_name.text=item.name.toString()
            //count_element.text=item.id.toString()

            this.setOnClickListener {
                var mIntent = Intent(it.context,  SecondActivity:: class.java)

                mIntent.putExtra(AppConstant.key_name, item.name)
                mIntent.putExtra(AppConstant.key_experience, item.experiencia)
                mIntent.putExtra(AppConstant.key_height, item.altura)
                mIntent.putExtra(AppConstant.key_weight, item.peso)
                mIntent.putExtra(AppConstant.key_id, item.id)
                mIntent.putExtra(AppConstant.key_url, item.url)



                this.context.startActivity(mIntent)
            }

            //Obtener(V)
        }
    }


    //abstract ontener()

}