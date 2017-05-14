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
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.tomitribe.fluxcapacitor.gen;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class NormalizeDump implements Opcodes {

    public static byte[] dump() throws Exception {

        ClassWriter cw = new ClassWriter(0);
        FieldVisitor fv;
        MethodVisitor mv;
        AnnotationVisitor av0;

        cw.visit(V1_6, ACC_PUBLIC + ACC_SUPER, "com/tomitribe/fluxcapacitor/gen/Normalize", null, "java/lang/Object", null);

        {
            fv = cw.visitField(0, "a", "J", null, null);
            fv.visitEnd();
        }
        {
            fv = cw.visitField(0, "b", "J", null, null);
            fv.visitEnd();
        }
        {
            fv = cw.visitField(0, "base", "Ljava/util/concurrent/TimeUnit;", null, null);
            fv.visitEnd();
        }
        {
            mv = cw.visitMethod(0, "<init>", "(Lcom/tomitribe/fluxcapacitor/gen/Duration;Lcom/tomitribe/fluxcapacitor/gen/Duration;)V", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitMethodInsn(INVOKESTATIC, "com/tomitribe/fluxcapacitor/gen/Normalize", "lowest",
                    "(Lcom/tomitribe/fluxcapacitor/gen/Duration;Lcom/tomitribe/fluxcapacitor/gen/Duration;)Ljava/util/concurrent/TimeUnit;", false);
            mv.visitFieldInsn(PUTFIELD, "com/tomitribe/fluxcapacitor/gen/Normalize", "base", "Ljava/util/concurrent/TimeUnit;");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitFieldInsn(GETFIELD, "com/tomitribe/fluxcapacitor/gen/Duration", "unit", "Ljava/util/concurrent/TimeUnit;");
            Label l0 = new Label();
            mv.visitJumpInsn(IFNONNULL, l0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitFieldInsn(GETFIELD, "com/tomitribe/fluxcapacitor/gen/Duration", "time", "J");
            Label l1 = new Label();
            mv.visitJumpInsn(GOTO, l1);
            mv.visitLabel(l0);
            mv.visitFrame(Opcodes.F_FULL, 3, new Object[]{"com/tomitribe/fluxcapacitor/gen/Normalize", "com/tomitribe/fluxcapacitor/gen/Duration",
                            "com/tomitribe/fluxcapacitor/gen/Duration"},
                    1, new Object[]{"com/tomitribe/fluxcapacitor/gen/Normalize"});
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, "com/tomitribe/fluxcapacitor/gen/Normalize", "base", "Ljava/util/concurrent/TimeUnit;");
            mv.visitVarInsn(ALOAD, 1);
            mv.visitFieldInsn(GETFIELD, "com/tomitribe/fluxcapacitor/gen/Duration", "time", "J");
            mv.visitVarInsn(ALOAD, 1);
            mv.visitFieldInsn(GETFIELD, "com/tomitribe/fluxcapacitor/gen/Duration", "unit", "Ljava/util/concurrent/TimeUnit;");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/util/concurrent/TimeUnit", "convert", "(JLjava/util/concurrent/TimeUnit;)J", false);
            mv.visitLabel(l1);
            mv.visitFrame(Opcodes.F_FULL, 3, new Object[]{"com/tomitribe/fluxcapacitor/gen/Normalize",
                            "com/tomitribe/fluxcapacitor/gen/Duration",
                            "com/tomitribe/fluxcapacitor/gen/Duration"}, 2,
                    new Object[]{"com/tomitribe/fluxcapacitor/gen/Normalize", Opcodes.LONG});
            mv.visitFieldInsn(PUTFIELD, "com/tomitribe/fluxcapacitor/gen/Normalize", "a", "J");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitFieldInsn(GETFIELD, "com/tomitribe/fluxcapacitor/gen/Duration", "unit", "Ljava/util/concurrent/TimeUnit;");
            Label l2 = new Label();
            mv.visitJumpInsn(IFNONNULL, l2);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitFieldInsn(GETFIELD, "com/tomitribe/fluxcapacitor/gen/Duration", "time", "J");
            Label l3 = new Label();
            mv.visitJumpInsn(GOTO, l3);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_SAME1, 0, null, 1, new Object[]{"com/tomitribe/fluxcapacitor/gen/Normalize"});
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, "com/tomitribe/fluxcapacitor/gen/Normalize", "base", "Ljava/util/concurrent/TimeUnit;");
            mv.visitVarInsn(ALOAD, 2);
            mv.visitFieldInsn(GETFIELD, "com/tomitribe/fluxcapacitor/gen/Duration", "time", "J");
            mv.visitVarInsn(ALOAD, 2);
            mv.visitFieldInsn(GETFIELD, "com/tomitribe/fluxcapacitor/gen/Duration", "unit", "Ljava/util/concurrent/TimeUnit;");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/util/concurrent/TimeUnit", "convert", "(JLjava/util/concurrent/TimeUnit;)J", false);
            mv.visitLabel(l3);
            mv.visitFrame(Opcodes.F_FULL, 3, new Object[]{
                            "com/tomitribe/fluxcapacitor/gen/Normalize",
                            "com/tomitribe/fluxcapacitor/gen/Duration",
                            "com/tomitribe/fluxcapacitor/gen/Duration"}, 2,
                    new Object[]{"com/tomitribe/fluxcapacitor/gen/Normalize", Opcodes.LONG});
            mv.visitFieldInsn(PUTFIELD, "com/tomitribe/fluxcapacitor/gen/Normalize", "b", "J");
            mv.visitInsn(RETURN);
            mv.visitMaxs(5, 3);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PRIVATE + ACC_STATIC, "lowest",
                    "(Lcom/tomitribe/fluxcapacitor/gen/Duration;Lcom/tomitribe/fluxcapacitor/gen/Duration;)Ljava/util/concurrent/TimeUnit;", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, "com/tomitribe/fluxcapacitor/gen/Duration", "unit", "Ljava/util/concurrent/TimeUnit;");
            Label l0 = new Label();
            mv.visitJumpInsn(IFNONNULL, l0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitFieldInsn(GETFIELD, "com/tomitribe/fluxcapacitor/gen/Duration", "unit", "Ljava/util/concurrent/TimeUnit;");
            mv.visitInsn(ARETURN);
            mv.visitLabel(l0);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitFieldInsn(GETFIELD, "com/tomitribe/fluxcapacitor/gen/Duration", "unit", "Ljava/util/concurrent/TimeUnit;");
            Label l1 = new Label();
            mv.visitJumpInsn(IFNONNULL, l1);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, "com/tomitribe/fluxcapacitor/gen/Duration", "unit", "Ljava/util/concurrent/TimeUnit;");
            mv.visitInsn(ARETURN);
            mv.visitLabel(l1);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, "com/tomitribe/fluxcapacitor/gen/Duration", "time", "J");
            mv.visitInsn(LCONST_0);
            mv.visitInsn(LCMP);
            Label l2 = new Label();
            mv.visitJumpInsn(IFNE, l2);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitFieldInsn(GETFIELD, "com/tomitribe/fluxcapacitor/gen/Duration", "unit", "Ljava/util/concurrent/TimeUnit;");
            mv.visitInsn(ARETURN);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitFieldInsn(GETFIELD, "com/tomitribe/fluxcapacitor/gen/Duration", "time", "J");
            mv.visitInsn(LCONST_0);
            mv.visitInsn(LCMP);
            Label l3 = new Label();
            mv.visitJumpInsn(IFNE, l3);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, "com/tomitribe/fluxcapacitor/gen/Duration", "unit", "Ljava/util/concurrent/TimeUnit;");
            mv.visitInsn(ARETURN);
            mv.visitLabel(l3);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitMethodInsn(INVOKESTATIC, "java/util/concurrent/TimeUnit", "values", "()[Ljava/util/concurrent/TimeUnit;", false);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, "com/tomitribe/fluxcapacitor/gen/Duration", "unit", "Ljava/util/concurrent/TimeUnit;");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/util/concurrent/TimeUnit", "ordinal", "()I", false);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitFieldInsn(GETFIELD, "com/tomitribe/fluxcapacitor/gen/Duration", "unit", "Ljava/util/concurrent/TimeUnit;");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/util/concurrent/TimeUnit", "ordinal", "()I", false);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Math", "min", "(II)I", false);
            mv.visitInsn(AALOAD);
            mv.visitInsn(ARETURN);
            mv.visitMaxs(4, 2);
            mv.visitEnd();
        }
        cw.visitEnd();

        return cw.toByteArray();
    }
}
