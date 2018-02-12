package com.pgmail.martsulg.testapp;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import io.reactivex.observers.DisposableObserver;
import p.martsulg.data.models.AnswersFeed;
import p.martsulg.data.models.AnswersParams;
import p.martsulg.data.models.CommentFeed;
import p.martsulg.domain.DelAnswerUseCase;

/**
 * Created by g_washingt0n on 08.02.2018.
 */

public class AnswersAdapter extends RecyclerView.Adapter<AnswersAdapter.Holder> {

    private ArrayList<AnswersFeed> answers;
    private CommentFeed comment;
    private int itemCount = 0;
    private Activity activity;

    public AnswersAdapter(Activity activity, CommentFeed comment) {
        this.activity = activity;
        this.comment = comment;
    }

    public static class Holder extends RecyclerView.ViewHolder {

        TextView ansAuthor;
        TextView ansCreated;
        TextView ansText;
        ImageView ansPicture;
        ImageView ansDel;


        public Holder(View itemView) {
            super(itemView);

            ansAuthor = itemView.findViewById(R.id.comment_auth);
            ansCreated = itemView.findViewById(R.id.comment_created);
            ansText = itemView.findViewById(R.id.comment_text);
            ansPicture = itemView.findViewById(R.id.comment_img);
            ansDel = itemView.findViewById(R.id.comment_delete);
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
    public void onBindViewHolder(AnswersAdapter.Holder holder, final int position) {
        holder.ansAuthor.setText(answers.get(position).getUser().getName());
        holder.ansCreated.setText(answers.get(position).getCreated_at());
        Picasso.with(holder.ansPicture.getContext())
                .load(answers.get(position).getUser().getAvatarUrl())
                .into(holder.ansPicture, new Callback() {
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError() {
                    }
                });
        holder.ansText.setText(answers.get(position).getMessage());

        holder.ansDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DelAnswerUseCase delUseCase = new DelAnswerUseCase();
                AnswersParams params = new AnswersParams();
                params.setAnswerId(answers.get(position).getAnswer_id());
                params.setCommentId(comment.getComment_id());
                params.setToken(EntryActivity.preferences.getString("Token", null));
                delUseCase.execute(params, new DisposableObserver<Object>() {
                    @Override
                    public void onNext(Object o) {
                        answers.remove(position);
                        dataChanged(answers, comment);
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

    public void dataChanged(ArrayList<AnswersFeed> answers, CommentFeed comment) {
        this.answers = answers;
        notifyDataSetChanged();
        itemCount = answers.size();
        new AnswersAdapter(activity, comment);
    }


}
