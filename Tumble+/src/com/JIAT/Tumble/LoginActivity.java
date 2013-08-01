package com.JIAT.Tumble;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class LoginActivity extends Activity implements CallbackListener{

    WebView webLogin;

    public void onCreate(Bundle savedInstanceState)
    {
        requestWindowFeature(Window.FEATURE_NO_TITLE); //Placing this elsewhere will cause a crash
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        LoginBrowser loginBrowser = new LoginBrowser(this);

        webLogin = (WebView)findViewById(R.id.webLogin);
        webLogin.getSettings().setJavaScriptEnabled(true);
        webLogin.getSettings().setPluginState(WebSettings.PluginState.ON);
        webLogin.setWebViewClient(loginBrowser);

        Log.v("URL: ", "http://www.tumblr.com/oauth/authorize/?oauth_token=" + OAuthHelper.oauthRequestToken);

        webLogin.loadUrl("http://www.tumblr.com/oauth/authorize/?oauth_token=" + OAuthHelper.oauthRequestToken);

    }

    //This gets triggered when the user browses to the callback URL
    public void gotCallback(String verifier)
    {
        OAuthHelper.oauthVerifier = verifier;

        int accessError = OAuthHelper.GetAccessTokens();

        if(accessError > 0)
        {
            //Save the access token and secret so that the user doesn't have to log in again
            SharedPreferences preferences = getSharedPreferences("Tumble+", MODE_PRIVATE);

            Editor editor = preferences.edit();

            editor.putString("accessToken", OAuthHelper.oauthAccessToken);
            editor.putString("accessSecret", OAuthHelper.oauthAccessSecret);

            editor.commit();

            //Start the dashboard activity
            Intent dashActivity = new Intent(this, DashActivity.class);
            startActivity(dashActivity);
        }
        else
        {
            new AlertDialog.Builder(this)
                    .setTitle("Error!")
                    .setMessage("Error Getting Access Tokens. Are you connected to the internet?")
                    .show();
        }
    }

}
