package com.example.fitnessapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Menu;

public class LogScrollView extends Activity {

	TableLayout tl;
	
	//Called when the activity is first created
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Sets the layout to use the correct file
        setContentView(R.layout.logscrollview);
        
        fillRows();
    }
    
    @SuppressWarnings("deprecation")
	public void fillRows()
    {
    	tl = (TableLayout) findViewById(R.id.BodyTable);
    	
    	
    	String FILENAME = "exerciselog.txt";
		try {
		    BufferedReader inputReader = new BufferedReader(new InputStreamReader(
		            openFileInput(FILENAME)));
		    String inputString;      
		    int count = 0;
		    while ((inputString = inputReader.readLine()) != null) {
		    	String date;
		    	String exercise;
		    	String sets;
		    	String reps;
		    	
		    	int indexStart = inputString.indexOf("Date:") + 6;
		    	int indexEnd = inputString.indexOf(", WORKOUT");
		    	date = inputString.substring(indexStart, indexEnd);
		    	
		    	indexStart = inputString.indexOf("WORKOUT:") + 9;
		    	indexEnd = inputString.indexOf(" LAPS");
		    	if(indexEnd == -1){
		    		indexEnd = inputString.indexOf(" SETS");
		    		exercise = inputString.substring(indexStart, indexEnd);
		    		
		    		indexStart = inputString.indexOf("SETS:") + 5;
			    	indexEnd = inputString.indexOf(" REPS");
			    	sets = inputString.substring(indexStart, indexEnd);
			    	
			    	indexStart = inputString.indexOf("REPS:") + 5;
			    	reps = inputString.substring(indexStart, inputString.length());
		    	}else {
		    		exercise = inputString.substring(indexStart, indexEnd);

		    		indexStart = inputString.indexOf("LAPS:") + 5;
			    	indexEnd = inputString.indexOf(" TIME");
			    	sets = inputString.substring(indexStart, indexEnd);
			    	
			    	indexStart = inputString.indexOf("TIME:") + 5;
			    	reps = inputString.substring(indexStart, inputString.length());
		    	}
		    	date = date.substring(0,10);
		    	String content = exercise + " " + sets + "sets X " + reps + "reps";
		    	tl.addView(this.tableChild(date,content));
		    }
		} catch (IOException e) {
		    e.printStackTrace();
		}
    }
    
    private View tableChild(String str1, String str2) {
    	TableRow tr = new TableRow(this);
        View v = LayoutInflater.from(this).inflate(R.layout.list_row, tr, false);
        //want to get childs of row for example TextView, get it like this:
        TextView date = (TextView)v.findViewById(R.id.title);
        date.setText(str1);
        TextView content = (TextView)v.findViewById(R.id.content);
        content.setText(str2);
        return v;//have to return View child, so return made 'v'
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
    	if(item.getItemId() == 5) {
    		String FILENAME = "exerciselog.txt";
    		String emailText = "";
    		try {
    		    BufferedReader inputReader = new BufferedReader(new InputStreamReader(
    		            openFileInput(FILENAME)));
    		    String inputString; 
    		    while ((inputString = inputReader.readLine()) != null) {
    		    	emailText += inputString;
    		    	emailText += "\r\n";
    		    }
    		} catch (IOException e) {
    		    e.printStackTrace();
    		}
    		Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
			emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Fitness Log");
			emailIntent.setType("text/plain");
			emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, emailText);
			startActivity(Intent.createChooser(emailIntent, "Send your email in:")); 
    	}else {
    		Toast toast = Toast.makeText(getApplicationContext(), "Coming Soon!" , Toast.LENGTH_LONG);
    		toast.setGravity(Gravity.TOP, 25, 400);
    		toast.show();
    	}
    	return super.onOptionsItemSelected(item);
    }
    
  //Inflates the menu and loads the relevant layout file
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_screen, menu);
        menu.add(1, 5, Menu.FIRST + 1, "Export");
        return true;
    }
}