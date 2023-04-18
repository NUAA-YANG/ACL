package com.nuaa.service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author YZX
 * @Create 2023-04-17 11:09
 * @Java-version jdk1.8
 */
//数据预处理
public class PreTreatment {
    public static void main(String[] args) throws IOException {
        PreTreatment treatment = new PreTreatment();
        treatment.readText();
    }

    //将文本文件中所有的和ACL相关的命令读取出来存入list集合中
    public List<String> readText() throws IOException {
        //创建集合来存储读取的相关ACL语句
        List<String> listAcl = new ArrayList<>();
        //创建io流
        FileInputStream fis = new FileInputStream("src/main/java/com/nuaa/text/0117.text");
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader reader = new BufferedReader(isr);
        //line用来接受读取的每一行
        String line = null;
        while ((line = reader.readLine())!=null){
            //找到全部的
            if (line.trim().startsWith("rule")){
                System.out.println(line.trim());
            }
        }
        return listAcl;
    }

    //将ACL中点进制的子网掩码字符串转化为数字【ACL的子网掩码0表示匹配，1表示不匹配】
    //所以转化为子网掩码之后要用32减一下
    public String netmaskToNum(String netmask){
        int result = 0 ;
        //基于.进行拆分[注意要进行转义]
        String[] splitMask = netmask.split("\\.");
        for (int i = 0; i<splitMask.length;i++){
            String binaryMask = Integer.toBinaryString(Integer.parseInt(splitMask[i]));
            result+=calculateOne(binaryMask);
        }
        return String.valueOf((32-result));
    }


    //计算字符串中有多少个连续的1
    public int calculateOne(String str){
        int one = 0 ;
        for (int i = 0 ;i<str.length();i++){
            if (str.charAt(i)=='0'){
                break;
            }else {
                one++;
            }
        }
        return one;
    }
}
