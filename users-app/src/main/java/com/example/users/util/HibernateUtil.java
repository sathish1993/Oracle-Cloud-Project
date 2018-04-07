package com.example.users.util;

import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.users.model.User;

public class HibernateUtil {
	
	private static SessionFactory concreteSessionFactory;
	
	static {
		try {
			
			Properties prop= new Properties();
			prop.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
			prop.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/oracle_user");
			prop.setProperty("hibernate.connection.username", "root");
			prop.setProperty("hibernate.connection.password", "root123");
			prop.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
			prop.setProperty("hibernate.order_updates", "true");
			
			Configuration cfg = new Configuration()
					.addAnnotatedClass(User.class)
					.setProperties(prop);
			concreteSessionFactory = cfg.buildSessionFactory();
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public static Session getSession() throws HibernateException {
		return concreteSessionFactory.openSession();
	}

	public static void closeSession() throws HibernateException {
		concreteSessionFactory.close();
	}

}
