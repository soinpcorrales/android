package com.labs.soin.pokedex.interactors;

import android.content.Context;
import android.util.Log;

import com.labs.soin.pokedex.MainApplication;
import com.labs.soin.pokedex.callbacks.PokemonCallback;
import com.labs.soin.pokedex.exception.HttpNotFound;
import com.labs.soin.pokedex.models.Pokemon;
import com.labs.soin.pokedex.models.Response;
import com.labs.soin.pokedex.services.ServiceGenerator;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * This class manage the communication with the Pokemon service and allow to return to the presenter
 * class the information required to show.
 * @author pcorrales
 */
public class PokemonInteractor {

    private final MainApplication myApp;
    private static final String TAG = "PokemonInteractor";

    public PokemonInteractor(Context context) {
        this.myApp = MainApplication.get(context);
    }

    /**
     * Load the data from the Pokemon API.
     * @param offset position to continue the search.
     * @param pokemonCallback callback from return teh result.
     */
    public void loadPokemonList(final int offset, final PokemonCallback pokemonCallback) {

        try {
            ServiceGenerator.getPokemonService().getPokemonList(20, offset)
                    .subscribeOn(myApp.SubscribeScheduler())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<Response>() {
                        @Override
                        public void onCompleted() {
                            Log.d("Log", "Complete");
                        }

                        @Override
                        public void onNext(Response response) {
                            PokemonInteractor.this.onSuccess(response, pokemonCallback);
                        }

                        @Override
                        public void onError(Throwable e) {
                            PokemonInteractor.this.onError(e, pokemonCallback);
                        }
                    });
        } catch (Exception error) {
            Log.e(TAG, error.getMessage(), error);
        }
    }

    /**
     * This Method  manage the success response
     * @param response
     * @param pokemonCallback
     */
    private void onSuccess(Response response, PokemonCallback pokemonCallback) {
        List<Pokemon> list = response.getResults();
        if (!list.isEmpty()) {
            pokemonCallback.onResponse(list);
        } else {
            pokemonCallback.onPokemonNotFound();
        }
    }

    /**
     * This method manage the error
     * @param throwable
     * @param pokemonCallback
     */
    private void onError(Throwable throwable, PokemonCallback pokemonCallback) {
        if (HttpNotFound.isHttp404(throwable)) {
            pokemonCallback.onPokemonNotFound();
        } else if (throwable.getMessage().equals(HttpNotFound.SERVER_INTERNET_ERROR)) {
            pokemonCallback.onNetworkConnectionError();
        } else {
            pokemonCallback.onServerError();
        }
    }
}
