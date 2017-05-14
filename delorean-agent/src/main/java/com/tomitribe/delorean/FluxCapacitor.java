/*
 * Tomitribe Confidential
 *
 * Copyright Tomitribe Corporation. 2017
 *
 * The source code for this program is not published or otherwise divested 
 * of its trade secrets, irrespective of what has been deposited with the 
 * U.S. Copyright Office.
 */
package com.tomitribe.delorean;

import com.tomitribe.delorean.api.Duration;
import com.tomitribe.delorean.api.Delorean;
import com.tomitribe.delorean.api.Normalize;
import com.tomitribe.delorean.util.Bytecode;
import com.tomitribe.delorean.util.Unsafe;

import java.io.IOException;

public class FluxCapacitor {

    public static void install() {
        try {
            install(Delorean.class);
            install(Duration.class);
            install(Normalize.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void install(final Class<?> clazz) throws IOException {
        final byte[] bytecode = RepackageApi.enhance(Bytecode.readClassFile(clazz), clazz.getName());
        final ClassLoader classLoader = Object.class.getClassLoader();
        final String name = clazz.getName().replace(Packages.from, Packages.to);
        Unsafe.defineClass(name, bytecode, 0, bytecode.length, classLoader, null);
    }
}
