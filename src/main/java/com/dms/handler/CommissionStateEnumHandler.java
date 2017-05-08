package com.dms.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.dms.enums.CommissionStateEnum;

public class CommissionStateEnumHandler extends BaseTypeHandler<CommissionStateEnum> {

	@Override
	public void setNonNullParameter(PreparedStatement preparedStatement, int i, CommissionStateEnum stateEnum, JdbcType jdbcType) throws SQLException {
		preparedStatement.setInt(i, stateEnum.getDbConstant());
	}

	@Override
	public CommissionStateEnum getNullableResult(ResultSet resultSet, String s) throws SQLException {
		return CommissionStateEnum.fromDbConstant(resultSet.getInt(s));
	}

	@Override
	public CommissionStateEnum getNullableResult(ResultSet resultSet, int i) throws SQLException {
		return CommissionStateEnum.fromDbConstant(resultSet.getInt(i));
	}

	@Override
	public CommissionStateEnum getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
		return CommissionStateEnum.fromDbConstant(callableStatement.getInt(i));
	}
}
