package com.example.android.newsapp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A {@link NewsAdapter} knows how to create a list item layout for each news
 * in the data source (a list of {@link NewsItem} objects).
 */
public class NewsAdapter extends ArrayAdapter<NewsItem> {

    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context   The current context. Used to inflate the layout file.
     * @param newsItems A List of newsItem objects to display in a list
     */
    public NewsAdapter(Activity context, ArrayList<NewsItem> newsItems) {
        super(context, 0, newsItems);
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

        // Get the appropriate Section text color based on the current section name
        int sectionColor = getSectionColor(currentNewsItem.getSection());

        // Set the text color on the section textview
        sectionTextView.setTextColor(sectionColor);

        // Find the ImageView in the news_list_item layout with the ID article_image
        ImageView thumbnailImageView = (ImageView) ListItemView.findViewById(R.id.article_image);
        thumbnailImageView.setImageDrawable(currentNewsItem.getThumbnail());

        // Find the TextView in the news_list_item layout with the ID article_title
        TextView titleTextView = (TextView) ListItemView.findViewById(R.id.article_title);
        titleTextView.setText(currentNewsItem.getTitle());

        // Find the TextView in the news_list_item layout with the ID article_author
        TextView authorTextView = (TextView) ListItemView.findViewById(R.id.article_author);
        String author = currentNewsItem.getAuthor();
        authorTextView.setText(R.string.author+author);

        // Create a new Date object from date and time of the newsItem
        Date dateObject = new Date(currentNewsItem.getDateAndTime());

        // Find the TextView in the news_list_item layout with the ID article_date
        TextView dateTextView = (TextView) ListItemView.findViewById(R.id.article_date);

        // Format the date string (i.e. "Jan 25, 2001")
        String formattedDate = formatDate(dateObject);

        // Check the availability of date and set it on the date textView
        processDate(dateTextView, formattedDate);

        // Return the whole list_item layout
        return ListItemView;
    }


    // Return the formatted date string from a Date object
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd LLL, yyyy");
        return dateFormat.format(dateObject);

    }
    // This is to check the date, if available will format the date and set it on the textview.
    // If not will take out the textview from the list_item
    private void processDate(TextView dateTextView, String date){
        if (date == null){
            dateTextView.setVisibility(View.GONE);
        } else {
            dateTextView.setVisibility(View.VISIBLE);

            // To set underline on the date
            SpannableString finalDate = new SpannableString(date);
            finalDate.setSpan(new UnderlineSpan(), 0, finalDate.length(), 0);
            dateTextView.setText("Published on : " + finalDate);
        }
    }

    /*
     ** Returns the color for the section_name
     *
     * @param section is the section_name of the NewsItem.
     */
    private int getSectionColor(String section) {
        int sectionColor;
        switch (section) {
            case "us_news":
                sectionColor = R.color.us_news;
                break;
            case "football":
                sectionColor = R.color.football;
                break;
            case "world_news":
                sectionColor = R.color.world_news;
                break;
            case "politics":
                sectionColor = R.color.politics;
                break;
            case "business":
                sectionColor = R.color.business;
                break;
            case "australia_news":
                sectionColor = R.color.australia_news;
                break;
            case "science":
                sectionColor = R.color.science;
                break;
            case "sport":
                sectionColor = R.color.sport;
                break;
            case "art_and_design":
                sectionColor = R.color.art_and_design;
                break;
            case "music":
                sectionColor = R.color.music;
                break;
            case "opinion":
                sectionColor = R.color.opinion;
                break;
            case "environment":
                sectionColor = R.color.environment;
                break;
            case "society":
                sectionColor = R.color.society;
                break;
            case "uk_news":
                sectionColor = R.color.uk_news;
                break;
            case "film":
                sectionColor = R.color.film;
                break;
            case "television_and_radio":
                sectionColor = R.color.television_and_radio;
                break;
            default:
                sectionColor = R.color.default_color;
                break;
        }
        return ContextCompat.getColor(getContext(),sectionColor);
    }
}

