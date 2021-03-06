package com.nextslide.nextslide;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by yangren on 5/14/17.
 */

public class PresentationList extends RecyclerView.Adapter<PresentationList.ViewHolder>
{
    private PresentationListManager mManager;
    private ArrayList<Presentation> mPresentations;
    private String TAG = "PresentationList";

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public ViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public PresentationList(PresentationListManager manager, ArrayList<Presentation> myPresentations) {
        mManager = manager;
        mPresentations = myPresentations;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public PresentationList.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        // create a new view
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_text_view, parent, false);
        // set the view's size, margins, paddings and layout parameters
        // ...
        v.setLongClickable(true);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final Presentation p = mPresentations.get(position);
        final int position_ = position;
        holder.mTextView.setText(p.getName() + "\n" + p.getDescription());
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mManager.startPresentation(p);
            }
        });
        holder.mTextView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.d(TAG, "onLongClick");

                // Delete from PresentationList.
                mManager.deletePresentation(position_);
                return true;
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mPresentations.size();
    }

    static interface PresentationListManager {
        void startPresentation(Presentation p);
        void deletePresentation(int position);
    }
}