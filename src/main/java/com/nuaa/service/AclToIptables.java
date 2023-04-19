package com.nuaa.service;

import com.nuaa.pojo.Iptables;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author YZX
 * @Create 2023-04-17 15:17
 * @Java-version jdk1.8
 */
//匹配语句
public class AclToIptables {
    public static void main(String[] args) {
        AclToIptables aclToIptables = new AclToIptables();
        //System.out.println(matchPro.turnAclToIptables("rule 10 permit ip 12.10.8.0 0.0.0.255 any"));
        System.out.println(aclToIptables.turnAclToIptables("rule 140 deny tcp any eq 8011 any"));
        //System.out.println(matchPro.turnAclToIptables("rule 100 deny tcp any eq 5900 any"));

    }


    //将单条ACL语句转化为 Iptables 类
    public Iptables turnAclToIptables(String line){
        Iptables iptables = new Iptables();
        PreData treatment = new PreData();
        //匹配行为和协议
        String Regex = "rule\\s+\\d*\\s*(permit|deny)(?:\\s+(tcp|udp|icmp|ip))?" +
                //匹配源ip地址 或 “any” 关键字
        "(?:\\s+((?:\\d{1,3}\\.){3}\\d{1,3}|\\w+))?" +
                //匹配源子网掩码
        "(?:\\s+((?:\\d{1,3}\\.){3}\\d{1,3}))?" +
                //匹配源端口“range”关键字、起始端口和结束端口  或   端口“eq”关键字和端口值
        "(?:\\s+(range)\\s+(\\d+)-(\\d+)|\\s+(eq|ge|le)\\s+(\\d+))?" +
                //匹配目的ip地址 或 “any” 关键字
        "(?:\\s+((?:\\d{1,3}\\.){3}\\d{1,3}|\\w+))?" +
                //匹配目的子网掩码
        "(?:\\s+((?:\\d{1,3}\\.){3}\\d{1,3}))?" +
                //匹配目的端口“range”关键字、起始端口和结束端口  或   端口“eq”关键字和端口值
        "(?:\\s+(range)\\s+(\\d+)-(\\d+)|\\s+(eq|ge|le)\\s+(\\d+))?" +
                //匹配两个特殊关键字
        "(?:\\s+(established))?" +
        "(?:\\s+precedence\\s+\\d+)?";

        Pattern pattern = Pattern.compile(Regex);
        Matcher matcher = pattern.matcher(line);
        matcher.find();
        //用于记录属性
        List<String> proList = new ArrayList<>();
        //将属性封装到集合当中
        for (int i = 1 ; i<17 ; i++){
            if (matcher.group(i)!=null){
                proList.add(matcher.group(i));
            }else {
                proList.add(null);
            }
        }
        //将集合中的属性转化为实体类
        iptables.setId(0);
        iptables.setTable("filter");
        iptables.setRule("I");
        iptables.setChain("INPUT");

        iptables.setJudge(proList.get(0));
        iptables.setProtocol(proList.get(1));

        iptables.setSIp(proList.get(2));
        if (proList.get(3)!=null){
            //将二进制的子网掩码转化为数字
            iptables.setSNetmask(treatment.netmaskToNum(proList.get(3)));
        }
        iptables.setSRange(proList.get(4));
        iptables.setSStartPort(proList.get(5));
        iptables.setSDestPort(proList.get(6));
        iptables.setSEq(proList.get(7));
        iptables.setSPort(proList.get(8));

        iptables.setDIp(proList.get(9));
        if (proList.get(10)!=null){
            //将二进制的子网掩码转化为数字
            iptables.setDNetmask(treatment.netmaskToNum(proList.get(10)));
        }
        iptables.setDRange(proList.get(11));
        iptables.setDStartPort(proList.get(12));
        iptables.setDDestPort(proList.get(13));
        iptables.setDEq(proList.get(14));
        iptables.setDPort(proList.get(15));

        return iptables;

    }

    //将ACL集合语句转化为 Iptables 类，返回转化的Iptables集合
    public List<Iptables> turnAclToIptables(List<String> listAcl){
        //用来返回结果
        List<Iptables> iptablesList = new ArrayList<>();
        //遍历ACL列表，将每个语句封装为Iptables对象并且存入集合
        for (int i = 0; i < listAcl.size(); i++) {
            Iptables iptables = turnAclToIptables(listAcl.get(i));
            iptablesList.add(iptables);
        }
        return iptablesList;
    }
}
