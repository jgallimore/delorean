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
package com.tomitribe.fluxcapacitor.util;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;

/**
 * @version $Revision$ $Date$
 */
@SuppressWarnings("deprecation")
public class Unsafe {

    private static final sun.misc.Unsafe UNSAFE;

    static {
        UNSAFE = (sun.misc.Unsafe) AccessController.doPrivileged(new PrivilegedAction<Object>() {
            @Override
            public Object run() {
                try {
                    final Field field = sun.misc.Unsafe.class.getDeclaredField("theUnsafe");
                    field.setAccessible(true);
                    return field.get(null);
                } catch (final Exception e) {
                    throw new IllegalStateException("Cannot get sun.misc.Unsafe", e);
                }
            }
        });
    }

    private Unsafe() {
        // no-op
    }

    @Deprecated
    public static void putBoolean(final Object o, final int i, final boolean b) {
        UNSAFE.putBoolean(o, i, b);
    }

    @Deprecated
    public static void putObject(final Object o, final int i, final Object o2) {
        UNSAFE.putObject(o, i, o2);
    }

    @Deprecated
    public static void putInt(final Object o, final int i, final int i2) {
        UNSAFE.putInt(o, i, i2);
    }

    @Deprecated
    public static double getDouble(final Object o, final int i) {
        return UNSAFE.getDouble(o, i);
    }

    @Deprecated
    public static void putChar(final Object o, final int i, final char c) {
        UNSAFE.putChar(o, i, c);
    }

    @Deprecated
    public static void putLong(final Object o, final int i, final long l) {
        UNSAFE.putLong(o, i, l);
    }

    @Deprecated
    public static Object getObject(final Object o, final int i) {
        return UNSAFE.getObject(o, i);
    }

    @Deprecated
    public static short getShort(final Object o, final int i) {
        return UNSAFE.getShort(o, i);
    }

    @Deprecated
    public static void putFloat(final Object o, final int i, final float v) {
        UNSAFE.putFloat(o, i, v);
    }

    @Deprecated
    public static void putDouble(final Object o, final int i, final double v) {
        UNSAFE.putDouble(o, i, v);
    }

    public static int arrayIndexScale(final Class<?> aClass) {
        return UNSAFE.arrayIndexScale(aClass);
    }

    @Deprecated
    public static Object staticFieldBase(final Class<?> aClass) {
        return UNSAFE.staticFieldBase(aClass);
    }

    public static void ensureClassInitialized(final Class<?> aClass) {
        UNSAFE.ensureClassInitialized(aClass);
    }

    @Deprecated
    public static int fieldOffset(final Field field) {
        return UNSAFE.fieldOffset(field);
    }

    @Deprecated
    public static long getLong(final Object o, final int i) {
        return UNSAFE.getLong(o, i);
    }

    public static int arrayBaseOffset(final Class<?> aClass) {
        return UNSAFE.arrayBaseOffset(aClass);
    }

    @Deprecated
    public static int getInt(final Object o, final int i) {
        return UNSAFE.getInt(o, i);
    }

    public static Object allocateInstance(final Class<?> aClass) throws InstantiationException {
        return UNSAFE.allocateInstance(aClass);
    }

    @Deprecated
    public static float getFloat(final Object o, final int i) {
        return UNSAFE.getFloat(o, i);
    }

    @Deprecated
    public static boolean getBoolean(final Object o, final int i) {
        return UNSAFE.getBoolean(o, i);
    }

    @Deprecated
    public static char getChar(final Object o, final int i) {
        return UNSAFE.getChar(o, i);
    }

    @Deprecated
    public static byte getByte(final Object o, final int i) {
        return UNSAFE.getByte(o, i);
    }

    @Deprecated
    public static void putShort(final Object o, final int i, final short i2) {
        UNSAFE.putShort(o, i, i2);
    }

    @Deprecated
    public static void putByte(final Object o, final int i, final byte b) {
        UNSAFE.putByte(o, i, b);
    }

    public static sun.misc.Unsafe get() {
        return UNSAFE;
    }

    public static void putByteVolatile(final Object o, final long offset, final byte x) {
        UNSAFE.putByteVolatile(o, offset, x);
    }

    public static void putByte(final Object o, final long offset, final byte x) {
        UNSAFE.putByte(o, offset, x);
    }

    public static int pageSize() {
        return UNSAFE.pageSize();
    }

    public static void putOrderedLong(final Object o, final long offset, final long x) {
        UNSAFE.putOrderedLong(o, offset, x);
    }

    public static void putOrderedObject(final Object o, final long offset, final Object x) {
        UNSAFE.putOrderedObject(o, offset, x);
    }

    public static char getChar(final Object o, final long offset) {
        return UNSAFE.getChar(o, offset);
    }

    public static boolean compareAndSwapInt(final Object o, final long offset, final int expected, final int x) {
        return UNSAFE.compareAndSwapInt(o, offset, expected, x);
    }

    public static long getLong(final Object o, final long offset) {
        return UNSAFE.getLong(o, offset);
    }

    public static void putDouble(final Object o, final long offset, final double x) {
        UNSAFE.putDouble(o, offset, x);
    }

    public static long allocateMemory(final long bytes) {
        return UNSAFE.allocateMemory(bytes);
    }

    public static int getInt(final long address) {
        return UNSAFE.getInt(address);
    }

    public static void putDoubleVolatile(final Object o, final long offset, final double x) {
        UNSAFE.putDoubleVolatile(o, offset, x);
    }

    public static float getFloat(final Object o, final long offset) {
        return UNSAFE.getFloat(o, offset);
    }

    public static boolean compareAndSwapObject(final Object o, final long offset, final Object expected, final Object x) {
        return UNSAFE.compareAndSwapObject(o, offset, expected, x);
    }

    public static void putFloat(final Object o, final long offset, final float x) {
        UNSAFE.putFloat(o, offset, x);
    }

    public static void putChar(final long address, final char x) {
        UNSAFE.putChar(address, x);
    }

    public static char getCharVolatile(final Object o, final long offset) {
        return UNSAFE.getCharVolatile(o, offset);
    }

    public static char getChar(final long address) {
        return UNSAFE.getChar(address);
    }

    public static boolean getBoolean(final Object o, final long offset) {
        return UNSAFE.getBoolean(o, offset);
    }

    public static void putLong(final Object o, final long offset, final long x) {
        UNSAFE.putLong(o, offset, x);
    }

    public static void putCharVolatile(final Object o, final long offset, final char x) {
        UNSAFE.putCharVolatile(o, offset, x);
    }

    public static Class defineClass(final String name, final byte[] b, final int off, final int len, final ClassLoader loader, final ProtectionDomain protectionDomain) {
        return UNSAFE.defineClass(name, b, off, len, loader, protectionDomain);
    }

    public static long getLong(final long address) {
        return UNSAFE.getLong(address);
    }

    public static void putByte(final long address, final byte x) {
        UNSAFE.putByte(address, x);
    }

    public static int getIntVolatile(final Object o, final long offset) {
        return UNSAFE.getIntVolatile(o, offset);
    }

    public static void park(final boolean isAbsolute, final long time) {
        UNSAFE.park(isAbsolute, time);
    }

    public static int addressSize() {
        return UNSAFE.addressSize();
    }

    public static void setMemory(final long address, final long bytes, final byte value) {
        UNSAFE.setMemory(address, bytes, value);
    }

    public static long objectFieldOffset(final Field f) {
        return UNSAFE.objectFieldOffset(f);
    }

    public static void monitorEnter(final Object o) {
        UNSAFE.monitorEnter(o);
    }

    public static void putIntVolatile(final Object o, final long offset, final int x) {
        UNSAFE.putIntVolatile(o, offset, x);
    }

    public static void putObject(final Object o, final long offset, final Object x) {
        UNSAFE.putObject(o, offset, x);
    }

    public static void putDouble(final long address, final double x) {
        UNSAFE.putDouble(address, x);
    }

    public static short getShort(final long address) {
        return UNSAFE.getShort(address);
    }

    public static void throwException(final Throwable ee) {
        UNSAFE.throwException(ee);
    }

    public static Object staticFieldBase(final Field f) {
        return UNSAFE.staticFieldBase(f);
    }

    public static Object getObject(final Object o, final long offset) {
        return UNSAFE.getObject(o, offset);
    }

    public static void putChar(final Object o, final long offset, final char x) {
        UNSAFE.putChar(o, offset, x);
    }

    public static void putOrderedInt(final Object o, final long offset, final int x) {
        UNSAFE.putOrderedInt(o, offset, x);
    }

    public static long getAddress(final long address) {
        return UNSAFE.getAddress(address);
    }

    public static void unpark(final Object thread) {
        UNSAFE.unpark(thread);
    }

    public static short getShort(final Object o, final long offset) {
        return UNSAFE.getShort(o, offset);
    }

    public static float getFloat(final long address) {
        return UNSAFE.getFloat(address);
    }

    public static void putShortVolatile(final Object o, final long offset, final short x) {
        UNSAFE.putShortVolatile(o, offset, x);
    }

    public static float getFloatVolatile(final Object o, final long offset) {
        return UNSAFE.getFloatVolatile(o, offset);
    }

    public static double getDouble(final Object o, final long offset) {
        return UNSAFE.getDouble(o, offset);
    }

    public static boolean tryMonitorEnter(final Object o) {
        return UNSAFE.tryMonitorEnter(o);
    }

    public static void copyMemory(final Object o, final long l, final Object o1, final long l1, final long l2) {
        UNSAFE.copyMemory(o, l, o1, l1, l2);
    }

    public static void putShort(final long address, final short x) {
        UNSAFE.putShort(address, x);
    }

    public static int getInt(final Object o, final long offset) {
        return UNSAFE.getInt(o, offset);
    }

    public static void monitorExit(final Object o) {
        UNSAFE.monitorExit(o);
    }

    public static void putObjectVolatile(final Object o, final long offset, final Object x) {
        UNSAFE.putObjectVolatile(o, offset, x);
    }

    public static short getShortVolatile(final Object o, final long offset) {
        return UNSAFE.getShortVolatile(o, offset);
    }

    public static double getDoubleVolatile(final Object o, final long offset) {
        return UNSAFE.getDoubleVolatile(o, offset);
    }

    public static void putFloatVolatile(final Object o, final long offset, final float x) {
        UNSAFE.putFloatVolatile(o, offset, x);
    }

    public static byte getByte(final Object o, final long offset) {
        return UNSAFE.getByte(o, offset);
    }

    public static void putBooleanVolatile(final Object o, final long offset, final boolean x) {
        UNSAFE.putBooleanVolatile(o, offset, x);
    }

    public static void putInt(final long address, final int x) {
        UNSAFE.putInt(address, x);
    }

    public static byte getByte(final long address) {
        return UNSAFE.getByte(address);
    }

    public static void putFloat(final long address, final float x) {
        UNSAFE.putFloat(address, x);
    }

    public static long staticFieldOffset(final Field f) {
        return UNSAFE.staticFieldOffset(f);
    }

    public static void putLongVolatile(final Object o, final long offset, final long x) {
        UNSAFE.putLongVolatile(o, offset, x);
    }

    public static void putShort(final Object o, final long offset, final short x) {
        UNSAFE.putShort(o, offset, x);
    }

    public static void putInt(final Object o, final long offset, final int x) {
        UNSAFE.putInt(o, offset, x);
    }

    public static Object getObjectVolatile(final Object o, final long offset) {
        return UNSAFE.getObjectVolatile(o, offset);
    }

    public static void copyMemory(final long srcAddress, final long destAddress, final long bytes) {
        UNSAFE.copyMemory(srcAddress, destAddress, bytes);
    }

    public static void putLong(final long address, final long x) {
        UNSAFE.putLong(address, x);
    }

    public static void putAddress(final long address, final long x) {
        UNSAFE.putAddress(address, x);
    }

    public static void putBoolean(final Object o, final long offset, final boolean x) {
        UNSAFE.putBoolean(o, offset, x);
    }

    public static boolean getBooleanVolatile(final Object o, final long offset) {
        return UNSAFE.getBooleanVolatile(o, offset);
    }

    public static void freeMemory(final long address) {
        UNSAFE.freeMemory(address);
    }

    public static boolean compareAndSwapLong(final Object o, final long offset, final long expected, final long x) {
        return UNSAFE.compareAndSwapLong(o, offset, expected, x);
    }

    public static int getLoadAverage(final double[] loadavg, final int nelems) {
        return UNSAFE.getLoadAverage(loadavg, nelems);
    }

    public static long reallocateMemory(final long address, final long bytes) {
        return UNSAFE.reallocateMemory(address, bytes);
    }

    public static long getLongVolatile(final Object o, final long offset) {
        return UNSAFE.getLongVolatile(o, offset);
    }

    public static double getDouble(final long address) {
        return UNSAFE.getDouble(address);
    }

    public static byte getByteVolatile(final Object o, final long offset) {
        return UNSAFE.getByteVolatile(o, offset);
    }
}
