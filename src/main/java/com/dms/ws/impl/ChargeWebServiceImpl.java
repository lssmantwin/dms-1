package com.dms.ws.impl;

import java.util.List;

import com.dms.request.BaseFilterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dms.dto.ChargeDetailDto;
import com.dms.service.ChargeService;
import com.dms.ws.ChargeWebService;

@Service
public class ChargeWebServiceImpl implements ChargeWebService {

	@Autowired
	private ChargeService chargeService;

	@Override
	public List<ChargeDetailDto> getChargeDetails(String employeeName) {
		BaseFilterRequest request = new BaseFilterRequest();
		request.setEmployeeName(employeeName);
		return chargeService.getChargeDetails(request);
	}
}
