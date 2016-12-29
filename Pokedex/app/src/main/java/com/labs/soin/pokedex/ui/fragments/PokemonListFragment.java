package com.labs.soin.pokedex.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.labs.soin.pokedex.R;
import com.labs.soin.pokedex.models.PokemonDetails;
import com.labs.soin.pokedex.models.Pokemon;
import com.labs.soin.pokedex.presenters.PokemonPresenter;
import com.labs.soin.pokedex.ui.adapters.PokemonAdapter;
import com.labs.soin.pokedex.views.PokemonView;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PokemonListFragment extends Fragment implements PokemonView {
    @BindView(R.id.rv_artists)
    RecyclerView rv_pokemos;

    @BindView(R.id.pb_pokemons)
    ProgressBar pb_pokemons;

    @BindView(R.id.iv_iconImage)
    ImageView iv_iconImage;

    @BindView(R.id.txt_message)
    TextView txt_message;

    private PokemonAdapter adapter;
    private PokemonPresenter pokemonPresenter;

    public PokemonListFragment() {
    }

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pokemonPresenter = new PokemonPresenter();
        pokemonPresenter.setView(this);
    }

    @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                                 Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pokemonlist, container, false);
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        setupRecyclerView();
    }

    @Override public void onDestroy() {
        pokemonPresenter.detachView();
        super.onDestroy();
    }

    @Override public Context getContext() {
        return getActivity();
    }

    @Override
    public void showLoading() {
        this.pb_pokemons.setVisibility(View.VISIBLE);
        this.iv_iconImage.setVisibility(View.GONE);
        this.txt_message.setVisibility(View.GONE);
        rv_pokemos.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        pb_pokemons.setVisibility(View.GONE);
        rv_pokemos.setVisibility(View.VISIBLE);
    }

    @Override
    public void showPokemonNotFoundMessage() {
        pb_pokemons.setVisibility(View.GONE);
        iv_iconImage.setVisibility(View.VISIBLE);
        txt_message.setVisibility(View.VISIBLE);
        txt_message.setText(getString(R.string.error_pokemons_not_found));
        iv_iconImage.setImageDrawable(ContextCompat.getDrawable(getContext(), R.mipmap.ic_launcher));

    }

    @Override
    public void showConnectionErrorMessage() {
        pb_pokemons.setVisibility(View.GONE);
        txt_message.setVisibility(View.VISIBLE);
        iv_iconImage.setVisibility(View.VISIBLE);
        txt_message.setText(getString(R.string.error_internet_connection));
        iv_iconImage.setImageDrawable(ContextCompat.getDrawable(getContext(), R.mipmap.ic_not_internet));
    }

    @Override
    public void showServerError() {
        pb_pokemons.setVisibility(View.GONE);
        txt_message.setVisibility(View.VISIBLE);
        iv_iconImage.setVisibility(View.VISIBLE);
        txt_message.setText(getString(R.string.error_server_internal));
        iv_iconImage.setImageDrawable(ContextCompat.getDrawable(getContext(), R.mipmap.ic_launcher));
    }

    @Override
    public void renderPokemons(List<Pokemon> objects) {
        adapter.setPokemons(objects);
        adapter.setActivity(getActivity());
        adapter.notifyDataSetChanged();
    }

    private void setupRecyclerView() {
        adapter = new PokemonAdapter();
        adapter.setActivity(getActivity());
        final GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);
        rv_pokemos.setLayoutManager(manager);
        rv_pokemos.setAdapter(adapter);
        rv_pokemos.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) {
                    int visibleItemCount = manager.getChildCount ();
                    int totalItemCount = manager.getItemCount();
                    int pastVisibleItems = manager.findFirstVisibleItemPosition();
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            pokemonPresenter.load();
                        }
                }
            }
        });
    }
}
