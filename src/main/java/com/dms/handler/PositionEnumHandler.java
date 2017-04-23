package com.dms.handler;

import com.dms.enums.PositionEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PositionEnumHandler extends BaseTypeHandler<PositionEnum> {

	@Override
	public void setNonNullParameter(PreparedStatement preparedStatement, int i, PositionEnum positionEnum, JdbcType jdbcType) throws SQLException {
		preparedStatement.setInt(i, positionEnum.getDbConstant());
	}

	@Override
	public PositionEnum getNullableResult(ResultSet resultSet, String s) throws SQLException {
		return PositionEnum.fromDbConstant(resultSet.getInt(s));
	}

	@Override
	public PositionEnum getNullableResult(ResultSet resultSet, int i) throws SQLException {
		return PositionEnum.fromDbConstant(resultSet.getInt(i));
	}

	@Override
	public PositionEnum getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
		return PositionEnum.fromDbConstant(callableStatement.getInt(i));
	}
}
