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




import android.annotation.TargetApi;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class News_List extends ListActivity {
	
	private final String NEWS_DB = "newsDB";
	private final String NEWS_TABLE = "newsTable";
	private ArrayList<News> results;
	
    /** Called when the activity is first created. */
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

    	StrictMode.setThreadPolicy(policy); 
        results = new ArrayList<News>();
        SQLiteDatabase sampleDB = null;
       
        
        try {
        	sampleDB =  this.openOrCreateDatabase(NEWS_DB, MODE_PRIVATE, null);
        	
        	sampleDB.execSQL("CREATE TABLE IF NOT EXISTS " +
        			NEWS_TABLE +
        			" (post_id INTEGER PRIMARY KEY, title VARCHAR," +
        			" content VARCHAR, image VARCHAR, category VARCHAR);");

//        			" Values (7,'class8','info 8');");
        	
        	Cursor c = sampleDB.rawQuery("SELECT post_id, title, content, image, category FROM " +
        			NEWS_TABLE, null);
        	
        	 /////////
            String result = "";
           //the year data to send
           ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
           nameValuePairs.add(new BasicNameValuePair("action","news"));
                   InputStream is = null;

           //http post
           try{
                  HttpClient httpclient = new DefaultHttpClient();
                  HttpPost httppost = new HttpPost("http://www.cefns.nau.edu/~drk46/vpr-p/fitnessapp/android/controllers.php?action=posts");
                  httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                  HttpResponse response = httpclient.execute(httppost);
                  HttpEntity entity = response.getEntity();
                  is = entity.getContent();
           }catch(Exception e){
                  Log.e("log_tag", "Error in http connection "+e.toString());
           }
           //convert response to string
           try{
           BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
                  StringBuilder sb = new StringBuilder();
                  String line = null;
                  while ((line = reader.readLine()) != null) {
                          sb.append(line + "\n");
                  }
                  is.close();
            
                  result=sb.toString();
           }catch(Exception e){
                  Log.e("log_tag", "Error converting result "+e.toString());
           }
            
           //parse json data
           try{
         	  
           String data = null;
                  JSONArray jArray = new JSONArray(result);
                  for(int i=0;i<jArray.length();i++){
                          JSONObject json_data = jArray.getJSONObject(i);
                          
                          sampleDB.execSQL("INSERT INTO " +
                      			NEWS_TABLE +
                      			" Values ("+json_data.getString("post_id")+",'"+
                      			json_data.getString("title")+"','"+
                      			json_data.getString("content")+"','"+
                      			json_data.getString("image")+"','"+
                      			json_data.getString("category")+"');");
                        }


                  
           }catch(JSONException e){
                  Log.e("log_tag", "Error parsing data "+e.toString());
           }
        	
        	if (c != null ) {
        		if  (c.moveToFirst()) {
        			do {
        				int post_id = c.getInt(c.getColumnIndex("post_id")); 
        				String title = c.getString(c.getColumnIndex("title"));
        				String content = c.getString(c.getColumnIndex("content"));
        				String image = c.getString(c.getColumnIndex("image"));
        				String category = c.getString(c.getColumnIndex("category"));
        				
        				
        						
        				results.add(new News(post_id,title,content,image,category));
        			}while (c.moveToNext());
        		} 
        	}
        	
        	
        	
        	this.setListAdapter( new NewsListAdapter(this, R.layout.new_row_item, results ) );
        	
        } catch (SQLiteException se ) {
        	Log.e(getClass().getSimpleName(), "Could not create or Open the database");
        } finally {
        	if (sampleDB != null) 
        		sampleDB.execSQL("DELETE FROM " + NEWS_TABLE);
        		sampleDB.close();
        }
    }
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		
		String title =     results.get(position).getTitle();
		String content =   results.get(position).getContent();
		String image =     results.get(position).getImage();
		String category =  results.get(position).getCategory();

		Intent newActivity = new Intent(getApplicationContext(), NewsView.class);  
		newActivity.putExtra("title",  title);
		newActivity.putExtra("content",  content);
		newActivity.putExtra("image",  image);
		//newActivity.putExtra("category",  category);
        startActivity(newActivity);
		//Toast.makeText(this, selectedValue.toString(), Toast.LENGTH_SHORT).show();

	}

}