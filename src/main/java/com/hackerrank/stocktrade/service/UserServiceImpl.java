package com.hackerrank.stocktrade.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackerrank.stocktrade.dao.IUserDao;
import com.hackerrank.stocktrade.model.User;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;
	
	@Override
	public void saveUser(User user) {
		userDao.save(user);
	}

	@Override
	public User findUserById(Long iduser) {
		User usr = (User) userDao.findByIdSQL(iduser);
		return usr;
	}

}
