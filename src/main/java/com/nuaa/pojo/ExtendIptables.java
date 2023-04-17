package com.nuaa.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author YZX
 * @Create 2023-04-17 10:40
 * @Java-version jdk1.8
 */
//协议的扩展模块
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExtendIptables {
    //id，自增
    Integer id;
    //扩展类型名称
    //常见的有： -m tcp 表示扩展协议，-m multiport 表示扩展端口，-m iprange 表示扩展ip范围
    String extendName;
    //标识源端口或目的端口，sport 表示源端口，dport 表示目的端口
    String flagPort;
    //端口【举例：单个端口为25，端口范围为22:25】
    String port;
    //标识源ip范围或目的ip范围，src-range 表示源ip范围，dst-range 表示目的ip范围
    String flagIpRange;
    //ip范围
    String ipRange;
}
