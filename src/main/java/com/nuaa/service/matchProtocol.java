package com.nuaa.service;

import java.io.*;

/**
 * @Author YZX
 * @Create 2023-04-17 11:09
 * @Java-version jdk1.8
 */
//匹配语句
public class matchProtocol {
    public static void main(String[] args) throws IOException {
        readText();
    }
    public static void readText() throws IOException {
        //创建io流
        FileInputStream fis = new FileInputStream("src/main/java/com/nuaa/text/0117.text");
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader reader = new BufferedReader(isr);
        //line用来接受读取的每一行
        String line = null;
        while ((line = reader.readLine())!=null){
            System.out.println(line);
        }
    }
}
