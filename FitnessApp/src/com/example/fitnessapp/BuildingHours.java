package com.example.fitnessapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.Toast;
import android.view.Menu;

public class BuildingHours extends Activity {
	
	//Called when the activity is first created
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Sets the layout to use the correct file
        setContentView(R.layout.building_hours);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
    	Toast toast = Toast.makeText(getApplicationContext(), "Coming Soon!" , Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP, 25, 400);
        toast.show();
    	return super.onOptionsItemSelected(item);
    }
    
  //Inflates the menu and loads the relevant layout file
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_screen, menu);
        menu.add(1, 5, Menu.FIRST + 1, "Contact Us");
        return true;
    }
}