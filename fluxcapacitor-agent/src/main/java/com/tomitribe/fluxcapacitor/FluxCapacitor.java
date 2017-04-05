/*
 * Tomitribe Confidential
 *
 * Copyright Tomitribe Corporation. 2017
 *
 * The source code for this program is not published or otherwise divested 
 * of its trade secrets, irrespective of what has been deposited with the 
 * U.S. Copyright Office.
 */
package com.tomitribe.fluxcapacitor;

import com.tomitribe.fluxcapacitor.util.Duration;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class FluxCapacitor {

    private static final AtomicLong offset = new AtomicLong(0);

    public static long currentTimeMillis() {
        return System.currentTimeMillis() + offset.get();
    }

    public static long getOffset() {
        return offset.get();
    }

    public static void setOffset(long milliseconds) {
        offset.set(milliseconds);
    }

    public static void setOffset(String duration) {
        long time = parseOffsetToMillis(duration);

        Log.log("Setting offset to '%s'", duration);
        offset.set(time);
    }

    public static long parseOffsetToMillis(String duration) {
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
}
