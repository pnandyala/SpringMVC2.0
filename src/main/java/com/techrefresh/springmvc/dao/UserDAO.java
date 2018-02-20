package com.techrefresh.springmvc.dao;

import java.util.List;

import com.techrefresh.springmvc.model.Login;
import com.techrefresh.springmvc.model.User;

public interface UserDAO {
	
	void register(User user);
	
	User validateUser(Login login);

	List<User> getAllUsers();

}
