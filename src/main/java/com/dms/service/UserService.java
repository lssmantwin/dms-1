package com.dms.service;

import com.dms.dto.UserDto;

public interface UserService {

	UserDto checkUsername(String username);

	void register(UserDto userDto);

	int login(String username, String password);

}
