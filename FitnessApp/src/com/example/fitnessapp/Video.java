package com.example.fitnessapp;


import com.actionbarsherlock.app.SherlockFragment;

import android.os.Bundle;
import android.widget.Button;

 
public class Video extends SherlockFragment {
	Button scanButton;
	Button readButton;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Sets the layout to use the correct file
        getActivity().setContentView(R.layout.video);
        
        initializeApp();
        
    }
	
	public void initializeApp()
    {
		
    }
}