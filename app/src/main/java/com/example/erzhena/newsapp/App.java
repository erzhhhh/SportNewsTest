package com.example.erzhena.newsapp;

import android.app.Application;

import com.example.erzhena.newsapp.di.components.AppComponent;
import com.example.erzhena.newsapp.di.components.DaggerAppComponent;
import com.example.erzhena.newsapp.di.modules.AppModule;

public class App extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this, "http://mikonatoruri.win/"))
                .build();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
