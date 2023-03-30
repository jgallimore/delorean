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
package org.tomitribe.delorean.api;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Duration implements Comparable<Duration> {

    long time;
    TimeUnit unit;

    public Duration() {
    }

    public Duration(final long time, final TimeUnit unit) {
        this.time = time;
        this.unit = unit;
    }

    public Duration(final String string) {
        this(string, null);
    }

    public Duration(final String string, final TimeUnit defaultUnit) {
        final String[] strings = string.split(",| and ");

        Duration total = new Duration();

        for (final String value : strings) {
            final Duration part = new Duration();
            final String s = value.trim();

            final StringBuilder t = new StringBuilder();
            final StringBuilder u = new StringBuilder();

            int i = 0;

            // get the number
            for (; i < s.length(); i++) {
                final char c = s.charAt(i);
                if (Character.isDigit(c) || i == 0 && c == '-') {
                    t.append(c);
                } else {
                    break;
                }
            }

            if (t.length() == 0) {
                invalidFormat(s);
            }

            // skip whitespace
            for (; i < s.length(); i++) {
                final char c = s.charAt(i);
                if (Character.isWhitespace(c)) {
                    // no-op
                } else {
                    break;
                }
            }

            // get time unit text part
            for (; i < s.length(); i++) {
                final char c = s.charAt(i);
                if (Character.isLetter(c)) {
                    u.append(c);
                } else {
                    invalidFormat(s);
                }
            }

            part.time = Integer.parseInt(t.toString());

            part.unit = parseUnit(u.toString());

            if (part.unit == null) {
                part.unit = defaultUnit;
            }

            total = total.add(part);
        }

        this.time = total.time;
        this.unit = total.unit;
    }

    public long getTime(final TimeUnit unit) {
        return unit.convert(this.time, this.unit);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Duration that = (Duration) o;

        final Normalize n = new Normalize(this, that);
        return n.a == n.b;
    }

    @Override
    public int hashCode() {
        int result = (int) (time ^ (time >>> 32));
        result = 31 * result + unit.hashCode();
        return result;
    }

    public Duration add(final Duration that) {
        final Normalize n = new Normalize(this, that);
        return new Duration(n.a + n.b, n.base);
    }

    private static void invalidFormat(final String text) {
        throw new IllegalArgumentException("Illegal duration format: '" + text +
                "'.  Valid examples are '10s' or '10 seconds'.");
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(time);
        if (unit != null) {
            sb.append(" ");
            sb.append(unit);
        }
        return sb.toString();
    }

    private static TimeUnit parseUnit(final String u) {
        if (u.length() == 0) {
            return null;
        }

        if ("NANOSECONDS".equalsIgnoreCase(u)) return TimeUnit.NANOSECONDS;
        if ("NANOSECOND".equalsIgnoreCase(u)) return TimeUnit.NANOSECONDS;
        if ("NANOS".equalsIgnoreCase(u)) return TimeUnit.NANOSECONDS;
        if ("NANO".equalsIgnoreCase(u)) return TimeUnit.NANOSECONDS;
        if ("NS".equalsIgnoreCase(u)) return TimeUnit.NANOSECONDS;

        if ("MICROSECONDS".equalsIgnoreCase(u)) return TimeUnit.MICROSECONDS;
        if ("MICROSECOND".equalsIgnoreCase(u)) return TimeUnit.MICROSECONDS;
        if ("MICROS".equalsIgnoreCase(u)) return TimeUnit.MICROSECONDS;
        if ("MICRO".equalsIgnoreCase(u)) return TimeUnit.MICROSECONDS;

        if ("MILLISECONDS".equalsIgnoreCase(u)) return TimeUnit.MILLISECONDS;
        if ("MILLISECOND".equalsIgnoreCase(u)) return TimeUnit.MILLISECONDS;
        if ("MILLIS".equalsIgnoreCase(u)) return TimeUnit.MILLISECONDS;
        if ("MILLI".equalsIgnoreCase(u)) return TimeUnit.MILLISECONDS;
        if ("MS".equalsIgnoreCase(u)) return TimeUnit.MILLISECONDS;

        if ("SECONDS".equalsIgnoreCase(u)) return TimeUnit.SECONDS;
        if ("SECOND".equalsIgnoreCase(u)) return TimeUnit.SECONDS;
        if ("SEC".equalsIgnoreCase(u)) return TimeUnit.SECONDS;
        if ("S".equalsIgnoreCase(u)) return TimeUnit.SECONDS;

        if ("MINUTES".equalsIgnoreCase(u)) return TimeUnit.MINUTES;
        if ("MINUTE".equalsIgnoreCase(u)) return TimeUnit.MINUTES;
        if ("MIN".equalsIgnoreCase(u)) return TimeUnit.MINUTES;
        if ("M".equalsIgnoreCase(u)) return TimeUnit.MINUTES;

        if ("HOURS".equalsIgnoreCase(u)) return TimeUnit.HOURS;
        if ("HOUR".equalsIgnoreCase(u)) return TimeUnit.HOURS;
        if ("HRS".equalsIgnoreCase(u)) return TimeUnit.HOURS;
        if ("HR".equalsIgnoreCase(u)) return TimeUnit.HOURS;
        if ("H".equalsIgnoreCase(u)) return TimeUnit.HOURS;

        if ("DAYS".equalsIgnoreCase(u)) return TimeUnit.DAYS;
        if ("DAY".equalsIgnoreCase(u)) return TimeUnit.DAYS;
        if ("D".equalsIgnoreCase(u)) return TimeUnit.DAYS;

        throw new IllegalArgumentException("Unknown time unit '" + u + "'.  Supported units " + Delorean.join(", ", lowercase(TimeUnit.values())));
    }

    @Override
    public int compareTo(final Duration that) {
        final Normalize n = new Normalize(this, that);
        if (n.a > n.b) return 1;
        if (n.a == n.b) return 0;
        return -1;
    }

    private static List<String> lowercase(final Enum... units) {
        final List<String> list = new ArrayList<String>();
        for (final Enum unit : units) {
            list.add(unit.name().toLowerCase());
        }
        return list;
    }
}
