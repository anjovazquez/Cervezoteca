package com.cervezoteca.anjov.presentation.di.component;

import com.cervezoteca.anjov.presentation.HallActivity;
import com.cervezoteca.anjov.presentation.TapBeerActivity;
import com.cervezoteca.anjov.presentation.di.PerActivity;
import com.cervezoteca.anjov.presentation.di.module.ActivityModule;
import com.cervezoteca.anjov.presentation.di.module.BreweriesModule;
import com.cervezoteca.anjov.presentation.di.module.TapBeersModule;

import dagger.Component;

/**
 * Created by anjov on 07/10/2016.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, TapBeersModule.class})
public interface TapBeersComponent {
    void inject(TapBeerActivity tapBeerActivity);
}
