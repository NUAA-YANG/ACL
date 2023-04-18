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
public class MatchPro {
    public static void main(String[] args) {
        MatchPro matchPro = new MatchPro();
        System.out.println(matchPro.turnAclToIptables("rule 10 permit ip 12.10.8.0 0.0.0.255 any"));
        //System.out.println(matchPro.turnAclToIptables("rule 140 deny tcp any eq 8011 any"));
        //System.out.println(matchPro.turnAclToIptables("rule 100 deny tcp any eq 5900 any"));

    }

    //将语句转化为 Iptables 类
    public Iptables turnAclToIptables(String line){
        Iptables iptables = new Iptables();
        PreTreatment treatment = new PreTreatment();
        String Regex = "rule\\s+\\d*\\s*(permit|deny)(?:\\s+(tcp|udp|icmp|ip))?" +
        "(?:\\s+((?:\\d{1,3}\\.){3}\\d{1,3}|\\w+))?" +
        "(?:\\s+((?:\\d{1,3}\\.){3}\\d{1,3}))?" +
        "(?:\\s+(range)\\s+(\\d+)-(\\d+)|\\s+(eq|ge|le)\\s+(\\d+))?" +
        "(?:\\s+((?:\\d{1,3}\\.){3}\\d{1,3}|\\w+))?" +
        "(?:\\s+((?:\\d{1,3}\\.){3}\\d{1,3}))?" +
        "(?:\\s+(range)\\s+(\\d+)-(\\d+)|\\s+(eq|ge|le)\\s+(\\d+))?" +
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
}
