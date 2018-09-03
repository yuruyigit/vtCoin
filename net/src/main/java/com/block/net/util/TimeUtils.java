package com.block.net.util;

import android.annotation.SuppressLint;
import android.text.format.Time;



import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;



@SuppressWarnings("deprecation")
@SuppressLint("SimpleDateFormat")
public class TimeUtils
{
    public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat(
            "yyyy-MM-dd   HH:mm:ss");
    public static final SimpleDateFormat DATE_FORMAT_DATE = new SimpleDateFormat(
            "yyyy-MM-dd");
    public static final SimpleDateFormat DATE_FORMAT_DATE2 = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm");
    public static final SimpleDateFormat DATE_FORMAT_DATE3 = new SimpleDateFormat(
            "yyyy年MM月dd日 HH:mm");
    public static final SimpleDateFormat DATE_FORMAT_DATE4 = new SimpleDateFormat(
            "yyyy年MM月dd日");
    public static final SimpleDateFormat YEAR_FORMAT_DATE = new SimpleDateFormat(
            "yyyy");

    private TimeUtils()
    {
        throw new AssertionError();
    }

    /**
     * long time to string
     *
     * @param timeInMillis
     * @param dateFormat
     * @return
     */
    public static String getTime(long timeInMillis, SimpleDateFormat dateFormat)
    {
        return dateFormat.format(new Date(timeInMillis));
    }

    /**
     * long time to string, format is {@link #DEFAULT_DATE_FORMAT}
     *
     * @param timeInMillis
     * @return
     */
    public static String getTime(long timeInMillis)
    {
        return getTime(timeInMillis, DEFAULT_DATE_FORMAT);
    }

    /**
     * get current time in milliseconds
     *
     * @return
     */
    public static long getCurrentTimeInLong()
    {
        return System.currentTimeMillis();
    }

    /**
     * get current time in milliseconds, format is {@link #DEFAULT_DATE_FORMAT}
     *
     * @return
     */
    public static String getCurrentTimeInString()
    {
        return getTime(getCurrentTimeInLong());
    }

    /**
     * get current time in milliseconds
     *
     * @return
     */
    public static String getCurrentTimeInString(SimpleDateFormat dateFormat)
    {
        return getTime(getCurrentTimeInLong(), dateFormat);
    }

    /**
     * 将时间格式化
     *
     * @return
     */
    public static Date getDateInString(String date, SimpleDateFormat dateFormat)
    {
        if (StringUtils.isBlank(date))
        {
            return null;
        }
        Date strtodate = null;
        try
        {
            if (dateFormat == null)
            {
                dateFormat = DATE_FORMAT_DATE;
            }
            strtodate = dateFormat.parse(date);
        } catch (ParseException e)
        {
            e.printStackTrace();
        }
        return strtodate;
    }

    public static Date getDateInString(String date)
    {
        return getDateInString(date, null);
    }

    /**
     * 根据时间获取星基期几
     *
     * @param dateStr
     * @return
     */
    public static String getWeek(String dateStr)
    {
        String week = "";
        Date date = getDateInString(dateStr);
        if (date == null)
        {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        int w = c.get(Calendar.DAY_OF_WEEK);
        switch (w)
        {
            case 1:
                week = "周日";
                break;
            case 2:
                week = "周一";
                break;
            case 3:
                week = "周二";
                break;
            case 4:
                week = "周三";
                break;
            case 5:
                week = "周四";
                break;
            case 6:
                week = "周五";
                break;
            case 7:
                week = "周六";
                break;

            default:
                break;
        }
        return week;
    }

    public static int getWeekInt(String dateStr)
    {
        int week = 1;
        Date date = getDateInString(dateStr);
        if (date == null)
        {
            return 1;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        week = c.get(Calendar.DAY_OF_WEEK);
        return week;
    }

    /**
     * 判断当前时间是哪一天
     *
     * @param dateStr
     * @return
     */
    public static String getDay(String dateStr)
    {
        if (StringUtils.isBlank(dateStr))
        {
            return null;
        }
        Date date = getDateInString(dateStr);
        if (date == null)
        {
            return null;
        }
        long lDate = date.getTime();
        long now = getCurrentTimeInLong();
        int i = (int) ((now - lDate) / 1000 / 60 / 60 / 24);
        String day = dateStr.substring(dateStr.indexOf("-") + 1,
                dateStr.indexOf(" "));
        switch (i)
        {
            case 0:
                day = "今天";
                break;
            case 1:
                day = "昨天";
                break;
            case 2:
                day = "前天";
                break;

            default:
                break;
        }
        return day;
    }

    /**
     * @param dateStr
     * @return
     */
    public static long getLongTimeInString(String dateStr)
    {
        return getLongTimeInString(dateStr, null);
    }

    public static long getLongTimeInString(String dateStr,
                                           SimpleDateFormat dateFormat)
    {
        Date date = getDateInString(dateStr, dateFormat);
        return date.getTime();
    }

    /**
     * 获取这一周时间的第一天也就是周日的时间
     *
     * @return
     */
    public static String getWeekFirstDay()
    {
        return getWeekFirstDay(null);
    }

    public static String getWeekFirstDay(String dateStr)
    {
        if (dateStr == null)
        {
            dateStr = getCurrentTimeInString(DATE_FORMAT_DATE);
        }
        int i = getWeekInt(dateStr);
        long time = getLongTimeInString(dateStr) - (i - 1) * 60 * 60 * 24
                * 1000;
        return getTime(time, DATE_FORMAT_DATE);
    }

    /**
     * 获取一周时间的最后一天时间
     *
     * @param dateStr
     * @return
     */
    public static String getWeekLastDay(String dateStr)
    {
        if (dateStr == null)
        {
            dateStr = getCurrentTimeInString(DATE_FORMAT_DATE);
        }
        int i = getWeekInt(dateStr);
        long time = getLongTimeInString(dateStr) + (7 - i) * 60 * 60 * 24
                * 1000;
        return getTime(time, DATE_FORMAT_DATE);
    }

    public static String getWeekLastDay()
    {
        return getWeekLastDay(null);
    }

    /**
     * 获取当前月的第一天时间
     *
     * @return
     */

    public static String getMonthFirstDay()
    {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);
        return DATE_FORMAT_DATE.format(c.getTime());
    }

    /**
     * 获取当前月的最后一天的时间
     *
     * @return
     */
    public static String getMonthLastDay()
    {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        return DATE_FORMAT_DATE.format(c.getTime());
    }

    /**
     * 获得本季度的第一天时间
     *
     * @return String
     */
    public static String getQuarterFirstDay()
    {
        return getQuarterFirstDay(0);
    }

    public static String getQuarterFirstDay(int month)
    {
        if (month == 0)
        {
            month = getCurrentMonth();
        }
        String[] quarter = {"01", "04", "07", "10",};
        int temp = 1;
        if (month > 0 && month < 4)
        {
            temp = 1;
        } else if (month > 3 && month < 7)
        {
            temp = 2;
        } else if (month > 6 && month < 10)
        {
            temp = 3;
        } else if (month > 9 && month < 13)
        {
            temp = 4;
        }
        String firstMonth = quarter[temp - 1];
        String firstDay = "01";

        String year = getTime(getCurrentTimeInLong(), YEAR_FORMAT_DATE);

        return year + "-" + firstMonth + "-" + firstDay;

    }

    /**
     * 获得本季度的最后一天时间
     *
     * @return
     */

    public static String getQuarterLastDay()
    {
        return getQuarterLastDay(0);
    }

    public static String getQuarterLastDay(int month)
    {
        if (month == 0)
        {
            month = getCurrentMonth();
        }
        String[] quarter = {"03", "06", "09", "12",};
        int temp = 1;
        if (month > 0 && month < 4)
        {
            temp = 1;
        } else if (month > 3 && month < 7)
        {
            temp = 2;
        } else if (month > 6 && month < 10)
        {
            temp = 3;
        } else if (month > 9 && month < 13)
        {
            temp = 4;
        }
        String endMonth = quarter[temp - 1];
        String endDay = getEndDay(temp);

        String year = getTime(getCurrentTimeInLong(), YEAR_FORMAT_DATE);

        return year + "-" + endMonth + "-" + endDay;

    }

    /**
     * 获取当前月分
     *
     * @return
     */
    public static int getCurrentMonth()
    {
        Calendar c = Calendar.getInstance();

        return c.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取本季度的最后一天
     *
     * @param temp
     * @return
     */
    private static String getEndDay(int temp)
    {
        String day = "30";
        switch (temp)
        {
            case 1:
                day = "31";
                break;
            case 2:
                day = "30";
                break;
            case 3:
                day = "30";
                break;
            case 4:
                day = "31";
                break;

            default:
                break;
        }
        return day;
    }

    /**
     * 获取今年的第一天日期
     *
     * @return
     */
    public static String getCurrentYearFirstDay()
    {
        return getTime(getCurrentTimeInLong(), YEAR_FORMAT_DATE) + "-01-01";
    }

    /**
     * 获取今年的最后一天日期
     *
     * @return
     */
    public static String getCurrentYearLastDay()
    {
        return getTime(getCurrentTimeInLong(), YEAR_FORMAT_DATE) + "-12-31";
    }

    /**
     * 判断是否登录过期
     */
    public static boolean isLoginPass(String dateStr)
    {
        boolean isPass = false;
        Date date = getDateInString(dateStr);
        if (date == null)
        {
            return false;
        }
        long lDate = date.getTime();
        Calendar c = Calendar.getInstance();

        String time = c.get(Calendar.YEAR) + "-" + // 得到年
                (c.get(Calendar.MONTH) + 1) + "-" + // month加一 //月
                c.get(Calendar.DAY_OF_MONTH); // 日
        L.v(time);
        Date dnow = getDateInString(time);
        long now = dnow.getTime();
        int i = (int) ((now - lDate) / 1000 / 60 / 60 / 24);
        L.v(i + "===============");
        if (i > 7)
        {
            isPass = true;
        }
        return isPass;
    }

    /**
     * 判断是早上、下午、中午的的时间
     *
     * @param time
     * @return
     */
    public static String getAMPMTime(String time)
    {
        String[] times = time.split(":");
        int h = Integer.parseInt(times[0]);
        String format = time;
        if (h >= 6 && h < 12)
        {
            format = "早上" + format;
        } else if (h == 12)
        {
            format = "中午" + format;
        } else if (h >= 13 && h <= 23)
        {
            format = "下午" + format;
        }
        return format;
    }

    /**
     * 自定义格式化时间格式 计算时差时当前时间-过去的时间再-（（当前小时-过去小时）*40）
     *
     * @return
     */
    public static int getIntTime()
    {
        Time t = new Time();
        t.setToNow();
        int h = t.hour;
        int m = t.minute;
        return h * 100 + m;
    }

    /**
     * 获取得小时
     *
     * @return
     */
    public static int getHour()
    {
        Time t = new Time();
        t.setToNow();
        int h = t.hour;
        return h;
    }

    public static boolean isEarly(int days, long time)
    {
        return (currentTimeMillis() - time) > (days * 24 * 3600 * 1000);
    }

    public static int currentTimeSecond()
    {
        return (int) (System.currentTimeMillis() / 1000);
    }

    public static long currentTimeMillis()
    {
        return System.currentTimeMillis();
    }

    public static long[] getTsTimes()
    {
        long[] times = new long[2];

        Calendar calendar = Calendar.getInstance();

        times[0] = calendar.getTimeInMillis() / 1000;

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        times[1] = calendar.getTimeInMillis() / 1000;

        return times;
    }

    public static String getFormatDatetime(int year, int month, int day)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(new GregorianCalendar(year, month, day)
                .getTime());
    }

    public static Date getDateFromFormatString(String formatDate)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            return sdf.parse(formatDate);
        } catch (ParseException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public static String getNowDatetime()
    {
        SimpleDateFormat formatter = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return (formatter.format(new Date()));
    }

    public static int getNow()
    {
        return (int) ((new Date()).getTime() / 1000);
    }

    public static String getNowDateTime(String format)
    {
        Date date = new Date();

        SimpleDateFormat df = new SimpleDateFormat(format, Locale.getDefault());
        return df.format(date);
    }

    public static String getDateString(long milliseconds)
    {
        return getDateTimeString(milliseconds, "yyyyMMdd");
    }

    public static String getTimeString(long milliseconds)
    {
        return getDateTimeString(milliseconds, "HHmmss");
    }

    public static String getBeijingNowTimeString(String format)
    {
        TimeZone timezone = TimeZone.getTimeZone("Asia/Shanghai");

        Date date = new Date(currentTimeMillis());
        SimpleDateFormat formatter = new SimpleDateFormat(format,
                Locale.getDefault());
        formatter.setTimeZone(timezone);
        int am = Calendar.AM;
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTimeZone(timezone);
        int calAm = gregorianCalendar.get(Calendar.AM_PM);
        String prefix = calAm == am ? "上午"
                : "下午";

        return prefix + formatter.format(date);
    }

    public static String getBeijingNowTime(String format)
    {
        TimeZone timezone = TimeZone.getTimeZone("Asia/Shanghai");

        Date date = new Date(currentTimeMillis());
        SimpleDateFormat formatter = new SimpleDateFormat(format,
                Locale.getDefault());
        formatter.setTimeZone(timezone);

        return formatter.format(date);
    }

    public static String getDateTimeString(long milliseconds, String format)
    {
        Date date = new Date(milliseconds);
        SimpleDateFormat formatter = new SimpleDateFormat(format,
                Locale.getDefault());
        return formatter.format(date);
    }

    public static String getFavoriteCollectTime(long milliseconds)
    {
        String showDataString = "";
        Date today = new Date();
        Date date = new Date(milliseconds);
        Date firstDateThisYear = new Date(today.getYear(), 0, 0);
        if (!date.before(firstDateThisYear))
        {
            SimpleDateFormat dateformatter = new SimpleDateFormat("MM-dd",
                    Locale.getDefault());
            showDataString = dateformatter.format(date);
        } else
        {
            SimpleDateFormat dateformatter = new SimpleDateFormat("yyyy-MM-dd",
                    Locale.getDefault());
            showDataString = dateformatter.format(date);
        }
        return showDataString;
    }

    public static String getTimeShowString(long milliseconds, boolean abbreviate)
    {
        String dataString = "";
        String timeStringBy24 = "";

        Date currentTime = new Date(milliseconds);
        Date today = new Date();
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        Date todaybegin = todayStart.getTime();
        Date yesterdaybegin = new Date(todaybegin.getTime() - 3600 * 24 * 1000);
        Date preyesterday = new Date(
                yesterdaybegin.getTime() - 3600 * 24 * 1000);

        if (!currentTime.before(todaybegin))
        {
            dataString = "今天";
        } else if (!currentTime.before(yesterdaybegin))
        {
            dataString = "昨天";
        } else if (!currentTime.before(preyesterday))
        {
            dataString = "前天";
        } else if (isSameWeekDates(currentTime, today))
        {
            dataString = getWeekOfDate(currentTime);
        } else
        {
            SimpleDateFormat dateformatter = new SimpleDateFormat("yyyy-MM-dd",
                    Locale.getDefault());
            dataString = dateformatter.format(currentTime);
        }

        SimpleDateFormat timeformatter24 = new SimpleDateFormat("HH:mm",
                Locale.getDefault());
        timeStringBy24 = timeformatter24.format(currentTime);

        if (abbreviate)
        {
            if (!currentTime.before(todaybegin))
            {
                return getTodayTimeBucket(currentTime);
            } else
            {
                return dataString;
            }
        } else
        {
            return dataString + " " + timeStringBy24;
        }
    }

    /**
     * 根据不同时间段，显示不同时间
     *
     * @param date
     * @return
     */
    public static String getTodayTimeBucket(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        SimpleDateFormat timeformatter0to11 = new SimpleDateFormat("KK:mm",
                Locale.getDefault());
        SimpleDateFormat timeformatter1to12 = new SimpleDateFormat("hh:mm",
                Locale.getDefault());
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (hour >= 0 && hour < 5)
        {
            return "凌晨 " + timeformatter0to11.format(date);
        } else if (hour >= 5 && hour < 12)
        {
            return "上午 " + timeformatter0to11.format(date);
        } else if (hour >= 12 && hour < 18)
        {
            return "下午 " + timeformatter1to12.format(date);
        } else if (hour >= 18 && hour < 24)
        {
            return "晚上 " + timeformatter1to12.format(date);
        }
        return "";
    }

    /**
     * 根据日期获得星期
     *
     * @param date
     * @return
     */
    public static String getWeekOfDate(Date date)
    {
        String[] weekDaysName = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五",
                "星期六"};
        // String[] weekDaysCode = { "0", "1", "2", "3", "4", "5", "6" };
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return weekDaysName[intWeek];
    }

    public static boolean isSameDay(long time1, long time2)
    {
        return isSameDay(new Date(time1), new Date(time2));
    }

    public static boolean isSameDay(Date date1, Date date2)
    {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);

        boolean sameDay = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                && cal1.get(Calendar.DAY_OF_YEAR) == cal2
                .get(Calendar.DAY_OF_YEAR);
        return sameDay;
    }

    /**
     * 判断两个日期是否在同一周
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameWeekDates(Date date1, Date date2)
    {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);

        int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
        int cal2Month = cal2.get(Calendar.MONTH);
        int cal1Month = cal1.get(Calendar.MONTH);
        if (0 == subYear)
        {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
                    .get(Calendar.WEEK_OF_YEAR))
                return true;
        } else if (1 == subYear && 11 == cal2Month)
        {
            // 如果12月的最后一周横跨来年第一周的话则最后一周即算做来年的第一周
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
                    .get(Calendar.WEEK_OF_YEAR))
                return true;
        } else if (-1 == subYear && 11 == cal1Month)
        {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
                    .get(Calendar.WEEK_OF_YEAR))
                return true;
        }
        return false;
    }

    public static long getSecondsByMilliseconds(long milliseconds)
    {
        long seconds = new BigDecimal(
                (float) ((float) milliseconds / (float) 1000)).setScale(0,
                BigDecimal.ROUND_HALF_UP).intValue();
        // if (seconds == 0) {
        // seconds = 1;
        // }
        return seconds;
    }

    public static String secToTime(int time)
    {
        String timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (time <= 0)
            return "00:00";
        else
        {
            minute = time / 60;
            if (minute < 60)
            {
                second = time % 60;
                timeStr = unitFormat(minute) + ":" + unitFormat(second);
            } else
            {
                hour = minute / 60;
                if (hour > 99)
                    return "99:59:59";
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":"
                        + unitFormat(second);
            }
        }
        return timeStr;
    }

    public static String unitFormat(int i)
    {
        String retStr = null;
        if (i >= 0 && i < 10)
            retStr = "0" + Integer.toString(i);
        else
            retStr = "" + i;
        return retStr;
    }

    public static String getElapseTimeForShow(int milliseconds)
    {
        StringBuilder sb = new StringBuilder();
        int seconds = milliseconds / 1000;
        if (seconds < 1)
            seconds = 1;
        int hour = seconds / (60 * 60);
        if (hour != 0)
        {
            sb.append(hour).append("小时");
        }
        int minute = (seconds - 60 * 60 * hour) / 60;
        if (minute != 0)
        {
            sb.append(minute).append("分");
        }
        int second = (seconds - 60 * 60 * hour - 60 * minute);
        if (second != 0)
        {
            sb.append(second).append("秒");
        }
        return sb.toString();
    }

    /**
     * 将时间戳转为代表"距现在多久之前"的字符串
     *
     * @param t 时间戳
     * @return
     */
    public static String getStandardDate(long t)
    {

        if (t <= 0)
        {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        long time = System.currentTimeMillis() - (t * 1000);
        long mill = (long) Math.ceil(time / 1000);// 秒前
        long minute = (long) Math.ceil(time / 60 / 1000.0f);// 分钟前

        long hour = (long) Math.ceil(time / 60 / 60 / 1000.0f);// 小时

        long day = (long) Math.ceil(time / 24 / 60 / 60 / 1000.0f);// 天前

        if (day - 1 > 0)
        {
            sb.append(day + "天");
        } else if (hour - 1 > 0)
        {
            if (hour >= 24)
            {
                sb.append(getTimeShowString(t));
            } else
            {
                sb.append(hour + "小时");
            }
        } else if (minute - 1 > 0)
        {
            if (minute == 60)
            {
                sb.append("1小时");
            } else
            {
                sb.append(minute + "分钟");
            }
        } else if (mill - 1 > 0)
        {
            if (mill == 60)
            {
                sb.append("1分钟");
            } else
            {
                sb.append(mill + "秒");
            }
        } else
        {
            sb.append("刚刚");
        }
        if (!sb.toString().equals("刚刚"))
        {
            sb.append("前");
        }
        return sb.toString();
    }

    public static String getTimeShowString(long milliseconds)
    {

        String dataString = "";
        String timeStringBy24 = "";

        Date currentTime = new Date(milliseconds);
        Date today = new Date();
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        Date todaybegin = todayStart.getTime();
        Date yesterdaybegin = new Date(todaybegin.getTime() - 3600 * 24 * 1000);
        Date preyesterday = new Date(
                yesterdaybegin.getTime() - 3600 * 24 * 1000);

        if (!currentTime.before(yesterdaybegin))
        {
            dataString = "昨天";
        } else if (!currentTime.before(preyesterday))
        {
            dataString = "前天";
        } else if (isSameWeekDates(currentTime, today))
        {
            dataString = getWeekOfDate(currentTime);
        } else
        {
            SimpleDateFormat dateformatter = new SimpleDateFormat("yyyy-MM-dd",
                    Locale.getDefault());
            dataString = dateformatter.format(currentTime);
        }

        SimpleDateFormat timeformatter24 = new SimpleDateFormat("HH:mm",
                Locale.getDefault());
        timeStringBy24 = timeformatter24.format(currentTime);

        return dataString + " " + timeStringBy24;
    }

    /**
     * 根据日期计算年龄
     *
     * @param birthDay
     * @return
     */
    public static int getAge(String birthDay)
    {
        if (StringUtils.isBlank(birthDay))
        {
            return 0;
        }
        return getAge(getDateInString(birthDay));
    }

    /**
     * 根据日期计算年龄
     *
     * @param birthDay
     * @return
     */
    public static int getAge(Date birthDay)
    {
        Calendar cal = Calendar.getInstance();

        if (cal.before(birthDay))
        {
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }

        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthDay);

        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth)
        {
            if (monthNow == monthBirth)
            {
                // monthNow==monthBirth
                if (dayOfMonthNow < dayOfMonthBirth)
                {
                    age--;
                } else
                {
                    // do nothing
                }
            } else
            {
                // monthNow>monthBirth
                age--;
            }
        } else
        {
            // monthNow<monthBirth
            // donothing
        }

        return age;
    }

    /**
     * 获取时间戳
     *
     * @param time
     * @return
     */
    public static long getTimeStamp(String time)
    {
        long mTimeStamp = 0;
        if (time != null && time.length() > 0)
        {
            Date date;
            String times = null;
            try
            {
                date = DEFAULT_DATE_FORMAT.parse(time);
                long l = date.getTime();
                String stf = String.valueOf(l);
                times = stf.substring(0, 10);
            } catch (ParseException e)
            {
                e.printStackTrace();
            }
            mTimeStamp = Long.parseLong(times);
        }
        return mTimeStamp;
    }

    /**
     * 将时间转为代表"距现在多久之前"的字符串
     *
     * @param time 时间
     * @return
     */
    public static String getStandardDate(String time)
    {
        return getStandardDate(getTimeStamp(time));
    }

    /**
     * 转换UTC格式
     *
     * @param utcTime UTC时间
     * @param utcTimePatten   UTC时间格式
     * @param localTimePatten  当地时间格式
     * @return
     */
    public static String utc2Local(String utcTime, String utcTimePatten,
                                   String localTimePatten)
    {
        SimpleDateFormat utcFormater = new SimpleDateFormat(utcTimePatten);
        utcFormater.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date gpsUTCDate = null;
        try
        {
            gpsUTCDate = utcFormater.parse(utcTime);
        } catch (ParseException e)
        {
            e.printStackTrace();
        }
        SimpleDateFormat localFormater = new SimpleDateFormat(localTimePatten);
        localFormater.setTimeZone(TimeZone.getDefault());
        String localTime = localFormater.format(gpsUTCDate.getTime());
        return localTime;
    }


    /**
     * 获取UTC时间
     * @return
     */

    public static String getUTCTimeStr() {

        DateFormat format = new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss");

        StringBuffer UTCTimeBuffer = new StringBuffer();

        // 1、取得本地时间：

        Calendar cal = Calendar.getInstance();

        // 2、取得时间偏移量：

        int zoneOffset = cal.get(Calendar.ZONE_OFFSET);

        // 3、取得夏令时差：

        int dstOffset = cal.get(Calendar.DST_OFFSET);

        // 4、从本地时间里扣除这些差量，即可以取得UTC时间：

        cal.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));

        int year = cal.get(Calendar.YEAR);

        int month = cal.get(Calendar.MONTH) + 1;

        int day = cal.get(Calendar.DAY_OF_MONTH);

        int hour = cal.get(Calendar.HOUR_OF_DAY);

        int minute = cal.get(Calendar.MINUTE);

        int second = cal.get(Calendar.SECOND);

        UTCTimeBuffer.append(year).append("/").append(month).append("/")

                .append(day);

        UTCTimeBuffer.append("/").append(hour).append("/").append(minute)

                .append("/").append(second);

        try {

            format.parse(UTCTimeBuffer.toString());

            return UTCTimeBuffer.toString();

        } catch (ParseException e) {

            e.printStackTrace();

        }

        return null;

    }


    /**
     * 获取时间戳
     * @param dateCur
     * @return
     */
    public static long GetTicks(String dateCur) {

        String[] ds = dateCur.split("/");

        // start of the ticks time
        Calendar calStart = Calendar.getInstance();
        /**

         * 此处的参数很重要，原则上都是1，日所以为2，是因为之前的日期没减掉1 第三个参数为1：日期多了2天，为2则日期多1天
         * **/

        //上传失败时这里总会出现混乱的情况，需要找到源头解决

//		 calStart.set(1, 1, 0, 0, 0, 0);

        calStart.set(1, 1, 2, 0, 0, 0);

        // the target time

        Calendar calEnd = Calendar.getInstance();

        calEnd.set(Integer.parseInt(ds[0]), Integer.parseInt(ds[1]),

                Integer.parseInt(ds[2]), Integer.parseInt(ds[3]),

                Integer.parseInt(ds[4]), Integer.parseInt(ds[5]));

        // epoch time of the ticks-start time

        long epochStart = calStart.getTime().getTime();

        // epoch time of the target time

        long epochEnd = calEnd.getTime().getTime();

        // get the sum of epoch time, from the target time to the ticks-start

        // time

        long all = epochEnd - epochStart;

        // convert epoch time to ticks time

        long ticks = ((all / 1000) * 1000000) * 10;

        return ticks;

    }
    /**
     * 获取UTC时间戳
     * @return
     */
    public static long getUTCtime(){

    Calendar cal = Calendar.getInstance();
    return cal.getTimeInMillis()/1000;


}
}
