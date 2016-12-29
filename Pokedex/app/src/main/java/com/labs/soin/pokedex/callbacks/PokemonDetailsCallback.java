 package com.labs.soin.pokedex.callbacks;

 import com.labs.soin.pokedex.models.PokemonDetails;


 public interface PokemonDetailsCallback {

     void onResponse(PokemonDetails pokemonData);

     void onPokemonNotFound();

     void onNetworkConnectionError();

     void onServerError();
 }

