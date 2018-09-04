package com.example.erzhena.newsapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.erzhena.newsapp.App;
import com.example.erzhena.newsapp.Constants;
import com.example.erzhena.newsapp.R;
import com.example.erzhena.newsapp.adapters.ArticleArticleAdapter;
import com.example.erzhena.newsapp.contracts.ArticleContract;
import com.example.erzhena.newsapp.models.Article;
import com.example.erzhena.newsapp.models.ArticleArticle;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ArticleActivity
        extends AppCompatActivity
        implements ArticleContract.View {

    @Inject
    ArticleContract.Presenter articlePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        App.getAppComponent().injects(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        Intent intent = getIntent();
        String article = intent.getStringExtra(Constants.article);



        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        articlePresenter.attachView(this);
        articlePresenter.loadArticle(article);
    }

    @Override
    public void showContent(Article article) {
        TextView team1 = (TextView) findViewById(R.id.team1);
        team1.setText(article.getTeam1());

        TextView team2 = (TextView) findViewById(R.id.team2);
        team2.setText(article.getTeam2());

        TextView time = (TextView) findViewById(R.id.time);
        time.setText(article.getTime());

        TextView tournament = (TextView) findViewById(R.id.tournament);
        tournament.setText(article.getTournament());

        TextView prediction = (TextView) findViewById(R.id.prediction);
        prediction.setText(article.getPrediction());

        ArticleArticleAdapter adapter = new ArticleArticleAdapter(this, article.getArticle());
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }

    @Override
    public void showError() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}