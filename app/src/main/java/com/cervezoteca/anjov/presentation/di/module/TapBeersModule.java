package com.cervezoteca.anjov.presentation.di.module;

import com.cervezoteca.anjov.domain.executor.PostExecutionThread;
import com.cervezoteca.anjov.domain.executor.ThreadExecutor;
import com.cervezoteca.anjov.domain.interactor.GetBottleBeers;
import com.cervezoteca.anjov.domain.interactor.GetBreweries;
import com.cervezoteca.anjov.domain.interactor.GetTapBeers;
import com.cervezoteca.anjov.domain.interactor.UseCase;
import com.cervezoteca.anjov.domain.repository.BreweryRepository;
import com.cervezoteca.anjov.presentation.di.PerActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by anjov on 07/10/2016.
 */
@Module
public class TapBeersModule {

    @Provides
    @PerActivity
    @Named("getTapBeers")
    UseCase provideGetTapBeers(BreweryRepository breweryRepository,
                                ThreadExecutor threadExecutor,
                                PostExecutionThread postExecutionThread) {
        return new GetTapBeers(breweryRepository, threadExecutor, postExecutionThread);
    }

    @Provides
    @PerActivity
    @Named("getBreweries")
    UseCase provideGetBreweries(BreweryRepository breweryRepository,
                                ThreadExecutor threadExecutor,
                                PostExecutionThread postExecutionThread) {
        return new GetBreweries(breweryRepository, threadExecutor, postExecutionThread);
    }

    @Provides
    @PerActivity
    @Named("getBottleBeers")
    UseCase provideGetBottleBeers(BreweryRepository breweryRepository,
                                ThreadExecutor threadExecutor,
                                PostExecutionThread postExecutionThread) {
        return new GetBottleBeers(breweryRepository, threadExecutor, postExecutionThread);
    }
}
