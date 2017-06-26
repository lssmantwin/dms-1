package com.dms.ws.impl;

import java.util.List;

import com.dms.aspect.CheckAuthority;
import com.dms.enums.ResponseEnum;
import com.dms.request.BaseFilterRequest;
import com.dms.response.DmsResponse;
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
	@CheckAuthority
	public DmsResponse getChargeDetails(String employeeName) {
		DmsResponse response = new DmsResponse();
		BaseFilterRequest request = new BaseFilterRequest();
		request.setEmployeeName(employeeName);
		List<ChargeDetailDto> detailDtos = chargeService.getChargeDetails(request);
		response.setCode(ResponseEnum.SUCCESS);
		response.setData(detailDtos);
		return response;
	}
}
