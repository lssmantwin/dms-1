package com.dms.aspect;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.stereotype.Service;

import com.dms.dto.UserDto;

@Service
public class AuthorityService {

	private static final ConcurrentMap<String, UserDto> cache = new ConcurrentHashMap<>();

	public void update(String key, UserDto userDto) {
		cache.put(key, userDto);
	}

	public UserDto get(String key) {
		return cache.get(key);
	}

	public void remove(String key) {
		cache.remove(key);
	}
}
