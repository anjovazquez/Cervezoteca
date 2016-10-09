package com.cervezoteca.anjov.presentation;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cervezoteca.anjov.domain.model.TapBeer;
import com.cervezoteca.anjov.presentation.adapter.BeerListAdapter;
import com.cervezoteca.anjov.presentation.di.component.ApplicationComponent;
import com.cervezoteca.anjov.presentation.di.component.DaggerTapBeersComponent;
import com.cervezoteca.anjov.presentation.di.component.TapBeersComponent;
import com.cervezoteca.anjov.presentation.di.module.ActivityModule;
import com.cervezoteca.anjov.presentation.di.module.TapBeersModule;
import com.cervezoteca.anjov.presentation.presenter.TapBeerPresenter;
import com.cervezoteca.anjov.presentation.view.TapBeersView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TapBeerActivity extends AppCompatActivity implements TapBeersView {

    private TapBeersComponent tapBeersComponent;

    @Inject
    TapBeerPresenter tapBeerPresenter;

    @BindView(R.id.beerList)
    RecyclerView rBeerList;

    BeerListAdapter beerListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tap_beers);
        ButterKnife.bind(this);

        rBeerList.setHasFixedSize(true);
        rBeerList.setLayoutManager(new LinearLayoutManager(this));
        beerListAdapter = new BeerListAdapter(this, new ArrayList<TapBeer>());
        rBeerList.setAdapter(beerListAdapter);

        this.initializeInjector();
        tapBeersComponent.inject(this);
        tapBeerPresenter.setView(this);
        tapBeerPresenter.listTapBeers();
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((CervezotecaApp)getApplication()).getApplicationComponent();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    private void initializeInjector() {
        TapBeersModule tapBeersModule = new TapBeersModule();
        this.tapBeersComponent = DaggerTapBeersComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule()).tapBeersModule(tapBeersModule)
                .build();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void renderTapBeers(List<TapBeer> beerList) {
        if (beerList != null) {
            this.beerListAdapter.setBeerListCollection(beerList);
            rBeerList.setAdapter(beerListAdapter);
        }
    }
}
