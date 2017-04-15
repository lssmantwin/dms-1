package com.dms.handler;

import java.sql.*;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.joda.time.LocalDateTime;

public class LocalDateTimeHandler extends BaseTypeHandler<LocalDateTime> {
	@Override
	public void setNonNullParameter(PreparedStatement preparedStatement, int i, LocalDateTime localDateTime, JdbcType jdbcType) throws SQLException {
		preparedStatement.setDate(i, new Date(localDateTime.toDate().getTime()));
	}

	@Override
	public LocalDateTime getNullableResult(ResultSet resultSet, String s) throws SQLException {
		Date date = resultSet.getDate(s);
		if (date != null) {
			return new LocalDateTime(date.getTime());
		}
		return null;
	}

	@Override
	public LocalDateTime getNullableResult(ResultSet resultSet, int i) throws SQLException {
		Date date = resultSet.getDate(i);
		if (date != null) {
			return new LocalDateTime(date.getTime());
		}
		return null;
	}

	@Override
	public LocalDateTime getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
		Date date = callableStatement.getDate(i);
		if (date != null) {
			return new LocalDateTime(date.getTime());
		}
		return null;
	}
}
