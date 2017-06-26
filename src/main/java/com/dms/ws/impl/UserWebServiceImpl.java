package com.dms.ws.impl;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dms.aspect.AuthorityService;
import com.dms.aspect.CheckAuthority;
import com.dms.dto.UserDto;
import com.dms.enums.ResponseEnum;
import com.dms.response.DmsResponse;
import com.dms.service.UserService;
import com.dms.ws.UserWebService;

@Service
public class UserWebServiceImpl implements UserWebService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserWebServiceImpl.class);

	@Autowired
	private UserService userService;

	@Autowired
	private AuthorityService authorityService;

	@Override
	public boolean check(String username) {
		LOGGER.info("check username: {}", username);
		UserDto userDto = userService.checkUsername(username);
		return userDto == null;
	}

	@Override
	public DmsResponse login(UserDto userDto) {
		LOGGER.info("user {} login", userDto);
		int i = userService.login(userDto.getUsername(), userDto.getPassword());
		DmsResponse response = new DmsResponse();
		if (i == 1) {
			String key = UUID.randomUUID().toString();
			authorityService.update(key, userDto);
			response.setCode(ResponseEnum.SUCCESS);
			response.setData(key);
		} else {
			response.setCode(ResponseEnum.ERROR);
		}
		return response;
	}

	@Override
	public DmsResponse register(UserDto userDto) {
		LOGGER.info("register user {}", userDto);
		DmsResponse response = new DmsResponse();
		try {
			userService.register(userDto);
			String key = UUID.randomUUID().toString();
			authorityService.update(key, userDto);
			response.setCode(ResponseEnum.SUCCESS);
			response.setData(key);
		} catch (Exception e) {
			LOGGER.error("register exception", e);
			response.setCode(ResponseEnum.ERROR);
		}
		return response;
	}

	@Override
	@CheckAuthority
	public DmsResponse getCurrentUser(String token) {
		DmsResponse response = new DmsResponse();
		if (StringUtils.isBlank(token)) {
			response.setCode(ResponseEnum.ERROR);
			return response;
		}
		UserDto userDto = authorityService.get(token);
		if (userDto == null) {
			response.setCode(ResponseEnum.ERROR);
		} else {
			response.setCode(ResponseEnum.SUCCESS);
			response.setData(userDto);
		}
		return response;
	}

	@Override
	public DmsResponse logout(String token) {
		DmsResponse response = new DmsResponse();
		if (StringUtils.isBlank(token)) {
			response.setCode(ResponseEnum.ERROR);
			return response;
		}
		authorityService.remove(token);
		response.setCode(ResponseEnum.SUCCESS);
		return response;
	}
}
