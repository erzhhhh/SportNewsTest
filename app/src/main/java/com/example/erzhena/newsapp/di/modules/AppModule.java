package com.example.erzhena.newsapp.di.modules;


import retrofit2.Retrofit;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = {NewsModule.class})
public class AppModule {

    private Application application;
    private String baseUrl;

    public AppModule(Application application, String url) {
        this.application = application;
        this.baseUrl = url;
    }

    @Provides
    @Singleton
    GsonConverterFactory provideGsonConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    RxJava2CallAdapterFactory provideRxJava2CallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(
            GsonConverterFactory provideGsonConverterFactory,
            RxJava2CallAdapterFactory provideRxJava2CallAdapterFactory,
            OkHttpClient provideOkHttpClient
    ) {
        return new Retrofit.Builder()
                .addConverterFactory(provideGsonConverterFactory)
                .addCallAdapterFactory(provideRxJava2CallAdapterFactory)
                .baseUrl(baseUrl)
                .client(provideOkHttpClient)
                .build();
    }

    @Provides
    @Singleton
    Context providesAppContext() {
        return application.getApplicationContext();
    }

}