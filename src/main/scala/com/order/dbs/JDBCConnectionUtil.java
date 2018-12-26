package com.order.dbs;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author yafei.hou
 * 2017-09-26 2:19 PM
 */
public class JDBCConnectionUtil  {

    private static JDBCPool jdbcPool = new JDBCPool() ;

    /**
     * 使用数据连接池连接数据库
     * @return Connection 数据库的连接对象
     *
     */
    public static Connection getConnection() {
        try {
            return jdbcPool.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 释放数据库资源，实际上是将数据库连接对象放回的数据库连接池
     * @param connection 数据库连接对象
     * @param statement 数据库连接状态
     * @param resultSet 数据库返回数据表的指针
     */
    public static void releaseConnection(Connection connection, Statement statement, ResultSet resultSet){

        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

}
