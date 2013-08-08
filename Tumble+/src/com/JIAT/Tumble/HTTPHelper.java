package com.JIAT.Tumble;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.util.Log;

import java.io.*;
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
        Log.v("DataThread:", "test");

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

            /*
            * We can't just decode our bitmap image from the stream. If we do too many,
            * we'll run out of memory! We have to check the size of the image,
            * then properly decode the bitmap in an efficient manner.
            *
            * the options.inJustDecodeBounds = true setting will allows to read
            * the size of the bitmap without fully downloading it.
            *
            * Then we'll turn that option off and download a scaled version of the bitmap.
            * */

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            options.inSampleSize = 1;

            //Read the entire image as an array of bytes.
            //Shouldn't cause memory issue because it will be disposed of later
            InputStream imageStream = connection.getInputStream();
            byte[] imageBytes = readInputStream(imageStream);

            if(imageBytes == null)
            {
                throw new Exception();
            }

            //This will write some data into the options
            BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length, options);

            int imageHeight = options.outHeight;
            int imageWidth = options.outHeight;

            //500x500 is our max size
            options.inSampleSize = calculateInSampleSize(options, 500,500);

            //Make sure we can actually return a bitmap
            options.inJustDecodeBounds = false;
            data = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length, options);
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

    //Some private functions to aid with bitmap decoding

    private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight)
    {
        //Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outHeight;
        int inSampleSize = 1;

        //If the actual height of the image is greater than our
        //Required height, we're going to get the factor to scale the image by
        if(height > reqHeight || width > reqWidth)
        {
            //Calculate rounded ratios
            final int heightRatio = Math.round((float)height / (float)reqHeight);
            final int widthRatio = Math.round((float)width / (float)reqWidth);

            //Choose the smallest sample size of the two to report back
            //If the height ratio is less, use height ratio, otherwise use width ratio
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }

        return inSampleSize;
    }

    private byte[] readInputStream(InputStream stream)
    {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        int read = 0;
        byte[] data = new byte[16384]; //Arbitrary size

        try{
            //read the data, save it, and continue reading if there's no error
            while((read = stream.read(data, 0, data.length)) != -1)
            {
                buffer.write(data, 0, read);
            }

            buffer.flush();
            return buffer.toByteArray();
        }
        catch (Exception e)
        {
            Log.e("Error: ", "Cannot Read InputStream", e);
        }
        //If we get this far, return null
        return null;
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
            Log.v("DataThread: ", "done");
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
