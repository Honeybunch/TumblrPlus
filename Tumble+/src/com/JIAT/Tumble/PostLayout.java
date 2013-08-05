package com.JIAT.Tumble;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: Arsen
 * Date: 7/30/13
 * Time: 9:44 AM
 *
 * This post layout is generic and is meant to be extended.
 * It contains the bare necessities that every PostLayout
 * will need.
 *
 */
public class PostLayout extends RelativeLayout {

    private Post post;

    //UI Elements
    protected LinearLayout headerLayout;
    protected LinearLayout mainLayout;
    protected LinearLayout footerLayout;

    protected ImageView blogAvatar;
    protected TextView blogName;
    protected TextView reblogName;

    protected TextView testView;

    public PostLayout(Context context, Post post)
    {
        super(context);

        this.post = post;

        //Setup layouts
        headerLayout = new LinearLayout(context);
        headerLayout.setOrientation(LinearLayout.HORIZONTAL);

        mainLayout = new LinearLayout(context);

        if(!post.getType().equals("photo"))  {
            //Setup the test Text View
            testView = new TextView(context);
            testView.setText(post.toString());
            mainLayout.addView(testView);
        }


        //Setup Header

        Bitmap avatar = HTTPHelper.downloadImage("http://api.tumblr.com/v2/blog/" + post.getBlog_name()  + ".tumblr.com/avatar");
        blogAvatar = new ImageView(context);
        blogAvatar.setPadding(5,5,5,5);

        if(avatar != null){
            blogAvatar.setImageBitmap(avatar);
        }
        else
        {
            //TODO: Set a default Avatar
        }

        blogName = new TextView(context);
        blogName.setText(post.getBlog_name());

        //Add layouts to their respective places
        headerLayout.addView(blogAvatar);
        headerLayout.addView(blogName);

        this.addView(headerLayout);
        this.addView(mainLayout);



        //Setup padding and rounded rectangles
        this.setPadding(10, 5, 10, 5);

        RoundRectShape roundedRect = new RoundRectShape(new float[] {10, 10, 10, 10, 10, 10, 10 ,10}, null, null);
        ShapeDrawable drawableShape = new ShapeDrawable(roundedRect);
        drawableShape.getPaint().setColor(Color.parseColor("#FFFFFF"));

        Log.v("CREATING LAYOUT: ", post.getBlog_name());

        this.setBackground(drawableShape);
    }

}
