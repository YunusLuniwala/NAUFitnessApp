package com.blundell.tut.domain;

import java.io.Serializable;
import java.util.List;

/**
 * The library or playlist of the user
 */
public class Library implements Serializable{
	// The username
	private String user;
	// The user's list of videos
	private List<Video2> videos;
	
	public Library(String user, List<Video2> videos) {
		this.user = user;
		this.videos = videos;
	}

	//Return Username
	public String getUser() {
		return user;
	}

	//Return the list of videos 
	public List<Video2> getVideos() {
		return videos;
	}
}