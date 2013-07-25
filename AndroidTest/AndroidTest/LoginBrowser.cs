using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Android.App;
using Android.Content;
using Android.OS;
using Android.Runtime;
using Android.Views;
using Android.Widget;
using Android.Webkit;

namespace AndroidTest
{
	class LoginBrowser : WebViewClient
	{
		public delegate void GotAuthEventHandler (object sender, AuthTokenArgs e);
		public event GotAuthEventHandler gotAuthTokens;

		public override bool ShouldOverrideUrlLoading(WebView view, String url)
		{
			String callbackURL = "http://www.google.com";

			if (!url.Contains (callbackURL)) {
				Console.WriteLine (url);
				view.LoadUrl (url);
				return true;
			} else 
			{
				String authTokens = url.Substring (callbackURL.Length + 1);

				if (gotAuthTokens != null) {
					AuthTokenArgs authArgs = new AuthTokenArgs ();
					authArgs.AuthTokens = authTokens;
					gotAuthTokens (this, authArgs);
				}

				return true;
			}
		}

	}

	//The event args to pass the authentication tokens
	public class AuthTokenArgs : EventArgs
	{
		private String authTokens;
		public String AuthTokens
		{
			get
			{
				return authTokens;
			}
			set
			{
				authTokens = value;
			}
		}
	}
}

