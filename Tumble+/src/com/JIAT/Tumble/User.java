package com.JIAT.Tumble;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Arsen
 * Date: 8/8/13
 * Time: 9:10 AM
 * To change this template use File | Settings | File Templates.
 */
public class User {

    private int following;
    private String default_post_format;
    private String name;
    private int likes;
    private ArrayList<Blog> blogs;

    public String toString()
    {
        StringBuilder builder = new StringBuilder();

        builder.append("Following: " + following + "\n");
        builder.append("Default Post Format: " + default_post_format + "\n");
        builder.append("Name: " + name + "\n");
        builder.append("Likes: " + likes + "\n");
        builder.append("Blogs: \n");

        for(int i = 0; i < blogs.size(); i++)
        {
            builder.append("\t" + blogs.get(i).toString() + "\n");
        }

        return builder.toString();
    }

    //Getters!

    public int getFollowing() {
        return following;
    }

    public String getDefault_post_format() {
        return default_post_format;
    }

    public String getName() {
        return name;
    }

    public int getLikes() {
        return likes;
    }

    public ArrayList<Blog> getBlogs() {
        return blogs;
    }

}
