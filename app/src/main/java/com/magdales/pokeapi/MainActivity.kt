package com.magdales.pokeapi

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.LinearLayout
import com.google.gson.GsonBuilder
import okhttp3.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private val url = "http://pokeapi.co/api/v2/pokemon/"
    private val pokemon = ArrayList<Pokemon>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        for (pokemonID in 1..20) {
            doAsync {
                val resultJson = url + pokemonID
                val client = OkHttpClient()
                val request = Request.Builder().url(resultJson).build()
                client.newCall(request).enqueue(object : Callback {
                    override fun onFailure(call: Call?, e: IOException?) {
                        println("Failed to execute request")
                    }

                    override fun onResponse(call: Call?, response: Response?) {
                        val body = response?.body()?.string()
                        val GSONObj = GsonBuilder().create()
                        val JSONObj = GSONObj.fromJson(body, Pokemon::class.java)

                        uiThread {
                            pokemon.add(Pokemon(JSONObj.name, JSONObj.sprites, JSONObj.id))
                            val adapter = CustomAdapter(pokemon)
                            recyclerView.adapter = adapter

                            if (pokemon.size != 0) {
                                textView_pokemon_count.text = "You have " + pokemon.size.toString() + " pokemons"
                            }

                            if (pokemon.size == 20) {
                                progressBar.visibility = View.GONE
                            }
                        }
                    }
                })
            }
        }
    }
}
