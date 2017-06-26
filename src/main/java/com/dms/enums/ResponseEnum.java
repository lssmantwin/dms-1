package com.dms.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ResponseEnum {
	SUCCESS(1000), ERROR(2000), NONE_PRIVILEGE(3000);

	private int code;

	ResponseEnum(int code) {
		this.code = code;
	}

	@JsonValue
	public int getCode() {
		return code;
	}
}
