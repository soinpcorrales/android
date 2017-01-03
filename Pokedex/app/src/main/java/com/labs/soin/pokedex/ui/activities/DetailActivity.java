package com.labs.soin.pokedex.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.labs.soin.pokedex.R;
import com.labs.soin.pokedex.commons.Constants;
import com.labs.soin.pokedex.models.Ability;
import com.labs.soin.pokedex.models.Move;
import com.labs.soin.pokedex.models.Pokemon;
import com.labs.soin.pokedex.models.PokemonDetails;
import com.labs.soin.pokedex.models.Stat;
import com.labs.soin.pokedex.presenters.PokemonDetailsPresenter;
import com.labs.soin.pokedex.utils.AlertUtils;
import com.labs.soin.pokedex.views.PokemonDetailsView;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DetailActivity extends BaseActivity  implements PokemonDetailsView {

    @BindView(R.id.name)
    TextView _name;

    @BindView(R.id.image_detail)
    ImageView _imageDetail;

    @BindView(R.id.number)
    TextView _number;

    @BindView(R.id.height)
    TextView _height;

    @BindView(R.id.weight)
    TextView _weight;

    @BindView(R.id.habilities)
    TextView _habilities;

    @BindView(R.id.stats)
    TextView _stat;

    @BindView(R.id.moves)
    TextView _moves;

    @BindView(R.id.pb_pokemons)
    ProgressBar _pb_pokemon;

    @BindView(R.id.txt_message)
    TextView _txt_message;

    @BindView(R.id.bt_addPokemon)
    ImageButton _bt_addPokemon;

    private Pokemon currentPokemon;
    private PokemonDetailsPresenter pokemonDetailsPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        init();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void showLoading() {
        this._pb_pokemon.setVisibility(View.GONE);
        this._txt_message.setVisibility(View.GONE);
        hideInformation();
    }

    @Override
    public void hideLoading() {
        _pb_pokemon.setVisibility(View.GONE);
        hideInformation();
    }

    @Override
    public void showPokemonNotFoundMessage() {
        _pb_pokemon.setVisibility(View.GONE);
        _txt_message.setVisibility(View.VISIBLE);
        _txt_message.setText(getString(R.string.error_pokemons_not_found));
        hideInformation();
    }

    @Override
    public void showConnectionErrorMessage() {
        _pb_pokemon.setVisibility(View.GONE);
        _txt_message.setVisibility(View.VISIBLE);
        _txt_message.setText(getString(R.string.error_internet_connection));
        hideInformation();
    }

    @Override
    public void showServerError() {
        _pb_pokemon.setVisibility(View.GONE);
        _txt_message.setVisibility(View.VISIBLE);
        _txt_message.setText(getString(R.string.error_server_internal));
        hideInformation();
    }

    /**
     * This method render the information and set in the components the information.
     *
     * @param data resource with pokemon details
     */
    @Override
    public void renderPokemonData(PokemonDetails data) {
        _txt_message.setVisibility(View.GONE);
        List<String> habilitiesText = new ArrayList<>();
        List<String> statText = new ArrayList<>();
        List<String> moveText = new ArrayList<>();
        this._name.setText(data.getName());
        this._height.setText(data.getHeight() + Constants.HEIGHT);
        this._weight.setText(data.getWeight() + Constants.WEIGHT);
        this._number.setText(Constants.NUMBER + data.getId());

        if (data.getAbilities() != null) {
            for (Ability a : data.getAbilities()) {
                String temp = a.getAbility().getName();
                habilitiesText.add(temp);
            }
        }

        if (data.getStats() != null) {
            for (Stat s : data.getStats()) {
                String temp = s.getStat().getName();
                statText.add(temp);
            }
        }

        //show only some movements
        if (data.getMoves() != null) {
            int move = 0;
            for (Move m : data.getMoves()) {
                if (move < 10) {
                    String temp = m.getMove().getName();
                    moveText.add(temp);
                    move++;
                } else {
                    break;
                }
            }
        }

        this._habilities.setText(Constants.ABILITIES + habilitiesText.toString());
        this._stat.setText(Constants.STATS + statText.toString());
        this._moves.setText(Constants.MOVES + moveText.toString());

        final String url = Constants.DEFAULT_URL_IMAG + data.getId() + Constants.IMAGE_EXTENSION;
        Glide.with(this._imageDetail.getContext())
                .load(url)
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(_imageDetail);

        showInformation();
    }

    private void hideInformation() {
        this._name.setVisibility(View.GONE);
        this._height.setVisibility(View.GONE);
        this._weight.setVisibility(View.GONE);
        this._number.setVisibility(View.GONE);
        this._imageDetail.setVisibility(View.GONE);
        this._habilities.setVisibility(View.GONE);
        this._stat.setVisibility(View.GONE);
        this._moves.setVisibility(View.GONE);
        this._bt_addPokemon.setVisibility(View.GONE);
    }


    private void showInformation() {
        this._name.setVisibility(View.VISIBLE);
        this._height.setVisibility(View.VISIBLE);
        this._weight.setVisibility(View.VISIBLE);
        this._number.setVisibility(View.VISIBLE);
        this._imageDetail.setVisibility(View.VISIBLE);
        this._habilities.setVisibility(View.VISIBLE);
        this._stat.setVisibility(View.VISIBLE);
        this._moves.setVisibility(View.VISIBLE);
        this._bt_addPokemon.setVisibility(View.VISIBLE);
    }

    @Override
    public void init() {
        initUI();
        initObs();
    }

    @Override
    public void initUI() {
        injectViews();
    }

    @Override
    public void initObs() {
        currentPokemon = Parcels.unwrap(getIntent().getParcelableExtra(Constants.CURRENT_POKEMON));
        pokemonDetailsPresenter = new PokemonDetailsPresenter(currentPokemon);
        pokemonDetailsPresenter.setView(this);
        this._bt_addPokemon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    AlertUtils.showToaster(_bt_addPokemon.getContext(), Constants.MS_ADD_BUTTON);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_detail;
    }
}
