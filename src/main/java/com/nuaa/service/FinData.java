package com.nuaa.service;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author YZX
 * @Create 2023-04-19 15:07
 * @Java-version jdk1.8
 */
//将转化好的Iptables命令行存储为文本文件
public class FinData {

    public static void main(String[] args) throws IOException {
        FinData finData = new FinData();
//        ArrayList<String> list = new ArrayList<>();
//        list.add("sdfgsdfs");
//        list.add("gregrfdf");
//        list.add("sdfgsdfs");
//        list.add("gregrfdf");
//        list.add("sdfgsdfs");
//        list.add("gregrfdf");
//        list.add("sdfgsdfs");
//        list.add("gregrfdf");
//        String pathName = "src/main/java/com/nuaa/text/out.text";
//        finData.writeText(list,pathName);
        System.out.println(finData.getTextName());
        System.out.println("src/main/java/com/nuaa/text/"+finData.getTextName());
    }


    //按照当前时间生成一个文件名称
    public String getTextName(){
        //格式化当前时间
        SimpleDateFormat formatter= new SimpleDateFormat("yyyyMMdd_HHmmss");
        Date date = new Date(System.currentTimeMillis());
        //获得当前时间
        String nowTime = formatter.format(date);
        return nowTime+"_OutPut.txt";
    }

    //将数据写入文本文件
    public void writeText(List<String> iptablesToLine,String pathName) throws IOException {
        //创建io流，表示追加内容
        FileOutputStream fos = new FileOutputStream(pathName,true);
        OutputStreamWriter osw = new OutputStreamWriter(fos);
        BufferedWriter writer = new BufferedWriter(osw);
        for (int i = 0; i < iptablesToLine.size(); i++) {
            String line = iptablesToLine.get(i);
            writer.write(line);
            //换行
            writer.write("\n");
            //每五条刷新一下
            if (i%5==0){
                writer.flush();
            }
        }
        //防止有未存入的
        writer.flush();
        //关闭io流
        fos.close();
        osw.close();
        writer.close();
    }
}
