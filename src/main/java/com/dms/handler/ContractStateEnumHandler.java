package com.dms.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.dms.enums.ContractStateEnum;

public class ContractStateEnumHandler extends BaseTypeHandler<ContractStateEnum> {

	@Override
	public void setNonNullParameter(PreparedStatement preparedStatement, int i, ContractStateEnum stateEnum, JdbcType jdbcType) throws SQLException {
		preparedStatement.setInt(i, stateEnum.getDbConstant());
	}

	@Override
	public ContractStateEnum getNullableResult(ResultSet resultSet, String s) throws SQLException {
		return ContractStateEnum.fromDbConstant(resultSet.getInt(s));
	}

	@Override
	public ContractStateEnum getNullableResult(ResultSet resultSet, int i) throws SQLException {
		return ContractStateEnum.fromDbConstant(resultSet.getInt(i));
	}

	@Override
	public ContractStateEnum getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
		return ContractStateEnum.fromDbConstant(callableStatement.getInt(i));
	}
}
