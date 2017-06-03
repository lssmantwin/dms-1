package com.dms.dto;

import com.dms.serializable.LocalDateTimeJacksonDeSerializable;
import com.dms.serializable.ShortLocalDateTimeJacksonSerializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.LocalDateTime;

import java.io.Serializable;
import java.math.BigDecimal;

public class DesignAssistantDto implements Serializable {

	private String acNumber;
	private String designAssistant;
	private double purchaseCost;
	private Long designAssistantId;

	public String getAcNumber() {
		return acNumber;
	}

	public void setAcNumber(String acNumber) {
		this.acNumber = acNumber;
	}

	public String getDesignAssistant() {
		return designAssistant;
	}

	public void setDesignAssistant(String designAssistant) {
		this.designAssistant = designAssistant;
	}

	public double getPurchaseCost() {
		return purchaseCost;
	}

	public void setPurchaseCost(double purchaseCost) {
		this.purchaseCost = purchaseCost;
	}

	public Long getDesignAssistantId() {
		return designAssistantId;
	}

	public void setDesignAssistantId(Long designAssistantId) {
		this.designAssistantId = designAssistantId;
	}
}
