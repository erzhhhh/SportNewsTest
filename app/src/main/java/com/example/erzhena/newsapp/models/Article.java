package com.example.erzhena.newsapp.models;

import java.util.ArrayList;

public class Article {
    private String team1;
    private String team2;
    private String prediction;
    private String time;
    private String tournament;
    private ArrayList<ArticleArticle> article;

    public String getTeam1() {
        return this.team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return this.team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getPrediction() {
        return this.prediction;
    }

    public void setPrediction(String prediction) {
        this.prediction = prediction;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTournament() {
        return this.tournament;
    }

    public void setTournament(String tournament) {
        this.tournament = tournament;
    }

    public ArrayList<ArticleArticle> getArticle() {
        return this.article;
    }

    public void setArticle(ArrayList<ArticleArticle> article) {
        this.article = article;
    }
}
