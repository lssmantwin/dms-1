package com.dms.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class BooleanHandler extends BaseTypeHandler<Boolean> {

	@Override
	public void setNonNullParameter(PreparedStatement preparedStatement, int i, Boolean aBoolean, JdbcType jdbcType) throws SQLException {
		preparedStatement.setInt(i, aBoolean ? 1 : 0);
	}

	@Override
	public Boolean getNullableResult(ResultSet resultSet, String s) throws SQLException {
		return resultSet.getBoolean(s);
	}

	@Override
	public Boolean getNullableResult(ResultSet resultSet, int i) throws SQLException {
		return resultSet.getBoolean(i);
	}

	@Override
	public Boolean getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
		return callableStatement.getBoolean(i);
	}
}
