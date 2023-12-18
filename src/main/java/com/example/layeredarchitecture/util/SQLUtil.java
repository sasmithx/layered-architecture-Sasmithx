package com.example.layeredarchitecture.util;

import com.example.layeredarchitecture.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Locale;

public class SQLUtil {
    public static <T> T execute(String sql, Object... obj) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        for (int i = 0; i < obj.length; i++) {
            preparedStatement.setObject((i + 1), obj[i]);
        }
        if (sql.trim().toUpperCase(Locale.ROOT).startsWith("SELECT")){
            return (T) preparedStatement.executeQuery();
        }else {
            return(T)(Boolean)(preparedStatement.executeUpdate()>0);
        }
    }
}
