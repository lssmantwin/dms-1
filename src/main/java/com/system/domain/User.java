package com.system.domain;

import java.io.Serializable;

public class User implements Serializable {

	private int id;
	private String name;
	private String email;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("id: ").append(id).append(", name: ").append(name).append(", email: ").append(email).toString();
	}
}
