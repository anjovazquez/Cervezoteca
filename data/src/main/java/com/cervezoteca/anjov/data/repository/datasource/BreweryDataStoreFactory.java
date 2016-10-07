package com.cervezoteca.anjov.data.repository.datasource;

import android.content.Context;

import javax.inject.Inject;

/**
 * Created by anjov on 07/10/2016.
 */

public class BreweryDataStoreFactory {

    private final Context context;

    @Inject
    public BreweryDataStoreFactory(Context context){
        this.context = context.getApplicationContext();

    }

    public CloudDataStore createCloudDataStore() {
        return new CloudDataStore();
    }
}
