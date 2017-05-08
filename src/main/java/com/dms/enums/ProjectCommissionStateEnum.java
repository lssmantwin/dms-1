package com.dms.enums;

public enum ProjectCommissionStateEnum {

	NO_COMMISSION(1), FIRST_COMMISSION(2), END_COMMISSION(3);

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
