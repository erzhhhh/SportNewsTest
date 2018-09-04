package com.example.erzhena.newsapp.presenters;

import com.example.erzhena.newsapp.App;
import com.example.erzhena.newsapp.api.NewsService;
import com.example.erzhena.newsapp.base.BasePresenter;
import com.example.erzhena.newsapp.contracts.CategoryContract;
import com.example.erzhena.newsapp.models.Category;
import com.example.erzhena.newsapp.models.CategoryEvents;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CategoryPresenter
        extends BasePresenter<CategoryContract.View>
        implements CategoryContract.Presenter {

    @Inject
    NewsService newsService;

    @Override
    public void loadCategories(String category) {
        App.getAppComponent().injects(this);
        newsService.getCategory(category)
                .map(Category::getEvents)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(categoryResults -> {
                    if (categoryResults != null) {
                        List<CategoryEvents> targetList = Arrays.asList(categoryResults);
                        getView().showContent(targetList);
                    } else {
                        getView().showError();
                    }
                });
    }

    public CategoryPresenter(NewsService newsService) {
        this.newsService = newsService;
    }

}
