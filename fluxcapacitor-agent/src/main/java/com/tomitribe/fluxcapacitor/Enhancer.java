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

import com.tomitribe.fluxcapacitor.util.Bytecode;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * Replaces usage of java.lang.System#currentTimeMillis
 * with com.tomitribe.delorean.Delorean#currentTimeMillis
 *
 * It is expected one instance of this class be created
 * for each class file's bytecode.
 *
 * @version $Revision$ $Date$
 */
public class Enhancer extends ClassVisitor implements Opcodes {

    private int replaced;

    public Enhancer(final ClassVisitor classVisitor) {
        super(Opcodes.ASM5, classVisitor);
    }

    public static byte[] enhance(byte[] bytes, final String className) {
        if (className.startsWith("sun/")) return bytes;
        if (className.startsWith("com/tomitribe/delorean/")) return bytes;

        try {
            final ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
            final Enhancer enhancer = new Enhancer(cw);
            Bytecode.read(bytes, enhancer);

            if (enhancer.getReplaced() > 0 ) {
                Log.debug("Replaced %s System.currentTimeMillis usages in %s", enhancer.getReplaced(), className);
            }

            return cw.toByteArray();
        } catch (Exception e) {

            Log.err("Enhance Failed for '%s' : %s %s", className, e.getClass().getName(), e.getMessage());
            e.printStackTrace();
            return bytes;
        }
    }

    @Override
    public MethodVisitor visitMethod(final int access, final String name, final String desc, final String signature, final String[] exceptions) {
        final MethodVisitor visitor = super.visitMethod(access, name, desc, signature, exceptions);
        return new MethodVisitor(Opcodes.ASM5, visitor) {
            @Override
            public void visitMethodInsn(int i, String s, String s1, String s2, boolean b) {
                if ("java/lang/System".equals(s) && "currentTimeMillis".equals(s1) && "()J".equals(s2)) {
                    replaced++;
                    super.visitMethodInsn(i, "com/tomitribe/fluxcapacitor/FluxCapacitor", s1, s2, b);
                } else {
                    super.visitMethodInsn(i, s, s1, s2, b);
                }
            }
        };
    }

    public int getReplaced() {
        return replaced;
    }
}
