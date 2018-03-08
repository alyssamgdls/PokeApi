package com.magdales.pokeapi

/**
 * Created by Lai on 3/7/2018.
 */
data class Pokemon(val name: String,
              val sprites: Sprite,
              val id: Int)

data class Sprite(val thumbnail: String)