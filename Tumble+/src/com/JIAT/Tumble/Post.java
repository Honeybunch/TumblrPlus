package com.JIAT.Tumble;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Arsen
 * Date: 7/30/13
 * Time: 9:43 AM
 * To change this template use File | Settings | File Templates.
 */

//Classes specific to posts

class Photo
{
    private int width;
    private int height;
    private String url;

    public String toString()
    {
        StringBuilder builder = new StringBuilder();

        builder.append("Width: " + width + "\n");
        builder.append("Height: " + height + "\n");
        builder.append("URL: " + url + "\n");

        return builder.toString();
    }
}

class PhotoObject
{
    private String caption;
    private ArrayList<Photo> alt_sizes;
    private Photo original_size;

    public String toString()
    {
        StringBuilder builder = new StringBuilder();

        builder.append("Caption: " + caption + "\n");

        //Append all Photos
        builder.append("Alt Sizes: " + "\n");
        for(int i = 0; i < alt_sizes.size(); i++)
        {
            builder.append("\t" + alt_sizes.get(i).toString());
        }

        builder.append("Original Size: " + original_size.toString());

        return builder.toString();
    }
}

public class Post {

    //General Post data
    private String blog_name;
    private String id;
    private String post_url;
    private String slug;
    private String type;
    private String date;
    private String timestamp;
    private String state;
    private String format;
    private String reblog_key;
    private ArrayList<String> tags;
    private String short_url;
    private boolean followed;
    private ArrayList<String> highlighted;
    private boolean liked;
    private String note_count;
    private String caption;
    private String image_permalink;
    private ArrayList<PhotoObject> photos;
    private boolean can_reply;
    private String source_url;
    private String source_title;
    private String title;
    private String body;

    public String getBlog_name()
    {
        return  blog_name;
    }

    public String getType()
    {
        return type;
    }

    //Print return the post in an easy to read format
    public String toString()
    {
        StringBuilder builder = new StringBuilder();

        builder.append("Blog Name: " + blog_name + "\n");
        builder.append("ID: " + id + "\n");
        builder.append("Post URL : " + post_url + "\n");
        builder.append("Slug : " + slug + "\n");
        builder.append("Type : " + type + "\n");
        builder.append("Date : " + date + "\n");
        builder.append("Timestamp : " + timestamp + "\n");
        builder.append("State : " + state + "\n");
        builder.append("Format : " + format + "\n");
        builder.append("Reblog Key : " + reblog_key + "\n");
        builder.append("Tags: ");

        //Append all tags
        for(int i =0; i < tags.size(); i++)
        {
            builder.append("\t" + tags.get(i) + "\n");
        }

        builder.append("Short URL: " + short_url + "\n");
        builder.append("Followed: " + followed + "\n");

        //Append all Highlighted
        for(int i = 0; i < highlighted.size(); i++)
        {
            builder.append("\t" + highlighted.get(i) + "\n");
        }

        builder.append("Liked: " + liked + "\n");
        builder.append("Note Count: " + note_count + "\n");
        builder.append("Caption: " + caption + "\n");
        builder.append("Image Permalink: " + image_permalink + "\n");

        //Append all PhotoObjects
        builder.append("Photos: " + "\n");

        if(photos != null){  //This will be null if the type is not photo
            for(int i = 0; i < photos.size(); i++)
            {
                builder.append("\t" + photos.get(i).toString() + "\n");
            }
        }

        builder.append("Can Reply: " + can_reply + "\n");
        builder.append("Source URL: " + source_url + "\n");
        builder.append("Source Title: " + source_title + "\n");
        builder.append("Title: " + title + "\n");
        builder.append("Body: " + body);

        return builder.toString();
    }

}
