package com.dms.enums;

public enum CommissionStateEnum implements Enums {

	COMMISSION_STATE_START(0, "未提"), COMMISSION_STATE_FIRST(1, "首提"), COMMISSION_STATE_FINISH(2, "已提");

	private int dbConstant;
	private String text;

	CommissionStateEnum(int dbConstant, String text) {
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

	public static CommissionStateEnum fromDbConstant(int dbConstant) {
		for (CommissionStateEnum state : values()) {
			if (state.getDbConstant() == dbConstant) {
				return state;
			}
		}
		return null;
	}
}
