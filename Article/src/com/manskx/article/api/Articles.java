package com.manskx.article.api;

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

import com.manskx.article.models.Article;

@Path("/articles")
/**
 * Main endpoint for articles and to do: - getall articles - get article byid -
 * edit article - delete article - search articles
 * 
 * @author mansk
 *
 */
public class Articles {
	@GET
	@Produces("application/json")
	public Response geArticles() {

	}

	@Path("/{id}")
	@GET
	@Produces("application/json")
	public Response geArticle(@PathParam("id") Integer id) {

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addArticle(Article article) {

	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editArticle(Article article) {

	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteArticle(Article article) {

	}

}
