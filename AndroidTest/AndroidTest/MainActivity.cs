using System;
using System.Net;
using Android.App;
using Android.Content;
using Android.Runtime;
using Android.Views;
using Android.Widget;
using Android.OS;

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
			TextView emailView = FindViewById<TextView> (Resource.Id.emailInput);
			TextView passView = FindViewById<TextView> (Resource.Id.passwordInput);

			String email = emailView.Text;
			String password = passView.Text;

			String oauthKey = Resources.GetString (Resource.String.oauth_key);
			String oauthSecret = Resources.GetString (Resource.String.oauth_secret);

			int timestamp = (DateTime.Today - new DateTime (1970, 01, 01)).TotalSeconds; //need total seconds since Jan 1, 1970

			String oauthTokenUrl = "www.tumblr.com/oauth/request_token?oauth_consumer_key=" + oauthKey + 
				"&oauth_signature=" + oauthSecret + 
				"&oauth_signature_method=HMAC-SHA1" + 
				"&oauth_timestamp=" + timestamp;

			StartActivity (DashActivity);
		}
	
	}
}


