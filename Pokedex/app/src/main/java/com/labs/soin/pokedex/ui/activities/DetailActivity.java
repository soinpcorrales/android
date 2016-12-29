package com.labs.soin.pokedex.ui.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.labs.soin.pokedex.R;
import com.labs.soin.pokedex.commons.Constants;
import com.labs.soin.pokedex.models.Pokemon;
import com.labs.soin.pokedex.ui.fragments.PokemonDetailsFragment;
import com.labs.soin.pokedex.ui.fragments.PokemonListFragment;

import org.parceler.Parcels;

import butterknife.BindView;

public class DetailActivity  extends AppCompatActivity {

    private Pokemon currentPokemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Pokemon current = Parcels.unwrap(getIntent().getParcelableExtra(Constants.CURRENT_POKEMON));
        Fragment fragment = new PokemonDetailsFragment();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.pokemonDetails_fragment, fragment);
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.CURRENT_POKEMON, Parcels.wrap(current));
        fragment.setArguments(bundle);
        fragmentTransaction.commit();
    }
}
