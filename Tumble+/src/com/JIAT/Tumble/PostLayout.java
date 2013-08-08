package com.JIAT.Tumble;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

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
public class PostLayout extends LinearLayout implements OnClickListener{

    private Post post;

    //UI Elements
    protected LinearLayout headerLayout;
    protected LinearLayout footerButtonLayout;
    protected LinearLayout mainLayout;
    protected LinearLayout footerLayout;
    protected LinearLayout footerSubLayout;
    protected FlowLayout tagsLayout;

    protected ImageView blogAvatar;
    protected TextView blogName;

    protected TextView noteCount;
    protected Button reblogButton;
    protected Button likeButton;
    protected Button shareButton;
    protected Button replyButton;
    protected Button followButton;


    protected TextView testView;


    //Getter
    public Post getPost()
    {
        return post;
    }

    public PostLayout(Context context, Post post)
    {
        super(context);

        this.post = post;

        //Setup layouts
        headerLayout = new LinearLayout(context);
        headerLayout.setOrientation(LinearLayout.HORIZONTAL);


        LayoutParams footerSubParams = new LayoutParams( //To give the footer a strict height
                LayoutParams.MATCH_PARENT,
                45);

        footerLayout = new LinearLayout(context);
        footerLayout.setOrientation(LinearLayout.VERTICAL);
        footerLayout.setMinimumHeight(20);

        footerSubLayout = new LinearLayout(context);
        footerSubLayout.setOrientation(LinearLayout.HORIZONTAL);
        footerSubLayout.setLayoutParams(footerSubParams);

        footerButtonLayout = new LinearLayout(context);
        footerButtonLayout.setOrientation(LinearLayout.HORIZONTAL);
        footerButtonLayout.setGravity(Gravity.RIGHT);
        footerButtonLayout.setWeightSum(5);

        tagsLayout = new FlowLayout(context);
        tagsLayout.setHorizontalSpacing(5);
        tagsLayout.setVerticalSpacing(1);
        tagsLayout.setPadding(0,20, 0, 0);

        LayoutParams mainParams = new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
        );

        mainLayout = new LinearLayout(context);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.setLayoutParams(mainParams);

        //Setup Header

        //setup avatar
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
        blogName.setTextColor(Color.BLACK);

        //Setup main body
        testView = new TextView(context);
        testView.setText(post.toString());
        testView.setTextColor(Color.BLACK);

        //setup tags layout
        for(int i = 0; i < post.getTags().size(); i++)
        {
            String tag = post.getTags().get(i);

            TextView tagView = new TextView(context);
            tagView.setText("#" + tag + " ");
            tagView.setTextColor(Color.BLUE);

            tagsLayout.addView(tagView);
        }
        //Add a blank space at the end so the space is always used
        TextView blankTagView = new TextView(context);
        blankTagView.setText(" ");
        blankTagView.setTextColor(Color.BLUE);

        tagsLayout.addView(blankTagView);


        //Setup footer

        //button params
        LayoutParams buttonParams = new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.MATCH_PARENT);
        buttonParams.weight = 1;

        noteCount = new TextView(context);
        noteCount.setText("Notes: " + post.getNote_count());
        noteCount.setWidth(150);
        noteCount.setGravity(Gravity.BOTTOM | Gravity.LEFT);
        noteCount.setTextColor(Color.BLACK);

        reblogButton = new Button(context);
        reblogButton.setText("ReB");
        reblogButton.setOnClickListener(this);
        reblogButton.setPadding(0,0,0,0);
        reblogButton.setTextSize(12);
        reblogButton.setWidth(0);
        reblogButton.setHeight(0);
        reblogButton.setLayoutParams(buttonParams);
        reblogButton.setTextColor(Color.BLACK);

        likeButton = new Button(context);
        likeButton.setText("<3");
        likeButton.setOnClickListener(this);
        likeButton.setPadding(-5,-5,-5,-5);
        likeButton.setTextSize(12);
        likeButton.setWidth(0);
        likeButton.setHeight(0);
        likeButton.setLayoutParams(buttonParams);
        likeButton.setTextColor(Color.BLACK);

        shareButton = new Button(context);
        shareButton.setText("Sh");
        shareButton.setOnClickListener(this);
        shareButton.setPadding(-5,-5,-5,-5);
        shareButton.setTextSize(12);
        shareButton.setWidth(0);
        shareButton.setHeight(0);
        shareButton.setLayoutParams(buttonParams);
        shareButton.setTextColor(Color.BLACK);

        replyButton = new Button(context);
        replyButton.setText("ReP");
        replyButton.setOnClickListener(this);
        replyButton.setPadding(-5,-5,-5,-5);
        replyButton.setTextSize(12);
        replyButton.setWidth(0);
        replyButton.setHeight(0);
        replyButton.setLayoutParams(buttonParams);
        replyButton.setTextColor(Color.BLACK);

        followButton = new Button(context);
        followButton.setText("+");
        followButton.setOnClickListener(this);
        followButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        followButton.setWidth(0);
        followButton.setHeight(0);
        followButton.setLayoutParams(buttonParams);
        followButton.setTextColor(Color.BLACK);

        //Add layouts to their respective places
        footerButtonLayout.addView(reblogButton);
        footerButtonLayout.addView(likeButton);
        footerButtonLayout.addView(shareButton);
        if(post.isCan_reply()){
            footerButtonLayout.addView(replyButton);
        }
        footerButtonLayout.addView(followButton);

        footerSubLayout.addView(noteCount);
        footerSubLayout.addView(footerButtonLayout);

        headerLayout.addView(blogAvatar);
        headerLayout.addView(blogName);


        mainLayout.addView(testView);

        footerLayout.addView(tagsLayout);
        footerLayout.addView(footerSubLayout);

        this.addView(headerLayout);
        this.addView(mainLayout);
        this.addView(footerLayout);



        //Setup padding and rounded rectangles
        this.setPadding(10, 5, 10, 5);

        RoundRectShape roundedRect = new RoundRectShape(new float[] {10, 10, 10, 10, 10, 10, 10 ,10}, null, null);
        ShapeDrawable drawableShape = new ShapeDrawable(roundedRect);
        drawableShape.getPaint().setColor(Color.parseColor("#FFFFFF"));

        this.setBackground(drawableShape);

        this.setOrientation(LinearLayout.VERTICAL);
    }

    @Override
    //Handle clicks
    public void onClick(View view)
    {

    }

}
