package com.example.users.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.users.model.User;
import com.example.users.util.HibernateUtil;

public class UserDao {

	private static UserDao instance;
	
    private UserDao(){}
	
    public static synchronized UserDao getInstance(){
        if(instance == null){
            instance = new UserDao();
        }
        return instance;
    }
    
    public List<User> getUsers() {
    	Session session=HibernateUtil.getSession();
    	Transaction transaction = session.beginTransaction();
		
		// Create CriteriaBuilder
		CriteriaBuilder builder = session.getCriteriaBuilder();
		// Create CriteriaQuery
		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		Root<User> contactRoot = criteria.from(User.class);
		criteria.select(contactRoot);
		List<User> users = session.createQuery(criteria).getResultList();
		
		transaction.commit();
		session.close();
		
        return users;
    }
	
	public User getUser(int userId){
		Session session=HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		User user = (User)session.get(User.class, userId);
		transaction.commit();
		session.close();
		return user;
	}
    
    public void create(User user) {
    	Session session=HibernateUtil.getSession();
    	Transaction transaction = session.beginTransaction();
		session.save(user);
		transaction.commit();
		session.close();
    }
    
    public void update(User user) {
    	Session session=HibernateUtil.getSession();
    	Transaction transaction = session.beginTransaction();
		session.update(user);
		transaction.commit();
		session.close();
    }
    
    public User delete(int userId) {
    	Session session=HibernateUtil.getSession();
    	Transaction transaction = session.beginTransaction();
    	User user = getUser(userId);
		session.delete(user);
		transaction.commit();
		session.close();
        return user;
    }
}
