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
package org.tomitribe.delorean;

import org.tomitribe.delorean.api.Duration;
import org.tomitribe.delorean.api.Delorean;
import org.tomitribe.delorean.api.Normalize;
import org.tomitribe.delorean.util.Archive;
import org.tomitribe.delorean.util.Bytecode;

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
