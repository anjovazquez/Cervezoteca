package com.cervezoteca.anjov.data.repository.datasource;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.cervezoteca.anjov.domain.model.BottleBeer;
import com.cervezoteca.anjov.domain.model.Brewery;
import com.cervezoteca.anjov.domain.model.TapBeer;

import java.io.IOException;
import java.util.List;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
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
    private Context context;

    public CloudDataStore(final Context context) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .cache(new Cache(context.getCacheDir(), 10 * 1024 * 1024));
        httpClient.addInterceptor(logging);
        httpClient.addInterceptor(new Interceptor() {
            @Override public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (CloudDataStore.isNetworkAvailable(context)) {
                    request = request.newBuilder().header("Cache-Control", "public, max-age=" + 60).build();
                } else {
                    request = request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build();
                }
                return chain.proceed(request);
            }
        });

        retrofit = new Retrofit.Builder().
                baseUrl("https://cervezoteca-b09b0.firebaseio.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        breweryApiService = retrofit.create(BreweryApiService.class);
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

    public interface BreweryApiService {

        @GET("/api/breweries/")
        Call<List<Brewery>> getBreweries();

        @GET("/beers.json")
        Call<List<TapBeer>> getTapBeer();

        @GET("/beers.json")
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
