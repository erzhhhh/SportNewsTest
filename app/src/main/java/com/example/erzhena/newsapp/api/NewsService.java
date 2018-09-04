package com.example.erzhena.newsapp.api;

import com.example.erzhena.newsapp.models.Category;
import com.example.erzhena.newsapp.models.Article;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsService {
    @GET("list.php")
    Observable<Category> getCategory(@Query("category") String category);

    @GET("post.php")
    Observable<Article> getArticle(@Query("article") String article);
}
