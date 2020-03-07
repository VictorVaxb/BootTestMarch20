package com.hackerrank.stocktrade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hackerrank.stocktrade.model.User;
import com.hackerrank.stocktrade.service.IUserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	IUserService userServ;
	
	@PostMapping("/save")
	public ResponseEntity<?> saveUser(@RequestBody User user){
		System.out.println("saveUser()");
		System.out.println(user.toString());
		userServ.saveUser(user);
		System.out.println("Usuario creado");
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@GetMapping("/finduser/{id}")
	public ResponseEntity<?> findUserById(@PathVariable(value="id") Long id){
		User usr = userServ.findUserById(id);
		if(usr != null) {
			return new ResponseEntity<User>(usr, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
}
