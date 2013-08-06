package com.JIAT.Tumble;

import android.content.Context;

/**
 * Created with IntelliJ IDEA.
 * User: Arsen
 * Date: 8/6/13
 * Time: 8:56 AM
 * To change this template use File | Settings | File Templates.
 */
public class TextPostLayout extends PostLayout{

    public TextPostLayout(Context context, Post post)
    {
        super(context, post);

        testView.setText("Text");
    }


}