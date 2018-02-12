package p.martsulg.data.models;

import java.util.ArrayList;

/**
 * Created by g_washingt0n on 11.02.2018.
 */

public class ListAnswers {
    private int current_page;
    private String from;
    private int last_page;
    private String next_page_url;
    private String path;
    private int per_page;
    private String prev_page_url;
    private String to;
    private int total;

    private ArrayList<AnswersFeed> data;

    public ArrayList<AnswersFeed> getData() {
        return data;
    }

    public void setData(ArrayList<AnswersFeed> data) {
        this.data = data;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public int getLast_page() {
        return last_page;
    }

    public void setLast_page(int last_page) {
        this.last_page = last_page;
    }

    public String getNext_page_url() {
        return next_page_url;
    }

    public void setNext_page_url(String next_page_url) {
        this.next_page_url = next_page_url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public String getPrev_page_url() {
        return prev_page_url;
    }

    public void setPrev_page_url(String prev_page_url) {
        this.prev_page_url = prev_page_url;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
