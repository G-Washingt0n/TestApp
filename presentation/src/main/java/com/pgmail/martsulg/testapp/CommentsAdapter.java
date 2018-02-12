package com.pgmail.martsulg.testapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pgmail.martsulg.testapp.fragments.AnswersFragment;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import io.reactivex.observers.DisposableObserver;
import p.martsulg.data.models.CommentFeed;
import p.martsulg.data.models.CommentParams;
import p.martsulg.domain.DelCommentUseCase;

/**
 * Created by g_washingt0n on 08.02.2018.
 */

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.Holder> {
    private FragmentManager manager;
    private ArrayList<CommentFeed> comments;
    private int itemCount = 0;

    public CommentsAdapter(FragmentManager manager) {
        this.manager = manager;
    }

    public static class Holder extends RecyclerView.ViewHolder {

        TextView commAuthor;
        TextView commTitle;
        TextView commCreated;
        TextView commText;
        TextView showAnswers;
        ImageView commPicture;
        ImageView commDel;


        public Holder(View itemView) {
            super(itemView);

            commAuthor = itemView.findViewById(R.id.comment_auth);
            commTitle = itemView.findViewById(R.id.comment_title);
            commCreated = itemView.findViewById(R.id.comment_created);
            commText = itemView.findViewById(R.id.comment_text);
            showAnswers = itemView.findViewById(R.id.comment_showAnswers);
            commPicture = itemView.findViewById(R.id.comment_img);
            commDel = itemView.findViewById(R.id.comment_delete);
        }
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_comment, parent, false);
        Log.e("AAAAA", "onCreateViewHolder");
        return new Holder(root);
    }

    @Override
    public void onBindViewHolder(CommentsAdapter.Holder holder, final int position) {
        holder.commTitle.setText(comments.get(position).getTitle());
        holder.commAuthor.setText(comments.get(position).getUser().getName());
        holder.commCreated.setText(comments.get(position).getCreated_at());
        //holder.ansPicture
        Picasso.with(holder.commPicture.getContext())
                .load(comments.get(position).getUser().getAvatarUrl())
                .into(holder.commPicture, new Callback() {
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError() {
                    }
                });
        holder.commText.setText(comments.get(position).getMessage());
        holder.showAnswers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showFragment(manager, new AnswersFragment().newInstance(manager, comments.get(position)));

            }
        });
        holder.commDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DelCommentUseCase delUseCase = new DelCommentUseCase();
                CommentParams params = new CommentParams();
                params.setCommentId(comments.get(position).getComment_id());
                params.setToken(EntryActivity.preferences.getString("Token", null));
                delUseCase.execute(params, new DisposableObserver<Object>() {
                    @Override
                    public void onNext(Object o) {
                        comments.remove(position);
                        dataChanged(comments);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Error removing comment", e.toString());
                    }

                    @Override
                    public void onComplete() {
                        delUseCase.dispose();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemCount;
    }

    public void dataChanged(ArrayList<CommentFeed> comments) {
        this.comments = comments;
        notifyDataSetChanged();
        itemCount = comments.size();
        new CommentsAdapter(manager);
    }

    public static void showFragment(FragmentManager fragmentManager, Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav_screen_fragment, fragment, fragment.getClass().getName());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
