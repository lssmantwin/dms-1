package com.dms.ws.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.dms.dto.UserDto;
import com.dms.service.UserService;
import com.dms.ws.UserWebService;

@Service
public class UserWebServiceImpl implements UserWebService {

	@Autowired
	private UserService userService;

	@Override
	public boolean check(UserDto request) {
		UserDto userDto = userService.getUser(request.getUsername());
		if (userDto == null) {
			UserDto newUser = new UserDto();
			newUser.setUsername(request.getUsername());
			newUser.setPassword(request.getPassword());
			userService.saveUser(newUser);
			return true;
		} else {
			return StringUtils.equals(request.getPassword(), userDto.getPassword());
		}
	}
}
