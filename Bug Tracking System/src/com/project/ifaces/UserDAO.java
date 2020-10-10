package com.project.ifaces;

/**
 * @author Madhura Satao
 * @author Aishwarya Thakur
 * 
 *User DAO 
 * 
 */

import com.project.entity.User;

public interface UserDAO extends DAO<User> {
	
	boolean exists(String userEmail, String userType);			//method to check if user exists in database
	boolean registeredUserExists(String userEmail);				//method to check if user is registered
	boolean updateRegistrationStatus(String userEmail);			//method to confirm user registration
}
