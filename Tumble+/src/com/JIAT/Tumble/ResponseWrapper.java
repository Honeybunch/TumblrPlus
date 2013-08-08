package com.JIAT.Tumble;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Arsen
 * Date: 8/8/13
 * Time: 9:11 AM
 *
 * This is an extendable reponse wrapper
 */

//Some internal objects specific to tumblr responses
class Meta
{
    public int status;
    public String msg;
}

class Response
{
    //The response from tumblr could have posts, user data, etc.
    public ArrayList<Post> posts;
    public User user;
}

public class ResponseWrapper {
    protected Meta meta;
    protected Response response;
}
