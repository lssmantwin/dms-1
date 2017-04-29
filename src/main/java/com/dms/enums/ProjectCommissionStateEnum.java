package com.dms.enums;

public enum ProjectCommissionStateEnum {

	NO_COMMISION(1), FIRST_COMMISION(2), END_COMMISION(3);

	private int dbConstant;

	ProjectCommissionStateEnum(int dbConstant) {
		this.dbConstant = dbConstant;
	}

	public int getDbConstant() {
		return dbConstant;
	}

	public static ProjectCommissionStateEnum fromDbConstant(int dbConstant) {
		for (ProjectCommissionStateEnum pe : values()) {
			if (pe.getDbConstant() == dbConstant) {
				return pe;
			}
		}
		return null;
	}
}
