package com.cervezoteca.anjov.domain.repository;

import com.cervezoteca.anjov.domain.model.BottleBeer;
import com.cervezoteca.anjov.domain.model.Brewery;
import com.cervezoteca.anjov.domain.model.TapBeer;

import java.util.List;

import rx.Observable;


/**
 * Created by anjov on 07/10/2016.
 */

public interface BreweryRepository {

    Observable<List<Brewery>> getBreweries();

    Observable<List<TapBeer>>  getTapBeers();

    Observable<List<BottleBeer>> getBottleBeers();
}
