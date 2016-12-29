package com.labs.soin.pokedex.services;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.labs.soin.pokedex.commons.Constants.POKEMON_API;

/**
 * Modify  by pcorrales
 *
 */

public class ServiceGenerator {

    static HttpLoggingInterceptor logging = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder().addInterceptor(logging);

    /**
     * create a retrofit builder, with the url base, and specif the converter of the result
     */
    private static Retrofit.Builder RETROFIT =
            new Retrofit.Builder()
                    .baseUrl(POKEMON_API)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

    private static final PokemonService POKEMON_SERVICE = createService(PokemonService.class);
    /**
     * This method create a new service with retrofit
     * @param serviceClass
     * @param <S> class
     * @return  new Service <S>
     */
    public static  <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = RETROFIT.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }

    public static PokemonService getPokemonService() {
        return POKEMON_SERVICE;
    }
}

