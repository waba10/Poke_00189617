package com.ayala.pokedex;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.ayala.pokedex.utilities.AppConstant;
import com.ayala.pokedex.utilities.Network;
import com.bumptech.glide.Glide;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

public class SecondActivity extends AppCompatActivity {

    private TextView mTextNombre, mTextId, mTextExperiencia, mTextAltura, mTextPeso;
    private ImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);



        mTextNombre = findViewById(R.id.tv_nombre);
        mTextId = findViewById(R.id.tv_id);
        mTextExperiencia = findViewById(R.id.tv_experiencia);
        mTextAltura = findViewById(R.id.tv_altura);
        mTextPeso = findViewById(R.id.tv_peso);
        mImage=findViewById(R.id.iv_pokemon2);

        Intent mIntent = getIntent();
        if (mIntent != null) {

            String url= mIntent.getStringExtra(AppConstant.key_url);
            String aux=mIntent.getIntExtra(AppConstant.key_id,0)+ "";


            Glide.with(this)
                    .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+aux+".png")
                    .into(mImage);

            Log.d("PokemonURL: ", url);

            new FetchPokemonTask().execute(url);



        }


    }
    private class FetchPokemonTask extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... pokemonNumbers) {




            URL pokeAPI = Network.Factory.buildUr(pokemonNumbers[0]);


            try {
                String result = Network.Factory.getResponseFromHttpUrl(pokeAPI);
                return result;
            } catch (IOException e) {
                e.printStackTrace();
                return "";
            }
        }

        @Override
        protected void onPostExecute(String pokemonInfo) {
            if (pokemonInfo != null || !pokemonInfo.equals("")) {



                try {
                    JSONObject Jprueba = new JSONObject(pokemonInfo);
                    String subObj = Jprueba.getString("name");
                    String subObj1 = Jprueba.getString("base_experience");
                    String subObj2 = Jprueba.getString("height");
                    String subObj3 = Jprueba.getString("weight");
                    String subObj4 = Jprueba.getString("id");










                    mTextNombre.setText(subObj.toString());
                    mTextExperiencia.setText(subObj1.toString());
                    //mTextExperiencia.setText(mIntent.getIntExtra(AppConstant.key_experience,0)+ "");
                    mTextAltura.setText(subObj2.toString());
                    mTextPeso.setText(subObj3.toString());
                    mTextId.setText(subObj4.toString());


                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        }
    }





}

