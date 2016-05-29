package com.hengsu.duobao.core;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

public class BillNumberBuilder {
    private static Object locker = new Object();
      
    private static int sn = 0;
      
    public static String nextBillNumber(){
        synchronized (locker){
            if(sn == 9999999999L)
                sn = 0;
            else
                sn++;
            return DateFormatUtils.format(new Date(), "yyyyMMddHHmmss") + StringUtils.leftPad(String.valueOf(sn), 10, '0');
        }
    }
    // 防止创建类的实例
    private BillNumberBuilder(){}
    
}