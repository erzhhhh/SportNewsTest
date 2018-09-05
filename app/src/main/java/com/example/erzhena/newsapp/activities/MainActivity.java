package com.example.erzhena.newsapp.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.erzhena.newsapp.App;
import com.example.erzhena.newsapp.Constants;
import com.example.erzhena.newsapp.R;
import com.example.erzhena.newsapp.adapters.CategoryAdapter;
import com.example.erzhena.newsapp.contracts.CategoryContract;
import com.example.erzhena.newsapp.models.CategoryEvents;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, CategoryContract.View {

    private RecyclerView recyclerView;
    View emptyView;
    View loadingView;
    View noInternetView;
    private String chosenCategory = "football";

    @Inject
    CategoryContract.Presenter categoryPresenter;
    CategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        App.getAppComponent().injects(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        emptyView = findViewById(R.id.emptyView);
        emptyView.setVisibility(View.GONE);

        noInternetView = findViewById(R.id.noInternetTextView);
        noInternetView.setVisibility(View.GONE);

        loadingView = findViewById(R.id.loadingInProgress);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            categoryPresenter.attachView(this);
            categoryPresenter.loadCategories(chosenCategory);
        } else {
            noInternetView.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void showContent(List<CategoryEvents> categories) {
        recyclerView.setVisibility(View.VISIBLE);
        loadingView.setVisibility(View.GONE);
        Intent intent = new Intent(MainActivity.this, ArticleActivity.class);
        recyclerView.setAdapter(new CategoryAdapter(categories, item ->
                startActivity(intent.putExtra(Constants.article, item.getArticle()))));
    }

    @Override
    public void showError() {
        if (categoryAdapter.getItemCount() == 0) {
            recyclerView.setVisibility(View.GONE);
            loadingView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.football) {
            setTitle(R.string.nav_football);
            chosenCategory = "football";
        } else if (id == R.id.hockey) {
            setTitle(R.string.nav_hockey);
            chosenCategory = "hockey";
        } else if (id == R.id.tennis) {
            setTitle(R.string.nav_tennis);
            chosenCategory = "tennis";
        } else if (id == R.id.basketball) {
            setTitle(R.string.nav_basketball);
            chosenCategory = "basketball";
        } else if (id == R.id.volleyball) {
            setTitle(R.string.nav_volleyball);
            chosenCategory = "volleyball";
        } else if (id == R.id.cybersport) {
            setTitle(R.string.nav_cybersport);
            chosenCategory = "cybersport";
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        categoryPresenter.loadCategories(chosenCategory);

        return true;
    }
}
