/*
 * Tomitribe Confidential
 *
 * Copyright Tomitribe Corporation. 2017
 *
 * The source code for this program is not published or otherwise divested 
 * of its trade secrets, irrespective of what has been deposited with the 
 * U.S. Copyright Office.
 */
package com.tomitribe.fluxcapacitor.api;

import java.util.concurrent.TimeUnit;

public class Normalize {
    long a;
    long b;
    TimeUnit base;

    Normalize(final Duration a, final Duration b) {
        this.base = lowest(a, b);
        this.a = a.unit == null ? a.time : base.convert(a.time, a.unit);
        this.b = b.unit == null ? b.time : base.convert(b.time, b.unit);
    }

    private static TimeUnit lowest(final Duration a, final Duration b) {
        if (a.unit == null) return b.unit;
        if (b.unit == null) return a.unit;
        if (a.time == 0) return b.unit;
        if (b.time == 0) return a.unit;
        return TimeUnit.values()[Math.min(a.unit.ordinal(), b.unit.ordinal())];
    }
}
