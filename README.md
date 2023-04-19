# 一. 目的
要实现中兴路由器的`ACL`命令转化为`Iptables`命令

# 二. 思路
1. 读取配置文件，返回读取的满足的规则`ACL`集合
2. 将`ACL`集合转化为`Iptables`类
3. 将`Iptables`类存入到数据库中
4. 从数据库中读取`Iptables`类集合
5. 将`Iptables`类集合转化为`Iptables`命令行
6. 将`Iptables`命令行写入文本文件

# 三. 代码说明
进入主方法`src/main/java/com/nuaa/service/Conversion.java`可知
        