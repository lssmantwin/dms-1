package com.dms.dto;

import java.io.Serializable;

public class EnumDto implements Serializable {

	private int value;
	private String text;

	public EnumDto() {
	}

	public EnumDto(int value, String text) {
		this.value = value;
		this.text = text;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
