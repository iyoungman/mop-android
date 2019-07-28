package com.youngman.mop.util;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import org.threeten.bp.LocalDate;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by YoungMan on 2019-07-06.
 */

public class DateUtils {

    public static String convertDateFormat(CalendarDay calendarDay) {
        Date date = new Date(calendarDay.getYear(), calendarDay.getMonth() - 1, calendarDay.getDay());
        SimpleDateFormat dateFormat = new SimpleDateFormat("20yy-MM-dd");
        String strDate = dateFormat.format(date);
        return strDate;
    }

    public static String convertMonthFormat(CalendarDay calendarDay) {
        Date date = new Date(calendarDay.getYear(), calendarDay.getMonth() - 1, calendarDay.getDay());
        SimpleDateFormat dateFormat = new SimpleDateFormat("20yy년 MM월");
        String strDate = dateFormat.format(date);
        return strDate;
    }

    public static String convertDateFormatNow() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("20yy-MM-dd");
        String strDate = dateFormat.format(date);
        return strDate;
    }

    public static String convertDateTimeFormatNow() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd일");
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");

        return dateFormat.format(date) + " " + timeFormat.format(timeFormat);
    }

    public static CalendarDay convertStrDateToCalendarDay(String strDate) {
        LocalDate localDate = LocalDate.parse(strDate);
        return CalendarDay.from(localDate);
    }

    /**
     * String Date + String Time 을 String DateTime 으로 변환
     */
    public static String convertToStrDateTime(String date, String hour, String minute) {
        return date + " " + hour + ":" + minute;
    }

}
