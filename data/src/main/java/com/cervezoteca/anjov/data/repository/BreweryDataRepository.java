package com.cervezoteca.anjov.data.repository;

import com.cervezoteca.anjov.data.repository.datasource.BreweryDataStore;
import com.cervezoteca.anjov.data.repository.datasource.BreweryDataStoreFactory;
import com.cervezoteca.anjov.domain.model.Brewery;
import com.cervezoteca.anjov.domain.repository.BreweryRepository;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by anjov on 07/10/2016.
 */

public class BreweryDataRepository implements BreweryRepository {

    private final BreweryDataStoreFactory breweryDataStoreFactory;

    @Inject
    public BreweryDataRepository(BreweryDataStoreFactory breweryDataStoreFactory){
        this.breweryDataStoreFactory = breweryDataStoreFactory;
    }

    @Override
    public Observable<List<Brewery>> getBreweries() {
        final BreweryDataStore breweryDataStore = this.breweryDataStoreFactory.createCloudDataStore();
        return breweryDataStore.getBreweries();
    }
}
