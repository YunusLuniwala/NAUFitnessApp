package com.example.fitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

import com.actionbarsherlock.app.SherlockFragment;
 
public class Schedule extends SherlockFragment {
	
	ImageButton newsfeed;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setContentView(R.layout.schedule);
        
        loadStuff();
    }
    
    public void loadStuff() {
    	newsfeed = (ImageButton) getActivity().findViewById(R.id.refreshNews);
    	newsfeed.setOnClickListener(new OnClickListener() {
   		 
			public void onClick(View arg0) {
				Intent nextScreen = new Intent(getActivity().getApplicationContext(), News_List.class);
                
                //Start this activity - Replace this Screen with the new activity
                startActivity(nextScreen);
			}
 
		});
    }

}