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
package com.tomitribe.fluxcapacitor;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

/**
 * @version $Rev$ $Date$
 */
public class Agent {

    private static Instrumentation instrumentation;

    private Agent() {
        // no-op
    }

    public static void premain(final String agentArgs, final Instrumentation instrumentation) {
        if (Agent.instrumentation != null) {
            return;
        }

        initialize(agentArgs, instrumentation);

        Agent.instrumentation = instrumentation;
    }

    public static void agentmain(final String agentArgs, final Instrumentation instrumentation) {
        if (Agent.instrumentation != null) {
            return;
        }

        initialize(agentArgs, instrumentation);

        Agent.instrumentation = instrumentation;
    }

    private static void initialize(final String agentArgs, final Instrumentation instrumentation) {
        try {

            Log.debug.set(Boolean.getBoolean("delorean.debug"));
            FluxCapacitor.setOffset(System.getProperty("delorean.offset", "0 milliseconds"));

            instrumentation.addTransformer(new Transformer());
            Log.log("Agent installed successfully.");

        } catch (final Throwable e) {
            Log.err("Agent installation failed %s", e.getMessage());
            e.printStackTrace();
        }
    }

    public static class Transformer implements ClassFileTransformer {

        public byte[] transform(final ClassLoader loader,
                                final String className,
                                final Class<?> classBeingRedefined,
                                final ProtectionDomain protectionDomain,
                                final byte[] classfileBuffer) throws IllegalClassFormatException {
            try {
                return Enhancer.enhance(classfileBuffer, className);
            } catch (final Throwable e) {
                return classfileBuffer;
            }
        }
    }
}
