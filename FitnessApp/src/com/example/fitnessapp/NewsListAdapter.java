package com.example.fitnessapp;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class NewsListAdapter extends ArrayAdapter<News> {

	private int				resource;
	private LayoutInflater	inflater;
	private Context 		context;
	private URL url;
	private Bitmap bmp;
	private HttpURLConnection conn;
	
	public NewsListAdapter ( Context ctx, int resourceId, List<News> objects) {
		
		super( ctx, resourceId, objects );
		resource = resourceId;
		inflater = LayoutInflater.from( ctx );
		context=ctx;
	}


	@Override
	public View getView ( int position, View convertView, ViewGroup parent ) {

		/* create a new view of my layout and inflate it in the row */
		convertView = ( RelativeLayout ) inflater.inflate( resource, null );

		/* Extract the city's object to show */
		News news = getItem( position );
		
	
		TextView title = (TextView) convertView.findViewById(R.id.newsTitle);
		title.setText(news.getTitle());
		
	
		TextView content = (TextView) convertView.findViewById(R.id.newsContent);
		content.setText(news.getContent());
		
		/* Take the ImageView from layout and set the city's image */
		ImageView imageCity = (ImageView) convertView.findViewById(R.id.NewsImage);
		//String uri = "drawable/" + city.getImage();
	    //int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
	    //Drawable image = context.getResources().getDrawable(imageResource);
		
		
		
	
			//url = new URL("http://image10.bizrate-images.com/resize?sq=60&uid=2216744464");
		new DownloadImageTask(imageCity).execute(news.getImage());



	    //imageCity.setImageDrawable(image);

		return convertView;

	}
	

private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;

    public DownloadImageTask(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
    }
}
}

