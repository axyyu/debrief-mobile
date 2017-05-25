package com.kaiamelung.debrief;

import android.content.Context;
import android.graphics.Paint;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class DayAdapter extends
        RecyclerView.Adapter<DayAdapter.ViewHolder> {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView mDate;
        public RecyclerView mArticleView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(final View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            mDate = (TextView) itemView.findViewById(R.id.date);
            mTaskDesc = (TextView) itemView.findViewById(R.id.task_desc);
            mChecked = (ToggleButton) itemView.findViewById(R.id.checked);
            mHandler = new Handler();
            mChecked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        mTask.setPaintFlags(mTask.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                        mTaskDesc.setPaintFlags(mTask.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                        mRunn = new Runnable() {
                            @Override
                            public void run() {
                                itemView.setVisibility(View.INVISIBLE);
                                removeAt(getAdapterPosition());
                            }
                        };
                        mHandler.postDelayed(mRunn, 2000);
                    } else {
                        mTask.setPaintFlags(mTask.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                        mTaskDesc.setPaintFlags(mTaskDesc.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                        mHandler.removeCallbacks(mRunn);
                        itemView.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
    }

    // Store a member variable for the contacts
    private List<Contact> mContacts;
    private List<Contact> mDone;
    private RecyclerView.Adapter mOther;
    // Store the context for easy access
    private Context mContext;

    public void swap(ArrayList<Contact> datas){
        mContacts.clear();
        mContacts.addAll(datas);
        notifyDataSetChanged();
    }

    public void removeAt(int position) {
        mDone.add(mContacts.remove(position));
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mContacts.size());
        mOther.notifyItemInserted( mDone.size() - 1);
        mOther.notifyDataSetChanged();
        System.out.println("Worked");
        System.out.println("Done");
    }

    // Pass in the contact array into the constructor
    public ContactsAdapter(Context context, List<Contact> contacts, List<Contact> done) {
        mContacts = contacts;
        mContext = context;
        mDone = done;
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public DayAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.day_layout, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(DayAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Contact contact = mContacts.get(position);

        // Set item views based on your views and data model
        TextView textView = viewHolder.mTask;
        TextView textView2 = viewHolder.mTaskDesc;
        textView.setText(contact.getName());
        textView2.setText(contact.getDesc());
    }

    public void setOtherAdapter(RecyclerView.Adapter adapter){
        mOther = adapter;
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mContacts.size();
    }
}