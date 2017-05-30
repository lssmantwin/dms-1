package com.dms.service.impl;

import com.dms.dao.UserDao;
import com.dms.dto.UserDto;
import com.dms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public UserDto getUser(String username) {
		return userDao.getUser(username);
	}

	@Override
	public void saveUser(UserDto userDto) {
		userDao.saveUser(userDto);
	}
}
