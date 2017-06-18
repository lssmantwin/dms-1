package com.dms.dto;

import java.io.Serializable;

import com.dms.serializable.PasswordEncrypt;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto implements Serializable {

	private int id;
	private String username;
	@JsonDeserialize(using = PasswordEncrypt.class)
	private String password;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("[username: ").append(username).append(", password: ").append(password).append("]").toString();
	}
}
