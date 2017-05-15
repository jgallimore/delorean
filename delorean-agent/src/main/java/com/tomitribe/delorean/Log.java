/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tomitribe.delorean;

import com.tomitribe.delorean.util.Join;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @version $Revision$ $Date$
 */
public class Log {

    public static final AtomicBoolean debug = new AtomicBoolean(false);

    private Log() {
        // no-op
    }

    public static void debug(final String format, final Object... details) {
        if (debug.get()) {
            final String message = String.format(format, details);
            System.out.printf("Delorean: %s%n", message);
        }
    }

    public static void log(final String format, final Object... details) {
        final String message = String.format(format, details);
        System.out.printf("Delorean: %s%n", message);
    }

    public static void err(final String format, final Object... details) {
        final String message = String.format(format, details);
        System.err.printf("Delorean: %s%n", message);
    }

    public static void main(String[] args) {
        String format = asDate(System.currentTimeMillis());
    }

    private static String asDate(final long timeMillis) {
        return String.format("%tF %<tT%n", timeMillis);
    }
}
