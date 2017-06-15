package com.kaiamelung.debrief;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class ArticleAdapter extends
        RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView mShort;
        public TextView mHeadline;
        public Intent mIntent;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(final View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            mHeadline = (TextView) itemView.findViewById(R.id.headline);
            mShort = (TextView) itemView.findViewById(R.id.shortsum);
            mIntent = new Intent(mContext, ArticleActivity.class);

            itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    mContext.startActivity(mIntent);
                }
            });
        }
    }

    // Store a member variable for the contacts
    private List<Article> mArticles;

    // Store the context for easy access
    private Context mContext;

    // Pass in the contact array into the constructor
    public ArticleAdapter(Context context, List<Article> contacts) {
        mArticles = contacts;
        mContext = context;
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public ArticleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.article_layout, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(ArticleAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Article article = mArticles.get(position);

        // Set item views based on your views and data model
        TextView headline = viewHolder.mHeadline;
        TextView shortsum = viewHolder.mShort;
        Intent mIntent = viewHolder.mIntent;

        headline.setText(article.getHeadline());
        shortsum.setText(article.getShort());
        viewHolder.itemView.setBackgroundColor(Color.parseColor(article.getColor()));

        mIntent.putExtra("S", mArticles.size());
        Bundle args = new Bundle();
        args.putSerializable("ARRAY",(Serializable) mArticles);
        mIntent.putExtra("A",args);
        mIntent.putExtra("I",position);
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mArticles.size();
    }
}