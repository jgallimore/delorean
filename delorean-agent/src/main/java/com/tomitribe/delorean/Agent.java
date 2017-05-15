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
package com.tomitribe.delorean;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.security.ProtectionDomain;
import java.util.Currency;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.logging.LogRecord;

/**
 * @version $Rev$ $Date$
 */
public class Agent {

    private static Instrumentation instrumentation;

    static {
        FluxCapacitor.install();
    }

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
            instrumentation.addTransformer(new Transformer(), true);
            Log.log("Agent installed successfully.");

            retransform(instrumentation,
                    Date.class,
                    GregorianCalendar.class,
                    Thread.class,
                    java.time.Clock.class,
                    Currency.class,
                    Timer.class,
                    LogRecord.class,
                    ResourceBundle.class
            );

            boolean modifiableClass = instrumentation.isModifiableClass(System.class);
            System.out.println(modifiableClass);
        } catch (final Throwable e) {
            Log.err("Agent installation failed %s", e.getMessage());
            e.printStackTrace();
        }
    }

    private static void retransform(Instrumentation instrumentation, Class<?>... clazzes) throws UnmodifiableClassException {
        for (final Class<?> clazz : clazzes) {
            retransform(instrumentation, clazz);
        }
    }
    private static void retransform(Instrumentation instrumentation, Class<?> clazz) throws UnmodifiableClassException {
        if (instrumentation.isModifiableClass(clazz)) {
            Log.debug("Retransform %s", clazz);
            instrumentation.retransformClasses(clazz);
        } else {
            Log.err("Cannot be retransformed %s", clazz);
        }
    }

    public static class Transformer implements ClassFileTransformer {

        public byte[] transform(final ClassLoader loader,
                                final String className,
                                final Class<?> classBeingRedefined,
                                final ProtectionDomain protectionDomain,
                                final byte[] classfileBuffer) throws IllegalClassFormatException {
            try {
                return UpdateMethods.enhance(classfileBuffer, className);
            } catch (final Throwable e) {
                return classfileBuffer;
            }
        }
    }
}
