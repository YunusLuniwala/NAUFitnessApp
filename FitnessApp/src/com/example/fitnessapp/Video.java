package com.example.fitnessapp;


import com.actionbarsherlock.app.SherlockFragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.blundell.tut.domain.Library;
import com.blundell.tut.domain.Video2;
import com.blundell.tut.service.task.GetYouTubeUserVideosTask;
import com.blundell.tut.ui.VideoClickListener;
import com.blundell.tut.ui.widget.VideosListView;

 
public class Video extends SherlockFragment implements VideoClickListener {
	// A reference to our list that will hold the video details
		private VideosListView listView;

	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        getActivity().setContentView(R.layout.video);
	        
	        listView = (VideosListView) getActivity().findViewById(R.id.videosListView);
	        // Here we are adding this activity as a listener for when any row in the List is 'clicked'
	        // The activity will be sent back the video that has been pressed to do whatever it wants with
	        // in this case we will retrieve the URL of the video and fire off an intent to view it
	        listView.setOnVideoClickListener(this);
	        new Thread(new GetYouTubeUserVideosTask(responseHandler, "NAZToday")).start();
	    }

	    public void getUserYouTubeFeed(View v){
	    	new Thread(new GetYouTubeUserVideosTask(responseHandler, "NAZToday")).start();
	    }
	   
		Handler responseHandler = new Handler() {
			public void handleMessage(Message msg) {
				populateListWithVideos(msg);
			};
		};

		private void populateListWithVideos(Message msg) {
			Library lib = (Library) msg.getData().get(GetYouTubeUserVideosTask.LIBRARY);
			listView.setVideos(lib.getVideos());
		}
		
		@Override
		public void onStop() {
			responseHandler = null;
			super.onStop();
		}

		// This is the interface method that is called when a video in the listview is clicked!
		public void onVideoClicked(Video2 video) {
			Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.setData(Uri.parse(video.getUrl()));
			startActivity(intent);
		}
	}