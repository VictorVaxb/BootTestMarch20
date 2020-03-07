package com.hackerrank.stocktrade.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.hackerrank.stocktrade.model.User;

public interface IUserDao extends CrudRepository<User, Long> {
	
	@Query("select u from User u where u.id=?1")
	public User findByIdSQL(Long id);
	
}