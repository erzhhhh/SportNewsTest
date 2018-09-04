package com.example.erzhena.newsapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.erzhena.newsapp.R;
import com.example.erzhena.newsapp.models.CategoryEvents;

import java.util.List;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(CategoryEvents item);
    }

    private final List<CategoryEvents> items;
    private final OnItemClickListener listener;

    public CategoryAdapter(List<CategoryEvents> items, OnItemClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(items.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView newsPreview;
        private TextView newsTitle;
        private TextView newsСoefficient;
        private TextView newsTime;
        private TextView newsPlace;

        public ViewHolder(View itemView) {
            super(itemView);
            newsPreview = itemView.findViewById(R.id.newsPreview);
            newsTitle = itemView.findViewById(R.id.newsTitle);
            newsСoefficient = itemView.findViewById(R.id.newsСoefficient);
            newsTime = itemView.findViewById(R.id.newsTime);
            newsPlace = itemView.findViewById(R.id.newsPlace);

        }

        public void bind(final CategoryEvents item, final OnItemClickListener listener) {
            newsPreview.setText(item.getPreview());
            newsTitle.setText(item.getTitle());
            newsСoefficient.setText(item.getCoefficient());
            newsTime.setText(item.getTime());
            newsPlace.setText(item.getPlace());
            itemView.setOnClickListener(v -> listener.onItemClick(item));
        }
    }
}