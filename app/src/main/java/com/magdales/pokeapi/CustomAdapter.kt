package com.magdales.pokeapi

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_layout.view.*

/**
 * Created by Lai on 3/7/2018.
 */
class CustomAdapter(val pokemonList: ArrayList<Pokemon>) : RecyclerView.Adapter<CustomAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context).inflate(R.layout.item_layout, parent, false)
        return CustomViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: CustomViewHolder?, position: Int) {
        val pos: Pokemon = pokemonList[position]
        holder!!.customView.pokemon_name.text = pos.name

        val thumbnailImageView = holder.customView.pokemon_image
        Picasso.with(holder.customView.context)
                .load(pos.sprites.thumbnail)
                .into(thumbnailImageView)
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    class CustomViewHolder(val customView: View): RecyclerView.ViewHolder(customView) {

    }
}