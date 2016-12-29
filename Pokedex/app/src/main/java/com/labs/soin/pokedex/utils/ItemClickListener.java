package com.labs.soin.pokedex.utils;

import android.view.View;


public interface ItemClickListener {
    void onItemClick(View view, int position, boolean isLongClick, int type);
}
