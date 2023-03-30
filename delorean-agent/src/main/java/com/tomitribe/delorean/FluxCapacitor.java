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
import com.tomitribe.delorean.util.Archive;
import com.tomitribe.delorean.util.Bytecode;

import java.io.File;
import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.util.jar.JarFile;

public class FluxCapacitor {
    public static void install(final Instrumentation instrumentation) throws IOException {
        final Archive archive = new Archive();
        add(archive, Delorean.class);
        add(archive, Duration.class);
        add(archive, Normalize.class);
        File jar = archive.toJar();

        // we want to append this to the bootstrap classpath
        instrumentation.appendToBootstrapClassLoaderSearch(new JarFile(jar));
    }

    private static void add(final Archive archive, final Class<?> clazz) throws IOException {
        final byte[] bytecode = RepackageApi.enhance(Bytecode.readClassFile(clazz), clazz.getName());
        final String name = clazz.getName().replace('.', '/').replace(Packages.apiPackage, Packages.genPackage) + ".class";
        archive.add(name, bytecode);
    }
}
