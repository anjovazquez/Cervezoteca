package com.cervezoteca.anjov.presentation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cervezoteca.anjov.domain.model.Brewery;
import com.cervezoteca.anjov.presentation.R;
import com.squareup.picasso.Picasso;

import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by anjov on 07/10/2016.
 */

public class BreweriesListAdapter extends RecyclerView.Adapter<BreweriesListAdapter.BreweryViewHolder> {

    private Collection<Brewery> mDataset;
    private Context context;

    public BreweriesListAdapter(Context context, Collection<Brewery> breweryListCollection) {
        this.mDataset = (List<Brewery>) breweryListCollection;
        this.context = context;
    }

    public void setBreweryListCollection(Collection<Brewery> breweryList) {
        mDataset = breweryList;
    }

    static class BreweryViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.breweryName)
        TextView breweryName;

        @BindView(R.id.breweryImage)
        ImageView breweryImage;

        public BreweryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public BreweriesListAdapter.BreweryViewHolder onCreateViewHolder(ViewGroup parent,
                                                               int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.brewery_item, parent, false);

        BreweryViewHolder vh = new BreweryViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(BreweriesListAdapter.BreweryViewHolder holder, int position) {
        final Brewery brewery = ((List<Brewery>)this.mDataset).get(position);
        holder.breweryName.setText(brewery.getName());
        Picasso.with(context).load(brewery.getImage()).into(holder.breweryImage);
    }

    @Override
    public int getItemCount() {
        return mDataset!=null?mDataset.size():0;
    }
}
