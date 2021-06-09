package org.apache.jmeter.functions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class testList {
    public static void main(String[] args) {
        getIdNo("1");
    }



    public static String getIdNo(String sex){
        //随机生成生日 1~99岁
        long begin = System.currentTimeMillis() - 3153600000000L;//100年内
        long end = System.currentTimeMillis() - 31536000000L; //1年内
        long rtn = begin + (long) (Math.random() * (end - begin));
        Date date = new Date(rtn);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMdd");
        String birth = simpleDateFormat.format(date);
        System.out.println(birth);
        return null;
    }
}
