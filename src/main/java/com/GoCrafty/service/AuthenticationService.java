package com.GoCrafty.service;

import com.GoCrafty.entity.User;

public interface AuthenticationService {
	

	public String login(User theUser, String role);


}
