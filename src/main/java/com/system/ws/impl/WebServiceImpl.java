package com.system.ws.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.cxf.message.Message;
import org.apache.cxf.phase.PhaseInterceptorChain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.domain.*;
import com.system.service.EmployeeService;
import com.system.service.UserService;
import com.system.ws.WebService;

@Service("workWebService")
public class WebServiceImpl implements WebService {

	private static final Logger LOGGER = LoggerFactory.getLogger(WebServiceImpl.class);

	@Autowired
	private UserService userService;

	@Autowired
	private EmployeeService employeeService;

	@Override
	public int login(User2 user) {
		User2 checkUser = userService.checkUser(user);
		if (checkUser == null) {
			return -1;
		}
		Message message = (Message) PhaseInterceptorChain.getCurrentMessage();
		HttpServletRequest request = (HttpServletRequest) message.get("HTTP.REQUEST");
		HttpSession session = request.getSession();
		session.setAttribute("Current-User", checkUser);
		return 0;
	}

	@Override
	public int save(List<User> users) {
		LOGGER.info("save users, {}", users);
		for (User user : users) {
			if (user.getId() == 0) {
				userService.save(user);
			} else {
				userService.update(user);
			}
		}
		return 0;
	}

	@Override
	public void delete(List<User> users) {
		LOGGER.info("delete users, {}", users);
		userService.delete(users);
	}

	@Override
	public MiniResponse<List<User>> getUsers(String key, int pageIndex, int pageSize) {

		LOGGER.info("get users, key {}, pageIndex {}, pageSize {}", key, pageIndex, pageSize);

		MiniRequest request = new MiniRequest();
		request.setStart(pageIndex * pageSize + 1);
		request.setEnd((pageIndex + 1) * pageSize);
		request.setKey(key);

		int count = userService.count(request);
		List<User> users = userService.getUsers(request);

		MiniResponse<List<User>> response = new MiniResponse<>();
		response.setTotal(count);
		response.setData(users);

		return response;
	}

}
