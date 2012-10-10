package com.example.fitnessapp;

import android.os.Bundle;
import android.app.ActionBar;
import android.util.Log;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;


public class MainScreen extends SherlockFragmentActivity implements com.actionbarsherlock.app.ActionBar.TabListener {

	
	//Called when the activity is first created
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //Sets the layout to use the correct file
        setContentView(R.layout.main_screen);
        
        //This line sets up the ActionBar to use the API's available for the Android Version
        com.actionbarsherlock.app.ActionBar actionbar = getSupportActionBar();
        
        //Sets the ActionBar to use Tabs instead of just a static bar
        actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
      	
        //Creates 3 tabs for the ActionBar (Schedule, Track, Connect) and sets their titles
        com.actionbarsherlock.app.ActionBar.Tab schedule = actionbar.newTab();
        schedule.setText("Schedule");
        com.actionbarsherlock.app.ActionBar.Tab track = actionbar.newTab();
        track.setText("Track");
        com.actionbarsherlock.app.ActionBar.Tab connect = actionbar.newTab();
        connect.setText("Connect");
  	
        //Sets the TabListener for each tab to handle when a user clicks it
        //The listener methods are implemented by this class further below
        schedule.setTabListener(this);
        track.setTabListener(this);
        connect.setTabListener(this);
        
        //Adds the tabs to the ActionBar
        actionbar.addTab(schedule);
  		actionbar.addTab(track);
  		actionbar.addTab(connect);
    }
    
    //TabListener method - When a tab is reselected
    public void onTabReselected(com.actionbarsherlock.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) 
    {
    	Log.d("SimpleActionBarTabsActivity","tab " 
                 + String.valueOf(tab.getPosition()) + " re-clicked");
	}

    //TabListener method - When a tab is newly selected
	public void onTabSelected(com.actionbarsherlock.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) 
	{
		//If the tab clicked is in position 0, switch to Schedule Activity
		if(tab.getPosition()==0)
		{
			Schedule frag = new Schedule();
			
			ft.replace(android.R.id.content, frag);
		}
		//If the tab clicked is in position 1, switch to Track Activity
		else if(tab.getPosition()==1)
		{
			Track frag = new Track();
			ft.replace(android.R.id.content, frag);
		}
		//If the tab clicked is in position 2, switch to Connect Activity
		else
		{
			Connect frag = new Connect();
			ft.replace(android.R.id.content, frag);
		}
		Log.d("SimpleActionBarTabsActivity","tab " 
                 + String.valueOf(tab.getPosition()) + " clicked");
	}

	//TabListener method - When a tab is unselected
	public void onTabUnselected(com.actionbarsherlock.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) 
	{
		Log.d("SimpleActionBarTabsActivity","tab " 
                 + String.valueOf(tab.getPosition()) + " un-clicked");
	}
    

	//Inflates the menu and loads the relevant layout file
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportMenuInflater().inflate(R.menu.main_screen, menu);
        return true;
    }
    

    
}