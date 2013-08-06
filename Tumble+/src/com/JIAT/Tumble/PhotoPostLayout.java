package com.JIAT.Tumble;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

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
public class PhotoPostLayout extends PostLayout {

    protected RelativeLayout photoRelLayout; //For making sure that the photoLayout will be centered
    protected LinearLayout photoLayout;

    public PhotoPostLayout(Context context, Post post)
    {
        super(context, post);

        testView.setText(""); //TODO: Remove the need for this

        //Get all the photo urls from the photo objects, and
        //get their bitmaps via the HTTPHelper and put them in
        //ImageViews so that we can easily lay them out

        //setup custom paramaters to allow images to get to layout in the center
        LayoutParams photoParams = new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        photoParams.weight = Gravity.CENTER;

        photoLayout = new LinearLayout(context);
        photoLayout.setOrientation(LinearLayout.VERTICAL);
        photoLayout.setGravity(Gravity.CENTER_VERTICAL);
        photoLayout.setLayoutParams(photoParams);

        photoRelLayout = new RelativeLayout(context);

        ArrayList<PhotoObject> photoObjects = post.getPhotos();

        //Put all image bitmaps into the photos array
        for(int i =0; i < photoObjects.size(); i++)
        {
            PhotoObject photoObject = photoObjects.get(i);
            Photo originalPhoto = photoObject.getOriginal_size();

            String url = originalPhoto.getUrl();

            Bitmap imageBitmap = HTTPHelper.downloadImage(url);

            if(imageBitmap != null)
            {
                ImageView imageView = new ImageView(context);
                imageView.setImageBitmap(imageBitmap);

                imageView.setPadding(5,5,5,5);
                imageView.setAdjustViewBounds(true);

                photoLayout.addView(imageView);
            }
        }

        //Add the populated photoLayout to the main layout
        photoRelLayout.addView(photoLayout);
        mainLayout.addView(photoRelLayout);
    }

}
