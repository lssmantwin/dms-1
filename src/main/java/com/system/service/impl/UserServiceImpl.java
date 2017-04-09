package com.system.service.impl;

import java.util.List;

import com.system.domain.User2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.dao.UserDao;
import com.system.domain.MiniRequest;
import com.system.domain.User;
import com.system.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	@Transactional
	public User2 checkUser(User2 user) {
		return userDao.checkUser(user);
	}

	@Override
	@Transactional
	public int save(User user) {
		userDao.save(user);
		return 0;
	}

	@Override
	@Transactional
	public void update(User user) {
		userDao.update(user);
	}

	@Override
	@Transactional
	public void delete(List<User> users) {
		for (User user : users) {
			userDao.delete(user.getId());
		}
	}

	@Override
	@Transactional
	public int count(MiniRequest request) {
		return userDao.count(request);
	}

	@Override
	@Transactional
	public List<User> getUsers(MiniRequest request) {
		return userDao.getUsers(request);
	}

}
