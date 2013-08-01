package com.JIAT.Tumble;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: Arsen
 * Date: 7/30/13
 * Time: 9:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class DashActivity extends Activity
{
    public void onCreate(Bundle savedInstanceState)
    {
        requestWindowFeature(Window.FEATURE_NO_TITLE); //Placing this elsewhere will cause a crash
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dash);


        TextView testText = (TextView)findViewById(R.id.json);

        String data = OAuthHelper.OAuthRequest("http://api.tumblr.com/v2/user/dashboard", "GET", OAuthHelper.oauthAccessToken, OAuthHelper.oauthAccessSecret, "");

        testText.setText(data);
    }

}
