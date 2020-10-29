package com.rice.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author wgz
 * @description
 * @date 2020/10/29 10:03
 */
public class NewTimeAPIDemo {

    public static void main(String[] args) {
//        localDate();
//        localTime();
        localDateTime();
    }

    /**
     * 类获取日期时间信息。格式为 yyyy-MM-ddTHH:mm:ss.SSS
     * @author wgz
     * @date 2020/10/29
     */
    private static void localDateTime() {
        LocalDateTime nowDateTime = LocalDateTime.now();
        System.out.println("现在是：" + nowDateTime);
        // 年
        System.out.println(nowDateTime.getYear());
        // 月
        System.out.println(nowDateTime.getMonthValue());
        // 日
        System.out.println(nowDateTime.getDayOfMonth());
        // 时
        System.out.println(nowDateTime.getHour());
        // 分
        System.out.println(nowDateTime.getMinute());
        // 秒
        System.out.println(nowDateTime.getSecond());
        // 纳秒
        System.out.println(nowDateTime.getNano());
        // 当年的第几天
        System.out.println("是今年的第" + nowDateTime.getDayOfYear() + "天");
        // 星期
        System.out.println(nowDateTime.getDayOfWeek());
        System.out.println(nowDateTime.getDayOfWeek().getValue());
        // 月份
        System.out.println(nowDateTime.getMonth());
        System.out.println(nowDateTime.getMonth().getValue());
    }

    /**
     * 类获取时间，格式为 HH:mm:ss.SSS
     * @author wgz
     * @date 2020/10/29
     */
    private static void localTime() {
        // HH:mm:ss.SSS
        LocalTime now = LocalTime.now();
        System.out.println("现在的时间是" + now);
        // 小时
        int hour = now.getHour();
        // 分钟
        int minute = now.getMinute();
        // 秒
        int second = now.getSecond();
        // 纳秒
        int nano = now.getNano();
        System.out.println("现在的时间是"
                + hour + "时"
                + minute + "分"
                + second + "秒"
                + nano);
    }

    /**
     * 类获取日期信息，格式为 yyyy-MM-dd
     * @author wgz
     * @date 2020/10/29
     */
    private static void localDate() {
        // yyyy-MM-dd 格式的日期
        LocalDate today = LocalDate.now();
        System.out.println("今天的日期是：" + today);
        // 获取年的常用方法
        int year = today.getYear();
        // 获取月的常用方法，
        int monthValue = today.getMonthValue();
        // 获取日的常用方法，返回的是当月的第几天
        int day = today.getDayOfMonth();
        System.out.println("今天的日期是："
                + year + "年"
                + monthValue + "月"
                + day + "日");

        // 当年的第几天
        int dayOfYear = today.getDayOfYear();
        System.out.println("是今年的第" + dayOfYear + "天");
        // 星期
        System.out.println(today.getDayOfWeek());
        System.out.println(today.getDayOfWeek().getValue());
        // 月份，today.getMonth() 方法返回的是一个枚举类的值
        System.out.println(today.getMonth());
        System.out.println(today.getMonth().getValue());
    }
}
