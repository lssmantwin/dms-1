package com.dms.aspect;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.jboss.resteasy.spi.ResteasyProviderFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dms.dto.UserDto;
import com.dms.enums.ResponseEnum;
import com.dms.response.DmsResponse;

@Component
@Aspect
public class AuthorityAspect {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthorityAspect.class);

	@Autowired
	private AuthorityService authorityService;

	@Pointcut("@annotation(com.dms.aspect.CheckAuthority)")
	public void check() {
	}

	@Around("check()")
	public Object doAround(ProceedingJoinPoint jp) throws Throwable {
		DmsResponse response = new DmsResponse();
		HttpServletRequest request = ResteasyProviderFactory.getContextData(HttpServletRequest.class);
		Cookie[] cookies = request.getCookies();
		Cookie cookie = getToken(cookies);
		if (cookie == null) {
			response.setCode(ResponseEnum.NONE_PRIVILEGE);
			return response;
		}
		UserDto userDto = authorityService.get(cookie.getValue());
		if (userDto == null) {
			LOGGER.info("user not found");
			response.setCode(ResponseEnum.NONE_PRIVILEGE);
			return response;
		}
		return jp.proceed();
	}

	private Cookie getToken(Cookie[] cookies) {
		if (cookies == null || cookies.length == 0) {
			LOGGER.info("cookies is empty");
			return null;
		}
		for (Cookie cookie : cookies) {
			if (StringUtils.equals(cookie.getName(), "Token")) {
				return cookie;
			}
		}
		LOGGER.info("token not found");
		return null;
	}
}
