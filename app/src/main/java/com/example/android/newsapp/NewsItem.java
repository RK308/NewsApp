package com.example.android.newsapp;

import android.graphics.drawable.Drawable;

public class NewsItem {

    // News section name
    private String mSection;

    // Title of the article
    private String mTitle;

    // Author of the article
    private String mAuthor;

    // Date of the article published
    private String mDateAndTime;

    // URL of the article
    private String mUrl;

    private Drawable mThumbnail;

    /*
     ** Create a new NewsItem object
     * @param section
     * @param title
     * @param author
     * @param dateAndTime
     * @param url
     * @param thumbnail
     */
    public NewsItem(String section, String title, String author, String dateAndTime, String url, Drawable thumbnail){
        mSection = section;
        mTitle = title;
        mAuthor = author;
        mDateAndTime = dateAndTime;
        mUrl = url;
        mThumbnail = thumbnail;
    }

    // Creates the method getSection that returns the string mSection
    public String getSection(){
        return mSection;
    }

    // Return the Title
    public String getTitle(){
        return mTitle;
    }

    // Return the Author
    public String getAuthor(){
        return mAuthor;
    }

    // Return the Date
    public String getDateAndTime(){
        return mDateAndTime;
    }

    // Return the URL
    public String getUrl(){
        return mUrl;
    }

    // Return the thumbnail
    public Drawable getThumbnail(){
        return mThumbnail;
    }
}
