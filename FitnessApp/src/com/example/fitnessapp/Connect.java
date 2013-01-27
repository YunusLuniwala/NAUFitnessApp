package com.example.fitnessapp;

import com.actionbarsherlock.app.SherlockFragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
 
public class Connect extends SherlockFragment {
	
	Button classButton;
	Button buildingButton;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Sets the layout to use the correct file
        getActivity().setContentView(R.layout.connect);
        
        initializeApp();
        
    }
	
	public void initializeApp()
    {
    	//Loads the logoButton from the layout file
    	classButton = (Button) getActivity().findViewById(R.id.classButton);
    	buildingButton = (Button) getActivity().findViewById(R.id.buildingButton);
    	
    	//Sets the listener for the button for when its clicked
    	classButton.setOnClickListener(new OnClickListener() {
 
			public void onClick(View arg0) {
				//Start a new Intent - Scanner
				//http://www.youtube.com/watch?v=FT0DpLkQYMU&feature=g-logo
				//startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=cxLG2wtE7TM")));
				Uri uri = Uri.parse("http://www.peerfit.com/facility.php?groups_id=153");
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
			}
 
		});
    	
    	buildingButton.setOnClickListener(new OnClickListener() {
      		 
   			public void onClick(View arg0) {
   				Intent nextScreen = new Intent(getActivity().getApplicationContext(), BuildingHours.class);
                   
                   //Start this activity - Replace this Screen with the new activity
                   startActivity(nextScreen);
   			}
    
   		});
    }
}