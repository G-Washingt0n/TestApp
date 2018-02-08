package com.pgmail.martsulg.testapp;

import android.app.Activity;
import android.database.Observable;
import android.databinding.ObservableField;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by g_washingt0n on 07.02.2018.
 */

public class CommentsViewModel implements MyViewModel {

    private Activity activity;
    public CommentsAdapter adapter;
    public ObservableField<String> comment2send = new ObservableField<>();


    public CommentsViewModel(Activity activity) {
        this.activity = activity;
        adapter = new CommentsAdapter(activity);
    }

    @Override
    public void init() {

    }


    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void release() {

    }
}
