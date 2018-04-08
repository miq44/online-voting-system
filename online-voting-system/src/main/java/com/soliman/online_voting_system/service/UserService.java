package com.soliman.online_voting_system.service;

import java.util.List;

import com.soliman.online_voting_system.entity.User;



public interface UserService {

	public List<User> getAllUsers();
	
	public void saveUser(User user);
	
	public User getUserByUserId(String userId);
	
	public void deleteUser(String userId);
	
	public void updateUserById(User user,String userId);
	
	public void updateUser(User user);

	public boolean isUniqueEmail(String emailId);

	public boolean isUniqueUserName(String userName);
}
