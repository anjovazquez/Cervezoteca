package com.cervezoteca.anjov.presentation.di.component;

import android.content.Context;

import com.cervezoteca.anjov.domain.executor.PostExecutionThread;
import com.cervezoteca.anjov.domain.executor.ThreadExecutor;
import com.cervezoteca.anjov.domain.repository.BreweryRepository;
import com.cervezoteca.anjov.presentation.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by anjov on 07/10/2016.
 */

@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    Context context();
    ThreadExecutor threadExecutor();
    PostExecutionThread postExecutionThread();
    BreweryRepository breweryRepository();

}
