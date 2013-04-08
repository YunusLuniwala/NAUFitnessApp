package com.example.fitnessapp;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.actionbarsherlock.app.SherlockFragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

 
public class Track extends SherlockFragment {
	
	ImageButton scanButton;
	ImageButton manualButton;
	ImageButton readButton;
	ImageButton trainerButton;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Sets the layout to use the correct file
        getActivity().setContentView(R.layout.track);
        
        initializeApp();
        
    }
	
	public void initializeApp()
    {
    	//Loads the logoButton from the layout file
    	scanButton = (ImageButton) getActivity().findViewById(R.id.scanMachine);
    	readButton = (ImageButton) getActivity().findViewById(R.id.viewWorkoutLog);
    	manualButton = (ImageButton) getActivity().findViewById(R.id.newLogEntry);
    	trainerButton = (ImageButton) getActivity().findViewById(R.id.getTrainer);
    	
    	
    	//Sets the listener for the button for when its clicked
    	scanButton.setOnClickListener(new OnClickListener() {
 
			public void onClick(View arg0) {
				//Start a new Intent - Scanner
				Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
                startActivityForResult(intent, 0);
			}
 
		});
    	
    	readButton.setOnClickListener(new OnClickListener() {
    		 
			public void onClick(View arg0) {
				Intent nextScreen = new Intent(getActivity().getApplicationContext(), LogScrollView.class);
                
                //Start this activity - Replace this Screen with the new activity
                startActivity(nextScreen);
			}
 
		});
    	
    	manualButton.setOnClickListener(new OnClickListener() {
   		 
			public void onClick(View arg0) {
				String[] exArray;
				ArrayList<String> tempArray = new ArrayList<String>();
		    	
		    	String FILENAME = "exerciselog.txt";
				try {
				    BufferedReader inputReader = new BufferedReader(new InputStreamReader(
				            getActivity().openFileInput(FILENAME)));
				    String inputString;   
				    
				    int count = 0;
				    while ((inputString = inputReader.readLine()) != null && count < 20) {
				    	String exercise;
				    	
				    	int indexStart;
				    	int indexEnd;
				    	
				    	indexStart = inputString.indexOf("WORKOUT:") + 9;
				    	indexEnd = inputString.indexOf(" LAPS");
				    	if(indexEnd == -1){
				    		indexEnd = inputString.indexOf(" SETS");
				    		exercise = inputString.substring(indexStart, indexEnd);
				    		tempArray.add(exercise);
				    		count++;
				    	}else {
				    		exercise = inputString.substring(indexStart, indexEnd);
				    		tempArray.add(exercise);
				    		count++;
				    	}
				    }
				} catch (IOException e) {
				    e.printStackTrace();
				}
				exArray = tempArray.toArray(new String[tempArray.size()]);
				
				
				Intent nextScreen = new Intent(getActivity().getApplicationContext(), ManualWorkout.class);
				
				Bundle b=new Bundle();
				b.putStringArray("array", exArray);
				nextScreen.putExtras(b);
                
                //Start this activity - Replace this Screen with the new activity
                startActivity(nextScreen);
			}
 
		});
    	
    	trainerButton.setOnClickListener(new OnClickListener() {
 
			public void onClick(View arg0) {
				Uri uri = Uri.parse("http://nau.edu/Recreation-Services/Fitness/Personal-Training/");
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
			}
 
		});
    }
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            if (resultCode == -1) {
                String contents = intent.getStringExtra("SCAN_RESULT");
                String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
                // Handle successful scan
                //Start a new Intent - MainScreen
                Intent nextScreen = new Intent(getActivity().getApplicationContext(), WorkoutWindow.class);
                nextScreen.putExtra("workout", contents);
                //Start this activity - Replace this Screen with the new activity
                startActivity(nextScreen);
            } else if (resultCode == 0) {
                // Handle cancel
                /*Toast toast = Toast.makeText(getActivity().getApplicationContext(), "Scan was Cancelled!", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP, 25, 400);
                toast.show();*/
                
            }
        }
    }
}