package com.JIAT.Tumble;

import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created with IntelliJ IDEA.
 * User: Arsen
 * Date: 8/1/13
 * Time: 12:15 PM
 * To change this template use File | Settings | File Templates.
 */

public class LoginBrowser extends WebViewClient{

    private String callbackUrl;

    CallbackListener listener;

    public LoginBrowser(CallbackListener listener)
    {
        callbackUrl = "http://www.google.com";

        this.listener = listener;
    }

    public boolean shouldOverrideUrlLoading(WebView view, String url)
    {
        //If the URL we're visiting isn't the callback url, just load it
         if(!url.contains(callbackUrl))
         {
            view.loadUrl(url);

            return true;

        }//Otherwise, we're going to get the verifier from the callback url
        else
        {
            String authTokens = url.substring(callbackUrl.length() + 1);

            int splitIndex = authTokens.indexOf("&");
            String authVerifier = authTokens.substring(splitIndex);
            authVerifier = authVerifier.substring(0, authVerifier.length() - 4);
            authVerifier = authVerifier.substring(16);

            listener.gotCallback(authVerifier);

            return true;
        }
    }

}
