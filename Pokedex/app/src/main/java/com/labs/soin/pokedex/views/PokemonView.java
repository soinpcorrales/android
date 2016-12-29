package com.labs.soin.pokedex.views;

import com.labs.soin.pokedex.models.PokemonDetails;
import com.labs.soin.pokedex.models.Pokemon;

import java.util.List;

/**
 * Interface with the required functions in list of pokemos view
 * @author  pcorrales
 */
public interface PokemonView  extends BasicView{
    void renderPokemons(List<Pokemon> objects);
}
