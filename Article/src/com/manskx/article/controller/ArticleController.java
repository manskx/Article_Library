package com.manskx.article.controller;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.manskx.article.models.Article;
import com.manskx.article.services.ArticleServiceImp;
import com.manskx.article.services.AtricleSearchService;
import com.manskx.article.services.AtricleService;

/**
 * Article controller is responsible for doing logic to endpoint
 * 
 * @author mansk
 *
 */
public class ArticleController {
	public List<Article> getAllArticles() {
		AtricleService articleService = new ArticleServiceImp();
		List<Article> articles = articleService.getAllArticles();
		return articles;
	}

	public boolean addArticle(Article article) {
		AtricleService articleService = new ArticleServiceImp();
		articleService.addArticle(article);
		return true;
	}

	public boolean editArticle(Article article) {
		AtricleService articleService = new ArticleServiceImp();
		articleService.editArticle(article);
		return true;
	}

	public boolean deleteArticle(Integer id) {
		Article article = new Article();
		article.setId(id);
		AtricleService articleService = new ArticleServiceImp();
		articleService.deleteArticle(article);
		return true;
	}

	public Article getArticle(Integer id) {
		Article article = new Article();
		article.setId(id);
		AtricleService articleService = new ArticleServiceImp();
		article = articleService.getArticle(article);
		return article;

	}
	public List<Article> searchArticles(String query) throws Exception {
		AtricleSearchService atricleSearchService = new ArticleServiceImp();
		List<Article> articles = atricleSearchService.searchArticles(query);
		return articles;
	}

}
