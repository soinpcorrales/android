package com.labs.soin.pokedex.presenters;

import com.labs.soin.pokedex.MainApplication;
import com.labs.soin.pokedex.callbacks.PokemonDetailsCallback;
import com.labs.soin.pokedex.interactors.PokemonDetailsIteractor;
import com.labs.soin.pokedex.models.PokemonDetails;
import com.labs.soin.pokedex.models.Pokemon;
import com.labs.soin.pokedex.views.PokemonDetailsView;

public class PokemonDetailsPresenter implements Presenter<PokemonDetailsView>, PokemonDetailsCallback {

    private PokemonDetailsView pokemonsMvpView;
    private PokemonDetailsIteractor pokemonsInteractor;
    private int offset;
    private  Pokemon currentPokemon;

    public PokemonDetailsPresenter(Pokemon data) {
        this.currentPokemon = data;
    }

    @Override
    public void setView(PokemonDetailsView view) {
        if (view == null) throw new IllegalArgumentException("You can't set a null view");
        pokemonsMvpView = view;
        pokemonsInteractor = new PokemonDetailsIteractor(MainApplication.getContext());
        pokemonsInteractor.loadPokemonInformation(String.valueOf(this.currentPokemon.getNumber()), this);
    }

    @Override
    public void detachView() {
        pokemonsMvpView = null;
    }

    @Override
    public void onResponse(PokemonDetails pokemonData) {
        pokemonsMvpView.hideLoading();
        pokemonsMvpView.renderPokemonData(pokemonData);
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


    public void load(String id){
        pokemonsInteractor.loadPokemonInformation(id, this);
    }
}
