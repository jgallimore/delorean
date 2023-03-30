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

import org.tomitribe.delorean.api.Orange;
import org.tomitribe.delorean.util.Bytecode;
import org.junit.Assert;
import org.junit.Test;
import org.objectweb.asm.ClassReader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @version $Revision$ $Date$
 */
public class RepackageApiTest extends Assert {

    @Test
    public void test() throws Exception {
        assertBytecode(Orange.class, org.tomitribe.delorean.gen.Orange.class);
    }

    public static void assertBytecode(final Class<?> beforeClass, final Class<?> afterClass) throws Exception {
        Log.debug.set(true);
        final String tag = "idea";
        Asmifier.asmify(beforeClass, "before." + tag);
        Asmifier.asmify(afterClass, "after." + tag);
        final byte[] bytes = Bytecode.readClassFile(beforeClass);

        final byte[] actualBytes = RepackageApi.enhance(bytes, beforeClass.getName());
        final byte[] expectedBytes = Bytecode.readClassFile(afterClass);

        final String expected;
        final String actual;
        try {
            expected = asmify(expectedBytes).replaceAll("After", "");
            actual = asmify(actualBytes).replaceAll("Before", "");
        } catch (final Exception e) {
            e.printStackTrace();
            assertArrayEquals(expectedBytes, actualBytes);
            throw e;
        }


        assertEquals(expected, actual);
    }

    public static String asmify(final byte[] actualBytes) throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Asmifier.write(new ClassReader(actualBytes), byteArrayOutputStream);
        return new String(byteArrayOutputStream.toByteArray());
    }
}
