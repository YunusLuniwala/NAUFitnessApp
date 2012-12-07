package com.example.fitnessapp;



public class News {

	public News(int post_id, String title, String content, String image, String category) {
		super();
		this.post_id = post_id;
		this.title=title;
		this.content=content;
		this.image=image;
		this.category=category;
	}

		
	private int post_id;
	private String title;
	private String content;
	private String image;
	private String category;

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	

}
