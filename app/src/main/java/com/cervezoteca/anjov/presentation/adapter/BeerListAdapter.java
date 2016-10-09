package com.cervezoteca.anjov.presentation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cervezoteca.anjov.domain.model.Brewery;
import com.cervezoteca.anjov.domain.model.TapBeer;
import com.cervezoteca.anjov.presentation.R;
import com.squareup.picasso.Picasso;

import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by anjov on 07/10/2016.
 */

public class BeerListAdapter extends RecyclerView.Adapter<BeerListAdapter.BeerViewHolder> {

    private Collection<TapBeer> mDataset;
    private Context context;

    public BeerListAdapter(Context context, Collection<TapBeer> tapBeerCollection) {
        this.mDataset = (List<TapBeer>) tapBeerCollection;
        this.context = context;
    }

    public void setBeerListCollection(Collection<TapBeer> beerList) {
        mDataset = beerList;
    }

    static class BeerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.beerId)
        TextView beerId;

        @BindView(R.id.beerName)
        TextView beerName;

        @BindView(R.id.beerLogo)
        ImageView beerLogo;

        @BindView(R.id.brewery)
        TextView brewery;

        @BindView(R.id.styleName)
        TextView styleName;

        @BindView(R.id.glassPrice)
        TextView glassPrice;

        @BindView(R.id.pintPrice)
        TextView pintPrice;



        public BeerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public BeerListAdapter.BeerViewHolder onCreateViewHolder(ViewGroup parent,
                                                                int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.beer_item, parent, false);

        BeerViewHolder vh = new BeerViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(BeerListAdapter.BeerViewHolder holder, int position) {
        final TapBeer tapBeer = ((List<TapBeer>)this.mDataset).get(position);
        holder.beerId.setText(tapBeer.getId());
        holder.beerName.setText(tapBeer.getBeer().getName());
        Picasso.with(context).load(tapBeer.getBeer().getLogo()).into(holder.beerLogo);
        holder.brewery.setText(tapBeer.getBeer().getBrewery_name()+ ", "+tapBeer.getBeer().getBrewery_country());
        holder.styleName.setText(tapBeer.getBeer().getStyle_name());
        holder.glassPrice.setText("Caña: "+tapBeer.getBeer().getGlass_price()+"€");
        holder.pintPrice.setText("Pinta: "+tapBeer.getBeer().getPint_price()+"€");
    }

    @Override
    public int getItemCount() {
        return mDataset!=null?mDataset.size():0;
    }
}
