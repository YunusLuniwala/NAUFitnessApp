package com.example.fitnessapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.widget.ImageButton;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.support.v4.app.NavUtils;

public class MainScreen extends Activity {

	ImageButton logoButton;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        Intent i = getIntent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_screen, menu);
        return true;
    }
    

    
}