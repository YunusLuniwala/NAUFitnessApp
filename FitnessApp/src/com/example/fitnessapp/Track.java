package com.example.fitnessapp;

import com.actionbarsherlock.app.SherlockFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import android.app.Activity;
import android.content.ActivityNotFoundException;

 
public class Track extends SherlockFragment {
	Button scanButton;
	
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
    	scanButton = (Button) getActivity().findViewById(R.id.button1);
    	
    	//Sets the listener for the button for when its clicked
    	scanButton.setOnClickListener(new OnClickListener() {
 
			public void onClick(View arg0) {
				//Start a new Intent - Scanner
				Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
                startActivityForResult(intent, 0);
			}
 
		});
    }
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            if (resultCode == -1) {
                String contents = intent.getStringExtra("SCAN_RESULT");
                String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
                // Handle successful scan
                Toast toast = Toast.makeText(getActivity().getApplicationContext(), "Content:" + contents + " Format:" + format , Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP, 25, 400);
                toast.show();
            } else if (resultCode == 0) {
                // Handle cancel
                Toast toast = Toast.makeText(getActivity().getApplicationContext(), "Scan was Cancelled!", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP, 25, 400);
                toast.show();
                
            }
        }
    }
}