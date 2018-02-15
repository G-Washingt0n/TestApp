package com.pgmail.martsulg.testapp;

import android.databinding.ObservableField;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import java.util.ArrayList;

import io.reactivex.observers.DisposableObserver;
import p.martsulg.data.models.CommentFeed;
import p.martsulg.data.models.CommentParams;
import p.martsulg.data.models.ListComments;
import p.martsulg.domain.AddCommentUseCase;
import p.martsulg.domain.DelCommentUseCase;
import p.martsulg.domain.GetCommentsListUseCase;

/**
 * Created by g_washingt0n on 07.02.2018.
 */

public class CommentsViewModel implements MyViewModel {

    private String token = EntryActivity.preferences.getString("Token", null);
    public CommentsAdapter adapter;
    public ObservableField<String> title2send = new ObservableField<>();
    public ObservableField<String> comment2send = new ObservableField<>();
    private GetCommentsListUseCase listUseCase = new GetCommentsListUseCase();
    private AddCommentUseCase addUseCase = new AddCommentUseCase();
    private DelCommentUseCase delUseCase = new DelCommentUseCase();
    private int currPage = 1;
    private ArrayList<CommentFeed> adapterList;

    public CommentsViewModel(FragmentManager manager) {
        adapter = new CommentsAdapter(manager);
    }

    @Override
    public void init() {
    }


    @Override
    public void resume() {
        getRequest(currPage);

    }

    @Override
    public void pause() {

    }


    @Override
    public void release() {
    }

    public void onSendClick() {
        if (!comment2send.equals(null) && !title2send.equals(null))
            addRequest();
    }

    @Override
    public void getRequest(int requiredPage) {
        CommentParams params = new CommentParams();
        params.setToken(EntryActivity.preferences.getString("Token", null));
        listUseCase.execute(params, new DisposableObserver<ListComments>() {
            @Override
            public void onNext(ListComments listComments) {
                adapterList = listComments.getData();
                adapter.dataChanged(adapterList);
                adapter.notifyDataSetChanged();
                Log.e("adapter notify", String.valueOf(listComments.getData().size()));
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                listUseCase.dispose();
            }
        });
    }

    @Override
    public void delRequest(int cummentID) {
    }

    @Override
    public void addRequest() {
        CommentParams params = new CommentParams();
        params.setTitle(title2send.get());
        params.setMessage(comment2send.get());
        params.setToken(EntryActivity.preferences.getString("Token", null));
        addUseCase.execute(params, new DisposableObserver<Object>() {
            @Override
            public void onNext(Object o) {
                getRequest(currPage);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                comment2send.set(null);
                title2send.set(null);
                addUseCase.dispose();
            }
        });
    }

}
