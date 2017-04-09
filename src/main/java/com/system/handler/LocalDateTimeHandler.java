package com.system.handler;

import java.sql.*;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.joda.time.LocalDateTime;

public class LocalDateTimeHandler extends BaseTypeHandler<LocalDateTime> {
	@Override
	public void setNonNullParameter(PreparedStatement preparedStatement, int i, LocalDateTime localDateTime, JdbcType jdbcType) throws SQLException {
		// preparedStatement.setTimestamp(i, new Timestamp(localDateTime.toDate().getTime()));
		preparedStatement.setDate(i, new Date(localDateTime.toDate().getTime()));
	}

	@Override
	public LocalDateTime getNullableResult(ResultSet resultSet, String s) throws SQLException {
		return new LocalDateTime(resultSet.getDate(s).getTime());
	}

	@Override
	public LocalDateTime getNullableResult(ResultSet resultSet, int i) throws SQLException {
		return new LocalDateTime(resultSet.getDate(i).getTime());
	}

	@Override
	public LocalDateTime getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
		return new LocalDateTime(callableStatement.getDate(i).getTime());
	}
}
