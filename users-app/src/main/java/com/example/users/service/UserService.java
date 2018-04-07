package com.example.users.service;

import java.util.ArrayList;
import java.util.List;

import com.example.users.dao.UserDao;
import com.example.users.model.User;


public class UserService {


    public List<User> getAllUsers() { 
    	UserDao userDao = UserDao.getInstance();
        return userDao.getUsers();
    }

    public List<User> searchUsersByName(String name) {
    	UserDao userDao = UserDao.getInstance();
    	
    	List<User> userList = userDao.getUsers();
        List<User> result = new ArrayList<>();
        for(User temp: userList) {
            if(temp.getName().toLowerCase().contains(name.toLowerCase())
            		|| temp.getLastName().toLowerCase().contains(name.toLowerCase())) {
                result.add(temp);
            }
        }
        return result;
    }

    public User getUser(int id) throws Exception {
        UserDao userDao = UserDao.getInstance();
    	return userDao.getUser(id);
    }   

    public int addUser(User user) {
        UserDao userDao = UserDao.getInstance();
        userDao.create(user);
        return user.getId();
    }

    public boolean updateUser(User user) {
        UserDao userDao = UserDao.getInstance();
        User userTemp = userDao.getUser(user.getId());
        if(userTemp != null) {
            userDao.update(user);
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteUser(int id) {
        UserDao userDao = UserDao.getInstance();
        User user = userDao.getUser(id);
        if(user != null) {
            userDao.delete(id);
            return true;
        } else {
            return false;
        }
    }

}