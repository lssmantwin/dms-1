package com.dms.service;

import com.dms.dto.ChargeDetailDto;

import java.util.List;

public interface ChargeService {

	void audit(ChargeDetailDto chargeDetailDto);

	List<ChargeDetailDto> getChargeDetails();

}
