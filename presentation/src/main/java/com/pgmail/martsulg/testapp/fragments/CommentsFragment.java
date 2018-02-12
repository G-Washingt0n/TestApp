package com.pgmail.martsulg.testapp.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pgmail.martsulg.testapp.CommentsViewModel;
import com.pgmail.martsulg.testapp.MyFragment;
import com.pgmail.martsulg.testapp.R;
import com.pgmail.martsulg.testapp.databinding.FragmentCommentsBinding;

/**
 * Created by g_washingt0n on 07.02.2018.
 */

public class CommentsFragment extends MyFragment {

    private static final int PAGE_SIZE = 5;

    public static CommentsFragment newInstance(FragmentManager fragmentManager) {
        Fragment fragment = fragmentManager
                .findFragmentByTag(CommentsFragment.class.getName());
        CommentsFragment commentsFragment;
        if (fragment != null && fragment instanceof CommentsFragment) {
            commentsFragment = (CommentsFragment) fragment;
        } else {
            commentsFragment = new CommentsFragment();

        }
        return commentsFragment;
    }

    private LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        CommentsViewModel viewModel = new CommentsViewModel(getActivity().getSupportFragmentManager());
        this.viewModel = viewModel;
        FragmentCommentsBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_comments, container, false);
        binding.setViewModel(viewModel);
        binding.commentsRecyclerView.setAdapter(viewModel.adapter);
        binding.commentsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.commentsRecyclerView.addOnScrollListener(recyclerViewOnScrollListener);

        return binding.getRoot();
    }

    private RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int visibleItemCount = layoutManager.getChildCount();
            int totalItemCount = layoutManager.getItemCount();
            int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();


            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                    && firstVisibleItemPosition >= 0
                    && totalItemCount >= PAGE_SIZE) {

                //запрос к серверу для загрузки последующих страниц

            }
        }
    };
}
