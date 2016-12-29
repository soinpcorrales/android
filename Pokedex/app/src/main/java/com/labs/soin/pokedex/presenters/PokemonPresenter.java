package com.labs.soin.pokedex.presenters;

import com.labs.soin.pokedex.MainApplication;
import com.labs.soin.pokedex.callbacks.PokemonCallback;
import com.labs.soin.pokedex.interactors.PokemonInteractor;
import com.labs.soin.pokedex.models.Pokemon;
import com.labs.soin.pokedex.services.ServiceGenerator;
import com.labs.soin.pokedex.views.PokemonView;

import java.util.List;

public class PokemonPresenter implements Presenter<PokemonView>, PokemonCallback {

    private PokemonView pokemonsMvpView;
    private PokemonInteractor pokemonsInteractor;
    private int offset;

    public PokemonPresenter() {
    }

    @Override
    public void setView(PokemonView view) {
        if (view == null) throw new IllegalArgumentException("You can't set a null view");
        else {
            pokemonsMvpView = view;
            pokemonsInteractor = new PokemonInteractor(MainApplication.getContext());
            pokemonsInteractor.loadPokemonList(offset, this);
        }
    }

    @Override
    public void detachView() {
       pokemonsMvpView = null;
    }

    @Override
    public void onResponse(List<Pokemon> pokemons) {
        pokemonsMvpView.hideLoading();
        pokemonsMvpView.renderPokemons(pokemons);
    }

    @Override
    public void onPokemonNotFound() {
        pokemonsMvpView.showPokemonNotFoundMessage();
    }

    @Override
    public void onNetworkConnectionError() {
        pokemonsMvpView.showConnectionErrorMessage();
    }

    @Override
    public void onServerError() {
        pokemonsMvpView.showServerError();
    }


    /**
     * This method allow to load in paged form the interactor
     */
    public void load(){
        this.offset =  offset+20;
        pokemonsInteractor.loadPokemonList(offset, this);
    }
}
