package com.labs.soin.pokedex.views;

import com.labs.soin.pokedex.models.PokemonDetails;

import java.util.List;
/**
 * Interface with the required functions in details view
 * @author  pcorrales
 */
public interface PokemonDetailsView extends BasicView{
    void renderPokemonData(PokemonDetails data);
}
