package com.nuaa.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author YZX
 * @Create 2023-04-17 9:55
 * @Java-version jdk1.8
 */
//来存储协议识别的类
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Iptables {
    //id，自增
    Integer id;
    //存储的表结构，默认使用 filter 表示过滤表
    String table;
    //插入规则，常见的使用 -I 表示在最后一条插入，-A 表示在第一条插入，-D 表示删除，-R 表示修改
    String rule;
    //存储的链结构，常见的使用 INPUT 表示进入的流量
    String chain;
    //标识源地址或目的地址，-s 表示源地址，-d 表示目的地址
    String flagIp;
    //IP地址【举例：单个ip为 10.250.143.31，网段为 10.250.143.31/28】
    String ip;
    //子网掩码
    String netmask;
    //标识进入网卡或流出网卡，-i 表示进入，-o 表示流出
    String flagEth;
    //网卡名称
    String eth;
    //标识协议，-p表示协议
    String flagPro;
    //匹配的协议类型，常见的为 tcp、udp、icmp
    String protocol;
    //标识是否拥有扩展协议，1 标识拥有扩展协议，0 标识没有扩展协议
    Integer flagExtendIptables;
    //扩展协议id
    Integer extendIptables_id;
    //行为，常见的为 ACCEPT 表示接受、REJECT 表示拒绝、DROP 表示丢弃
    String judge;
}
