package com.example.fitnessapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.widget.ImageButton;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class SplashActivity extends Activity {

	ImageButton logoButton;
	
	//Called when the activity is first created
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Sets the layout to use the correct file
        setContentView(R.layout.splash);
        
        initializeApp();
    }
    
    public void initializeApp()
    {
    	//Loads the logoButton from the layout file
    	logoButton = (ImageButton) findViewById(R.id.logoButton);
    	
    	//Sets the listener for the button for when its clicked
    	logoButton.setOnClickListener(new OnClickListener() {
 
			public void onClick(View arg0) {
				//Start a new Intent - MainScreen
                Intent nextScreen = new Intent(getApplicationContext(), MainScreen.class);
                
                //Start this activity - Replace this Screen with the new activity
                startActivity(nextScreen);
                
                //Kill this activity, never return
                finish();
			}
 
		});
    }

    
}
