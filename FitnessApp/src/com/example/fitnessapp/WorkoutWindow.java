package com.example.fitnessapp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.Date;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;

public class WorkoutWindow extends Activity {
	
	Button saveButton;
	TextView sets;
	TextView reps;
	TextView weight;
	TextView exercise;
	EditText setsField;
	EditText repsField;
	EditText weightField;

	
	//Called when the activity is first created
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Sets the layout to use the correct file
        setContentView(R.layout.workout_window);
        
        Intent myIntent = getIntent();
        String firstKeyName = myIntent.getStringExtra("workout");
        initializeApp(firstKeyName);
    }
    
    public void initializeApp(final String machine) {
    	
    	//TODO - Load in the file name from the QR code and rename the labels and set up custom fields
    	
    	
    	
    	//Loads the logoButton and textviews from the layout file
    	saveButton = (Button) findViewById(R.id.button1);
    	sets = (TextView) findViewById(R.id.sets);
    	reps = (TextView) findViewById(R.id.reps);
    	weight = (TextView) findViewById(R.id.weight);
    	exercise = (TextView) findViewById(R.id.exercise);
    	setsField = (EditText) findViewById(R.id.setsField);
    	repsField = (EditText) findViewById(R.id.repsField);
    	weightField = (EditText) findViewById(R.id.weightField);
    	
    	if(machine.equalsIgnoreCase("Track Workout")) 
    	{
    		sets.setText("LAPS:");
    		reps.setText("TIME:");
    		exercise.setText("RUNNING");
    		weight.setEnabled(false);
    		weightField.setEnabled(false);
    		weight.setVisibility(4);
    		weightField.setVisibility(4);
    	}else 
    	{
    		sets.setText("SETS:");
    		reps.setText("REPS:");
    		exercise.setText(machine.toUpperCase());
    		weight.setEnabled(true);
    		weightField.setEnabled(true);
    		weight.setVisibility(0);
    		weightField.setVisibility(0);
    	}
    	
    	//Sets the listener for the button for when its clicked
    	saveButton.setOnClickListener(new OnClickListener() {
 
			public void onClick(View arg0) {
				Date date = new Date();
				String FILENAME = "exerciselog.txt";
				String newLog = "Date: " + (new Timestamp(date.getTime())) + ", WORKOUT: " + machine + " " + 
						sets.getText().toString() + " " + setsField.getText().toString() + " " + 
						reps.getText().toString() + " " + repsField.getText().toString() + "\n";
				
				try {
					FileOutputStream fos = openFileOutput(FILENAME, MODE_APPEND);
					fos.write(newLog.getBytes());
					fos.close();
					
					/*Toast toast = Toast.makeText(getApplicationContext(), "Saved Log.", Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.TOP, 25, 400);
					toast.show();*/
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
				    BufferedReader inputReader = new BufferedReader(new InputStreamReader(
				            openFileInput("exerciselog.txt")));
				    String inputString;
				    StringBuffer stringBuffer = new StringBuffer();                
				    while ((inputString = inputReader.readLine()) != null) {
				        stringBuffer.append(inputString + "\n");
				    }
				    /*Toast toast = Toast.makeText(getApplicationContext(), stringBuffer.toString(), Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.TOP, 25, 400);
					toast.show();*/
				} catch (IOException e) {
				    e.printStackTrace();
				}
	            
                finish();
			}
 
		});
    }
}
