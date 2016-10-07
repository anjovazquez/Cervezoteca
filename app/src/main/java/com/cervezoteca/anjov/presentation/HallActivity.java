package com.cervezoteca.anjov.presentation;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cervezoteca.anjov.domain.model.Brewery;
import com.cervezoteca.anjov.presentation.adapter.BreweriesListAdapter;
import com.cervezoteca.anjov.presentation.di.component.ApplicationComponent;
import com.cervezoteca.anjov.presentation.di.component.BreweriesComponent;
import com.cervezoteca.anjov.presentation.di.component.DaggerBreweriesComponent;
import com.cervezoteca.anjov.presentation.di.module.ActivityModule;
import com.cervezoteca.anjov.presentation.di.module.BreweriesModule;
import com.cervezoteca.anjov.presentation.presenter.BreweriesPresenter;
import com.cervezoteca.anjov.presentation.view.BreweriesView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HallActivity extends AppCompatActivity implements BreweriesView {

    private BreweriesComponent breweriesComponent;

    @Inject
    BreweriesPresenter breweriesPresenter;

    @BindView(R.id.breweryList)
    RecyclerView rBreweryList;

    BreweriesListAdapter breweriesListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall);
        ButterKnife.bind(this);

        rBreweryList.setHasFixedSize(true);
        rBreweryList.setLayoutManager(new LinearLayoutManager(this));
        breweriesListAdapter = new BreweriesListAdapter(this, new ArrayList<Brewery>());
        rBreweryList.setAdapter(breweriesListAdapter);

        this.initializeInjector();
        breweriesComponent.inject(this);
        breweriesPresenter.setView(this);
        breweriesPresenter.listBreweries();
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((CervezotecaApp)getApplication()).getApplicationComponent();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    private void initializeInjector() {
        BreweriesModule breweriesModule = new BreweriesModule();
        this.breweriesComponent = DaggerBreweriesComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule()).breweriesModule(breweriesModule)
                .build();
    }

    @Override
    public void renderBreweries(List<Brewery> breweryList) {
        if (breweryList != null) {
            this.breweriesListAdapter.setBreweryListCollection(breweryList);
            rBreweryList.setAdapter(breweriesListAdapter);
        }
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
}
