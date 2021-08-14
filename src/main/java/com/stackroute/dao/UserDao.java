package com.stackroute.dao;

import java.util.List;

import com.stackroute.model.User;

public interface UserDao {
	
	//CRUD Operation
	public boolean addUser(User user);
	public boolean deleteUser(String email);
	public List<User> listAllUsers();
	public boolean updateUser(User user);
	public User getUserByEmail(String email);
}
