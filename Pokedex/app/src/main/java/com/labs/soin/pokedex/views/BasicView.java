package com.labs.soin.pokedex.views;

import com.labs.soin.pokedex.models.Pokemon;
import com.labs.soin.pokedex.models.PokemonDetails;

import java.util.List;

/**
 * Interface with the required functions in any view.
 * @author  pcorrales
 */

public interface BasicView {

    void showLoading();

    void hideLoading();

    void showPokemonNotFoundMessage();

    void showConnectionErrorMessage();

    void showServerError();
}
