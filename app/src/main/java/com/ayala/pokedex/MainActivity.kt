package com.ayala.pokedex

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.ayala.pokedex.models.Pokemon
import com.ayala.pokedex.utilities.Network
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_element_pokemon.*
import org.json.JSONObject
import java.io.IOException
import java.net.URL
import android.R.attr.data
import android.content.Intent
import com.ayala.pokedex.utilities.AppConstant


class MainActivity : AppCompatActivity() {

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var pokemon: MutableList<Pokemon>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FetchPokemonTask().execute()

        /*tv_pokemon_name.setOnClickListener {

            FetchPokemonTask1().execute()

            val segundo= Network.Factory
            val ayuda ="https://pokeapi.co/api/v2/pokemon/12"
            val pokeAPI2: URL = segundo.buildUr(ayuda)!!

            val guardar:String= segundo.getResponseFromHttpUrl(pokeAPI2)!!

            if (guardar != null || guardar != "") {
                Log.d("guardar", guardar+"")

                val jObj = JSONObject(guardar)

                val Nombre = jObj.getString("name")
                val Experiencia = jObj.getInt("base_experience").toString()
                val Identificador = jObj.getInt("id").toString()
                val Altura = jObj.getInt("height").toString()
                val Peso = jObj.getInt("weight").toString()


                val mIntent = Intent(this@MainActivity, SecondActivity::class.java)
                mIntent.putExtra(AppConstant.key_name, Nombre)
                mIntent.putExtra(AppConstant.key_id, Experiencia)
                mIntent.putExtra(AppConstant.key_height, Altura)
                mIntent.putExtra(AppConstant.key_weight, Peso)
                mIntent.putExtra(AppConstant.key_id, Identificador)
                startActivity(mIntent)


                //Log.d("OBJ", jObj.getJSONArray("results")[0].toString())

                //val jObjresult = jObj.getJSONArray("results")

                pokemon = MutableList(20) { i ->
                    Log.d("prueba", i.toString())
                    val jObjresultobj = JSONObject(jObjresult[i].toString())
                    Pokemon(i, jObjresultobj.getString("name"), jObjresultobj.getString("url"))

                }

            }


        }*/
    }

    fun initRecycler() {
        viewManager = LinearLayoutManager(this)
        viewAdapter = PokemonAdapter(pokemon)

        rv_pokemon_list.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

    }
    private inner class FetchPokemonTask : AsyncTask<Void, Void, String>() {
        override fun doInBackground(vararg params: Void?): String? {


            // val ID = pokemonNumbers[0]

            val prueba = Network.Factory

            //Por que?? !!
            val pokeAPI: URL = prueba.buildUrl()!!

            return try {
                prueba.getResponseFromHttpUrl(pokeAPI)

            } catch (e: IOException) {
                e.printStackTrace()
                ""
            }
        }

        override fun onPostExecute(pokemonInfo: String?) {
            if (pokemonInfo != null || pokemonInfo != "") {
                Log.d("Info", pokemonInfo + "")


                val jObj = JSONObject(pokemonInfo)
                Log.d("OBJ", jObj.getJSONArray("results")[0].toString())

                val jObjresult = jObj.getJSONArray("results")

                pokemon = MutableList(20) { i ->
                    Log.d("prueba", i.toString())


                    val jObjresultobj = JSONObject(jObjresult[i].toString())

                    Pokemon(i + 1, jObjresultobj.getString("name"), jObjresultobj.getString("url"))


                }
                initRecycler()
            } else {
                tv_pokemon_name.text = pokemonInfo
            }

        }

    }

}
