package com.GoCrafty.dao;

import com.GoCrafty.entity.User;

public interface AuthenticationDAO {

	public String login(User theUser, String role);

}
