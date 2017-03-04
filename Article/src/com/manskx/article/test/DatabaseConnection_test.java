package com.manskx.article.test;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.junit.Test;

import com.manskx.article.database.HibernateUtil;


public class DatabaseConnection_test {
	@Test
	public void testDatabaseConnection() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		assertEquals(session.isConnected(), true);
	}

	@Test
	public void testDatabaseInsertion() {
		Session session = HibernateUtil.getSessionFactory().openSession();

		assertEquals(" ", " ");
	}
}
