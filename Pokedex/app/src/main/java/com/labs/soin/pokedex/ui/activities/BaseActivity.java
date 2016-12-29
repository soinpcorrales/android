package com.labs.soin.pokedex.ui.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by jjimenez on 11/23/16.
 */

public abstract class BaseActivity extends AppCompatActivity {

    public abstract void init();

    public abstract void initUI();

    public abstract void initObs();

    protected abstract int getLayout();

    public void injectViews() {
        ButterKnife.bind(this);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
