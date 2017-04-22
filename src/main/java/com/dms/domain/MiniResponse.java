package com.dms.domain;

import java.io.Serializable;

public class MiniResponse<T> implements Serializable {

	private int total;
	private T data;
	private T rows;

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

	public T getRows() {
		return rows;
	}

	public void setRows(T rows) {
		this.rows = rows;
	}
}
