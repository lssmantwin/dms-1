package com.dms.request;

import java.io.Serializable;

public class FinanceFilterRequest extends BaseFilterRequest implements Serializable {

	private String month;

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
}
