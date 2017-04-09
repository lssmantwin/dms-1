package com.system.service;

import com.system.domain.MiniRequest;
import com.system.domain.User;
import com.system.domain.User2;

import java.util.List;

public interface UserService {

	User2 checkUser(User2 user);

	int save(User user);

	void update(User user);

	void delete(List<User> users);

	int count(MiniRequest request);

	List<User> getUsers(MiniRequest request);

}
