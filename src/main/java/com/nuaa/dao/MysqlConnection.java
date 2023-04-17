package com.nuaa.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @Author YZX
 * @Create 2023-04-17 9:47
 * @Java-version jdk1.8
 */
public class MysqlConnection {

    //连接数据库
    public Connection getConnection(){
        String url="jdbc:mysql://192.168.31.104:3306/DHCPPools?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false";
        String user="root";
        String password="root";
        Connection conn = null;
        try {
            //1. 加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2. 获取连接
            conn = DriverManager.getConnection(url,user,password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
