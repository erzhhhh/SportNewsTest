package com.example.erzhena.newsapp.di.modules;

import com.example.erzhena.newsapp.api.NewsService;
import com.example.erzhena.newsapp.contracts.ArticleContract;
import com.example.erzhena.newsapp.contracts.CategoryContract;
import com.example.erzhena.newsapp.presenters.ArticlePresenter;
import com.example.erzhena.newsapp.presenters.CategoryPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class NewsModule {

    @Provides
    @Singleton
    public NewsService ProvideNewsService(Retrofit retrofit) {
        return retrofit.create(NewsService.class);
    }

    @Provides
    public CategoryContract.Presenter ProvideCategoryPresenter(NewsService newsService) {
        return new CategoryPresenter(newsService);
    }

    @Provides
    public ArticleContract.Presenter ProvideArticlePresenter(NewsService newsService) {
        return new ArticlePresenter(newsService);
    }
}