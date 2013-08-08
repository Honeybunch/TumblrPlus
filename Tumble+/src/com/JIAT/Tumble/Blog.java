package com.JIAT.Tumble;

/**
 * Created with IntelliJ IDEA.
 * User: Arsen
 * Date: 8/8/13
 * Time: 9:21 AM
 *
 * Data of a blog
 */
public class Blog {

    private String name;
    private String title;
    private String url;
    private String tweet;
    private boolean primary;
    private int followers;

    public String toString()
    {
        StringBuilder builder = new StringBuilder();

        builder.append("Name: " + name + "\n");
        builder.append("Title: " + title + "\n");
        builder.append("URL: " + url + "\n");
        builder.append("Tweet: " + tweet + "\n");
        builder.append("Primary: " + primary + "\n");
        builder.append("Followers: " + followers + "\n");

        return builder.toString();
    }

    //Getters!

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getTweet() {
        return tweet;
    }

    public boolean isPrimary() {
        return primary;
    }

    public int getFollowers() {
        return followers;
    }
}
