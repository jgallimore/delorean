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
package com.tomitribe.delorean.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @version $Revision$ $Date$
 */
public class Archive {

    final Map<String, String> manifest = new HashMap<String, String>();
    final Map<String, byte[]> entries = new HashMap<String, byte[]>();

    public static Archive archive() {
        return new Archive();
    }

    public Archive manifest(final String key, final Object value) {
        manifest.put(key, value.toString());
        return this;
    }

    public Archive manifest(final String key, final Class value) {
        manifest.put(key, value.getName());
        return this;
    }

    public Archive add(final Class<?> clazz) {
        try {
            final String name = clazz.getName().replace('.', '/') + ".class";

            final URL resource = this.getClass().getClassLoader().getResource(name);

            final InputStream from = new BufferedInputStream(resource.openStream());
            final ByteArrayOutputStream to = new ByteArrayOutputStream();

            final byte[] buffer = new byte[1024];
            int length;
            while ((length = from.read(buffer)) != -1) {
                to.write(buffer, 0, length);
            }
            to.flush();

            entries.put(name, to.toByteArray());
        } catch (final IOException e) {
            throw new IllegalStateException(e);
        }

        return this;
    }
    public Archive add(final String name, byte[] content) {
        entries.put(name, content);
        return this;
    }

    public Archive addDir(final File dir) {
        try {

            addDir(null, dir);

        } catch (final IOException e) {
            throw new IllegalStateException(e);
        }

        return this;
    }

    private void addDir(final String path, final File dir) throws IOException {
        for (final File file : dir.listFiles()) {

            final String childPath = (path != null) ? path + "/" + file.getName() : file.getName();

            if (file.isFile()) {
                entries.put(childPath, IO.readBytes(file));
            } else {
                addDir(childPath, file);
            }
        }
    }

    public File toJar() throws IOException {
        final File file = File.createTempFile("archive-", ".jar");
        file.deleteOnExit();

        // Create the ZIP file
        final ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(file)));

        for (final Map.Entry<String, byte[]> entry : entries().entrySet()) {
            out.putNextEntry(new ZipEntry(entry.getKey()));
            out.write(entry.getValue());
        }

        // Complete the ZIP file
        out.close();
        return file;
    }

    public File asJar() {
        try {
            return toJar();
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    public File toDir() throws IOException {

        final File classpath = Files.tmpdir();

        for (final Map.Entry<String, byte[]> entry : entries().entrySet()) {

            final String key = entry.getKey().replace('/', File.separatorChar);

            final File file = new File(classpath, key);

            final File d = file.getParentFile();

            if (!d.exists()) {
                if (!d.mkdirs()) {
                    throw new RuntimeException("Unable to create " + d.getAbsolutePath());
                }
            }

            final BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));

            out.write(entry.getValue());

            out.close();
        }

        return classpath;
    }

    public File asDir() {
        try {
            return toDir();
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    private HashMap<String, byte[]> entries() {
        final HashMap<String, byte[]> entries = new HashMap<String, byte[]>(this.entries);
        entries.put("META-INF/MANIFEST.MF", buildManifest().getBytes());
        return entries;
    }

    private String buildManifest() {
        return Join.join("\r\n", new Join.NameCallback<Map.Entry<String, String>>() {
            @Override
            public String getName(final Map.Entry<String, String> entry) {
                return entry.getKey() + ": " + entry.getValue();
            }
        }, manifest.entrySet());
    }

    public Archive addJar(final File file) {
        try {
            final JarFile jarFile = new JarFile(file);

            final Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                final JarEntry entry = entries.nextElement();
                this.entries.put(entry.getName(), IO.readBytes(jarFile.getInputStream(entry)));
            }

            return this;
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }
}
