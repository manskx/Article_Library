package com.manskx.article.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.manskx.article.api.Articles;
import com.manskx.article.models.Article;

public class ArticleAPI_test extends JerseyTest {

	@Override
	protected Application configure() {
		return new ResourceConfig(Articles.class);
	}

	@Test
	public void getAllArticles_test() throws JsonParseException, JsonMappingException, IOException {
		String allArticlesString = target("articles").request().get(String.class);

		ObjectMapper mapper = new ObjectMapper();
		List<Article> myObjects = Arrays.asList(mapper.readValue(allArticlesString, Article[].class));
		Integer Result = myObjects.size();
		Integer Expected = 1;
		assertEquals(Result, Expected);
	}

	@Test
	public void getArticle_test() {
		Article article = target("articles/2").request().get(Article.class);
		String Expected = "test mansy body 2";

		assertEquals(Expected, article.getBody());
	}

	@Test
	public void addArticle_test() {
		Article article = new Article();
		article.setTitle("MANSY XXXSPECIAL");
		article.setBody("MANSY BODY TEST 2");

		Entity<Article> articleEntity = Entity.entity(article, MediaType.APPLICATION_JSON_TYPE);
		target("articles").request().post(articleEntity);

		Response response = target("articles/search").queryParam("query", "XXXSPECIAL").request().get();

		Assert.assertEquals("MANSY XXXSPECIAL", response.readEntity(Article[].class)[0].getTitle());

	}

	@Test
	public void deleteArticle_test() {
		Integer articleId = 5;
		Response response = target("articles/" + articleId).request().get();
		Article article = response.readEntity(Article.class);
		if (article.getId() > 0) {
			target("articles/" + articleId).request().delete();
		}

		response = target("articles/" + articleId).request().get();
		article = response.readEntity(Article.class);
		Assert.assertNotEquals(articleId, article.getId());

	}

}
