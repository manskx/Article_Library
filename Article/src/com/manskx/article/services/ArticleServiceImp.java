package com.manskx.article.services;

import java.io.IOException;
import java.util.*;

import org.hibernate.Criteria;
import org.hibernate.Session;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.manskx.article.database.HibernateUtil;
import com.manskx.article.models.Article;

/**
 * This is an implementation for user services and Article search services that
 * provides functionalities for CRUD operations and search
 * 
 * @author mansk
 *
 */
public class ArticleServiceImp implements AtricleService, AtricleSearchService {

	public List<Article> getAllArticles() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(Article.class);
		List<Article> articles = (List<Article>) criteria.list();
		session.close();
		return articles;
	}

	public void addArticle(Article article) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(article);
		session.getTransaction().commit();
	}

	public Article getArticle(Article article) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		article = (Article) session.get(Article.class, article.getId());
		session.close();
		return article;
	}

	public void editArticle(Article article) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(article);
		session.getTransaction().commit();
	}

	public void deleteArticle(Article article) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(article);
		session.getTransaction().commit();
	}

	/**
	 * This is implementation for search articles using solr
	 */
	public List<Article> searchArticles(String query) throws Exception {
		List<Article> articles = null;

		try {
			ArticleSolrSearchService articleSearchService = ArticleSolrSearchService.getInstance();
			articles = articleSearchService.getArticlesSearchRsults(query);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
		return articles;

	}

}
