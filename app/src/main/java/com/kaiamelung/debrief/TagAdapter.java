package com.kaiamelung.debrief;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
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


public class TagAdapter extends
        RecyclerView.Adapter<TagAdapter.ViewHolder> {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView mTag;
        public LinearLayout mHeadlines;
        public Intent mIntent;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(final View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            mHeadlines = (LinearLayout) itemView.findViewById(R.id.headline_list);
            mTag = (TextView) itemView.findViewById(R.id.tag_value);
            mIntent = new Intent(mContext, TagActivity.class);

            itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    mContext.startActivity(mIntent);
                }
            });
        }
    }

    // Store a member variable for the contacts
    private List<Tag> mTags;
    private List<Article> articles;

    // Store the context for easy access
    private Context mContext;

    // Pass in the contact array into the constructor
    public TagAdapter(Context context, List<Tag> contacts) {
        mTags = contacts;
        mContext = context;
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public TagAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.tag_layout, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(TagAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Tag tag = mTags.get(position);

        // Set item views based on your views and data model
        TextView mTag = viewHolder.mTag;
        LinearLayout mHeadlines = viewHolder.mHeadlines;
        Intent mIntent = viewHolder.mIntent;

        mTag.setText(tag.getTag());
        viewHolder.itemView.setBackgroundColor(Color.parseColor(tag.getColor()));

        mHeadlines.removeAllViews();
        if(tag.getArticle() != null){
            if(tag.getArticle().size() > 3){
                for(int a=0; a<3; a++){
                    Article art = tag.getArticle().get(a);
                    TextView head = new TextView(viewHolder.itemView.getContext());
                    head.setText(art.getHeadline());
                    head.setTextColor(Color.parseColor("#FFFFFF"));
                    head.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                    head.setPadding(5,10,0,10);
                    mHeadlines.addView(head);
                }
            }
            else{
                for ( Article art: tag.getArticle()) {
                    TextView head = new TextView(viewHolder.itemView.getContext());
                    head.setText(art.getHeadline());
                    head.setTextColor(Color.parseColor("#FFFFFF"));
                    head.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                    head.setPadding(5,10,0,10);
                    mHeadlines.addView(head);
                }
            }
        }
        mIntent.putExtra("S", mTags.size());
        Bundle args = new Bundle();
        for(int a = 0; a<mTags.size(); a++){
            mIntent.putExtra("T"+a, mTags.get(a).getTag());
            args.putSerializable("A"+a,(Serializable) mTags.get(a).getArticle());
        }
        mIntent.putExtra("A",args);
        mIntent.putExtra("I",position);
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mTags.size();
    }
}