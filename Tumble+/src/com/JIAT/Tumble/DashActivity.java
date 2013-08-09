package com.JIAT.Tumble;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.*;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Arsen
 * Date: 7/30/13
 * Time: 9:44 AM
 * To change this template use File | Settings | File Templates.
 */
//Thread to return posts since the last shown post
class PostsSinceThread extends AsyncTask<String, Void, ArrayList<PostLayout>>
{
    protected ArrayList<PostLayout> doInBackground(String...p)
    {
        return null;
    }
}

//Thread to return trailing posts
class NewPageThread extends AsyncTask<String, Void, NewPageThread>
{
    private Context context;
    private int postsPerPage;
    private PostThreadListener listener;
    private ArrayList<PostLayout> newPosts;

    public NewPageThread(PostThreadListener listener, Context context, int postsPerPage)
    {
        this.context = context;
        this.postsPerPage = postsPerPage;
        this.listener = listener;

        newPosts = new ArrayList<PostLayout>();
    }

    protected NewPageThread doInBackground(String...json)
    {
        ArrayList<String> params = new ArrayList<String>();
        params.add("reblog_info=true");
        params.add("limit=" + postsPerPage);

        String jsonData = json[0];

        //Setup parameters for the post layouts
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        layoutParams.setMargins(20, 20, 20, 0);

        //Create the postsWrapper object based off the JSON string
        PostsWrapper postsWrapper = new Gson().fromJson(jsonData, PostsWrapper.class);

        for(int i = 0; i < postsWrapper.getPosts().size(); i++)
        {
            Post post = postsWrapper.getPosts().get(i);
            String type = post.getType();

            PostLayout postLayout = null;

            //Can't run a string switch, use if/else chain
            if(type.equals("answer"))
            {
                postLayout = new AnswerPostLayout(context, post);
            }
            else if(type.equals("audio"))
            {
                postLayout = new AudioPostLayout(context, post);
            }
            else if(type.equals("chat"))
            {
                postLayout = new ChatPostLayout(context, post);
            }
            else if(type.equals("photo"))
            {
                postLayout = new PhotoPostLayout(context, post);
            }
            else if(type.equals("link"))
            {
                postLayout = new LinkPostLayout(context, post);
            }
            else if(type.equals("quote"))
            {
                postLayout = new QuotePostLayout(context, post);
            }
            else if(type.equals("text"))
            {
                postLayout = new TextPostLayout(context, post);
            }
            else if(type.equals("video"))
            {
                postLayout = new VideoPostLayout(context, post);
            }
            else
            {
                postLayout = new PostLayout(context, post);
            }

            postLayout.setLayoutParams(layoutParams);
            newPosts.add(postLayout);

            Log.v("Loading: ", "" + i);
        }

        return this;
    }

    @Override
    protected void onPostExecute(NewPageThread newPageThread) {
        listener.onThreadFinished(this, newPosts);
    }
}


public class DashActivity extends Activity implements View.OnClickListener, ScrollViewListener, PostThreadListener
{
    String jsonData;

    //UI Elements
    private Button refreshButton;
    private TextView titleView;
    private ImageView avatarView;
    private ObservableScrollView scrollView;
    private LinearLayout dashListLayout;

    private int postCount = 0;
    private final int postsPerPage = 15;

    public void onCreate(Bundle savedInstanceState)
    {
        requestWindowFeature(Window.FEATURE_NO_TITLE); //Placing this elsewhere will cause a crash
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dash);

        //Get the user object and their avatar
        String userJSON = OAuthHelper.OAuthRequest("http://api.tumblr.com/v2/user/info", "GET", OAuthHelper.oauthAccessToken, OAuthHelper.oauthAccessSecret, "", null);
        UserWrapper userWrapper = new Gson().fromJson(userJSON, UserWrapper.class);
        User user = userWrapper.getUser();

        //Get Title objects
        String title = user.getName();
        Bitmap avatar = HTTPHelper.downloadImage("http://api.tumblr.com/v2/blog/"+ title +".tumblr.com/avatar");

        //Setup custom header bar objects
        titleView = (TextView)findViewById(R.id.titleView);
        titleView.setText("Dashboard");
        titleView.setTextColor(Color.BLACK);

        avatarView = (ImageView)findViewById(R.id.avatarView);
        avatarView.setImageBitmap(avatar);

        //Setup the listeners
        refreshButton = (Button)findViewById(R.id.refreshButton);
        refreshButton.setOnClickListener(this);
        refreshButton.setTextColor(Color.BLACK);

        scrollView = (ObservableScrollView)findViewById(R.id.scrollView);
        scrollView.setScrollViewListener(this);

        getNewPage();
    }

    //Refreshes the last 15 posts
    private void getNewPage()
    {
        //Make sure this is up to date
        dashListLayout = (LinearLayout)findViewById(R.id.dashListLayout);

        //TODO: Start some identifier that the app is getting content

        //The OAuth request is quick, do it here and then pass it to the newPageThread
        String jsonData = OAuthHelper.OAuthRequest("http://api.tumblr.com/v2/user/dashboard", "GET", OAuthHelper.oauthAccessToken, OAuthHelper.oauthAccessSecret, "", null);
        Log.v("Test Data: ", jsonData);


        //Startup a thread to format the data without breaking the GUI
        //This will alert us when its done
        NewPageThread newPageThread = new NewPageThread(this, this, postsPerPage);
        newPageThread.execute(jsonData);
    }

    private void getNewPosts()
    {

    }

    //Handle the refresh press here
    public void onClick(View view)
    {
        if(scrollView.getScrollY() != 0)
        {
            scrollView.smoothScrollTo(0, 0);
        }
        else
        {
            getNewPosts();
        }
    }

    //Handle the scroll change
    public void onScrollChanged(ObservableScrollView  scrollView, int x, int y, int oldX, int oldY)
    {
        /*
        * If the user isn't at the top, change the refresh button
        * to let the user know it can go to the top
        * */

        if(y > 0)
        {
            refreshButton.setText("To Top");
        }
        else
        {
            refreshButton.setText("Refresh");
        }
    }

    //When a thread finishes, this method will run
    //Look at the type of thread and handle its data appropriately
    public void onThreadFinished(AsyncTask thread, ArrayList<PostLayout> posts)
    {
        for(int i = 0; i < posts.size(); i++)
        {
            dashListLayout.addView(posts.get(i));
        }

        //If the thread is a NewPageThread, we'll append its data
        //To the end of the dashListLayout
        if(thread instanceof NewPageThread)
        {
            Log.v("Got Thread: ", posts.toString());
        }
        //If it's a PostsSinceThread we'll put the data
        //at the top of the dashListLayout
        else if(thread instanceof  PostsSinceThread)
        {

        }

        //Recount the posts
        postCount += dashListLayout.getChildCount() - 1;
    }


    //Don't want the back button to send us to the MainActivity
    public void onBackPressed()
    {

    }

}
