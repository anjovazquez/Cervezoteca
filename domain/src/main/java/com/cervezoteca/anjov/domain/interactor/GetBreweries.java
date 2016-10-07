package com.cervezoteca.anjov.domain.interactor;

import com.cervezoteca.anjov.domain.executor.PostExecutionThread;
import com.cervezoteca.anjov.domain.executor.ThreadExecutor;
import com.cervezoteca.anjov.domain.repository.BreweryRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by anjov on 07/10/2016.
 */

public class GetBreweries extends UseCase {

    private final BreweryRepository breweryRepository;

    @Inject
    public GetBreweries(BreweryRepository breweryRepository,
                     ThreadExecutor threadExecutor,
                     PostExecutionThread postExecutionThread){
        super(threadExecutor, postExecutionThread);
        this.breweryRepository = breweryRepository;
    }

    @Override public Observable buildUseCaseObservable() {
        return this.breweryRepository.getBreweries();
    }
}
