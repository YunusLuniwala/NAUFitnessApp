package com.example.fitnessapp;


import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.app.Activity;
import android.content.Intent;

public class StaffScreen extends Activity {
	
	Button login;
	EditText userName;
	EditText password;

	
	//Called when the activity is first created
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Sets the layout to use the correct file
        setContentView(R.layout.staff_screen);
        initializeApp();
    }
    
    public void initializeApp() {
    	
    	//TODO - Load in the file name from the QR code and rename the labels and set up custom fields
    	
    	
    	
    	//Loads the logoButton and textviews from the layout file
    	login = (Button) findViewById(R.id.login);
    	password = (EditText) findViewById(R.id.password);
    	userName = (EditText) findViewById(R.id.userName);
    	
    	//Sets the listener for the button for when its clicked
    	login.setOnClickListener(new OnClickListener() {
 
			public void onClick(View arg0) {
				if(userName.getText().toString().equalsIgnoreCase("admin") && password.getText().toString().equalsIgnoreCase("password")){
					Intent nextScreen = new Intent(getApplicationContext(), StaffEmail.class);
	                
	                //Start this activity - Replace this Screen with the new activity
	                startActivity(nextScreen);
				}
			}
 
		});
    }
}