package com.example.fitnessapp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

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