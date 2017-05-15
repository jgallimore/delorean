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

import com.tomitribe.delorean.util.Bytecode;
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
public class UpdateMethods extends ClassVisitor implements Opcodes {

    private int replaced;

    public UpdateMethods(final ClassVisitor classVisitor) {
        super(Opcodes.ASM5, classVisitor);
    }

    public static byte[] enhance(byte[] bytes, final String className) {
        try {
            Log.debug("Visit %s", className);

            final ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
            final UpdateMethods updateMethods = new UpdateMethods(cw);
            Bytecode.read(bytes, updateMethods);

            if (updateMethods.getReplaced() > 0) {
                Log.log("Replaced %s System.currentTimeMillis usages in %s", updateMethods.getReplaced(), className);
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

                // Replace System.currentTimeMillis() with FluxCapacitor.currentTimeMillis()
                if ("java/lang/System".equals(s) && "currentTimeMillis".equals(s1) && "()J".equals(s2)) {

                    replaced++;
                    super.visitMethodInsn(i, Packages.genClass, s1, s2, b);

                    // Replace the FluxCapacitor from the agent classpath with one in the bootstrap classpath
                } else if (Packages.apiClass.equals(s)) {

                    super.visitMethodInsn(i, Packages.genClass, s1, s2, b);

                    // Let the method through unmodified
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
