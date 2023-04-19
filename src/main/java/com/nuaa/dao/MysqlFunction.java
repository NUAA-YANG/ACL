package com.nuaa.dao;

import com.nuaa.pojo.Iptables;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author YZX
 * @Create 2023-04-18 18:14
 * @Java-version jdk1.8
 */
//实现对数据库iptables类的添加和读取查询
public class MysqlFunction {

    //查询出iptables列表
    public List<Iptables> getList() throws SQLException {
        //返回列表
        List<Iptables> list = new ArrayList<>();
        //获取数据库连接对象
        MysqlConnection connection = new MysqlConnection();
        Connection conn = connection.getConnection();
        //创建执行语句
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from Iptables");
        while (rs.next()){
            Iptables iptables = new Iptables(
                    rs.getInt("id"),
                    rs.getString("table"),
                    rs.getString("rule"),
                    rs.getString("chain"),
                    rs.getString("judge"),
                    rs.getString("protocol"),
                    rs.getString("sIp"),
                    rs.getString("sNetmask"),
                    rs.getString("sRange"),
                    rs.getString("sStartPort"),
                    rs.getString("sDestPort"),
                    rs.getString("sEq"),
                    rs.getString("sPort"),
                    rs.getString("dIp"),
                    rs.getString("dNetmask"),
                    rs.getString("dRange"),
                    rs.getString("dStartPort"),
                    rs.getString("dDestPort"),
                    rs.getString("dEq"),
                    rs.getString("dPort")
            );
            list.add(iptables);
        }
        //关闭连接
        rs.close();;
        stmt.close();
        conn.close();
        return list;
    }

    //插入单个iptables数据，返回值为影响的行数
    public int insertIptables(Iptables iptables) throws SQLException {
        //获取数据库连接对象
        MysqlConnection connection = new MysqlConnection();
        Connection conn = connection.getConnection();
        String sql = "insert into Iptables values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        //设置执行语句
        PreparedStatement ps = conn.prepareStatement(sql);

        //id自增，随便插入即可
        ps.setInt(1,0);
        ps.setString(2,iptables.getTable());
        ps.setString(3,iptables.getRule());
        ps.setString(4,iptables.getChain());

        ps.setString(5,iptables.getJudge());
        ps.setString(6,iptables.getProtocol());

        ps.setString(7,iptables.getSIp());
        ps.setString(8,iptables.getSNetmask());
        ps.setString(9,iptables.getSStartPort());
        ps.setString(10,iptables.getSDestPort());
        ps.setString(11,iptables.getSDestPort());
        ps.setString(12,iptables.getSEq());
        ps.setString(13,iptables.getSPort());

        ps.setString(14,iptables.getDIp());
        ps.setString(15,iptables.getDNetmask());
        ps.setString(16,iptables.getDRange());
        ps.setString(17,iptables.getDStartPort());
        ps.setString(18,iptables.getDDestPort());
        ps.setString(19,iptables.getDEq());
        ps.setString(20,iptables.getDPort());

        //返回执行影响的行数
        int count = ps.executeUpdate();
        //关闭连接
        ps.close();
        conn.close();
        return count;
    }


    //插入iptables列表
    public void insertIptablesList(List<Iptables> iptablesList) throws SQLException {
        //获取数据库连接对象
        MysqlConnection connection = new MysqlConnection();
        Connection conn = connection.getConnection();
        String sql = "insert into Iptables values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        //设置执行语句
        PreparedStatement ps = conn.prepareStatement(sql);
        for (int i = 0; i < iptablesList.size(); i++) {
            Iptables iptables = iptablesList.get(i);
            //id自增，随便插入即可
            ps.setInt(1,0);
            ps.setString(2,iptables.getTable());
            ps.setString(3,iptables.getRule());
            ps.setString(4,iptables.getChain());

            ps.setString(5,iptables.getJudge());
            ps.setString(6,iptables.getProtocol());

            ps.setString(7,iptables.getSIp());
            ps.setString(8,iptables.getSNetmask());
            ps.setString(9,iptables.getSStartPort());
            ps.setString(10,iptables.getSDestPort());
            ps.setString(11,iptables.getSDestPort());
            ps.setString(12,iptables.getSEq());
            ps.setString(13,iptables.getSPort());

            ps.setString(14,iptables.getDIp());
            ps.setString(15,iptables.getDNetmask());
            ps.setString(16,iptables.getDRange());
            ps.setString(17,iptables.getDStartPort());
            ps.setString(18,iptables.getDDestPort());
            ps.setString(19,iptables.getDEq());
            ps.setString(20,iptables.getDPort());

            //添加批处理命令
            ps.addBatch();
            //每五句插入一次
            if (i%5==0){
                ps.executeBatch();
            }
        }
        //防止没有执行完毕
        ps.executeBatch();
        //清空批处理命令
        ps.clearBatch();
        //关闭连接
        ps.close();
        conn.close();
    }
}
