package com.rice.time;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * @author wgz
 * @description
 * @date 2020/10/29 10:03
 */
public class NewTimeAPIDemo {

    private static final LocalDate NATIONAL_DAY = LocalDate.of(1949, 10, 1);


    public static void main(String[] args) {
//        localDate();
//        localTime();
//        localDateTime();
//        construction();
//        compare();
//        change();
        formatter();
    }

    /**
     * 日期时间格式化
     * @author wgz
     * @date 2020/10/29
     */
    private static void formatter() {
        // 在JDK8之前，时间日期的格式化非常麻烦，
        // 经常使用SimpleDateFormat来进行格式化，但是SimpleDateFormat并不是线程安全的。
        // 在JDK8中，引入了一个全新的线程安全的日期与时间格式器DateTimeFormatter。
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // 正反都能调用format方法。
        String ldtStr = now.format(dtf);
        System.out.println(ldtStr);
        // 正反都能调用format方法。
        String ldtStr1 = dtf.format(now);
        System.out.println(ldtStr1);

        // JDK8获取时间戳(从1970-01-01 00：00：00到当前时间的毫秒值)特别简单。
        // Instant类由一个静态的工厂方法now()可以返回当前时间戳。
        Instant instant = Instant.now();
        System.out.println("当前时间戳是：" + instant);
        // Instant类 转 Date类
        Date date = Date.from(instant);
        System.out.println("当前时间戳是：" + date);
        Date date1 = new Date();
        System.out.println("当前时间戳是：" + date1);
        // Date类 转 Instant类
        Instant instant1 = date1.toInstant();
        System.out.println("当前时间戳是：" + instant1);

        // Timestamp 转 LocalDateTime
        Timestamp time = Timestamp.from(Instant.now());
        LocalDateTime localDateTime = time.toLocalDateTime();
        System.out.println(localDateTime);
        // LocalDateTime 转 Timestamp
        Timestamp time1 = Timestamp.valueOf(LocalDateTime.now());
        System.out.println(time1);
    }

    /**
     * 日期时间修改
     * @author wgz
     * @date 2020/10/29
     */
    private static void change() {
        // ----------LocalDate---------
        LocalDate nowDate = LocalDate.now();
        System.out.println(nowDate + "的一年前是： " + nowDate.minusYears(1));
        System.out.println(nowDate + "的一年后是： " + nowDate.plusYears(1));
        System.out.println(nowDate + "的一月前是： " + nowDate.minusMonths(1));
        System.out.println(nowDate + "的一月后是： " + nowDate.plusMonths(1));
        System.out.println(nowDate + "的一周前是： " + nowDate.minusWeeks(1));
        System.out.println(nowDate + "的一周后是： " + nowDate.plusWeeks(1));
        System.out.println(nowDate + "的一天前是： " + nowDate.minusDays(1));
        System.out.println(nowDate + "的一天后是： " + nowDate.plusDays(1));

        // ----------LocalTime---------
        LocalTime nowTime = LocalTime.now();
        System.out.println(nowTime + "的一小时前是： " + nowTime.minusHours(1));
        System.out.println(nowTime + "的一小时后是： " + nowTime.plusHours(1));
        System.out.println(nowTime + "的一分钟前是： " + nowTime.minusMinutes(1));
        System.out.println(nowTime + "的一分钟后是： " + nowTime.plusMinutes(1));
        System.out.println(nowTime + "的一秒钟前是： " + nowTime.minusSeconds(1));
        System.out.println(nowTime + "的一秒钟后是： " + nowTime.plusSeconds(1));
        System.out.println(nowTime + "的一纳秒前是： " + nowTime.minusNanos(1));
        System.out.println(nowTime + "的一纳秒后是： " + nowTime.plusNanos(1));

        // 还可以直接使用 plus 和 minus 方法来增加和减少日期时间，
        // LocalDateTime、LocalDate、LocalTime 都适用，
        // ChronoUnit 用来表示时间单位，
        // 只需要替换成相应的年、月、周、日、时、分、秒、纳秒，就能减少或增加相应的单位。
        System.out.println(nowDate + "的一年前是： " +
                nowDate.minus(1, ChronoUnit.YEARS));
        System.out.println(nowTime + "的一秒钟后是： " +
                nowTime.plus(1,ChronoUnit.SECONDS));
    }

    /**
     * 日期时间比较
     * @author wgz
     * @date 2020/10/29
     */
    private static void compare() {
        // isBefore()
        // isAfter()
        // equals()
        // ----------LocalDate---------
        LocalDate demoDate = LocalDate.of(2016, 8, 31);
        LocalDate nowDate = LocalDate.now();
        System.out.println(demoDate + "是否是" + nowDate + "？ " + demoDate.equals(nowDate));
        System.out.println(demoDate + "是否在" + nowDate + "之后？ " + demoDate.isAfter(nowDate));
        System.out.println(demoDate + "是否在" + nowDate + "之前？ " + demoDate.isBefore(nowDate));

        // ----------LocalTime---------
        LocalTime demoTime = LocalTime.of(8, 20, 30, 150);
        LocalTime nowTime = LocalTime.now();
        System.out.println(demoTime + "是否是" + nowTime + "？ " + demoTime.equals(nowTime));
        System.out.println(demoTime + "是否在" + nowTime + "之后？ " + demoTime.isAfter(nowTime));
        System.out.println(demoTime + "是否在" + nowTime + "之前？ " + demoTime.isBefore(nowTime));

        // 判断是否是某个节日或者重复事件，使用MonthDay类。
        // 这个类由月日组合，不包含年信息，可以用来代表每年重复出现的一些日期。
        // 它和新的日期库中的其他类一样也都是不可变且线程安全的。
        MonthDay nationalHoliday = MonthDay.of(NATIONAL_DAY.getMonthValue(), NATIONAL_DAY.getDayOfMonth());
        MonthDay today = MonthDay.from(nowDate);
        System.out.println("今天是国庆节吗？ " + today.equals(nationalHoliday));
        // YearMonth表示固定的日期。
        YearMonth ym = YearMonth.of(2008, 2);
        System.out.println(ym + "有多少天？ " + ym.lengthOfMonth());
        // Year表示年。
        Year year = Year.of(2008);
        System.out.println(year + "有多少天？ " + year.length());
        System.out.println(year + "是否是闰年？ " + year.isLeap());
    }

    /**
     * 构造日期时间
     * @author wgz
     * @date 2020/10/29
     */
    private static void construction() {
        // ----------LocalDate---------
        // 直接传入int类型的年、月、日
        System.out.println(LocalDate.of(1997, 7, 1));
        // 把月换成了枚举
        System.out.println(LocalDate.of(1997, Month.JULY, 1));
        LocalDate demoDate = LocalDate.of(1997, 7, 1);
        // 第一个参数为年，第二个参数为当年的第多少天
        System.out.println(LocalDate.ofYearDay(1997, demoDate.getDayOfYear()));
        // 参数为距离1970-01-01的天数
        System.out.println(LocalDate.ofEpochDay(demoDate.toEpochDay()));
        // 字符串
        System.out.println(LocalDate.parse("1997-07-01"));
        // 字符串，对应的格式
        System.out.println(LocalDate.parse("19970701", DateTimeFormatter.ofPattern("yyyyMMdd")));

        // ----------LocalTime---------
        // int类型的时、分
        System.out.println(LocalTime.of(8, 20));
        // int类型的时、分、秒
        System.out.println(LocalTime.of(8, 20, 30));
        // int类型的时、分、秒、纳秒
        System.out.println(LocalTime.of(8, 20, 30, 150));
        LocalTime demoTime = LocalTime.of(8, 20, 30, 150);
        // 参数为距离当天零时的秒数
        System.out.println(LocalTime.ofSecondOfDay(demoTime.toSecondOfDay()));
        // 参数为距离当天零时的纳秒数
        System.out.println(LocalTime.ofNanoOfDay(demoTime.toNanoOfDay()));
        // 字符串
        System.out.println(LocalTime.parse("08:20:30"));
        // 字符串，对应的格式
        System.out.println(LocalTime.parse("082030", DateTimeFormatter.ofPattern("HHmmss")));

        // ----------LocalDateTime-----
        // 参数为LocalDate和LocalTime
        System.out.println(LocalDateTime.of(demoDate, demoTime));
        System.out.println(LocalDateTime.of(1997, 7, 1, 8, 20));
        System.out.println(LocalDateTime.of(1997, Month.JULY, 1, 8, 20));
        System.out.println(LocalDateTime.of(1997, 7, 1, 8, 20, 30));
        System.out.println(LocalDateTime.of(1997, Month.JULY, 1, 8, 20, 30));
        System.out.println(LocalDateTime.of(1997, 7, 1, 8, 20, 30, 150));
        System.out.println(LocalDateTime.of(1997, Month.JULY, 1, 8, 20, 30, 150));
        System.out.println(LocalDateTime.parse("1997-07-01T08:20:30"));
        System.out.println(LocalDateTime.parse("1997-07-01 08:20:30", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
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
