package com.soyummyrecips.anna.soyummyrecipes.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.soyummyrecips.anna.soyummyrecipes.R;
import com.soyummyrecips.anna.soyummyrecipes.pojo.UserList;

import java.util.ArrayList;
import java.util.List;

public class RecipeNewListAdapter extends RecyclerView.Adapter<RecipeNewListAdapter.ViewHolder> {

    private static List<UserList.Recipie> dataSet;

    public RecipeNewListAdapter(List<UserList.Recipie> os_versions) {

        dataSet = os_versions;
    }


    @Override
    public RecipeNewListAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
// create a new view
        View itemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.custom_recepyelist_main, null);

        // create ViewHolder

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecipeNewListAdapter.ViewHolder viewHolder, int i) {

        viewHolder.tvVersionName.setText(""+dataSet.get(i).first_name);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    // inner class to hold a reference to each item of RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvVersionName;
        public ImageView iconView;


        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);

            tvVersionName = (TextView) itemLayoutView
                    .findViewById(R.id.textViewMainName);
            iconView = (ImageView) itemLayoutView
                    .findViewById(R.id.imageView);

            itemLayoutView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });


        }

    }
}