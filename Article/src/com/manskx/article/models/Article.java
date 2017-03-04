package com.manskx.article.models;

/**
 * Database model
 * 
 * @author mansk
 *
 */

public class Article {

	private Integer id;
	private String title;
	private String body;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}