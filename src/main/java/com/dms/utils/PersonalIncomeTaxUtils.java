package com.dms.utils;

import java.math.BigDecimal;

public final class PersonalIncomeTaxUtils {

	private PersonalIncomeTaxUtils() {
	}

	public static BigDecimal getPersonalIncomeTax(BigDecimal beforeTaxSalary) {
		if (beforeTaxSalary == null) {
			beforeTaxSalary = BigDecimal.ZERO;
		}
		BigDecimal threshold = beforeTaxSalary.subtract(BigDecimal.valueOf(3500));
		if (threshold.compareTo(BigDecimal.ZERO) <= 0) {
			return BigDecimal.ZERO;
		} else if (threshold.compareTo(BigDecimal.valueOf(1500)) <= 0) {
			return threshold.multiply(BigDecimal.valueOf(0.03));
		} else if (threshold.compareTo(BigDecimal.valueOf(4500)) <= 0) {
			return threshold.multiply(BigDecimal.valueOf(0.1)).subtract(BigDecimal.valueOf(105));
		} else if (threshold.compareTo(BigDecimal.valueOf(9000)) <= 0) {
			return threshold.multiply(BigDecimal.valueOf(0.2)).subtract(BigDecimal.valueOf(555));
		} else if (threshold.compareTo(BigDecimal.valueOf(35000)) <= 0) {
			return threshold.multiply(BigDecimal.valueOf(0.25)).subtract(BigDecimal.valueOf(1005));
		} else if (threshold.compareTo(BigDecimal.valueOf(55000)) <= 0) {
			return threshold.multiply(BigDecimal.valueOf(0.3)).subtract(BigDecimal.valueOf(2755));
		} else if (threshold.compareTo(BigDecimal.valueOf(80000)) <= 0) {
			return threshold.multiply(BigDecimal.valueOf(0.35)).subtract(BigDecimal.valueOf(5505));
		} else {
			return threshold.multiply(BigDecimal.valueOf(0.45)).subtract(BigDecimal.valueOf(13505));
		}
	}
}
