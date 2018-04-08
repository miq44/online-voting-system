package com.soliman.online_voting_system.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soliman.online_voting_system.dao.UserDAO;
import com.soliman.online_voting_system.entity.User;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDao;
	
	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void saveUser(User user) {
		userDao.saveUser(user);
       
	}

	@Override
	@Transactional
	public User getUserByUserId(String userId) {
		// TODO Auto-generated method stub
	   return userDao.getUserByUserId(userId);
	}

	@Override
	public void deleteUser(String userId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateUserById(User user, String userId) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional
	public void updateUser(User user) {
		userDao.updateUser(user);
		
	}

	@Override
	@Transactional
	public boolean isUniqueEmail(String emailId) {
		List<User> users = userDao.getUserByEmailId(emailId);
		if(users.size()==0) {
			return true;
		}else {
			return false;
		}
		
	}

	@Override
	@Transactional
	public boolean isUniqueUserName(String userName) {
		User user = userDao.getUserByUserId(userName);
		if(user==null) {
			return true;
		}else {
			return false;
		}
	}

}
