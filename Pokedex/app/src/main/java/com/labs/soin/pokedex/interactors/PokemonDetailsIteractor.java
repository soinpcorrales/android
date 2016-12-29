package com.labs.soin.pokedex.interactors;

import android.content.Context;
import android.util.Log;

import com.labs.soin.pokedex.MainApplication;
import com.labs.soin.pokedex.callbacks.PokemonDetailsCallback;
import com.labs.soin.pokedex.exception.HttpNotFound;
import com.labs.soin.pokedex.models.PokemonDetails;
import com.labs.soin.pokedex.services.ServiceGenerator;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * This class manage the communication with the Pokemon service and allow to return to the presenter
 * class the information required to show.
 * @author pcorrales
 */
public class PokemonDetailsIteractor {

    private final MainApplication myApp;
    private static final String TAG = "PokemonInteractor";

    public PokemonDetailsIteractor(Context context) {
        this.myApp = MainApplication.get(context);
    }

    /**
     * Load the data from the API (load a single resource)
     * @param id identifier of the pokemon resource
     * @param PokemonDetailsCallback
     */
    public void loadPokemonInformation(final String id, final PokemonDetailsCallback PokemonDetailsCallback) {

        try {
            ServiceGenerator.getPokemonService().getPokemonById(id)
                    .subscribeOn(myApp.SubscribeScheduler())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<PokemonDetails>() {
                        @Override
                        public void onCompleted() {
                            Log.d(TAG, "complete teh response");
                        }

                        @Override
                        public void onNext(PokemonDetails pokemonData) {
                            PokemonDetailsIteractor.this.onSuccess(pokemonData, PokemonDetailsCallback);
                        }

                        @Override
                        public void onError(Throwable e) {
                            PokemonDetailsIteractor.this.onError(e, PokemonDetailsCallback);
                        }
                    });
        } catch (Exception error) {
            Log.e(TAG, error.getMessage(), error);
        }
    }

    /**
     * This Method  manage the success response
     * @param pokemonData
     * @param PokemonDetailsCallback
     */
    private void onSuccess(PokemonDetails pokemonData, PokemonDetailsCallback PokemonDetailsCallback) {
        if (pokemonData != null) {
            PokemonDetailsCallback.onResponse(pokemonData);
        } else {
            PokemonDetailsCallback.onPokemonNotFound();
        }
    }

    /**
     * This method manage the error
     * @param throwable
     * @param PokemonDetailsCallback
     */
    private void onError(Throwable throwable, PokemonDetailsCallback PokemonDetailsCallback) {
        if (HttpNotFound.isHttp404(throwable)) {
            PokemonDetailsCallback.onPokemonNotFound();
        } else if (throwable.getMessage().equals(HttpNotFound.SERVER_INTERNET_ERROR)) {
            PokemonDetailsCallback.onNetworkConnectionError();
        } else {
            PokemonDetailsCallback.onServerError();
        }
    }
}
