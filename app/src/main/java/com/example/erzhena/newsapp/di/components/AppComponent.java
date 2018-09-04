package com.example.erzhena.newsapp.di.components;

import android.content.Context;

import com.example.erzhena.newsapp.activities.ArticleActivity;
import com.example.erzhena.newsapp.activities.MainActivity;
import com.example.erzhena.newsapp.api.NewsService;
import com.example.erzhena.newsapp.di.modules.AppModule;
import com.example.erzhena.newsapp.di.modules.NewsModule;
import com.example.erzhena.newsapp.presenters.ArticlePresenter;
import com.example.erzhena.newsapp.presenters.CategoryPresenter;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;


@Singleton
@Component(modules = {
        AppModule.class,
        NewsModule.class
})
public interface AppComponent {

    Retrofit exposerRetrofit();
    Context exposeContext();

    void injects(MainActivity mainActivity);

    void injects(ArticleActivity detailsActivity);

    void injects(CategoryPresenter categoryPresenter);

    void injects(ArticlePresenter articlePresenter);

    void injects(NewsService newsService);
}
