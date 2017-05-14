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
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.tomitribe.delorean.service;

import org.tomitribe.util.TimeUtils;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.concurrent.TimeUnit;

@XmlRootElement
public class Status {

    private String offset;
    private long offsetMillis;
    private String currentTime;
    private long currentTimeMillis;

    public Status() {
    }

    public Status(long offsetMillis, long currentTimeMillis) {

        this.offsetMillis = offsetMillis;
        this.currentTimeMillis = currentTimeMillis;

        if (offsetMillis < 0) {
            offsetMillis = offsetMillis * -1;
            this.offset = TimeUtils.format(offsetMillis, TimeUnit.MILLISECONDS) + " ago";
        } else {
            this.offset = TimeUtils.format(offsetMillis, TimeUnit.MILLISECONDS);
        }

        this.currentTime = asDate(System.currentTimeMillis());
    }

    public static String asDate(final long milliseconds) {
        return String.format("%tF %<tT", milliseconds);
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public long getOffsetMillis() {
        return offsetMillis;
    }

    public void setOffsetMillis(long offsetMillis) {
        this.offsetMillis = offsetMillis;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public long getCurrentTimeMillis() {
        return currentTimeMillis;
    }

    public void setCurrentTimeMillis(long currentTimeMillis) {
        this.currentTimeMillis = currentTimeMillis;
    }
}