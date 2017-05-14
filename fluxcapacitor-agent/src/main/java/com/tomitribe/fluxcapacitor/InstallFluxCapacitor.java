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
import com.tomitribe.fluxcapacitor.gen.DurationDump;
import com.tomitribe.fluxcapacitor.gen.FluxCapacitorDump;
import com.tomitribe.fluxcapacitor.gen.NormalizeDump;
import com.tomitribe.fluxcapacitor.util.Unsafe;

public class InstallFluxCapacitor {

    public static void install() {
        try {
            install(FluxCapacitorDump.dump(), FluxCapacitor.class);
            install(DurationDump.dump(), Duration.class);
            install(NormalizeDump.dump(), Normalize.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void install(byte[] bytecode, final Class<?> clazz) {
        final ClassLoader classLoader = Object.class.getClassLoader();
        final String name = clazz.getName().replace("api", "gen");
        Unsafe.defineClass(name, bytecode, 0, bytecode.length, classLoader, null);
    }
}
