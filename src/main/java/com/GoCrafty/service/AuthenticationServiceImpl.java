package com.GoCrafty.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.GoCrafty.dao.AuthenticationDAO;
import com.GoCrafty.entity.User;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
	@Autowired
	private AuthenticationDAO authenticationDAO;



	@Override
	@Transactional
	public String login(User theUser,String role) {
		// TODO Auto-generated method stub
		return authenticationDAO.login( theUser,role);	
	}
	

}
