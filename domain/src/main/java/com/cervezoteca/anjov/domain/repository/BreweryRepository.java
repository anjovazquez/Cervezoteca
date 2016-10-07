package com.cervezoteca.anjov.domain.repository;

import com.cervezoteca.anjov.domain.model.Brewery;

import java.util.List;

import rx.Observable;


/**
 * Created by anjov on 07/10/2016.
 */

public interface BreweryRepository {

    Observable<List<Brewery>> getBreweries();
}
