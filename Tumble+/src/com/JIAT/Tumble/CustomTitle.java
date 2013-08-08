package com.JIAT.Tumble;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: Arsen
 * Date: 8/8/13
 * Time: 9:01 AM
 *
 * This custom title object is a linear layout which will act
 * as a custom title bar on the activity.
 */
public class CustomTitle extends LinearLayout{

    private TextView titleView;
    private ImageView avatarView;

    public CustomTitle(Context context, String title, Bitmap avatar)
    {
        super(context);

        //Setup layout paramaters
        LayoutParams titleParams = new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
            );


        //Setup title objects
        titleView = new TextView(context);
        titleView.setText(title);
        titleView.setTextColor(Color.BLACK);

        avatarView = new ImageView(context);
        avatarView.setImageBitmap(avatar);

        setOrientation(LinearLayout.HORIZONTAL);
        setLayoutParams(titleParams);
        setGravity(Gravity.CENTER);

        addView(titleView);
        addView(avatarView);
    }

}
