package com.dms.request;

import java.io.Serializable;
import java.util.List;

import com.dms.dto.FinanceDto;

public class FinanceRequest implements Serializable {

	private String month;
	private List<FinanceDto> finances;

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public List<FinanceDto> getFinances() {
		return finances;
	}

	public void setFinances(List<FinanceDto> finances) {
		this.finances = finances;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("{ month: ").append(month).append(", finances: ").append(finances).append(" }").toString();
	}
}
