package com.pgmail.martsulg.testapp.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pgmail.martsulg.testapp.CommentsViewModel;
import com.pgmail.martsulg.testapp.MyFragment;
import com.pgmail.martsulg.testapp.MyViewModel;
import com.pgmail.martsulg.testapp.R;
import com.pgmail.martsulg.testapp.databinding.FragmentCommentsBinding;

/**
 * Created by g_washingt0n on 07.02.2018.
 */

public class CommentsFragment extends MyFragment {

    public static CommentsFragment newInstance(FragmentManager fragmentManager) {

        CommentsFragment commentsFragment = new CommentsFragment();

        return commentsFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        CommentsViewModel viewModel = new CommentsViewModel(getActivity());
        this.viewModel = viewModel;
        FragmentCommentsBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_comments, container, false);
        binding.setViewModel(viewModel);
        binding.commentsRecyclerView.setAdapter(viewModel.adapter);
        binding.commentsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return binding.getRoot();
    }
}
