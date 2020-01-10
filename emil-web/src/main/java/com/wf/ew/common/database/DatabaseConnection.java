package com.wf.ew.common.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author emil
 */
public class DatabaseConnection {


    public static Connection DatabaseConnection(String dbName, String serviceName, String userName, String userPwd){
        String url = "jdbc:mysql://" + serviceName + "/" + dbName;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("未能成功加载驱动程序，请检查是否导入驱动程序！");
        }
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, userName, userPwd);
            System.out.println("获取数据库连接成功！");
        } catch (SQLException e) {
            System.out.println("获取数据库连接失败！");
        }
        return conn;
    }

    /**
     *数据库连接测试
     */
    public static boolean DatabaseConnectionTest(String dbName, String serviceName, String userName, String userPwd) {
        boolean status;
        String url = "jdbc:mysql://" + serviceName + "/" + dbName;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("未能成功加载驱动程序，请检查是否导入驱动程序！");
        }
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, userName, userPwd);
            System.out.println("获取数据库连接成功！");
            status = true;
        } catch (SQLException e) {
            System.out.println("获取数据库连接失败！");
            status = false;
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return status;
    }
}
