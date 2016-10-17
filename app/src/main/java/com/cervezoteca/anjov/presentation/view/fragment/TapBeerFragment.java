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
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.cervezoteca.anjov.domain.model.TapBeer;
import com.cervezoteca.anjov.presentation.R;
import com.cervezoteca.anjov.presentation.adapter.BeerListAdapter;
import com.cervezoteca.anjov.presentation.di.HasComponent;
import com.cervezoteca.anjov.presentation.di.component.TapBeersComponent;
import com.cervezoteca.anjov.presentation.presenter.TapBeerPresenter;
import com.cervezoteca.anjov.presentation.view.TapBeersView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class TapBeerFragment extends Fragment implements TapBeersView {

    @Inject
    TapBeerPresenter tapBeerPresenter;

    @BindView(com.cervezoteca.anjov.presentation.R.id.beerList)
    RecyclerView rBeerList;

    BeerListAdapter beerListAdapter;

    public TapBeerFragment() {
        // Required empty public constructor
    }

    public static TapBeerFragment newInstance() {
        TapBeerFragment fragment = new TapBeerFragment();
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
        View view = inflater.inflate(R.layout.tap_beers_fragment, container, false);
        ButterKnife.bind(this, view);

        this.getComponent(TapBeersComponent.class).inject(this);

        rBeerList.setHasFixedSize(true);
        rBeerList.setLayoutManager(new LinearLayoutManager(getActivity()));
        beerListAdapter = new BeerListAdapter(getActivity(), new ArrayList<TapBeer>());
        rBeerList.setAdapter(beerListAdapter);


        tapBeerPresenter.setView(this);
        tapBeerPresenter.listTapBeers();

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
        return getActivity();
    }

    @Override
    public void renderTapBeers(List<TapBeer> beerList) {
        if (beerList != null) {
            this.beerListAdapter.setBeerListCollection(beerList);
            rBeerList.setAdapter(beerListAdapter);
        }
    }
}
