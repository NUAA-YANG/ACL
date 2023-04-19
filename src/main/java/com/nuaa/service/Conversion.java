package com.nuaa.service;

import com.nuaa.dao.MysqlFunction;
import com.nuaa.pojo.Iptables;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author YZX
 * @Create 2023-04-19 10:24
 * @Java-version jdk1.8
 */
//实现完整的转化
public class Conversion {

    public static void main(String[] args) throws IOException, SQLException {
        //1. 读取配置文件，返回读取的满足的规则ACL集合
        PreData preData = new PreData();
        List<String> listAcl = preData.readText("src/main/java/com/nuaa/text/0117.text");
        //2. 将ACL集合转化为Iptables类
        AclToIptables aclToIptables = new AclToIptables();
        List<Iptables> aclToIptablesList = aclToIptables.turnAclToIptables(listAcl);
        //3. 将Iptables类存入到数据库中
        MysqlFunction function = new MysqlFunction();
        function.insertIptablesList(aclToIptablesList);
        //4. 从数据库中读取Iptables类集合
        List<Iptables> iptablesList = function.getList();
        //5. 将Iptables类集合转化为Iptables命令行
        IptablesToLine iptablesToLine = new IptablesToLine();
        List<String> turnToLine = iptablesToLine.turnToLine(iptablesList);
        //6. 将Iptables命令行写入文本文件
        FinData finData = new FinData();
        finData.writeText(turnToLine,"src/main/java/com/nuaa/text/"+finData.getTextName());
        System.out.println("ACL转化为Iptables成功！");
    }
}
