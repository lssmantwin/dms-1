package com.dms.handler;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class BigDecimalHandler extends BaseTypeHandler<BigDecimal> {
	@Override
	public void setNonNullParameter(PreparedStatement preparedStatement, int i, BigDecimal bigDecimal, JdbcType jdbcType) throws SQLException {
		preparedStatement.setBigDecimal(i, bigDecimal);
	}

	@Override
	public BigDecimal getNullableResult(ResultSet resultSet, String s) throws SQLException {
		return resultSet.getBigDecimal(s);
	}

	@Override
	public BigDecimal getNullableResult(ResultSet resultSet, int i) throws SQLException {
		return resultSet.getBigDecimal(i);
	}

	@Override
	public BigDecimal getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
		return callableStatement.getBigDecimal(i);
	}
}
