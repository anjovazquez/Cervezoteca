package com.cervezoteca.anjov.presentation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cervezoteca.anjov.domain.model.BottleBeer;
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

public class BottleBeerListAdapter extends RecyclerView.Adapter<BottleBeerListAdapter.BeerViewHolder> {

    private Collection<BottleBeer> mDataset;
    private Context context;
    private OnBottleBeerSelectedListener listener = null;

    public BottleBeerListAdapter(Context context, Collection<BottleBeer> beerCollection, OnBottleBeerSelectedListener listener) {

        this.mDataset = (List<BottleBeer>) beerCollection;
        this.context = context;
        this.listener = listener;
    }

    public void setBeerListCollection(Collection<BottleBeer> beerList) {
        mDataset = beerList;
    }

    public interface OnBottleBeerSelectedListener {
        void onItemClick(BottleBeer item);
    }

    static class BeerViewHolder extends RecyclerView.ViewHolder {
        //@BindView(R.id.beerId)
        //TextView beerId;

        @BindView(R.id.beerName)
        TextView beerName;

        @BindView(R.id.beerLogo)
        ImageView beerLogo;


        public BeerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public BottleBeerListAdapter.BeerViewHolder onCreateViewHolder(ViewGroup parent,
                                                                   int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bottle_beer_item, parent, false);

        BeerViewHolder vh = new BeerViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(BottleBeerListAdapter.BeerViewHolder holder, int position) {
        final BottleBeer bottleBeer = ((List<BottleBeer>) this.mDataset).get(position);
        //holder.beerId.setText(bottleBeer.getId());
        holder.beerName.setText(bottleBeer.getName());
        Picasso.with(context).load(bottleBeer.getLogo()).into(holder.beerLogo);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(bottleBeer);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset != null ? mDataset.size() : 0;
    }
}
