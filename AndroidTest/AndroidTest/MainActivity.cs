using System;
using System.Net;
using Android.App;
using Android.Content;
using Android.Runtime;
using Android.Views;
using Android.Widget;
using Android.OS;
using Android.Webkit;
using OAuth;

namespace AndroidTest
{
	[Activity (Label = "AndroidTest", MainLauncher = true)]
	public class MainActivity : Activity
	{
		WebClient client;
		
		//Constructor
		public MainActivity()
		{
			client = new WebClient ();
		}

		protected override void OnCreate (Bundle bundle)
		{
			base.OnCreate (bundle);

			RequestWindowFeature(WindowFeatures.NoTitle);

			// Set our view from the "main" layout resource
			SetContentView (Resource.Layout.Main);

			//Connect events
			Button loginButton = FindViewById<Button> (Resource.Id.loginButton);
			loginButton.Click += new EventHandler (LoginClick);
		}
	

		/*
		 * Event Handlers
		 */

		public void LoginClick(object sender, EventArgs e)
		{
			OAuthBase oauth = new OAuthBase ();

			String baseURL = "http://www.tumblr.com/oauth/request_token/";
			String oauthKey = Resources.GetString (Resource.String.oauth_key);
			String oauthSecret = Resources.GetString (Resource.String.oauth_secret);
			String oauthNonce = oauth.GenerateNonce ();
			long timestamp = (long)(DateTime.Now - new DateTime (1970, 1, 1, 0, 0, 0)).TotalSeconds +  14408; //add this when on actual device;need total seconds since Jan 1, 1970 

			String normalizedURL;
			String normalizedParameters;

			String oauthSignature = oauth.GenerateSignature(new Uri(baseURL),oauthKey, oauthSecret, "", "", "GET", timestamp.ToString(),  oauthNonce, out normalizedURL, out normalizedParameters);

			String oauthTokenUrl = normalizedURL +"?" + normalizedParameters + "&oauth_signature=" + oauthSignature;
			oauthTokenUrl = oauthTokenUrl.Replace (" ", "+");

			Console.WriteLine(oauthTokenUrl);

			//Use the web client to get a request token
			Byte[] data = null;

			try
			{
				 data = client.DownloadData (oauthTokenUrl);
			}catch(WebException ex)
			{
				Console.WriteLine(ex.Message);
			}

			if (data != null) 
			{
				String tokens = System.Text.Encoding.UTF8.GetString (data);

				Console.WriteLine (tokens);

				//Start the login activity with the token string so it can ask for the right login page
				var loginActivity = new Intent (this, typeof(LoginActivity));
				loginActivity.PutExtra ("tokens", tokens);
				loginActivity.PutExtra ("oauthKey", oauthKey);
				loginActivity.PutExtra ("oauthSecret", oauthSecret);
				loginActivity.PutExtra ("timestamp", timestamp.ToString ());

				StartActivity (loginActivity);
			}
		}
	
	}
}


