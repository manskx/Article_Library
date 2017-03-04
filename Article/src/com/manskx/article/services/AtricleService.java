package com.manskx.article.services;

import java.util.*;

import com.manskx.article.models.Article;

public interface AtricleService {
	void addArticle(Article article);

	Article getArticle(Article article);

	void editArticle(Article article);

	void deleteArticle(Article article);

	List<Article> getAllArticles();

}
