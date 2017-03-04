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
import javax.ws.rs.QueryParam;
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
	@Path("/search")
	@GET
	@Produces("application/json")
	public Response searchArticles(@QueryParam("query") String query) throws Exception {
		ArticleController articleController = new ArticleController();
		List<Article> articles = articleController.searchArticles(query);
		return Response.status(200).entity(articles).build();

	}
	@Path("/{id}")
	@GET
	@Produces("application/json")
	public Response geArticle(@PathParam("id") Integer id) {
		ArticleController articleController = new ArticleController();
		Article article = articleController.getArticle(id);
		return Response.status(200).entity(article).build();

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addArticle(Article article) {
		ArticleController articleController = new ArticleController();
		articleController.addArticle(article);
		String result = "article added";
		return Response.status(200).entity(result).build();

	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editArticle(Article article) {
		ArticleController articleController = new ArticleController();
		articleController.editArticle(article);
		String result = "article saved";
		return Response.status(200).entity(result).build();
	}

	@Path("/{id}")
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteArticle(@PathParam("id") Integer id) {
		ArticleController articleController = new ArticleController();
		articleController.deleteArticle(id);
		String result = "article deleted";
		return Response.status(200).entity(result).build();
	}

}
