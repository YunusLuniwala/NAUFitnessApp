package com.example.fitnessapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
        LoadText(R.raw.workoutlog);
        
        initializeApp(firstKeyName);
    }

    public void LoadText(int resourceId) {
        // The InputStream opens the resourceId and sends it to the buffer
        InputStream is = this.getResources().openRawResource(resourceId);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String readLine = null;

        try {
            // While the BufferedReader readLine is not null 
            while ((readLine = br.readLine()) != null) {
            Toast toast = Toast.makeText(getApplicationContext(), readLine, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP, 25, 400);
            toast.show();
            Log.d("TEXT", readLine);
        }

        // Close the InputStream and BufferedReader
        is.close();
        br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void initializeApp(String machine) {
    	
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
    	
    	if(machine.equalsIgnoreCase("Hamstring Curls")) 
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
                finish();
			}
 
		});
    }
}
