using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Net;
using System.Web;
using Android.App;
using Android.Content;
using Android.OS;
using Android.Runtime;
using Android.Views;
using Android.Widget;
using Android.Webkit;

using OAuth;

namespace AndroidTest
{
	[Activity (Label = "LoginActivity")]			
	public class LoginActivity : Activity
	{
		String loginTokens;

		String oauthKey;
		String oauthSecret;
		String timestamp;

		protected override void OnCreate (Bundle bundle)
		{
			base.OnCreate (bundle);

			RequestWindowFeature(WindowFeatures.NoTitle);

			SetContentView (Resource.Layout.LoginActivity);

			loginTokens = Intent.GetStringExtra ("tokens");
			oauthKey = Intent.GetStringExtra ("oauthKey");
			oauthSecret = Intent.GetStringExtra ("oauthSecret");
			timestamp = Intent.GetStringExtra ("timestamp");


			LoginBrowser loginBrowser = new LoginBrowser ();

			WebView webLogin = FindViewById<WebView> (Resource.Id.webLogin);
			webLogin.Settings.JavaScriptEnabled = true;
			webLogin.Settings.PluginsEnabled = true;
			webLogin.SetWebViewClient(loginBrowser);
			webLogin.LoadUrl ("http://www.tumblr.com/oauth/authorize/?" + loginTokens);

			loginBrowser.gotAuthVerifier += new LoginBrowser.GotAuthEventHandler (GotCallback);
		}
	
		void GotCallback(object sender, AuthVerifierArgs e)
		{
			//Not going back to the main activity, so we get the access tokens here
			WebClient client = new WebClient();

			String accessUrl = "http://www.tumblr.com/oauth/access_token/";

			//Generate another signature
			OAuthBase oauth = new OAuthBase ();

			//Seperate the token and the token secret
			int splitIndex = loginTokens.IndexOf ("&");
			String token = loginTokens.Substring (12, splitIndex - 12);
			String tokenSecret = loginTokens.Remove (0, splitIndex + 1);
			tokenSecret = tokenSecret.Substring (19, tokenSecret.IndexOf("&") - 19);

			String authVerifier = e.AuthVerifier;
			String normalizedURL;
			String normalizedParameters;
			String oauthNonce = oauth.GenerateNonce();

			String oauthSignature = oauth.GenerateSignature (new Uri(accessUrl), oauthKey, oauthSecret, token, tokenSecret, "GET", timestamp, oauthNonce, out normalizedURL, out normalizedParameters);

			String oauthAccessUrl = normalizedURL +"?" + normalizedParameters + authVerifier + "&oauth_signature=" + oauthSignature;
			oauthAccessUrl = oauthAccessUrl.Replace ("+", "%2b"); //Encode the + so that it will work in the URL

			Console.WriteLine (oauthAccessUrl);

			//Download the access token
			Byte[] data = null;
			
			try
			{
				data = client.DownloadData (oauthAccessUrl);
			}catch(WebException ex)
			{
				Console.WriteLine(ex.Message);
			}

			if (data != null) 
			{
				String tokens = System.Text.Encoding.UTF8.GetString (data);

				Console.WriteLine (tokens);
			}
		}
	}
}

