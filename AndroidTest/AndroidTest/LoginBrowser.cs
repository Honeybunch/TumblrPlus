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
		public delegate void GotAuthEventHandler (object sender, AuthVerifierArgs e);
		public event GotAuthEventHandler gotAuthVerifier;

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

				if (gotAuthVerifier != null) {
					AuthVerifierArgs authArgs = new AuthVerifierArgs ();

					int splitIndex = authTokens.IndexOf ("&");
					String authVerifier = authTokens.Substring (splitIndex);
					authVerifier = authVerifier.Substring (0, authVerifier.Length - 4);

					authArgs.AuthVerifier = authVerifier;
					gotAuthVerifier (this, authArgs);
				}

				return true;
			}
		}

	}

	//The event args to pass the authentication tokens
	public class AuthVerifierArgs : EventArgs
	{
		private String authVerifier;
		public String AuthVerifier
		{
			get
			{
				return authVerifier;
			}
			set
			{
				authVerifier = value;
			}
		}
	}
}

