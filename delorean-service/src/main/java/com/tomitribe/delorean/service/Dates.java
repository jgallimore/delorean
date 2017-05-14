/*
 * Tomitribe Confidential
 *
 * Copyright Tomitribe Corporation. 2017
 *
 * The source code for this program is not published or otherwise divested 
 * of its trade secrets, irrespective of what has been deposited with the 
 * U.S. Copyright Office.
 */
package com.tomitribe.delorean.service;

import com.tomitribe.delorean.util.Join;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Dates {
    private Dates() {
    }

    public static Date parse(String s) throws UnsupportedDateFormatException {
        for (final String s1 : formats()) {
            try {
                final SimpleDateFormat format = new SimpleDateFormat(s1);
                return format.parse(s);
            } catch (ParseException tryAgain) {
            }
        }

        throw new UnsupportedDateFormatException(formats(), s);
    }

    public static List<String> format(long time, String... f) {
        final List<String> dates = new ArrayList<String>();
        for (final String s1 : f) {
            final SimpleDateFormat format = new SimpleDateFormat(s1);
            dates.add(format.format(time));
        }

        return dates;
    }

    public static String[] formats() {
        return new String[]{
                "yyyy-MM-dd'T'HH:mm:ss.SSSZ",
                "yyyy-MM-dd'T'HH:mm:ss",
                "yyyy-MM-dd HH:mm:ss z",
                "yyyy-MM-dd HH:mm z",
                "yyyy-MM-dd z",
                "yyyy-MM-dd HH:mm:ss",
                "yyyy-MM-dd HH:mm",
                "yyyy-MM-dd"
        };
    }

    public static class UnsupportedDateFormatException extends RuntimeException {

        private final String[] supported;
        private final String date;

        public UnsupportedDateFormatException(String[] supported, String date) {
            this.supported = supported;
            this.date = date;
        }

        public String[] getSupported() {
            return supported;
        }

        public List<String> getExamples() {
            return format(System.currentTimeMillis(), formats());
        }

        public String getDate() {
            return date;
        }

        @Override
        public String getMessage() {
            return toString();
        }

        @Override
        public String toString() {
            return "UnsupportedDateFormatException{" +
                    "date='" + date + '\'' +
                    ", supported=" + Arrays.toString(supported) +
                    ", examples=[" + Join.join(", ", getExamples()) + "]" +
                    '}';
        }
    }
}
