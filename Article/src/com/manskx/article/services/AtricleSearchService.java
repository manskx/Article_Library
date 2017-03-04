package com.manskx.article.services;

import java.util.List;

import com.manskx.article.models.Article;

public interface AtricleSearchService {
	List<Article> searchArticles(String query) throws Exception;
}
