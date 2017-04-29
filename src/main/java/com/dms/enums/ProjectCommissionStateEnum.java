package com.dms.enums;

public enum ProjectCommisionStateEnum {

	NO_COMMISION(1), FIRST_COMMISION(2), END_COMMISION(3);

	private int dbConstant;

	ProjectCommisionStateEnum(int dbConstant) {
		this.dbConstant = dbConstant;
	}

	public int getDbConstant() {
		return dbConstant;
	}

	public static ProjectCommisionStateEnum fromDbConstant(int dbConstant) {
		for (ProjectCommisionStateEnum pe : values()) {
			if (pe.getDbConstant() == dbConstant) {
				return pe;
			}
		}
		return null;
	}
}
