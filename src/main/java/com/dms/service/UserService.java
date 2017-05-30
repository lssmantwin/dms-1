package com.dms.service;

import com.dms.dto.UserDto;

public interface UserService {

	UserDto getUser(String username);

	void saveUser(UserDto userDto);

}
