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
	/**
	 * 
	 * @return get all articles from database
	 */
	public List<Article> getAllArticles() {
		AtricleService articleService = new ArticleServiceImp();
		List<Article> articles = articleService.getAllArticles();
		return articles;
	}

	/**
	 * adding article to databse
	 * 
	 * @param article:
	 *            input article object containing all required prams #### NOTE:
	 *            id is not required ####
	 * @return success or not
	 */
	public boolean addArticle(Article article) {
		AtricleService articleService = new ArticleServiceImp();
		articleService.addArticle(article);
		((AtricleSearchService) articleService).importData();
		return true;
	}

	/**
	 * 
	 * @param article:
	 *            input article object to be modified containing all required
	 *            prams #### NOTE: id is required ####
	 * @return success or not
	 */
	public boolean editArticle(Article article) {
		AtricleService articleService = new ArticleServiceImp();
		articleService.editArticle(article);
		((AtricleSearchService) articleService).importData();

		return true;
	}

	/**
	 * 
	 * @param id:
	 *            is of article wanted to be deleted
	 * @return
	 */
	public boolean deleteArticle(Integer id) {
		Article article = new Article();
		article.setId(id);
		AtricleService articleService = new ArticleServiceImp();
		articleService.deleteArticle(article);
		((AtricleSearchService) articleService).importData();

		return true;
	}

	/**
	 * get only one article
	 * 
	 * @param id:
	 *            is of article wanted to be retrieved
	 * @return
	 */
	public Article getArticle(Integer id) {
		Article article = new Article();
		article.setId(id);
		AtricleService articleService = new ArticleServiceImp();
		article = articleService.getArticle(article);
		return article;

	}

	/**
	 * 
	 * @param query:
	 *            query string for searching
	 * @return: list of articles that matched this query
	 * @throws Exception
	 */
	public List<Article> searchArticles(String query) throws Exception {
		AtricleSearchService atricleSearchService = new ArticleServiceImp();
		List<Article> articles = atricleSearchService.searchArticles(query);
		return articles;
	}

}
