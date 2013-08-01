package com.JIAT.Tumble;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

    //Variables
    private boolean gotToken;

    public MainActivity()
    {
        gotToken = false;
    }

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        requestWindowFeature(Window.FEATURE_NO_TITLE); //Placing this elsewhere will cause a crash
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        OAuthHelper.oauthConsumerKey = getResources().getString(R.string.oauth_key);
        OAuthHelper.oauthSecretKey = getResources().getString(R.string.oauth_secret);


        SharedPreferences sharedPreferences = getSharedPreferences("Tumble+", MODE_PRIVATE);

        //If we have the access token stored in the shared preferences, we don't have to log in

        String token = sharedPreferences.getString("accessToken", null);
        String secret = sharedPreferences.getString( "accessSecret", null);

        if(token != null)
        {
            gotToken = true;

            OAuthHelper.oauthAccessToken = token;
            OAuthHelper.oauthAccessSecret = secret;

            Intent dashActivity = new Intent(this, DashActivity.class);
            startActivity(dashActivity);
        }

        if(!gotToken)
        {
            //Use the main.xml layout
            setContentView(R.layout.main);

            Button loginButton = (Button)findViewById(R.id.loginButton);
            loginButton.setOnClickListener(this);
        }

    }

    //Handle the button press here
    public void onClick(View view)
    {
        int requestError = OAuthHelper.GetRequestTokens();

        if(requestError > 0)
        {
            //Start the login activity
            Intent loginActivity = new Intent(this, LoginActivity.class);

            startActivity(loginActivity);
        }
        else
        {
            new AlertDialog.Builder(this)
                    .setTitle("Error!")
                    .setMessage("Error Getting Request Tokens. Are you connected to the internet?")
                    .show();
        }
    }
}
