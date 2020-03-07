package com.hackerrank.stocktrade.service;

import com.hackerrank.stocktrade.model.User;

public interface IUserService {

	public void saveUser(User user);
	
	public User findUserById(Long iduser);
	
}
