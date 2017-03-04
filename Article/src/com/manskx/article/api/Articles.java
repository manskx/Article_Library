package com.manskx.article.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.manskx.article.controller.ArticleController;
import com.manskx.article.models.Article;

/**
 * Main endpoint for articles and to do: - getall articles - get article byid -
 * edit article - delete article - search articles
 * 
 * @author mansk
 *
 */

@Path("/articles")
public class Articles {
	@GET
	@Produces("application/json")
	public Response getArticles() {
		ArticleController articleController = new ArticleController();
		List<Article> articles = articleController.getAllArticles();
		return Response.status(200).entity(articles).build();

	}

	@Path("/{id}")
	@GET
	@Produces("application/json")
	public Response geArticle(@PathParam("id") Integer id) {
		ArticleController articleController = new ArticleController();
		return Response.status(200).entity(true).build();

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addArticle(Article article) {
		ArticleController articleController = new ArticleController();
		return Response.status(200).entity(true).build();

	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editArticle(Article article) {
		ArticleController articleController = new ArticleController();
		return Response.status(200).entity(true).build();

	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteArticle(Article article) {
		ArticleController articleController = new ArticleController();
		return Response.status(200).entity(true).build();

	}

}
