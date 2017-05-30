package com.dms.dao;

import com.dms.dto.UserDto;

public interface UserDao {

	UserDto getUser(String username);

	void saveUser(UserDto userDto);

}
