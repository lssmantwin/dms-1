package com.dms.dao;

import com.dms.dto.ChargeDetailDto;
import com.dms.request.BaseFilterRequest;
import com.dms.request.ChargeFilterRequest;

import java.util.List;

public interface ChargeDao {

	void audit(ChargeDetailDto chargeDetailDto);

	List<ChargeDetailDto> getChargeDetails(ChargeFilterRequest request);
}
