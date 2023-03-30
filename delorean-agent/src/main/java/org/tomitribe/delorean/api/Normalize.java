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
