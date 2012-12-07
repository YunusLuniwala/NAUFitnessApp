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
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
 
public class Schedule extends SherlockFragment {
	private final String NEWS_DB = "newsDB1";
	private final String NEWS_TABLE = "newsTable1";
	private ArrayList<News> results;
	private ListView lv;
	static android.content.ContextWrapper db;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setContentView(R.layout.schedule);
        lv = (ListView) getActivity().findViewById(R.id.news_list);
        results = new ArrayList<News>();
        SQLiteDatabase sampleDB = null;
       
        
        try {
        	sampleDB =  SQLiteDatabase.openOrCreateDatabase(NEWS_DB, null);
        	
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
                  HttpPost httppost = new HttpPost("http://www.cefns.nau.edu/~drk46/fitnessapp/android/controllers.php?action=posts");
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
        	
        	
        	Toast.makeText(getActivity().getApplicationContext(), "" + results.size(), Toast.LENGTH_SHORT).show();
        	lv.setAdapter( new NewsListAdapter(getActivity().getApplicationContext(), R.layout.new_row_item, results ) );
        	
        } catch (SQLiteException se ) {
        	Log.e(getClass().getSimpleName(), "Could not create or Open the database " + se );
        } finally {
        	if (sampleDB != null) 
        		sampleDB.execSQL("DELETE FROM " + NEWS_TABLE);
        		//sampleDB.close();
        }
    }
	protected void onListItemClick(ListView l, View v, int position, long id) {

		
		String title =     results.get(position).getTitle();
		String content =   results.get(position).getContent();
		String image =     results.get(position).getImage();
		String category =  results.get(position).getCategory();

		Intent newActivity = new Intent(getActivity().getApplicationContext(), NewsView.class);  
		newActivity.putExtra("title",  title);
		newActivity.putExtra("content",  content);
		newActivity.putExtra("image",  image);
		//newActivity.putExtra("category",  category);
        startActivity(newActivity);
		//Toast.makeText(this, selectedValue.toString(), Toast.LENGTH_SHORT).show();

	}

}