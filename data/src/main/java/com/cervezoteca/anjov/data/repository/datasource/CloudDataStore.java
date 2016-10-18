package com.cervezoteca.anjov.data.repository.datasource;

import com.cervezoteca.anjov.domain.model.BottleBeer;
import com.cervezoteca.anjov.domain.model.Brewery;
import com.cervezoteca.anjov.domain.model.TapBeer;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by angel.vazquez on 22/07/2016.
 */
public class CloudDataStore implements BreweryDataStore {

    private Retrofit retrofit;
    private BreweryApiService breweryApiService;

    public CloudDataStore() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        retrofit = new Retrofit.Builder().
                baseUrl("http://dev.cervezotecamalte.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        breweryApiService = retrofit.create(BreweryApiService.class);
    }

    public interface BreweryApiService {

        @GET("/api/breweries/")
        Call<List<Brewery>> getBreweries();

        @GET("/api/tap/")
        Call<List<TapBeer>> getTapBeer();

        @GET("/api/beers/?page_size=100")
        Call<BottleBeerResponse> getBottleBeer();

    }

    public Observable<List<Brewery>> getBreweries() {

        return Observable.create(new Observable.OnSubscribe<List<Brewery>>() {
            @Override
            public void call(Subscriber<? super List<Brewery>> subscriber) {
                try {
                    Call<List<Brewery>> getBreweriesUserCall = breweryApiService.getBreweries();
                    Response<List<Brewery>> getBreweriesResponse = getBreweriesUserCall.execute();
                    if(getBreweriesResponse.code()==200) {
                        subscriber.onNext(getBreweriesResponse.body());
                        subscriber.onCompleted();
                    }
                    else
                        subscriber.onError(new Exception(getBreweriesResponse.errorBody().string()));
                } catch (Exception e) {
                    e.printStackTrace();
                    subscriber.onError(new Exception(e.getMessage()));
                }
            }
        });
    }

    public Observable<List<TapBeer>> getTapBeers() {

        return Observable.create(new Observable.OnSubscribe<List<TapBeer>>() {
            @Override
            public void call(Subscriber<? super List<TapBeer>> subscriber) {
                try {
                    Call<List<TapBeer>> getTapBeers = breweryApiService.getTapBeer();
                    Response<List<TapBeer>> getTapBeersResponse = getTapBeers.execute();
                    if(getTapBeersResponse.code()==200) {
                        subscriber.onNext(getTapBeersResponse.body());
                        subscriber.onCompleted();
                    }
                    else
                        subscriber.onError(new Exception(getTapBeersResponse.errorBody().string()));
                } catch (Exception e) {
                    e.printStackTrace();
                    subscriber.onError(new Exception(e.getMessage()));
                }
            }
        });
    }

    public Observable<List<BottleBeer>> getBottleBeers() {

        return Observable.create(new Observable.OnSubscribe<List<BottleBeer>>() {
            @Override
            public void call(Subscriber<? super List<BottleBeer>> subscriber) {
                try {
                    Call<BottleBeerResponse> getBottleBeers = breweryApiService.getBottleBeer();
                    Response<BottleBeerResponse> getBottleBeersResponse = getBottleBeers.execute();
                    if(getBottleBeersResponse.code()==200) {
                        BottleBeerResponse response = getBottleBeersResponse.body();
                        subscriber.onNext(response.getResults());
                        subscriber.onCompleted();
                    }
                    else
                        subscriber.onError(new Exception(getBottleBeersResponse.errorBody().string()));
                } catch (Exception e) {
                    e.printStackTrace();
                    subscriber.onError(new Exception(e.getMessage()));
                }
            }
        });
    }
}
