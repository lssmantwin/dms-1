package com.dms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dms.dao.UserDao;
import com.dms.dto.UserDto;
import com.dms.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public UserDto checkUsername(String username) {
		return userDao.checkUsername(username);
	}

	@Override
	public void register(UserDto userDto) {
		userDao.register(userDto);
	}

	@Override
	public int login(String username, String password) {
		return userDao.login(username, password);
	}
}
