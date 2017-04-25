package com.dms.response;

import java.io.Serializable;

public class DataGridResponse<T> implements Serializable {

	private int total;
	private T data; // for miniui
	private T rows; // for easyui

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
