package com.JIAT.Tumble;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created with IntelliJ IDEA.
 * User: Arsen
 * Date: 8/5/13
 * Time: 3:19 PM
 * A static class to help with non-oauth requests
 */

//ASync thread class to download strings outside the main thread
class StringDownloader extends AsyncTask<String, Void, String>
{
    private String data = "";

    public String getData()
    {
        return data;
    }

    protected String doInBackground(String... requestUrl)
    {
        //We have the URL to perform our request, time to actually go perform the request
        URL url = null;
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        String line = "";

        try
        {
            url = new URL(requestUrl[0]);
            inputStream = url.openStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            //Use the buffered reader to store the info line by line into the data string
            while((line = bufferedReader.readLine()) != null)
            {
                data = data + line;
            }
        }
        catch (MalformedURLException e)
        {
            Log.e("Error", "Malformed Exception", e);
        }
        catch (IOException e)
        {
            Log.e("Error", "IO Exception", e);
        }
        catch (Exception e)
        {
            Log.e("Error", "Exception", e);
        }
        finally
        {
            try
            {
                if(inputStream != null)
                    inputStream.close();
            }
            catch (IOException e)
            {
                Log.e("Error", "IO Exception - Closing Stream", e);
            }
        }

        //By this point, our data string has been filled and we can return it
        return data;
    }
}


//ASync thread class to download strings outside the main thread
class BitmapDownloader extends AsyncTask<String, Void, Bitmap>
{
    private Bitmap data = null;

    public Bitmap getData()
    {
        return data;
    }

    protected Bitmap doInBackground(String... requestUrl)
    {
        //We have the URL to perform our request, time to actually go perform the request
        URL url = null;
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        String line = "";

        try
        {
            url = new URL(requestUrl[0]);
            URLConnection connection = url.openConnection();
            data = BitmapFactory.decodeStream(connection.getInputStream());
        }
        catch (MalformedURLException e)
        {
            Log.e("Error", "Malformed Exception", e);
        }
        catch (IOException e)
        {
            Log.e("Error", "IO Exception", e);
        }
        catch (Exception e)
        {
            Log.e("Error", "Exception", e);
        }
        finally
        {
            try
            {
                if(inputStream != null)
                    inputStream.close();
            }
            catch (IOException e)
            {
                Log.e("Error", "IO Exception - Closing Stream", e);
            }
        }

        //By this point, our data string has been filled and we can return it
        return data;
    }
}


public class HTTPHelper
{
    //Static method to download an image via an AsyncTask
    public static Bitmap downloadImage(String url)
    {
        BitmapDownloader dataDownloader = new BitmapDownloader();
        dataDownloader.execute(url);

        //Try to wait for the thread to finish
        try
        {
            dataDownloader.get();
        }
        catch (Exception e)
        {
            //If there is an exception, we return null
            Log.e("Error", "Exception", e);
            return null;
        }

        //If we get this far and no exceptions, get the data out of the thread
        return dataDownloader.getData();
    }


    public static String downloadString(String url)
    {
        StringDownloader dataDownloader = new StringDownloader();
        dataDownloader.execute(url);

        //Try to wait for the thread to finish
        try
        {
            dataDownloader.get();
        }
        catch (Exception e)
        {
            //If there is an exception, we return null
            Log.e("Error", "Exception", e);
            return null;
        }

        //If we get this far and no exceptions, get the data out of the thread
        return dataDownloader.getData();
    }

}
