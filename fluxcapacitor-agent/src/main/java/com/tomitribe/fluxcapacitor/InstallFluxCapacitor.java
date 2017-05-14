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

import com.tomitribe.fluxcapacitor.api.Duration;
import com.tomitribe.fluxcapacitor.api.FluxCapacitor;
import com.tomitribe.fluxcapacitor.api.Normalize;
import com.tomitribe.fluxcapacitor.util.Bytecode;
import com.tomitribe.fluxcapacitor.util.Unsafe;

import java.io.IOException;

public class InstallFluxCapacitor {

    public static void install() {
        try {
            install(FluxCapacitor.class);
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
