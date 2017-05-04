package com.dms.request;

import java.io.Serializable;

import org.joda.time.LocalDateTime;

import com.dms.serializable.LocalDateTimeJacksonDeSerializable;
import com.dms.serializable.LocalDateTimeJacksonSerializable;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class ProjectCommissionFilterRequest extends BaseFilterRequest implements Serializable {

	private String designer;
	private String contractState;
	private String commissionState;

	@JsonSerialize(using = LocalDateTimeJacksonSerializable.class)
	@JsonDeserialize(using = LocalDateTimeJacksonDeSerializable.class)
	private LocalDateTime contractDateStart;
	@JsonSerialize(using = LocalDateTimeJacksonSerializable.class)
	@JsonDeserialize(using = LocalDateTimeJacksonDeSerializable.class)
	private LocalDateTime contractDateEnd;

	@JsonSerialize(using = LocalDateTimeJacksonSerializable.class)
	@JsonDeserialize(using = LocalDateTimeJacksonDeSerializable.class)
	private LocalDateTime firstCommissionDateStart;
	@JsonSerialize(using = LocalDateTimeJacksonSerializable.class)
	@JsonDeserialize(using = LocalDateTimeJacksonDeSerializable.class)
	private LocalDateTime firstCommissionDateEnd;

	@JsonSerialize(using = LocalDateTimeJacksonSerializable.class)
	@JsonDeserialize(using = LocalDateTimeJacksonDeSerializable.class)
	private LocalDateTime actualStartTimeStart;
	@JsonSerialize(using = LocalDateTimeJacksonSerializable.class)
	@JsonDeserialize(using = LocalDateTimeJacksonDeSerializable.class)
	private LocalDateTime actualStartTimeEnd;

	@JsonSerialize(using = LocalDateTimeJacksonSerializable.class)
	@JsonDeserialize(using = LocalDateTimeJacksonDeSerializable.class)
	private LocalDateTime actualEndTimeStart;
	@JsonSerialize(using = LocalDateTimeJacksonSerializable.class)
	@JsonDeserialize(using = LocalDateTimeJacksonDeSerializable.class)
	private LocalDateTime actualEndTimeEnd;

	@JsonSerialize(using = LocalDateTimeJacksonSerializable.class)
	@JsonDeserialize(using = LocalDateTimeJacksonDeSerializable.class)
	private LocalDateTime balanceTimeStart;
	@JsonSerialize(using = LocalDateTimeJacksonSerializable.class)
	@JsonDeserialize(using = LocalDateTimeJacksonDeSerializable.class)
	private LocalDateTime balanceTimeEnd;

	@JsonSerialize(using = LocalDateTimeJacksonSerializable.class)
	@JsonDeserialize(using = LocalDateTimeJacksonDeSerializable.class)
	private LocalDateTime balanceCommissionDateStart;
	@JsonSerialize(using = LocalDateTimeJacksonSerializable.class)
	@JsonDeserialize(using = LocalDateTimeJacksonDeSerializable.class)
	private LocalDateTime balanceCommissionDateEnd;

	public String getDesigner() {
		return designer;
	}

	public void setDesigner(String designer) {
		this.designer = designer;
	}

	public String getContractState() {
		return contractState;
	}

	public void setContractState(String contractState) {
		this.contractState = contractState;
	}

	public String getCommissionState() {
		return commissionState;
	}

	public void setCommissionState(String commissionState) {
		this.commissionState = commissionState;
	}

	public LocalDateTime getContractDateStart() {
		return contractDateStart;
	}

	public void setContractDateStart(LocalDateTime contractDateStart) {
		this.contractDateStart = contractDateStart;
	}

	public LocalDateTime getContractDateEnd() {
		return contractDateEnd;
	}

	public void setContractDateEnd(LocalDateTime contractDateEnd) {
		this.contractDateEnd = contractDateEnd;
	}

	public LocalDateTime getFirstCommissionDateStart() {
		return firstCommissionDateStart;
	}

	public void setFirstCommissionDateStart(LocalDateTime firstCommissionDateStart) {
		this.firstCommissionDateStart = firstCommissionDateStart;
	}

	public LocalDateTime getFirstCommissionDateEnd() {
		return firstCommissionDateEnd;
	}

	public void setFirstCommissionDateEnd(LocalDateTime firstCommissionDateEnd) {
		this.firstCommissionDateEnd = firstCommissionDateEnd;
	}

	public LocalDateTime getActualStartTimeStart() {
		return actualStartTimeStart;
	}

	public void setActualStartTimeStart(LocalDateTime actualStartTimeStart) {
		this.actualStartTimeStart = actualStartTimeStart;
	}

	public LocalDateTime getActualStartTimeEnd() {
		return actualStartTimeEnd;
	}

	public void setActualStartTimeEnd(LocalDateTime actualStartTimeEnd) {
		this.actualStartTimeEnd = actualStartTimeEnd;
	}

	public LocalDateTime getActualEndTimeStart() {
		return actualEndTimeStart;
	}

	public void setActualEndTimeStart(LocalDateTime actualEndTimeStart) {
		this.actualEndTimeStart = actualEndTimeStart;
	}

	public LocalDateTime getActualEndTimeEnd() {
		return actualEndTimeEnd;
	}

	public void setActualEndTimeEnd(LocalDateTime actualEndTimeEnd) {
		this.actualEndTimeEnd = actualEndTimeEnd;
	}

	public LocalDateTime getBalanceTimeStart() {
		return balanceTimeStart;
	}

	public void setBalanceTimeStart(LocalDateTime balanceTimeStart) {
		this.balanceTimeStart = balanceTimeStart;
	}

	public LocalDateTime getBalanceTimeEnd() {
		return balanceTimeEnd;
	}

	public void setBalanceTimeEnd(LocalDateTime balanceTimeEnd) {
		this.balanceTimeEnd = balanceTimeEnd;
	}

	public LocalDateTime getBalanceCommissionDateStart() {
		return balanceCommissionDateStart;
	}

	public void setBalanceCommissionDateStart(LocalDateTime balanceCommissionDateStart) {
		this.balanceCommissionDateStart = balanceCommissionDateStart;
	}

	public LocalDateTime getBalanceCommissionDateEnd() {
		return balanceCommissionDateEnd;
	}

	public void setBalanceCommissionDateEnd(LocalDateTime balanceCommissionDateEnd) {
		this.balanceCommissionDateEnd = balanceCommissionDateEnd;
	}
}
