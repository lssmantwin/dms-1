package com.dms.enums;

public enum PositionEnum implements Enums {

	CEO(1, "CEO"), CTO(2, "CTO"), HR(3, "HR"), DEV(4, "DEVELOPER"), ADMIN(5, "ADMIN"), IT(6, "IT");

	private int dbConstant;
	private String text;

	PositionEnum(int dbConstant, String text) {
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

	public static PositionEnum fromDbConstant(int dbConstant) {
		for (PositionEnum pe : values()) {
			if (pe.getDbConstant() == dbConstant) {
				return pe;
			}
		}
		return null;
	}
}
