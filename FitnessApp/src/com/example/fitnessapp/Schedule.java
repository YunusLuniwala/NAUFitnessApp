package com.example.fitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.actionbarsherlock.app.SherlockFragment;
 
public class Schedule extends SherlockFragment {
	
	Button newsfeed;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setContentView(R.layout.schedule);
        
        loadStuff();
    }
    
    public void loadStuff() {
    	newsfeed = (Button) getActivity().findViewById(R.id.newsButton);
    	newsfeed.setOnClickListener(new OnClickListener() {
   		 
			public void onClick(View arg0) {
				Intent nextScreen = new Intent(getActivity().getApplicationContext(), News_List.class);
                
                //Start this activity - Replace this Screen with the new activity
                startActivity(nextScreen);
			}
 
		});
    }

}