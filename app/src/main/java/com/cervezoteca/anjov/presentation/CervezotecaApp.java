package com.cervezoteca.anjov.presentation;

import android.app.Application;

import com.cervezoteca.anjov.presentation.di.component.ApplicationComponent;
import com.cervezoteca.anjov.presentation.di.component.DaggerApplicationComponent;
import com.cervezoteca.anjov.presentation.di.module.ApplicationModule;

/**
 * Created by anjov on 07/10/2016.
 */

public class CervezotecaApp extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }


}
