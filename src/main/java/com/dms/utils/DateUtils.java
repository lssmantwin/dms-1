package com.dms.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by louis.liu on 2017/5/13.
 */
public class DateUtils {

     public static Date getCurrentDayStart() {
         Calendar calendar  = Calendar.getInstance();
         calendar.set(Calendar.HOUR, 0);
         calendar.set(Calendar.MINUTE, 0);
         calendar.set(Calendar.SECOND, 0);
         return calendar.getTime();
     }

    public static Date getCurrentDayEnd() {
        Calendar calendar  = Calendar.getInstance();
        calendar.set(Calendar.HOUR, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }
}
