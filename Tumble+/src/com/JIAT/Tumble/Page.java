package com.JIAT.Tumble;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Arsen
 * Date: 8/5/13
 * Time: 9:00 AM
 * To change this template use File | Settings | File Templates.
 */

//Some internal objects specific to the page

class Meta
{
    public int status;
    public String msg;
}

class Response
{
    public ArrayList<Post> posts;
}

public class Page {

    private Meta meta;
    private Response response;

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

    /*
    * Getters and Setters
    * */

    public ArrayList<Post> getPosts()
    {
        return response.posts;
    }

 }
