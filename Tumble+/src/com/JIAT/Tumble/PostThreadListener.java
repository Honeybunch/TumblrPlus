package com.JIAT.Tumble;

import android.os.AsyncTask;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Arsen
 * Date: 8/8/13
 * Time: 5:31 PM
 *
 * To be used to listen for either a NewPageThread or a PostsSinceThread to finish
 */
public interface PostThreadListener {

    void onThreadFinished(AsyncTask thread, ArrayList<PostLayout> posts);

}
