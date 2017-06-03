package com.dms.request;

import com.dms.utils.DateUtils;

import java.io.Serializable;

public class ChargeFilterRequest extends BaseFilterRequest implements Serializable {

	String chargeTime;
	String chargeBalance;
	String chargeStartTime;
	String chargeEndTime;

	public String getChargeTime() {
		return chargeTime;
	}

	public void setChargeTime(String chargeTime) {
		this.chargeTime = chargeTime;
	}

	public String getChargeBalance() {
		return chargeBalance;
	}

	public void setChargeBalance(String chargeBalance) {
		this.chargeBalance = chargeBalance;
	}

	public String getChargeStartTime() {
		if (chargeTime != null) {
			String[] ymDate = chargeTime.split("\\-");
			return DateUtils.getFirstDayOfMontString(Integer.valueOf(ymDate[0]), Integer.valueOf(ymDate[1]));

		}
		return null;
	}

	public void setChargeStartTime(String chargeStartTime) {
		this.chargeStartTime = chargeStartTime;
	}

	public String getChargeEndTime() {
		if (chargeTime != null) {
			String[] ymDate = chargeTime.split("\\-");
			return DateUtils.getLastDayOfMonthString(Integer.valueOf(ymDate[0]), Integer.valueOf(ymDate[1]));

		}
		return null;
	}

	public void setChargeEndTime(String chargeEndTime) {
		this.chargeEndTime = chargeEndTime;
	}
}
