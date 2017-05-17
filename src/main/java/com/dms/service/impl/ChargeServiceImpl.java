package com.dms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dms.dao.ChargeDao;
import com.dms.dto.ChargeDetailDto;
import com.dms.service.ChargeService;

import java.util.List;

@Service
public class ChargeServiceImpl implements ChargeService {

	@Autowired
	private ChargeDao chargeDao;

	@Override
	public void audit(ChargeDetailDto chargeDetailDto) {
		chargeDao.audit(chargeDetailDto);
	}

	@Override
	public List<ChargeDetailDto> getChargeDetails() {
		return chargeDao.getChargeDetails();
	}

}
