/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.tomitribe.delorean.service;

import org.tomitribe.delorean.util.Join;

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
