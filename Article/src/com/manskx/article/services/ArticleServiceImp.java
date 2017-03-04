package com.manskx.article.services;

import java.util.*;

import org.hibernate.Criteria;
import org.hibernate.Session;

import com.manskx.article.database.HibernateUtil;
import com.manskx.article.models.Article;

public class ArticleServiceImp implements AtricleService {

	public List<Article> getAllArticles() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(Article.class);
		List<Article> articles = (List<Article>) criteria.list();
		session.close();
		return articles;
	}

	public void addArticle(Article article) {

	}

	public Article getArticle(Article article) {
		return article;
	}

	public void editArticle(Article article) {

	}

	public void deleteArticle(Article article) {

	}

}
