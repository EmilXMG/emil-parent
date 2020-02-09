package com.wf.ew.common.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author emil
 */
public class DataTableOperate {
    private static Connection conn;

    public static void createTable(String tableName, String dbName, String serviceName, String userName, String userPwd, String tableSqlName) {
        String sql = "CREATE TABLE " + tableSqlName + " (" +
                "  rowGuid varchar(50) NOT NULL COMMENT '全局唯一标识'," +
                "  operateUserName varchar(50) DEFAULT NULL COMMENT '操作用户'," +
                "  operateDate datetime DEFAULT NULL COMMENT '操作日期'," +
                "  PRIMARY KEY (rowGuid)" +
                ") COMMENT='" + tableName + "' ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";
        conn = DatabaseConnection.DatabaseConnection(dbName, serviceName, userName, userPwd);
        Statement stmt = null;
        try {
            System.out.println("获取数据库连接成功！");
            stmt = conn.createStatement();
            stmt.executeLargeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("获取数据库连接失败！");
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    public static void deleteTable(String dbName, String serviceName, String userName, String userPwd, String tableName) {
        String sql = "drop table " + tableName;
        conn = DatabaseConnection.DatabaseConnection(dbName, serviceName, userName, userPwd);
        Statement stmt = null;
        try {
            System.out.println("获取数据库连接成功！");
            stmt = conn.createStatement();
            stmt.executeLargeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("获取数据库连接失败！");
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
