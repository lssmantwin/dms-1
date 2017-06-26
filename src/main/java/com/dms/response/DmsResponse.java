package com.dms.response;

import java.io.Serializable;

import com.dms.enums.ResponseEnum;

public class DmsResponse<T> implements Serializable {

	private ResponseEnum code;
	private int total;
	private T data;

	public ResponseEnum getCode() {
		return code;
	}

	public void setCode(ResponseEnum code) {
		this.code = code;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
