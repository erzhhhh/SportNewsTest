package com.example.erzhena.newsapp.contracts;


import com.example.erzhena.newsapp.models.Article;
import com.example.erzhena.newsapp.mvp.MvpPresenter;
import com.example.erzhena.newsapp.mvp.MvpView;


public interface ArticleContract {

    interface View extends MvpView {

        void showContent(Article article);

        void showError();
    }

    interface Presenter extends MvpPresenter<ArticleContract.View> {

        void loadArticle(String article);

    }
}
