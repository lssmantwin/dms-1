package com.dms.enums;

import com.alibaba.druid.util.StringUtils;

public enum ContractStateEnum implements Enums {

	STATE1(1, "合同状态1"), STATE2(2, "合同状态2"), STATE3(3, "合同状态3"), STATE4(4, "合同状态4");

	private int dbConstant;
	private String text;

	ContractStateEnum(int dbConstant, String text) {
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

	public static ContractStateEnum fromDbConstant(int dbConstant) {
		for (ContractStateEnum state : values()) {
			if (state.getDbConstant() == dbConstant) {
				return state;
			}
		}
		return null;
	}

	public static ContractStateEnum fromText(String text) {
		for (ContractStateEnum state : values()) {
			if (StringUtils.equals(state.getText(), text)) {
				return state;
			}
		}
		return null;
	}
}
