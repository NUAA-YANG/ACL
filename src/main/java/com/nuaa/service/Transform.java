package com.nuaa.service;

import com.nuaa.pojo.Iptables;

/**
 * @Author YZX
 * @Create 2023-04-18 10:59
 * @Java-version jdk1.8
 */
//将iptables类转化为命令
public class Transform {
    public static void main(String[] args) {
        MatchPro matchPro = new MatchPro();
        //Iptables iptables = matchPro.turnAclToIptables("rule 10 permit ip 12.10.8.0 0.0.0.255 any");
        Iptables iptables = matchPro.turnAclToIptables("rule 140 deny tcp any eq 8011 any");
        Transform transform = new Transform();
        String line = transform.turnToLine(iptables);
        System.out.println(line);
    }

    public String turnToLine(Iptables iptables){
        StringBuffer sb = new StringBuffer("iptables -t");
        //添加插入表
        sb.append(" "+iptables.getTable());
        //添加插入规则
        sb.append(" -"+iptables.getRule()+" "+iptables.getChain());
        //添加源地址范围
        if (iptables.getSIp()!=null && iptables.getSNetmask()!=null){
            if (!"any".equals(iptables.getSIp())){
                //给了指定的范围，添加源地址和子网掩码
                sb.append(" -s "+iptables.getSIp()+"/"+iptables.getSNetmask());
            }else {
                //允许所有源ip，直接进行添加
                sb.append("-m iprange --dst-range 0.0.0.0-255.255.255.255");
            }
        }

        //添加目的地址范围
        if (iptables.getDIp()!=null && iptables.getDNetmask()!=null){
            if (!"any".equals(iptables.getDIp())){
                //给了指定的范围，添加源地址和子网掩码
                sb.append(" -d "+iptables.getDIp()+"/"+iptables.getDNetmask());
            }else {
                //允许所有源ip，直接进行添加
                sb.append("-m iprange --src-range 0.0.0.0-255.255.255.255");
            }
        }





        //添加行为规则
        if (iptables.getJudge()!=null){
            if ("permit".equals(iptables.getJudge())){
                sb.append(" -j ACCEPT");
            }else {
                sb.append(" -j DROP");
            }
        }
        return new String(sb);
    }
}
