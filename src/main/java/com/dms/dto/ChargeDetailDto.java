
package com.dms.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.dms.serializable.ShortDateTimeJacksonDeSerializable;
import com.dms.serializable.ShortLocalDateTimeJacksonSerializable;
import org.joda.time.LocalDateTime;

import com.dms.serializable.LocalDateTimeJacksonDeSerializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ChargeDetailDto implements Serializable {

	private String id;
	private Long employeeId;
	private String employeeName;
    @JsonSerialize(using = ShortLocalDateTimeJacksonSerializable.class)
    @JsonDeserialize(using = ShortDateTimeJacksonDeSerializable.class)
	private LocalDateTime chargeTime;
	private BigDecimal charge;
	private BigDecimal chargeBalance;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public LocalDateTime getChargeTime() {
		return chargeTime;
	}

	public void setChargeTime(LocalDateTime chargeTime) {
		this.chargeTime = chargeTime;
	}

	public BigDecimal getCharge() {
		return charge;
	}

	public void setCharge(BigDecimal charge) {
		this.charge = charge;
	}

	public BigDecimal getChargeBalance() {
		return chargeBalance;
	}

	public void setChargeBalance(BigDecimal chargeBalance) {
		this.chargeBalance = chargeBalance;
	}
}
