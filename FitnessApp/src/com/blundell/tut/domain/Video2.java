package com.blundell.tut.domain;

import java.io.Serializable;

/**
 * Represents the videos off of youtube
 */
public class Video2 implements Serializable {
	// Video title
	private String title;
	// Video link
	private String url;
	// Video thumbnail
	private String thumbUrl;
	
	public Video2(String title, String url, String thumbUrl) {
		super();
		this.title = title;
		this.url = url;
		this.thumbUrl = thumbUrl;
	}

	//Return Title
	public String getTitle(){
		return title;
	}

	//Return Url
	public String getUrl() {
		return url;
	}
	
	public String getUrlID(){
		int index = url.indexOf("v=");
		String v = url.substring(index);
		return v;
	}

	//Return Thumbnail
	public String getThumbUrl() {
		return thumbUrl;
	}
}