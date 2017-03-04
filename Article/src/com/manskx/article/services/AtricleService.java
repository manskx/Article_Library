package com.manskx.article.services;

import java.util.*;

import com.manskx.article.models.Article;

/**
 *
 * @author mansk
 *
 *         This Interface defines the main functionalites should be implemented
 *         in any class that could provide userservices
 */
public interface AtricleService {
	void addArticle(Article article);

	Article getArticle(Article article);

	void editArticle(Article article);

	void deleteArticle(Article article);

	List<Article> getAllArticles();

}
