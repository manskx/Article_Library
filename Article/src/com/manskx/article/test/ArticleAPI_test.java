package com.manskx.article.test;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import com.manskx.article.api.Articles;
import com.manskx.article.models.Article;

public class ArticleAPI_test extends JerseyTest {

	@Override
	protected Application configure() {
		return new ResourceConfig(Articles.class);
	}

	@Test
	public void getAllArticles_test() {
		String hello = target("articles").request().get(String.class);
		String Expected = "";

		JSONObject responseJSON_obj = new JSONObject(hello);

		assertEquals(Expected, hello);
	}

	@Test
	public void getArticle_test() {
		String hello = target("articles/").queryParam("id", "1").request().get(String.class);
		String Expected = "";

		JSONObject responseJSON_obj = new JSONObject(hello);

		assertEquals(Expected, hello);
	}

	@Test
	public void addArticle_test() {
		Article article = new Article();
		article.setTitle("MANSY TEST");
		article.setTitle("MANSY BODY TEST");

		Entity<Article> articleEntity = Entity.entity(article, MediaType.APPLICATION_JSON_TYPE);
		target("articles").request().post(articleEntity);

		Response response = target("articles/search").queryParam("title", "MANSY TEST").request().get();

		Assert.assertEquals("MANSY TEST", response.readEntity(Article.class).getTitle());

	}

	@Test
	public void deleteArticle_test() {
		Response response = target("articles/").queryParam("id", "1").request().get();
		Article article= 	response.readEntity(Article.class);
		Integer articleId	=	article.getId();
		if(article.getId()>0){
			Entity<Article> articleEntity = Entity.entity(article, MediaType.APPLICATION_JSON_TYPE);
			target("articles").request().method("DELETE", articleEntity);
		}
		
		response = target("articles/").queryParam("id", "1").request().get();
		article= 	response.readEntity(Article.class);
		Assert.assertNotEquals(articleId, article.getId());

	}

}
