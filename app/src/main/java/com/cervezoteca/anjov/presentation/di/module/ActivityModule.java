package com.cervezoteca.anjov.presentation.di.module;

import android.app.Activity;

import com.cervezoteca.anjov.presentation.di.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by angelvazquez on 26/7/16.
 */

@Module
public class ActivityModule {

    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    /**
     * Expose the activity to dependents in the graph.
     */
    @Provides
    @PerActivity
    Activity activity() {
        return this.activity;
    }
}
