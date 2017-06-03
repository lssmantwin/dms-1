package com.dms.utils;

import java.math.BigDecimal;

/**
 * Created by louis.liu on 2017/4/30.
 */
public interface DmsConstants {
    String JYW_PROJECT= "CG";
    String FX_PROJECT= "FG";
    String SUBURBS_SONGJIANG = "松江";
    String SUBURBS_QINGPU = "青浦";
    BigDecimal MIN_CONTRACT_COMMISSION = new BigDecimal(10000);
    BigDecimal MIN_COMMISSION = new BigDecimal(250);

    BigDecimal DESIGN_ASSISTANT_COMMISSION_RATE = new BigDecimal(0.005);
    BigDecimal DESIGN_ASSISTANT_SUBURBS_COMMISSION_RATE = new BigDecimal(0.006);
    BigDecimal DESIGN_COMMISSION_SUB_RATE = new BigDecimal(0.0001);

}
