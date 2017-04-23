package com.dms.enums;

public enum PositionEnum {

	CEO(1), CTO(2), HR(3), DEVELOPER(4), ADMIN(5), IT(6);

	private int dbConstant;

	PositionEnum(int dbConstant) {
		this.dbConstant = dbConstant;
	}

	public int getDbConstant() {
		return dbConstant;
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
