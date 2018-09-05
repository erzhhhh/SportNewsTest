package com.example.erzhena.newsapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.erzhena.newsapp.R;
import com.example.erzhena.newsapp.models.ArticleArticle;

import java.util.ArrayList;

public class ArticleArticleAdapter extends ArrayAdapter<ArticleArticle> {
    public ArticleArticleAdapter(Context context, ArrayList<ArticleArticle> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ArticleArticle user = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.article_item, parent, false);
        }

        TextView articleHeader = convertView.findViewById(R.id.articleHeader);
        TextView articleText = convertView.findViewById(R.id.articleText);

        articleHeader.setText(user.getHeader());
        articleText.setText(user.getText());
        return convertView;
    }
}
