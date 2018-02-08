package com.pgmail.martsulg.testapp;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by g_washingt0n on 08.02.2018.
 */

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.Holder> {


    public CommentsAdapter(Activity activity) {


    }

    public static class Holder extends RecyclerView.ViewHolder {



        public Holder(View itemView) {
            super(itemView);


        }
    }
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(CommentsAdapter.Holder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

}
