package p.martsulg.data.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by g_washingt0n on 11.02.2018.
 */

public class AnswersParams {
    private String message;
    @SerializedName("api_token")
    private String token;
    private String next_page_url;
    private int commentId;
    private int answerId;

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getNext_page_url() {
        return next_page_url;
    }

    public void setNext_page_url(String next_page_url) {
        this.next_page_url = next_page_url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
