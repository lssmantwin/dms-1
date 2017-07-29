package com.dms;

import com.dms.utils.DateUtils;
import org.joda.time.LocalDateTime;

/**
 * Created by louis.liu on 2017/7/29.
 */
public class Test {
     public static void main(String[] args) {
        System.out.println( new LocalDateTime(DateUtils.getPreviousMonth()));
     }
}

