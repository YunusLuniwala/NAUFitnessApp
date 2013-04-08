package com.example.fitnessapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PeerViewer extends Activity {
	
	TextView t1,t2;
	WebView browser;
	/** Called when the activity is first created. */
    @SuppressLint("NewApi")
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.peerviewer);

        String loadUrl = "http://www.peerfit.com/facility.php?groups_id=435";
        
        Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int width = size.x;
		int height = size.y;
        
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
  			    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
         LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
   			    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
         LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(
    			    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        // initialize the browser object
        browser = (WebView) findViewById(R.id.webview);
        t1 = (TextView) findViewById(R.id.paddingTop);
        t2 = (TextView) findViewById(R.id.paddingBottom);
        float middle_ratio = 470.0f/height;
        float pad_ratio = (1.0f - middle_ratio)/2;
        Toast toast = Toast.makeText(getApplicationContext(), "" + (middle_ratio + 2*pad_ratio), Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 25, 400);
		toast.show();
        params.weight = middle_ratio;
        params2.weight = pad_ratio;
        params3.weight = pad_ratio;
        
        browser.setInitialScale(100);
        browser.getSettings().setLoadWithOverviewMode(true);
        
        browser.setLayoutParams(params);
        t1.setLayoutParams(params2);
        t2.setLayoutParams(params3);
        try {
            // load the url
            browser.loadData("<iframe width=\"700\" height=\"470\" src=\"http://www.peerfit.com/embed?groups_id=435\" frameborder=\"0\"></iframe>", "text/html","utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
