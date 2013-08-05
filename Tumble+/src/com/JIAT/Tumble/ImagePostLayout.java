package com.JIAT.Tumble;

import android.content.Context;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Arsen
 * Date: 8/5/13
 * Time: 2:56 PM
 *
 * Extends the PostLayout to display image posts
 *
 */
public class ImagePostLayout extends PostLayout {

    protected ArrayList<ImageView> photos;

    public ImagePostLayout(Context context, Post post)
    {
        super(context, post);


    }

}
