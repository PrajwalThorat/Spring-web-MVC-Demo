package com.stackroute.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stackroute.model.User;
@Component
@Transactional
public class UserDaoImpl implements UserDao{

	//private List<User> list;
	SessionFactory sessionFactory;
	
	@Autowired
	public UserDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}
	public boolean addUser(User user) {
		sessionFactory.getCurrentSession().save(user);
		return true;
	}

	public boolean deleteUser(String email) {
		User existingUser=getUserByEmail(email);
		sessionFactory.getCurrentSession().delete(existingUser);
		return true;
	}

	public List<User> listAllUsers() {
		return sessionFactory.getCurrentSession().createQuery("from user").list();
	}

	public boolean updateUser(User user) {
		sessionFactory.getCurrentSession().update(user);
		return true;
	}

	public User getUserByEmail(String email) {
		return (User)sessionFactory.getCurrentSession().createQuery("from user WHERE email='"+email+"'").uniqueResult();
	}

}
