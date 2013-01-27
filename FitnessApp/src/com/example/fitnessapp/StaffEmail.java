package com.example.fitnessapp;


import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.app.Activity;
import android.content.Intent;

public class StaffEmail extends Activity {
	
	Button email;

	
	//Called when the activity is first created
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Sets the layout to use the correct file
        setContentView(R.layout.staffemail);
        initializeApp();
    }
    
    public void initializeApp() {
    	
    	//TODO - Load in the file name from the QR code and rename the labels and set up custom fields
    	
    	
    	
    	//Loads the logoButton and textviews from the layout file
    	email = (Button) findViewById(R.id.email);
    	
    	//Sets the listener for the button for when its clicked
    	email.setOnClickListener(new OnClickListener() {
 
			public void onClick(View arg0) {
				Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
				emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Fitness Staff Update");
				emailIntent.setType("text/plain");
				emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,new String[] { "fitness@nau.edu" });
				emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Insert Update here.");
				startActivity(Intent.createChooser(emailIntent, "Send your email in:")); 
			}
 
		});
    }
}