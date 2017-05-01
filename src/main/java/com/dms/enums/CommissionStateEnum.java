package com.dms.enums;

public enum CommissionStateEnum implements Enums {

	STATE1(1, "佣金状态1"), STATE2(2, "佣金状态2"), STATE3(3, "佣金状态3"), STATE4(4, "佣金状态4");

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
