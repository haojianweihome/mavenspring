package com.hjw.home.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hjw.home.user.User;
import com.hjw.home.user.dao.UserDao;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;

	@Override
	public void saveuser(User user)throws RuntimeException 
    {
		userDao.insert(user);
		
	}

	@Override
	public List<User> getalluser() {
		return null; 
	}
	
	

}
