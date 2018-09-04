package com.example.erzhena.newsapp.presenters;

import com.example.erzhena.newsapp.App;
import com.example.erzhena.newsapp.api.NewsService;
import com.example.erzhena.newsapp.base.BasePresenter;
import com.example.erzhena.newsapp.contracts.ArticleContract;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ArticlePresenter
        extends BasePresenter<ArticleContract.View>
        implements ArticleContract.Presenter {

    @Inject
    NewsService newsService;

    @Override
    public void loadArticle(String category) {
        App.getAppComponent().injects(this);
        newsService.getArticle(category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(article -> {
                    if (article != null) {
                        getView().showContent(article);
                    } else {
                        getView().showError();
                    }
                });
    }

    public ArticlePresenter(NewsService newsService) {
        this.newsService = newsService;
    }
}
