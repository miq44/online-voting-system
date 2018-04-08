package com.soliman.online_voting_system.dao;

import java.util.List;

import com.soliman.online_voting_system.entity.User;



public interface UserDAO {

    public List<User> getAllUsers();
	
	public void saveUser(User user);
	
	public User getUserByUserId(String userId);
	
	public void deleteUser(String userId);
	
	public void updateUser(User user,String userId);

	public void updateUser(User user);

	public List<User> getUserByEmailId(String emailId);
}
