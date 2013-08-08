package com.JIAT.Tumble;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Arsen
 * Date: 8/5/13
 * Time: 9:00 AM
 *
 * This wrapper class wraps a posts response in the
 * appropriate metadata
 */

public class PostsWrapper extends ResponseWrapper{

    public String toString()
    {
        StringBuilder builder = new StringBuilder();

        //Append meta info
        builder.append("Status: " + meta.status + "\n");
        builder.append("Message: " + meta.msg + "\n");

        //Append Posts
        builder.append("Posts: ");

        for (int i =0; i < response.posts.size(); i++)
        {
            builder.append("\t" + response.posts.get(i) + "\n" );
        }

        return builder.toString();
    }

    //Getters

    public ArrayList<Post> getPosts()
    {
        return response.posts;
    }

 }
