package com.project.ifaces;

import com.project.entity.User;

public interface UserDAO<T> extends DAO<User> {
	
	boolean loginMethod(String email,String Password); //Change Name
	
}
