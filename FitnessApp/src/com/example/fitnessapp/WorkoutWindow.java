package com.example.fitnessapp;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;

public class WorkoutWindow extends Activity implements OnClickListener{
	
	static final int CUSTOM_DIALOG_ID = 0;
	int setAmount = 0;
	int lastIndex = 0;
	String expreview = "";
	String conpreview = "";
	Button saveButton;
	TextView sets;
	TextView reps;
	TextView weight;
	TextView exercise;
	EditText setsField;
	EditText repsField;
	EditText weightField;
	String[] exArray;
	String diffReps = "";
	int[] weightArray;
	int[] repsArray;
	TextView alert;
	boolean same = true;
	ToggleButton sameWR,diffWR;
	
	TextView customDialog_TextView,customDialog_TextView2,preview_Exercise,preview_Content;
	EditText customDialog_EditText,customDialog_EditText2;
	Button customDialog_Dismiss;
	ToggleButton set1,set2,set3,set4,set5,set6,set7,set8,set9,set10,set11,set12;

	
	//Called when the activity is first created
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Sets the layout to use the correct file
        setContentView(R.layout.manual_workout);
        Intent myIntent = getIntent();
        String firstKeyName = myIntent.getStringExtra("workout");
        initializeApp(firstKeyName);
    }
    
    public void initializeApp(String machine) {
    	//Loads the logoButton and textviews from the layout file
    	saveButton = (Button) findViewById(R.id.button1);
    	sets = (TextView) findViewById(R.id.sets);
    	reps = (TextView) findViewById(R.id.reps);
    	weight = (TextView) findViewById(R.id.weight);
    	repsField = (EditText) findViewById(R.id.repsField);
    	weightField = (EditText) findViewById(R.id.weightField);
    	alert = (TextView) findViewById(R.id.setsAlert);
    	exercise = (TextView) findViewById(R.id.exercise);
    	setsField = (EditText) findViewById(R.id.setsField);
    	sameWR = (ToggleButton) findViewById(R.id.sameWR);
    	diffWR = (ToggleButton) findViewById(R.id.diffWR);
    	
    	sets.setText("SETS:");
    	reps.setText("REPS:");
    	weight.setEnabled(true);
    	weightField.setEnabled(true);
    	
    	sameWR.setOnClickListener(sameWR_Listener);
    	diffWR.setOnClickListener(diffWR_Listener);
    	
    	exercise.setText(machine.toUpperCase());
    	
    	setsField.setOnEditorActionListener(new OnEditorActionListener() {
			public boolean onEditorAction(TextView v, int actionId,
					KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_DONE) {
					setAmount = Integer.valueOf(setsField.getText().toString()); 
					weightArray = new int[setAmount];
					repsArray = new int[setAmount];
					for(int i = 0; i < setAmount; i++){
						weightArray[i] = -1;
						repsArray[i] = 1;
					}
    	            if(setAmount > 1){
    	            	sameWR.setVisibility(ToggleButton.VISIBLE);
    	            	diffWR.setVisibility(ToggleButton.VISIBLE);
    	            	alert.setVisibility(TextView.VISIBLE);
    	            }else{
    	            	sameWR.setVisibility(ToggleButton.VISIBLE);
    	            	diffWR.setVisibility(ToggleButton.VISIBLE);
    	            	alert.setVisibility(TextView.INVISIBLE);
    	            }
    	            return false;
    	        }
    	        else {
    	            return false;
    	        }
			}
    	});
    	
    	//Sets the listener for the button for when its clicked
    	saveButton.setOnClickListener(new OnClickListener() {
 
			public void onClick(View arg0) {
				Date date = new Date();
				String FILENAME = "exerciselog.txt";
				String newLog = "";
				if(same){
					newLog = "Date: " + (new Timestamp(date.getTime())) + ", WORKOUT: " + exercise.getText().toString() + " " + 
							sets.getText().toString() + " " + setsField.getText().toString() + " " + 
							reps.getText().toString() + " " + weightField.getText() + "x" +  repsField.getText().toString() + "\n";
				}else {
					Toast toast = Toast.makeText(getApplicationContext(), "Saved Log.", Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.TOP, 25, 400);
					toast.show();
					newLog = "Date: " + (new Timestamp(date.getTime())) + ", WORKOUT: " + exercise.getText().toString() + " " + 
							sets.getText().toString() + " " + setsField.getText().toString() + " " + 
							reps.getText().toString();
					for(int i = 1; i < weightArray.length; i++){
						newLog += " " + weightArray[i] + "x" + repsArray[i];
					}
					
					newLog += "\n";
				}
				
				try {
					FileOutputStream fos = openFileOutput(FILENAME, MODE_APPEND);
					fos.write(newLog.getBytes());
					fos.close();
					
					/*Toast toast = Toast.makeText(getApplicationContext(), "Saved Log.", Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.TOP, 25, 400);
					toast.show();*/
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
				    BufferedReader inputReader = new BufferedReader(new InputStreamReader(
				            openFileInput("exerciselog.txt")));
				    String inputString;
				    StringBuffer stringBuffer = new StringBuffer();                
				    while ((inputString = inputReader.readLine()) != null) {
				        stringBuffer.append(inputString + "\n");
				    }
				    /*Toast toast = Toast.makeText(getApplicationContext(), stringBuffer.toString(), Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.TOP, 25, 400);
					toast.show();*/
				} catch (IOException e) {
				    e.printStackTrace();
				}
	            
                finish();
			}
 
		});
    }
    
    private ToggleButton.OnClickListener sameWR_Listener
    = new ToggleButton.OnClickListener(){

		public void onClick(View v) {
			if(setsField.getText().toString().length() > 0){
				setAmount = Integer.valueOf(setsField.getText().toString()); 
				diffWR.setChecked(false);
				sameWR.setChecked(true);
		        weight.setVisibility(TextView.VISIBLE);
		        weightField.setVisibility(EditText.VISIBLE);
		        reps.setVisibility(TextView.VISIBLE);
		        repsField.setVisibility(EditText.VISIBLE);
				same = true;
			}
		}
    };
    
    private ToggleButton.OnClickListener diffWR_Listener
    = new ToggleButton.OnClickListener(){

		public void onClick(View v) {
			if(setsField.getText().toString().length() > 0){
				setAmount = Integer.valueOf(setsField.getText().toString());
				sameWR.setChecked(false);
				diffWR.setChecked(true);
		        weight.setVisibility(TextView.INVISIBLE);
		        weightField.setVisibility(EditText.INVISIBLE);
		        reps.setVisibility(TextView.INVISIBLE);
		        repsField.setVisibility(EditText.INVISIBLE);
				same = false;
				showDialog(CUSTOM_DIALOG_ID);
			}
		}
    };
    
    private Button.OnClickListener customDialog_DismissOnClickListener
    = new Button.OnClickListener(){
  
    	public void onClick(View arg0) {
    		set1.setChecked(false);
    		set2.setChecked(false);
    		set3.setChecked(false);
    		set4.setChecked(false);
    		set5.setChecked(false);
    		set6.setChecked(false);
    		set7.setChecked(false);
    		set8.setChecked(false);
    		set9.setChecked(false);
    		set10.setChecked(false);
    		set11.setChecked(false);
    		set12.setChecked(false);
    		if(customDialog_EditText.getText().toString().length()>0){
    			weightArray[lastIndex] = Integer.valueOf(customDialog_EditText.getText().toString());
    		}else{
    			weightArray[lastIndex] = 0;
    		}
    		if(customDialog_EditText2.getText().toString().length()>0){
    			repsArray[lastIndex] = Integer.valueOf(customDialog_EditText2.getText().toString());
    		}else{
    			repsArray[lastIndex] = 1;
    		}
    		for(int i = (1+lastIndex); i<weightArray.length; i++){
    			if(weightArray[i] < 0){
    				weightArray[i] = weightArray[lastIndex];
    				repsArray[i] = repsArray[lastIndex];
    			}
    		}
    		dismissDialog(CUSTOM_DIALOG_ID);
    	}
     
    };
    
    @Override
    protected Dialog onCreateDialog(int id) {
     // TODO Auto-generated method stub
     Dialog dialog = null;;
        switch(id) {
        case CUSTOM_DIALOG_ID:
         dialog = new Dialog(WorkoutWindow.this);
     
         dialog.setContentView(R.layout.custom_dialog);
         dialog.setTitle("Custom Dialog");
         
         customDialog_EditText = (EditText)dialog.findViewById(R.id.dialogedittext);
         customDialog_TextView = (TextView)dialog.findViewById(R.id.dialogtextview);
         customDialog_EditText2 = (EditText)dialog.findViewById(R.id.dialogedittext2);
         customDialog_TextView2 = (TextView)dialog.findViewById(R.id.dialogtextview2);
         customDialog_Dismiss = (Button)dialog.findViewById(R.id.dialogdismiss);
         preview_Exercise = (TextView)dialog.findViewById(R.id.exercisepreview);
         preview_Content = (TextView)dialog.findViewById(R.id.contentpreview);
         set1 = (ToggleButton)dialog.findViewById(R.id.button1);
         set2 = (ToggleButton)dialog.findViewById(R.id.button2);
         set3 = (ToggleButton)dialog.findViewById(R.id.button3);
         set4 = (ToggleButton)dialog.findViewById(R.id.button4);
         set5 = (ToggleButton)dialog.findViewById(R.id.button5);
         set6 = (ToggleButton)dialog.findViewById(R.id.button6);
         set7 = (ToggleButton)dialog.findViewById(R.id.button7);
         set8 = (ToggleButton)dialog.findViewById(R.id.button8);
         set9 = (ToggleButton)dialog.findViewById(R.id.button9);
         set10 = (ToggleButton)dialog.findViewById(R.id.button10);
         set11 = (ToggleButton)dialog.findViewById(R.id.button11);
         set12 = (ToggleButton)dialog.findViewById(R.id.button12);
         
         //customDialog_Next.setOnClickListener(customDialog_NextOnClickListener);
         //customDialog_Previous.setOnClickListener(customDialog_PreviousOnClickListener);
         customDialog_Dismiss.setOnClickListener(customDialog_DismissOnClickListener);
         set1.setOnClickListener(this);
         set2.setOnClickListener(this);
         set3.setOnClickListener(this);
         set4.setOnClickListener(this);
         set5.setOnClickListener(this);
         set6.setOnClickListener(this);
         set7.setOnClickListener(this);
         set8.setOnClickListener(this);
         set9.setOnClickListener(this);
         set10.setOnClickListener(this);
         set11.setOnClickListener(this);
         set12.setOnClickListener(this);
         
         if(exercise.getText().toString().length() > 0){
        	 expreview = exercise.getText().toString();
         }else {
        	 expreview = "Exercise";
         }
         String content = "";
         for(int i = 0; i < weightArray.length; i++){
 			content += " " + weightArray[i] + "x" + repsArray[i];
 		 }
         conpreview = setAmount + " sets -" + content;
         preview_Exercise.setText(expreview);
         preview_Content.setText(conpreview);
         
         LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
  			    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
         LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
   			    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
         LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(
   			    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
         LinearLayout.LayoutParams params4 = new LinearLayout.LayoutParams(
    			    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
         switch(setAmount){
         	case 1:
         		params.weight = 3.0f;
         		set1.setLayoutParams(params);
         		break;
         	case 2:
         		params.weight = 1.5f;
         		set1.setLayoutParams(params);
         		set2.setLayoutParams(params);
         		break;
         	case 3:
         		params.weight = 1.0f;
         		set1.setLayoutParams(params);
         		set2.setLayoutParams(params);
         		set3.setLayoutParams(params);
         		break;
         	case 4:
         		params.weight = 1.0f;
         		set1.setLayoutParams(params);
         		set2.setLayoutParams(params);
         		set3.setLayoutParams(params);
         		params2.weight =3.0f;
         		set4.setLayoutParams(params2);
         		break;
         	case 5:
         		params.weight = 1.0f;
         		set1.setLayoutParams(params);
         		set2.setLayoutParams(params);
         		set3.setLayoutParams(params);
         		params2.weight = 1.5f;
         		set4.setLayoutParams(params2);
         		set5.setLayoutParams(params2);
         		break;
         	case 6:
         		params.weight = 1.0f;
         		set1.setLayoutParams(params);
         		set2.setLayoutParams(params);
         		set3.setLayoutParams(params);
         		params2.weight = 1.0f;
         		set4.setLayoutParams(params2);
         		set5.setLayoutParams(params2);
         		set6.setLayoutParams(params2);
         		break;
         	case 7:
         		params.weight = 1.0f;
         		set1.setLayoutParams(params);
         		set2.setLayoutParams(params);
         		set3.setLayoutParams(params);
         		params2.weight = 1.0f;
         		set4.setLayoutParams(params2);
         		set5.setLayoutParams(params2);
         		set6.setLayoutParams(params2);
         		params3.weight = 3.0f;
         		set7.setLayoutParams(params3);
         		break;
         	case 8:
         		params.weight = 1.0f;
         		set1.setLayoutParams(params);
         		set2.setLayoutParams(params);
         		set3.setLayoutParams(params);
         		params2.weight = 1.0f;
         		set4.setLayoutParams(params2);
         		set5.setLayoutParams(params2);
         		set6.setLayoutParams(params2);
         		params3.weight = 1.5f;
         		set7.setLayoutParams(params3);
         		set8.setLayoutParams(params3);
         		break;
         	case 9:
         		params.weight = 1.0f;
         		set1.setLayoutParams(params);
         		set2.setLayoutParams(params);
         		set3.setLayoutParams(params);
         		params2.weight = 1.0f;
         		set4.setLayoutParams(params2);
         		set5.setLayoutParams(params2);
         		set6.setLayoutParams(params2);
         		params3.weight = 1.0f;
         		set7.setLayoutParams(params3);
         		set8.setLayoutParams(params3);
         		set9.setLayoutParams(params3);
         		break;
         	case 10:
         		params.weight = 1.0f;
         		set1.setLayoutParams(params);
         		set2.setLayoutParams(params);
         		set3.setLayoutParams(params);
         		params2.weight = 1.0f;
         		set4.setLayoutParams(params2);
         		set5.setLayoutParams(params2);
         		set6.setLayoutParams(params2);
         		params3.weight = 1.0f;
         		set7.setLayoutParams(params3);
         		set8.setLayoutParams(params3);
         		set9.setLayoutParams(params3);
         		params4.weight = 3.0f;
         		set10.setLayoutParams(params4);
         		break;
         	case 11:
         		params.weight = 1.0f;
         		set1.setLayoutParams(params);
         		set2.setLayoutParams(params);
         		set3.setLayoutParams(params);
         		params2.weight = 1.0f;
         		set4.setLayoutParams(params2);
         		set5.setLayoutParams(params2);
         		set6.setLayoutParams(params2);
         		params3.weight = 1.0f;
         		set7.setLayoutParams(params3);
         		set8.setLayoutParams(params3);
         		set9.setLayoutParams(params3);
         		params4.weight = 1.5f;
         		set10.setLayoutParams(params4);
         		set11.setLayoutParams(params4);
         		break;
         	case 12:
         		params.weight = 1.0f;
         		set1.setLayoutParams(params);
         		set2.setLayoutParams(params);
         		set3.setLayoutParams(params);
         		params2.weight = 1.0f;
         		set4.setLayoutParams(params2);
         		set5.setLayoutParams(params2);
         		set6.setLayoutParams(params2);
         		params3.weight = 1.0f;
         		set7.setLayoutParams(params3);
         		set8.setLayoutParams(params3);
         		set9.setLayoutParams(params3);
         		params4.weight = 1.0f;
         		set10.setLayoutParams(params4);
         		set11.setLayoutParams(params4);
         		set12.setLayoutParams(params4);
         		break;
         }
         
         if(setAmount > 2) set3.setVisibility(ToggleButton.VISIBLE);
         if(setAmount > 3) set4.setVisibility(ToggleButton.VISIBLE);
         if(setAmount > 4) set5.setVisibility(ToggleButton.VISIBLE);
         if(setAmount > 5) set6.setVisibility(ToggleButton.VISIBLE);
         if(setAmount > 6) set7.setVisibility(ToggleButton.VISIBLE);
         if(setAmount > 7) set8.setVisibility(ToggleButton.VISIBLE);
         if(setAmount > 8) set9.setVisibility(ToggleButton.VISIBLE);
         if(setAmount > 9) set10.setVisibility(ToggleButton.VISIBLE);
         if(setAmount > 10) set11.setVisibility(ToggleButton.VISIBLE);
         if(setAmount > 11) set12.setVisibility(ToggleButton.VISIBLE);
         
         
         customDialog_EditText.setOnEditorActionListener(new OnEditorActionListener() {
 			public boolean onEditorAction(TextView v, int actionId,
 					KeyEvent event) {
 				if (actionId == EditorInfo.IME_ACTION_NEXT) {
 					customDialog_EditText2.requestFocus();
 					customDialog_EditText2.selectAll();
     	            return false;
     	        }else if (actionId == EditorInfo.IME_ACTION_DONE) {
     	        	if(exercise.getText().toString().length() > 0){
     	        		expreview = exercise.getText().toString();
     	        	}else {
     	        		expreview = "Exercise";
     	        	}
     	        	String content = "";
     	        	for(int i = 0; i < weightArray.length; i++){
     	        		content += " " + weightArray[i] + "x" + repsArray[i];
     	        	}
     	        	conpreview = setAmount + " sets -" + content;
     	        	preview_Exercise.setText(expreview);
     	        	preview_Content.setText(conpreview);
     	        	set1.setChecked(false);
     	        	set2.setChecked(false);
     	        	set3.setChecked(false);
     	        	set4.setChecked(false);
     	        	set5.setChecked(false);
     	        	set6.setChecked(false);
     	        	set7.setChecked(false);
     	        	set8.setChecked(false);
     	        	set9.setChecked(false);
     	        	set10.setChecked(false);
     	        	set11.setChecked(false);
     	        	set12.setChecked(false);
     	        	if(customDialog_EditText.getText().toString().length()>0){
     	        		weightArray[lastIndex] = Integer.valueOf(customDialog_EditText.getText().toString());
     	        	}else{
     	        		weightArray[lastIndex] = 0;
     	        	}
     	        	if(customDialog_EditText2.getText().toString().length()>0){
     	        		repsArray[lastIndex] = Integer.valueOf(customDialog_EditText2.getText().toString());
     	        	}else{
     	        		repsArray[lastIndex] = 1;
     	        	}
     	        	for(int i = (1+lastIndex); i<weightArray.length; i++){
     	        		if(weightArray[i] < 0){
     	        			weightArray[i] = weightArray[lastIndex];
     	        			repsArray[i] = repsArray[lastIndex];
     	        		}
     	        	}
     	        	switch(lastIndex) {
     				case 0:
     					set1.setChecked(true);
     					lastIndex = 1;
     					customDialog_EditText.setText("" + weightArray[lastIndex]);
     					customDialog_EditText2.setText("" + repsArray[lastIndex]);
     					break;
     				case 1:
     					set2.setChecked(true);
     					lastIndex = 2;
     					customDialog_EditText.setText("" + weightArray[lastIndex]);
     					customDialog_EditText2.setText("" + repsArray[lastIndex]);
     					break;
     				case 2:
     					set3.setChecked(true);
     					lastIndex = 3;
     					customDialog_EditText.setText("" + weightArray[lastIndex]);
     					customDialog_EditText2.setText("" + repsArray[lastIndex]);
     					break;
     				case 3:
     					set4.setChecked(true);
     					lastIndex = 4;
     					customDialog_EditText.setText("" + weightArray[lastIndex]);
     					customDialog_EditText2.setText("" + repsArray[lastIndex]);
     					break;
     				case 4:
     					set5.setChecked(true);
     					lastIndex = 5;
     					customDialog_EditText.setText("" + weightArray[lastIndex]);
     					customDialog_EditText2.setText("" + repsArray[lastIndex]);
     					break;
     				case 5:
     					set6.setChecked(true);
     					lastIndex = 6;
     					customDialog_EditText.setText("" + weightArray[lastIndex]);
     					customDialog_EditText2.setText("" + repsArray[lastIndex]);
     					break;
     				case 6:
     					set7.setChecked(true);
     					lastIndex = 7;
     					customDialog_EditText.setText("" + weightArray[lastIndex]);
     					customDialog_EditText2.setText("" + repsArray[lastIndex]);
     					break;
     				case 7:
     					set8.setChecked(true);
     					lastIndex = 8;
     					customDialog_EditText.setText("" + weightArray[lastIndex]);
     					customDialog_EditText2.setText("" + repsArray[lastIndex]);
     					break;
     				case 8:
     					set9.setChecked(true);
     					lastIndex = 9;
     					customDialog_EditText.setText("" + weightArray[lastIndex]);
     					customDialog_EditText2.setText("" + repsArray[lastIndex]);
     					break;
     				case 9:
     					set10.setChecked(true);
     					lastIndex = 10;
     					customDialog_EditText.setText("" + weightArray[lastIndex]);
     					customDialog_EditText2.setText("" + repsArray[lastIndex]);
     					break;
     				case 10:
     					set11.setChecked(true);
     					lastIndex = 11;
     					customDialog_EditText.setText("" + weightArray[lastIndex]);
     					customDialog_EditText2.setText("" + repsArray[lastIndex]);
     					break;
     				case 11:
     					set12.setChecked(true);
     					lastIndex = 11;
     					customDialog_EditText.setText("" + weightArray[lastIndex]);
     					customDialog_EditText2.setText("" + repsArray[lastIndex]);
     					dismissDialog(CUSTOM_DIALOG_ID);
     					break;
     	        	}
     	        	customDialog_EditText.requestFocus();
     	        	customDialog_EditText.selectAll();
     	            return false;
     	        }else {
     	        	return false;
     	        }
 			}
     	});
         
         
            break;
        }
        return dialog;
    } 

	public void onClick(View v) {
		// TODO Auto-generated method stub
		set1.setChecked(false);
		set2.setChecked(false);
		set3.setChecked(false);
		set4.setChecked(false);
		set5.setChecked(false);
		set6.setChecked(false);
		set7.setChecked(false);
		set8.setChecked(false);
		set9.setChecked(false);
		set10.setChecked(false);
		set11.setChecked(false);
		set12.setChecked(false);
		if(exercise.getText().toString().length() > 0){
       	 expreview = exercise.getText().toString();
        }else {
       	 expreview = "Exercise";
        }
        String content = "";
        for(int i = 0; i < weightArray.length; i++){
			content += " " + weightArray[i] + "x" + repsArray[i];
		 }
        conpreview = setAmount + " sets -" + content;
        preview_Exercise.setText(expreview);
        preview_Content.setText(conpreview);
		
		if(customDialog_EditText.getText().toString().length()>0){
			weightArray[lastIndex] = Integer.valueOf(customDialog_EditText.getText().toString());
		}else{
			weightArray[lastIndex] = 0;
		}
		if(customDialog_EditText2.getText().toString().length()>0){
			repsArray[lastIndex] = Integer.valueOf(customDialog_EditText2.getText().toString());
		}else{
			repsArray[lastIndex] = 1;
		}
		for(int i = (1+lastIndex); i<weightArray.length; i++){
			if(weightArray[i] < 0){
				weightArray[i] = weightArray[lastIndex];
				repsArray[i] = repsArray[lastIndex];
			}
		}
		switch(v.getId()) {
			case R.id.button1:
				set1.setChecked(true);
				lastIndex = 0;
				customDialog_EditText.setText("" + weightArray[lastIndex]);
				customDialog_EditText2.setText("" + repsArray[lastIndex]);
				break;
			case R.id.button2:
				set2.setChecked(true);
				lastIndex = 1;
				customDialog_EditText.setText("" + weightArray[lastIndex]);
				customDialog_EditText2.setText("" + repsArray[lastIndex]);
				break;
			case R.id.button3:
				set3.setChecked(true);
				lastIndex = 2;
				customDialog_EditText.setText("" + weightArray[lastIndex]);
				customDialog_EditText2.setText("" + repsArray[lastIndex]);
				break;
			case R.id.button4:
				set4.setChecked(true);
				lastIndex = 3;
				customDialog_EditText.setText("" + weightArray[lastIndex]);
				customDialog_EditText2.setText("" + repsArray[lastIndex]);
				break;
			case R.id.button5:
				set5.setChecked(true);
				lastIndex = 4;
				customDialog_EditText.setText("" + weightArray[lastIndex]);
				customDialog_EditText2.setText("" + repsArray[lastIndex]);
				break;
			case R.id.button6:
				set6.setChecked(true);
				lastIndex = 5;
				customDialog_EditText.setText("" + weightArray[lastIndex]);
				customDialog_EditText2.setText("" + repsArray[lastIndex]);
				break;
			case R.id.button7:
				set7.setChecked(true);
				lastIndex = 6;
				customDialog_EditText.setText("" + weightArray[lastIndex]);
				customDialog_EditText2.setText("" + repsArray[lastIndex]);
				break;
			case R.id.button8:
				set8.setChecked(true);
				lastIndex = 7;
				customDialog_EditText.setText("" + weightArray[lastIndex]);
				customDialog_EditText2.setText("" + repsArray[lastIndex]);
				break;
			case R.id.button9:
				set9.setChecked(true);
				lastIndex = 8;
				customDialog_EditText.setText("" + weightArray[lastIndex]);
				customDialog_EditText2.setText("" + repsArray[lastIndex]);
				break;
			case R.id.button10:
				set10.setChecked(true);
				lastIndex = 9;
				customDialog_EditText.setText("" + weightArray[lastIndex]);
				customDialog_EditText2.setText("" + repsArray[lastIndex]);
				break;
			case R.id.button11:
				set11.setChecked(true);
				lastIndex = 10;
				customDialog_EditText.setText("" + weightArray[lastIndex]);
				customDialog_EditText2.setText("" + repsArray[lastIndex]);
				break;
			case R.id.button12:
				set12.setChecked(true);
				lastIndex = 11;
				customDialog_EditText.setText("" + weightArray[lastIndex]);
				customDialog_EditText2.setText("" + repsArray[lastIndex]);
				break;
		}
		customDialog_EditText.requestFocus();
		customDialog_EditText.selectAll();
	}
}