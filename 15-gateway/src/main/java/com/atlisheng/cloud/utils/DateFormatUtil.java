package com.atlisheng.cloud.utils;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateFormatUtil {
    public static void main(String[] args) {
        // java.time.ZonedDateTime可以生成Gateway默认时区的时间格式
        // 2023-11-03T07:56:50.123+08:00[Asia/Shanghai]
        ZonedDateTime zbj = ZonedDateTime.now(); // 默认不声明情况下时区是Asia/Shanghai
        System.out.println(zbj);
        // 2023-11-02T19:56:50.124-04:00[America/New_York]
        ZonedDateTime zny = ZonedDateTime.now(ZoneId.of("America/New_York")); // 用指定时区获取当前时间
        System.out.println(zny);
    }
}
