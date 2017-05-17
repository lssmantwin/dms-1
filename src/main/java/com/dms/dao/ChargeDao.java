package com.dms.dao;

import com.dms.dto.ChargeDetailDto;

import java.util.List;

public interface ChargeDao {

	void audit(ChargeDetailDto chargeDetailDto);

	List<ChargeDetailDto> getChargeDetails();
}
