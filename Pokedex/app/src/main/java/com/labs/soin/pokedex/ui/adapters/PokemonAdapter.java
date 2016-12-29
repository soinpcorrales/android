package com.labs.soin.pokedex.ui.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.labs.soin.pokedex.R;
import com.labs.soin.pokedex.commons.Constants;
import com.labs.soin.pokedex.models.Pokemon;
import com.labs.soin.pokedex.ui.activities.DetailActivity;
import com.labs.soin.pokedex.utils.ItemClickListener;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

/**
 *  This class provides a binding  from the data in the recycler view
 */
public class PokemonAdapter extends RecyclerView.Adapter<ViewHolder>  {

    private List<Pokemon> pokemonList;
    private Activity _activity;

    public PokemonAdapter() {
        pokemonList = new ArrayList<>();
    }

    public void setActivity(Activity activity){
        this._activity = activity;
    }

    public void setPokemons(List<Pokemon> pokemonList) {
        this.pokemonList.addAll(pokemonList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return this.pokemonList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Pokemon current = pokemonList.get(position);
        final String url = Constants.DEFAULT_URL_IMAG + current.getNumber() + Constants.IMAGE_EXTENSION;
        holder.getTxt_name().setText(current.getName());
        Glide.with(holder._img_view.getContext())
                .load(url)
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder._img_view);
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View view, int position, boolean isLongClick, int type) {
                Intent intent = new Intent(view.getContext(), DetailActivity.class);
                intent.putExtra(Constants.CURRENT_POKEMON, Parcels.wrap(current));
                view.getContext().startActivity(intent);
            }
        });
    }
}
