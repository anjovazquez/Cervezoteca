package com.cervezoteca.anjov.presentation.di.component;

import android.app.Activity;

import com.cervezoteca.anjov.presentation.di.PerActivity;
import com.cervezoteca.anjov.presentation.di.module.ActivityModule;

import dagger.Component;

/**
 * Created by angelvazquez on 26/7/16.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    //Exposed to sub-graphs.
    Activity activity();
}
