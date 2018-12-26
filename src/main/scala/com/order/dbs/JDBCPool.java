package com.order.dbs;


import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Properties;


/**
 * @author yafei.hou
 * 2017-09-26 1:45 PM
 */
public class JDBCPool extends JDBCAdapter {

    private static LinkedList<Connection> linkedList = new LinkedList<>();

    /**
     * 静态区域实现数据库连接
     */
    static {
        //InputStream in = JDBCPool.class.getClassLoader().getResourceAsStream("mysql.properties");
        InputStream in = JDBCPool.class.getClassLoader().getResourceAsStream("sql-pro.properties");

        Properties properties = new Properties();
        try {
            properties.load(in);

            String driver = properties.getProperty("sqlite.driver");
            String url = properties.getProperty("sqlite.url");


            int JdbcConnectionInitial = Integer.parseInt(properties.getProperty("sqlite.jdbcPoolInitSize"));

            Class.forName(driver);

            for (int i = 0; i < JdbcConnectionInitial; i++) {
                Connection connection = DriverManager.getConnection(url);
                linkedList.add(connection);
            }
        } catch (IOException|ClassNotFoundException|SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    public Connection getConnection() throws SQLException {

        if (linkedList == null || linkedList.size()<=0) {
            return null;
        }

        final Connection conn = linkedList.removeFirst() ;
        return (Connection) Proxy.newProxyInstance(JDBCPool.class.getClassLoader(),
                new Class[]{Connection.class},
                (o, method, objects) -> {
                    if (!method.getName().equals("close")) {
                        return method.invoke(conn,objects);
                    }else{
                        linkedList.add(conn);
                        return null;
                    }
                });
    }



}
