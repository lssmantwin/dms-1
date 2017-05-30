package com.dms.enums;

import org.apache.commons.lang3.StringUtils;

public enum CompanyEnum implements Enums {

	JY(1, "佳园"), JN(2, "进念");

	private int dbConstant;
	private String text;

	CompanyEnum(int dbConstant, String text) {
		this.dbConstant = dbConstant;
		this.text = text;
	}

	@Override
	public int getDbConstant() {
		return dbConstant;
	}

	@Override
	public String getText() {
		return text;
	}

	public static CompanyEnum fromDbConstant(int dbConstant) {
		for (CompanyEnum c : values()) {
			if (c.getDbConstant() == dbConstant) {
				return c;
			}
		}
		return null;
	}

	public static CompanyEnum fromText(String text) {
		for (CompanyEnum c : values()) {
			if (StringUtils.equals(c.getText(), text)) {
				return c;
			}
		}
		return null;
	}
}
