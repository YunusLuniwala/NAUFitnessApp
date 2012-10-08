package com.example.fitnessapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.widget.ImageButton;
import android.widget.Toast;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.support.v4.app.NavUtils;

public class SplashActivity extends Activity {

	ImageButton logoButton;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        
        initializeApp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.splash, menu);
        return true;
    }
    
    public void initializeApp()
    {
    	logoButton = (ImageButton) findViewById(R.id.logoButton);
    	
    	logoButton.setOnClickListener(new OnClickListener() {
 
			public void onClick(View arg0) {
 
			   /*Toast.makeText(SplashActivity.this,
				"ImageButton is clicked!", Toast.LENGTH_SHORT).show();*/
				//Starting a new Intent
                Intent nextScreen = new Intent(getApplicationContext(), MainScreen.class);
 
                startActivity(nextScreen);
                finish();
			}
 
		});
    }

    
}
