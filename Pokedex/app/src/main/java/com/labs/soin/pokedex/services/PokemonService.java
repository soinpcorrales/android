package com.labs.soin.pokedex.services;

import com.labs.soin.pokedex.commons.Constants;
import com.labs.soin.pokedex.models.PokemonDetails;
import com.labs.soin.pokedex.models.Response;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * This class exposes the API method.
 * @author pcorrales
 * @since 12/10/2016.
 */

public interface PokemonService {

    /**
     * Method finds a pokemon by id
     * @param id  identifier of the result
     * @return observable with Resource
     */
    @GET(Constants.POKEMON_SEARCH)
    Observable<PokemonDetails> getPokemonById(
            @Path(Constants.PATH_PARAM) String id);

    /**
     * This method finds a pokemon list in pagined form
     * @param limit number of pokemon resource to return
     * @param offset final position
     * @return observable with a Response Ressource
     */
    @GET(Constants.POKEMON_BASE)
    Observable<Response> getPokemonList(@Query("limit") int limit, @Query("offset") int offset);

}
