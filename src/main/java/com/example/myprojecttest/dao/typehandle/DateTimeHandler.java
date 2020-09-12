package com.example.myprojecttest.dao.typehandle;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

/**
 * @description:
 * @author: luyuxiong
 * @date: 2020/9/12 23:26
 */
public class DateTimeHandler implements TypeHandler<LocalDateTime> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, LocalDateTime localDateTime, JdbcType jdbcType) throws SQLException {
        Timestamp timestamp = Timestamp.from(localDateTime.toInstant(ZoneOffset.of("+8")));
        preparedStatement.setTimestamp(i, timestamp);
    }

    @Override
    public LocalDateTime getResult(ResultSet resultSet, String s) throws SQLException {
        Timestamp timestamp = resultSet.getTimestamp(s);
        if (null != timestamp) {
            LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp.getTime()), ZoneId.systemDefault());
            return localDateTime;
        }
        return null;
    }

    @Override
    public LocalDateTime getResult(ResultSet resultSet, int i) throws SQLException {
        Timestamp timestamp = resultSet.getTimestamp(i);
        if (null != timestamp) {
            LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp.getTime()), ZoneId.systemDefault());
            return localDateTime;
        }
        return null;
    }

    @Override
    public LocalDateTime getResult(CallableStatement callableStatement, int i) throws SQLException {
        Timestamp timestamp = callableStatement.getTimestamp(i);
        if (null != timestamp) {
            LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp.getTime()), ZoneId.systemDefault());
            return localDateTime;
        }
        return null;
    }
}
