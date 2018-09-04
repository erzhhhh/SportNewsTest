package com.example.erzhena.newsapp.contracts;

import com.example.erzhena.newsapp.models.CategoryEvents;
import com.example.erzhena.newsapp.mvp.MvpPresenter;
import com.example.erzhena.newsapp.mvp.MvpView;

import java.util.List;

public interface CategoryContract {

    interface View extends MvpView {

        void showContent(List<CategoryEvents> categories);

        void showError();
    }

    interface Presenter extends MvpPresenter<View> {

        void loadCategories(String category);

    }
}