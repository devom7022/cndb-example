package com.devom.cndb_example.di.module;

import android.util.Log;

import com.devom.cndb_example.api.ApiClient;
import com.devom.cndb_example.app.BaseApplication;
import com.devom.cndb_example.di.scope.ApplicationScope;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.devom.cndb_example.api.ApiConstants.BASE_URL;
import static com.devom.cndb_example.api.ApiConstants.CACHE_SIZE;
import static com.devom.cndb_example.api.ApiConstants.HEADER_CACHE_CONTROL;
import static com.devom.cndb_example.api.ApiConstants.HEADER_PRAGMA;


@Module
public class RetrofitModule {
    private static String TAG = RetrofitModule.class.getSimpleName();

    @Provides
    @ApplicationScope
    GsonConverterFactory providesGsonConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Provides
    @ApplicationScope
    Cache cache() {
        return new Cache(new File(BaseApplication.getInstance().getCacheDir(), "someIdentifier"), CACHE_SIZE);
    }

    @Provides
    @ApplicationScope
    HttpLoggingInterceptor provideHttpLoginInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Named("online")
    @Provides
    @ApplicationScope
    Interceptor networkInterceptor() {
        return chain -> {
            Response response = chain.proceed(chain.request());

            CacheControl cacheControl = new CacheControl.Builder()
                    .maxAge(5, TimeUnit.SECONDS)
                    .build();

            return response.newBuilder()
                    .removeHeader(HEADER_PRAGMA)
                    .removeHeader(HEADER_CACHE_CONTROL)
                    .header(HEADER_CACHE_CONTROL, cacheControl.toString())
                    .build();
        };
    }

    @Named("offline")
    @Provides
    @ApplicationScope
    Interceptor offlineInterceptor() {
        return chain -> {
            Log.d(TAG, "offline interceptor: called.");
            Request request = chain.request();

            if (!BaseApplication.getConnectionToNetwork()) {
                CacheControl cacheControl = new CacheControl.Builder()
                        .maxStale(7, TimeUnit.DAYS)
                        .build();

                request = request.newBuilder()
                        .removeHeader(HEADER_PRAGMA)
                        .removeHeader(HEADER_CACHE_CONTROL)
                        .cacheControl(cacheControl)
                        .build();
            }

            return chain.proceed(request);
        };
    }

    @Provides
    @ApplicationScope
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor loggingInterceptor, @Named("online") Interceptor onlineInterceptor, @Named("offline") Interceptor offlineInterceptor) {
        return new OkHttpClient.Builder()
                .cache(cache())
                .addInterceptor(loggingInterceptor)
                .addNetworkInterceptor(onlineInterceptor)
                .addInterceptor(offlineInterceptor)
                .build();
    }

    @Provides
    @ApplicationScope
    Retrofit provideRetrofit(OkHttpClient client, GsonConverterFactory converterFactory) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(converterFactory)
                .client(client)
                .build();
    }

    @Provides
    @ApplicationScope
    ApiClient provideApiCliente(Retrofit retrofit) {
        return retrofit.create(ApiClient.class);
    }
}
