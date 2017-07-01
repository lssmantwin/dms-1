package com.dms.ws.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dms.aspect.CheckAuthority;
import com.dms.dto.ChargeDetailDto;
import com.dms.enums.ResponseEnum;
import com.dms.request.ChargeFilterRequest;
import com.dms.response.DmsResponse;
import com.dms.service.ChargeService;
import com.dms.ws.ChargeWebService;

@Service
public class ChargeWebServiceImpl implements ChargeWebService {

	@Autowired
	private ChargeService chargeService;

	@Override
	@CheckAuthority
	public DmsResponse getChargeDetails(String employeeName, String chargeTime, String chargeBalance) {
		DmsResponse response = new DmsResponse();
		ChargeFilterRequest request = new ChargeFilterRequest();
		request.setEmployeeName(employeeName);
		request.setChargeBalance(chargeBalance);
		request.setChargeTime(chargeTime);
		List<ChargeDetailDto> detailDtos = chargeService.getChargeDetails(request);
		response.setCode(ResponseEnum.SUCCESS);
		response.setData(detailDtos);
		return response;
	}
}
