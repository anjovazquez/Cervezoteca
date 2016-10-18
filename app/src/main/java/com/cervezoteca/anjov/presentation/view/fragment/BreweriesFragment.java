package com.cervezoteca.anjov.presentation.view.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.cervezoteca.anjov.domain.model.Brewery;
import com.cervezoteca.anjov.presentation.R;
import com.cervezoteca.anjov.presentation.adapter.BreweriesListAdapter;
import com.cervezoteca.anjov.presentation.di.HasComponent;
import com.cervezoteca.anjov.presentation.di.component.TapBeersComponent;
import com.cervezoteca.anjov.presentation.presenter.BreweriesPresenter;
import com.cervezoteca.anjov.presentation.view.BreweriesView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BreweriesFragment extends Fragment implements BreweriesView {

    @Inject
    BreweriesPresenter breweriesPresenter;

    @BindView(R.id.breweryList)
    RecyclerView rBreweryList;

    @BindView(R.id.loadingBreweriesBar)
    ProgressBar loadingBar;

    BreweriesListAdapter breweriesListAdapter;

    public BreweriesFragment() {
    }
    // TODO: Rename and change types and number of parameters
    public static BreweriesFragment newInstance() {
        BreweriesFragment fragment = new BreweriesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.breweries_fragment, container, false);
        ButterKnife.bind(this, view);

        this.getComponent(TapBeersComponent.class).inject(this);

        rBreweryList.setHasFixedSize(true);
        rBreweryList.setLayoutManager(new LinearLayoutManager(getActivity()));
        breweriesListAdapter = new BreweriesListAdapter(getActivity(), new ArrayList<Brewery>());
        rBreweryList.setAdapter(breweriesListAdapter);

        breweriesPresenter.setView(this);
        breweriesPresenter.listBreweries();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
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
        loadingBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loadingBar.setVisibility(View.INVISIBLE);
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
        return getActivity();
    }
}
