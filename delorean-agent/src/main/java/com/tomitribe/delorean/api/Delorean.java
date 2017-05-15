/*
 * Tomitribe Confidential
 *
 * Copyright Tomitribe Corporation. 2017
 *
 * The source code for this program is not published or otherwise divested 
 * of its trade secrets, irrespective of what has been deposited with the 
 * U.S. Copyright Office.
 */
package com.tomitribe.delorean.api;


import java.util.Collection;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

public class Delorean {

    private static final AtomicBoolean debug = new AtomicBoolean(false);
    private static final AtomicLong offset = new AtomicLong(0);

    static {
        debug.set(Boolean.getBoolean("delorean.debug"));
        setOffset(System.getProperty("delorean.offset", "0 milliseconds"));
    }

    public static long currentTimeMillis() {
        return System.currentTimeMillis() + offset.get();
    }

    public static long actualTimeMillis() {
        return System.currentTimeMillis();
    }

    public static long getOffset() {
        return offset.get();
    }

    public static long addOffset(final long delta) {
        return offset.addAndGet(delta);
    }

    public static void setOffset(long milliseconds) {
        offset.set(milliseconds);
    }

    public static void setOffset(String duration) {
        long time = parse(duration);
        log("Setting offset to '%s'", duration);
        offset.set(time);
    }

    public static long parse(String duration) {
        duration = duration.trim();

        if (duration.endsWith(" ago")) {
            duration = duration.substring(0, duration.length() - 4);
            duration = duration.trim();

            final Duration d = new Duration(duration);
            return d.getTime(TimeUnit.MILLISECONDS) * -1;
        }

        final Duration d = new Duration(duration);
        return d.getTime(TimeUnit.MILLISECONDS);
    }

    public static String join(final String delimiter, final Collection collection) {
        if (collection.size() == 0) {
            return "";
        }
        final StringBuilder sb = new StringBuilder();
        for (final Object obj : collection) {
            sb.append(obj).append(delimiter);
        }
        return sb.substring(0, sb.length() - delimiter.length());
    }

    private static void log(final String format, final Object... details) {
        final String message = String.format(format, details);
        System.out.printf("Delorean: %s%n", message);
    }
}
