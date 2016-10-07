package com.cervezoteca.anjov.presentation.di.module;

import android.content.Context;
import android.support.annotation.UiThread;

import com.cervezoteca.anjov.data.repository.BreweryDataRepository;
import com.cervezoteca.anjov.data.repository.executor.JobExecutor;
import com.cervezoteca.anjov.domain.executor.PostExecutionThread;
import com.cervezoteca.anjov.domain.executor.ThreadExecutor;
import com.cervezoteca.anjov.domain.repository.BreweryRepository;
import com.cervezoteca.anjov.presentation.CervezotecaApp;
import com.cervezoteca.anjov.presentation.UIThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by anjov on 07/10/2016.
 */
@Module
public class ApplicationModule {

    private final CervezotecaApp application;

    public ApplicationModule(CervezotecaApp application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    BreweryRepository provideBreweryRepository(BreweryDataRepository breweryDataRepository) {
        return breweryDataRepository;
    }

    @Provides @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }
}
