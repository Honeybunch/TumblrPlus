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

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public String getUrl()
    {
        return  url;
    }

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

    /*
    * Getters!
    * */

    public String getCaption()
    {
        return caption;
    }

    public ArrayList<Photo> getAlt_sizes()
    {
        return alt_sizes;
    }

    public Photo getOriginal_size()
    {
        return original_size;
    }

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

    //All Possible Post Data
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
    private boolean can_reply;

    //For Photo posts
    private String caption;
    private String image_permalink;
    private ArrayList<PhotoObject> photos;
    private String source_url;
    private String source_title;

    //For Answer posts
    private String asking_name;
    private String asking_url;
    private String question;
    private String answer;

    //For Text posts
    private String title;
    private String body;

    //Getter functions (Generated)

    public String getBlog_name() {
        return blog_name;
    }
    public String getId() {
        return id;
    }
    public String getPost_url() {
        return post_url;
    }
    public String getSlug() {
        return slug;
    }
    public String getType() {
        return type;
    }
    public String getDate() {
        return date;
    }
    public String getTimestamp() {
        return timestamp;
    }
    public String getState() {
        return state;
    }
    public String getFormat() {
        return format;
    }
    public String getReblog_key() {
        return reblog_key;
    }
    public ArrayList<String> getTags() {
        return tags;
    }
    public String getShort_url() {
        return short_url;
    }
    public boolean isFollowed() {
        return followed;
    }
    public ArrayList<String> getHighlighted() {
        return highlighted;
    }
    public boolean isLiked() {
        return liked;
    }
    public String getNote_count() {
        return note_count;
    }
    public boolean isCan_reply() {
        return can_reply;
    }

    //For Photo Posts
    public String getCaption() {
        return caption;
    }
    public String getImage_permalink() {
        return image_permalink;
    }
    public ArrayList<PhotoObject> getPhotos() {
        return photos;
    }
    public String getSource_url() {
        return source_url;
    }
    public String getSource_title() {
        return source_title;
    }

    //For Answer Posts
    public String getAsking_name() {
        return asking_name;
    }
    public String getAsking_url() {
        return asking_url;
    }
    public String getQuestion() {
        return question;
    }
    public String getAnswer() {
        return answer;
    }

    //For Text Posts
    public String getTitle() {
        return title;
    }
    public String getBody() {
        return body;
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
