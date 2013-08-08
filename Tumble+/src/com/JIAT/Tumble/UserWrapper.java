package com.JIAT.Tumble;

/**
 * Created with IntelliJ IDEA.
 * User: Arsen
 * Date: 8/8/13
 * Time: 9:29 AM
 * To change this template use File | Settings | File Templates.
 */
public class UserWrapper extends ResponseWrapper {

    public String toString()
    {
        StringBuilder builder = new StringBuilder();

        //Append meta info
        builder.append("Status: " + meta.status + "\n");
        builder.append("Message: " + meta.msg + "\n");

        //Append Posts
        builder.append("User: " + response.user.toString() + "\n");

        return builder.toString();
    }

    //Getters

    public User getUser()
    {
        return response.user;
    }

}
