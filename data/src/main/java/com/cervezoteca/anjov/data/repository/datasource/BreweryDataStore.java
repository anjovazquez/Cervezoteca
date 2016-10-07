package com.cervezoteca.anjov.data.repository.datasource;

import com.cervezoteca.anjov.domain.model.Brewery;

import java.util.List;

import rx.Observable;

/**
 * Created by anjov on 07/10/2016.
 */

public interface BreweryDataStore {

    Observable<List<Brewery>> getBreweries();
}
