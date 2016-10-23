package com.cervezoteca.anjov.presentation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.cervezoteca.anjov.domain.model.TapBeer;
import com.cervezoteca.anjov.presentation.adapter.BeerListAdapter;
import com.cervezoteca.anjov.presentation.di.HasComponent;
import com.cervezoteca.anjov.presentation.di.component.ApplicationComponent;
import com.cervezoteca.anjov.presentation.di.component.DaggerTapBeersComponent;
import com.cervezoteca.anjov.presentation.di.component.TapBeersComponent;
import com.cervezoteca.anjov.presentation.di.module.ActivityModule;
import com.cervezoteca.anjov.presentation.di.module.TapBeersModule;
import com.cervezoteca.anjov.presentation.presenter.TapBeerPresenter;
import com.cervezoteca.anjov.presentation.view.TapBeersView;
import com.cervezoteca.anjov.presentation.view.fragment.BeerFragment;
import com.cervezoteca.anjov.presentation.view.fragment.BreweriesFragment;
import com.cervezoteca.anjov.presentation.view.fragment.TapBeerFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TapBeerActivity extends AppCompatActivity implements HasComponent<TapBeersComponent> {

    private TapBeersComponent tapBeersComponent;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fragment_container)
    FrameLayout fragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tap_beers);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        final android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //if (navigationView != null) {
        setupDrawerContent(navigationView);
        installMenuListeners(navigationView);
        //}


        this.initializeInjector();
        tapBeersComponent.inject(this);

        BeerFragment f = BeerFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, f).commit();

    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    private void installMenuListeners(NavigationView navigationView) {
        MenuItem navBeers = navigationView.getMenu().findItem(R.id.nav_beers);
        navBeers.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                TapBeerFragment f = TapBeerFragment.newInstance();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, f).commit();
                return false;
            }

        });

        MenuItem navBreweries = navigationView.getMenu().findItem(R.id.nav_breweries);
        navBreweries.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                BreweriesFragment f = BreweriesFragment.newInstance();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, f).commit();
                return false;
            }
        });

        MenuItem navBottleBeers = navigationView.getMenu().findItem(R.id.nav_shop);
        navBottleBeers.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                BeerFragment f = BeerFragment.newInstance();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, f).commit();
                return false;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((CervezotecaApp) getApplication()).getApplicationComponent();
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
    public TapBeersComponent getComponent() {
        return tapBeersComponent;
    }
}
