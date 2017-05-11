package com.dms.utils;

import java.math.BigDecimal;

/**
 * Created by louis.liu on 2017/4/30.
 */
public interface DmsConstants {
    String COMMISION_STATE_START = "未提";
    String COMMISION_STATE_FIRST = "首提";
    String COMMISION_STATE_FINISH = "已提";
    BigDecimal MIN_CONTRACT_COMMISSION = new BigDecimal(10000);
    BigDecimal MIN_COMMISSION = new BigDecimal(250);

    BigDecimal DESIGN_ASSISTANT_COMMISSION_RATE = new BigDecimal(0.005);
    BigDecimal DESIGN_ASSISTANT_SUBURBS_COMMISSION_RATE = new BigDecimal(0.005);
    BigDecimal DESIGN_COMMISSION_SUB_RATE = new BigDecimal(0.0001);

}
