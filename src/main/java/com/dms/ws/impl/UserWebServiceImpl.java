package com.dms.ws.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dms.dto.UserDto;
import com.dms.service.UserService;
import com.dms.ws.UserWebService;

@Service
public class UserWebServiceImpl implements UserWebService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserWebServiceImpl.class);

	@Autowired
	private UserService userService;

	@Override
	public boolean check(String username) {
		LOGGER.info("check username: {}", username);
		UserDto userDto = userService.checkUsername(username);
		return userDto == null;
	}

	@Override
	public boolean login(UserDto userDto) {
		LOGGER.info("user {} login", userDto);
		int i = userService.login(userDto.getUsername(), userDto.getPassword());
		return i == 1;
	}

	@Override
	public boolean register(UserDto userDto) {
		LOGGER.info("register user {}", userDto);
		try {
			userService.register(userDto);
			return true;
		} catch (Exception e) {
			LOGGER.error("register exception", e);
		}
		return false;
	}
}
