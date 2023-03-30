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
