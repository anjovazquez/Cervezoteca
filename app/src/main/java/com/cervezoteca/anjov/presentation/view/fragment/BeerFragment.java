package com.cervezoteca.anjov.presentation.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.cervezoteca.anjov.domain.model.BottleBeer;
import com.cervezoteca.anjov.presentation.R;
import com.cervezoteca.anjov.presentation.adapter.BottleBeerListAdapter;
import com.cervezoteca.anjov.presentation.di.HasComponent;
import com.cervezoteca.anjov.presentation.di.component.TapBeersComponent;
import com.cervezoteca.anjov.presentation.presenter.BottleBeerPresenter;
import com.cervezoteca.anjov.presentation.view.BottleBeersView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BeerFragment extends Fragment implements BottleBeersView {

    @Inject
    BottleBeerPresenter bottleBeerPresenter;

    @BindView(R.id.beerList)
    RecyclerView rBeerList;

    @BindView(R.id.loadingBeersBar)
    ProgressBar loadingBar;

    BottleBeerListAdapter bottleBeerListAdapter;

    public BeerFragment() {
        // Required empty public constructor
    }

    public static BeerFragment newInstance() {
        BeerFragment fragment = new BeerFragment();
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
        View view = inflater.inflate(R.layout.bottle_beers_fragment, container, false);
        ButterKnife.bind(this, view);

        this.getComponent(TapBeersComponent.class).inject(this);

        rBeerList.setHasFixedSize(true);
        rBeerList.setLayoutManager(new LinearLayoutManager(getActivity()));
        bottleBeerListAdapter = new BottleBeerListAdapter(getActivity(), new ArrayList<BottleBeer>());
        rBeerList.setAdapter(bottleBeerListAdapter);


        bottleBeerPresenter.setView(this);
        bottleBeerPresenter.listBottleBeers();

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

    @Override
    public void renderBottleBeers(List<BottleBeer> beerList) {
        if (beerList != null) {
            this.bottleBeerListAdapter.setBeerListCollection(beerList);
            rBeerList.setAdapter(bottleBeerListAdapter);
        }
    }
}
