package com.pgmail.martsulg.testapp.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pgmail.martsulg.testapp.AnswersViewModel;
import com.pgmail.martsulg.testapp.MyFragment;
import com.pgmail.martsulg.testapp.R;
import com.pgmail.martsulg.testapp.databinding.FragmentAnswersBinding;

import p.martsulg.data.models.CommentFeed;
import p.martsulg.data.models.UserModel;

/**
 * Created by g_washingt0n on 07.02.2018.
 */

public class AnswersFragment extends MyFragment {

    public static AnswersFragment newInstance(FragmentManager fragmentManager, CommentFeed comment) {

        AnswersFragment answersFragment = new AnswersFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", comment.getTitle());
        bundle.putString("message", comment.getMessage());
        bundle.putString("created", comment.getCreated_at());
        bundle.putString("name", comment.getUser().getName());
        bundle.putInt("commentId", comment.getComment_id());
        answersFragment.setArguments(bundle);
        return answersFragment;
    }

    private CommentFeed comment = new CommentFeed();

    private LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        comment.setTitle(bundle.getString("title"));
        comment.setMessage(bundle.getString("message"));
        comment.setCreated_at(bundle.getString("created"));
        UserModel model = new UserModel();
        model.setName(bundle.getString("name"));
        comment.setUser(model);
        comment.setComment_id(bundle.getInt("commentId"));

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        AnswersViewModel viewModel = new AnswersViewModel(getActivity(), comment);
        this.viewModel = viewModel;
        FragmentAnswersBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_answers, container, false);
        binding.setViewModel(viewModel);
        binding.answersRecyclerView.setLayoutManager(layoutManager);
        binding.answersRecyclerView.setAdapter(viewModel.adapter);

        return binding.getRoot();
    }


}
