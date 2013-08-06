package com.JIAT.Tumble;

import android.content.Context;

/**
 * Created with IntelliJ IDEA.
 * User: Arsen
 * Date: 8/6/13
 * Time: 8:58 AM
 * To change this template use File | Settings | File Templates.
 */
public class LinkPostLayout extends PostLayout{

    public LinkPostLayout(Context context, Post post)
    {
        super(context, post);

        testView.setText("Link");
    }
}