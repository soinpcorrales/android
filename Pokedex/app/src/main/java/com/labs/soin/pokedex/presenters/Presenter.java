 package com.labs.soin.pokedex.presenters;

public interface Presenter<V>{

    void setView(V view);
    void detachView();
}
