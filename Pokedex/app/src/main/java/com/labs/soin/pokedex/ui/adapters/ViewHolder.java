package com.labs.soin.pokedex.ui.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.labs.soin.pokedex.R;
import com.labs.soin.pokedex.commons.Constants;
import com.labs.soin.pokedex.models.Pokemon;
import com.labs.soin.pokedex.ui.activities.DetailActivity;
import com.labs.soin.pokedex.utils.ItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * This class manage the view in the recyclerview. It represents each of the component in the list.
 * @author  pcorrales
 */

public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    @BindView(R.id.img_view)
    ImageView _img_view;
    @BindView(R.id.txt_name)
    TextView _txt_name;

    private ItemClickListener _itemClickListener;

    public ViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setClickable(true);
        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        _itemClickListener = itemClickListener;
    }

    public ImageView getImg_view() {
        return _img_view;
    }

    public void setImg_view(ImageView img_view) {
        _img_view = img_view;
    }

    public TextView getTxt_name() {
        return _txt_name;
    }

    public void setTxt_name(TextView txt_name) {
        _txt_name = txt_name;
    }

    @Override
    public void onClick(View view) {
        _itemClickListener.onItemClick(view, getLayoutPosition(), false,0);
    }
}
