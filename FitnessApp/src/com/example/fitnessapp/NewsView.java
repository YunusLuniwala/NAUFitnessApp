package com.example.fitnessapp;



import java.io.InputStream;



import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class NewsView extends Activity {
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.news_layout);
        

        Intent intent = getIntent();
        String titleString = intent.getStringExtra("title");
        String imageString = intent.getStringExtra("image");
        String contentString = intent.getStringExtra("content");

        TextView title = (TextView) findViewById(R.id.title1);
        title.setText(titleString);
        
        TextView content = (TextView) findViewById(R.id.newsContent1);
        content.setText(contentString);
		
		ImageView newsImage = (ImageView) findViewById(R.id.imageView1);
		new DownloadImageTask(newsImage).execute(imageString);



		
        

        
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