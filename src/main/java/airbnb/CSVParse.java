package Airbnb;

import java.util.ArrayList;

/*
题目是关于如何Parse csv file：举个例子，给定一个CSV文件，格式是
“some_name|some_address|some_phone|some_job”，
要求输出:
“{name:some_name, address:some_addres,phone:some_phone, job:some_job}”
特殊情为两个引号之间的分号，不可作为分割字符 http://itjob.io/post/349
John,Smith,john.smith@gmail.com,Los Angeles,1
Jane,Roberts,janer@msn.com,"San Francisco, CA",0
"Alexandra ""Alex""",Menendez,alex.menendez@gmail.com,Miami,1
"""Alexandra Alex"""

John|Smith|john.smith@gmail.com|Los Angeles|1
Jane|Roberts|janer@msn.com|San Francisco, CA|0
Alexandra "Alex"|Menendez|alex.menendez@gmail.com|Miami|1
"Alexandra Alex"

要考虑 quote, escape \得情况
*/

public class CSVParse {
    public CSVParse() {
    }

    public void main(String[] args) {
        CSVParse parser = new CSVParse();
        //#1
        ArrayList<String> output = parser.parseCSV("John,Smith,john.smith@gmail.com,Los Angeles,1");
        System.out.println(output.toString());
        //#2
        output = parser.parseCSV("Jane,Roberts,janer@msn.com,\"San Francisco, CA\",0");
        System.out.println(output.toString());
        //#3
        output = parser.parseCSV("\"Alexandra \"\"Alex\"\"\",Menendez,alex.menendez@gmail.com,Miami,1");
        System.out.println(output.toString());
    }

    private ArrayList<String> parseCSV(String s) {
        return null;
    }
}
