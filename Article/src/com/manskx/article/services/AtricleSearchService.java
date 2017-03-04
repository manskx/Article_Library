package com.manskx.article.services;

import java.util.List;

import com.manskx.article.models.Article;
/**
 * 
 * @author mansk
 * 
 * This interface is used for search,
 * Any class needs to privide search for articles should implement this interface
 * NOTE: could be used by solr or elasticsearch .....
 */
public interface AtricleSearchService {
	List<Article> searchArticles(String query) throws Exception;
	void importData();
}
