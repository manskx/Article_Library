package com.manskx.article.controller;

import java.util.List;

import com.manskx.article.models.Article;
import com.manskx.article.services.ArticleServiceImp;
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
	
	
	
}
