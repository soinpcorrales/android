 package com.labs.soin.pokedex.callbacks;

import com.labs.soin.pokedex.models.Pokemon;


import java.util.List;

/**
 * Created by jjimenez on 11/20/16.
 */

public interface PokemonCallback{

    void onResponse(List<Pokemon> pokemonList);

    void onPokemonNotFound();

    void onNetworkConnectionError();

    void onServerError();
}

