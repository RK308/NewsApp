package com.example.android.newsapp;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A {@link NewsAdapter} knows how to create a list item layout for each news
 * in the data source (a list of {@link NewsItem} objects).
 */
public class NewsAdapter extends ArrayAdapter<NewsItem> {

    Context mContext;
    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context   The current context. Used to inflate the layout file.
     * @param newsItems A List of newsItem objects to display in a list
     */
    public NewsAdapter(Context context, ArrayList<NewsItem> newsItems) {
        super(context, 0, newsItems);
        mContext = context;
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View ListItemView = convertView;
        if (ListItemView == null) {
            ListItemView = LayoutInflater.from(getContext()).inflate(R.layout.news_list_item, parent, false);
        }

        // Get the NewsItem object located at this position in the list
        NewsItem currentNewsItem = getItem(position);

        // Find the TextView in the news_list_item layout with the ID section_label
        TextView sectionTextView = (TextView) ListItemView.findViewById(R.id.section_label);
        sectionTextView.setText((mContext.getResources().getString(R.string.section)) + currentNewsItem.getSection());

        // Find the ImageView in the news_list_item layout with the ID article_image
        ImageView thumbnailImageView = (ImageView) ListItemView.findViewById(R.id.article_image);

        // To check whether the image for the current news item is not null
        // If not null will set the image for the current news item
        // else will set the default image.
        if (currentNewsItem.getThumbnail()!= null) {
            thumbnailImageView.setImageDrawable(currentNewsItem.getThumbnail());
        } else {
            thumbnailImageView.setImageResource(R.drawable.news_image);
        }

        // Find the TextView in the news_list_item layout with the ID article_title
        TextView titleTextView = (TextView) ListItemView.findViewById(R.id.article_title);
        titleTextView.setText(currentNewsItem.getTitle());

        // Find the TextView in the news_list_item layout with the ID article_author
        TextView authorTextView = (TextView) ListItemView.findViewById(R.id.article_author);
        String author = currentNewsItem.getAuthor();
        authorTextView.setText((mContext.getResources().getString(R.string.author)) + author);

        // Find the TextView in the news_list_item layout with the ID article_date
        TextView dateTextView = (TextView) ListItemView.findViewById(R.id.article_date);
        String dateAndTime = currentNewsItem.getDateAndTime();

        // Split date and time and just set the date on the dateTextView.
        String [] dateTime = dateAndTime.split("T");
        String date = dateTime[0];
        String time = dateTime[1];
        dateTextView.setText((mContext.getResources().getString(R.string.published)) + date);

        // Return the whole list_item layout
        return ListItemView;
    }

}

