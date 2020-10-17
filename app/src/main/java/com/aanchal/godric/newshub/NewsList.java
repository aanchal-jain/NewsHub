package com.aanchal.godric.newshub;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NewsList {

    @SerializedName("status")
    String status;

    @SerializedName("totalresults")
    int totalResults;

    @SerializedName("articles")
    ArrayList<News> articles;

    public String getStatus() {
        return status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public ArrayList<News> getArticles() {
        return articles;
    }
}
