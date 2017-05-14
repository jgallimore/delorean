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

public class FluxCapacitorDump implements Opcodes {

    public static byte[] dump() throws Exception {

        ClassWriter cw = new ClassWriter(0);
        FieldVisitor fv;
        MethodVisitor mv;
        AnnotationVisitor av0;

        cw.visit(V1_6, ACC_PUBLIC + ACC_SUPER, "com/tomitribe/fluxcapacitor/gen/FluxCapacitor", null, "java/lang/Object", null);

        {
            fv = cw.visitField(ACC_PRIVATE + ACC_FINAL + ACC_STATIC, "debug", "Ljava/util/concurrent/atomic/AtomicBoolean;", null, null);
            fv.visitEnd();
        }
        {
            fv = cw.visitField(ACC_PRIVATE + ACC_FINAL + ACC_STATIC, "offset", "Ljava/util/concurrent/atomic/AtomicLong;", null, null);
            fv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            mv.visitInsn(RETURN);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "currentTimeMillis", "()J", null, null);
            mv.visitCode();
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
            mv.visitFieldInsn(GETSTATIC, "com/tomitribe/fluxcapacitor/gen/FluxCapacitor", "offset",
                    "Ljava/util/concurrent/atomic/AtomicLong;");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/util/concurrent/atomic/AtomicLong", "get", "()J", false);
            mv.visitInsn(LADD);
            mv.visitInsn(LRETURN);
            mv.visitMaxs(4, 0);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "getOffset", "()J", null, null);
            mv.visitCode();
            mv.visitFieldInsn(GETSTATIC, "com/tomitribe/fluxcapacitor/gen/FluxCapacitor", "offset",
                    "Ljava/util/concurrent/atomic/AtomicLong;");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/util/concurrent/atomic/AtomicLong", "get", "()J", false);
            mv.visitInsn(LRETURN);
            mv.visitMaxs(2, 0);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "setOffset", "(J)V", null, null);
            mv.visitCode();
            mv.visitFieldInsn(GETSTATIC, "com/tomitribe/fluxcapacitor/gen/FluxCapacitor", "offset",
                    "Ljava/util/concurrent/atomic/AtomicLong;");
            mv.visitVarInsn(LLOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/util/concurrent/atomic/AtomicLong", "set", "(J)V", false);
            mv.visitInsn(RETURN);
            mv.visitMaxs(3, 2);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "setOffset", "(Ljava/lang/String;)V", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESTATIC, "com/tomitribe/fluxcapacitor/gen/FluxCapacitor", "parseOffsetToMillis",
                    "(Ljava/lang/String;)J", false);
            mv.visitVarInsn(LSTORE, 1);
            mv.visitLdcInsn("Setting offset to '%s'");
            mv.visitInsn(ICONST_1);
            mv.visitTypeInsn(ANEWARRAY, "java/lang/Object");
            mv.visitInsn(DUP);
            mv.visitInsn(ICONST_0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(AASTORE);
            mv.visitMethodInsn(INVOKESTATIC, "com/tomitribe/fluxcapacitor/gen/FluxCapacitor", "log",
                    "(Ljava/lang/String;[Ljava/lang/Object;)V", false);
            mv.visitFieldInsn(GETSTATIC, "com/tomitribe/fluxcapacitor/gen/FluxCapacitor", "offset",
                    "Ljava/util/concurrent/atomic/AtomicLong;");
            mv.visitVarInsn(LLOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/util/concurrent/atomic/AtomicLong", "set", "(J)V", false);
            mv.visitInsn(RETURN);
            mv.visitMaxs(5, 3);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "parseOffsetToMillis", "(Ljava/lang/String;)J", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "trim", "()Ljava/lang/String;", false);
            mv.visitVarInsn(ASTORE, 0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitLdcInsn(" ago");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "endsWith", "(Ljava/lang/String;)Z", false);
            Label l0 = new Label();
            mv.visitJumpInsn(IFEQ, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(ICONST_0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "length", "()I", false);
            mv.visitInsn(ICONST_4);
            mv.visitInsn(ISUB);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "substring", "(II)Ljava/lang/String;", false);
            mv.visitVarInsn(ASTORE, 0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "trim", "()Ljava/lang/String;", false);
            mv.visitVarInsn(ASTORE, 0);
            mv.visitTypeInsn(NEW, "com/tomitribe/fluxcapacitor/gen/Duration");
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, "com/tomitribe/fluxcapacitor/gen/Duration", "<init>", "(Ljava/lang/String;)V", false);
            mv.visitVarInsn(ASTORE, 1);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitFieldInsn(GETSTATIC, "java/util/concurrent/TimeUnit", "MILLISECONDS",
                    "Ljava/util/concurrent/TimeUnit;");
            mv.visitMethodInsn(INVOKEVIRTUAL, "com/tomitribe/fluxcapacitor/gen/Duration", "getTime",
                    "(Ljava/util/concurrent/TimeUnit;)J", false);
            mv.visitLdcInsn(new Long(-1L));
            mv.visitInsn(LMUL);
            mv.visitInsn(LRETURN);
            mv.visitLabel(l0);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitTypeInsn(NEW, "com/tomitribe/fluxcapacitor/gen/Duration");
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, "com/tomitribe/fluxcapacitor/gen/Duration", "<init>", "(Ljava/lang/String;)V", false);
            mv.visitVarInsn(ASTORE, 1);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitFieldInsn(GETSTATIC, "java/util/concurrent/TimeUnit", "MILLISECONDS",
                    "Ljava/util/concurrent/TimeUnit;");
            mv.visitMethodInsn(INVOKEVIRTUAL, "com/tomitribe/fluxcapacitor/gen/Duration", "getTime",
                    "(Ljava/util/concurrent/TimeUnit;)J", false);
            mv.visitInsn(LRETURN);
            mv.visitMaxs(4, 2);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "join", "(Ljava/lang/String;Ljava/util/Collection;)Ljava/lang/String;", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Collection", "size", "()I", true);
            Label l0 = new Label();
            mv.visitJumpInsn(IFNE, l0);
            mv.visitLdcInsn("");
            mv.visitInsn(ARETURN);
            mv.visitLabel(l0);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitTypeInsn(NEW, "java/lang/StringBuilder");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);
            mv.visitVarInsn(ASTORE, 2);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Collection", "iterator", "()Ljava/util/Iterator;", true);
            mv.visitVarInsn(ASTORE, 3);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitFrame(Opcodes.F_APPEND, 2, new Object[]{"java/lang/StringBuilder", "java/util/Iterator"}, 0, null);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Iterator", "hasNext", "()Z", true);
            Label l2 = new Label();
            mv.visitJumpInsn(IFEQ, l2);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Iterator", "next", "()Ljava/lang/Object;", true);
            mv.visitVarInsn(ASTORE, 4);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitVarInsn(ALOAD, 4);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/Object;)Ljava/lang/StringBuilder;", false);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
            mv.visitInsn(POP);
            mv.visitJumpInsn(GOTO, l1);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_CHOP, 1, null, 0, null);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitInsn(ICONST_0);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "length", "()I", false);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "length", "()I", false);
            mv.visitInsn(ISUB);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "substring", "(II)Ljava/lang/String;", false);
            mv.visitInsn(ARETURN);
            mv.visitMaxs(4, 5);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PRIVATE + ACC_STATIC + ACC_VARARGS, "log", "(Ljava/lang/String;[Ljava/lang/Object;)V", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/String", "format", "(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;", false);
            mv.visitVarInsn(ASTORE, 2);
            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("%tF %<tT - FluxCapacitor: %s%n");
            mv.visitInsn(ICONST_2);
            mv.visitTypeInsn(ANEWARRAY, "java/lang/Object");
            mv.visitInsn(DUP);
            mv.visitInsn(ICONST_0);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;", false);
            mv.visitInsn(AASTORE);
            mv.visitInsn(DUP);
            mv.visitInsn(ICONST_1);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitInsn(AASTORE);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "printf",
                    "(Ljava/lang/String;[Ljava/lang/Object;)Ljava/io/PrintStream;", false);
            mv.visitInsn(POP);
            mv.visitInsn(RETURN);
            mv.visitMaxs(7, 3);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_STATIC, "<clinit>", "()V", null, null);
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/util/concurrent/atomic/AtomicBoolean");
            mv.visitInsn(DUP);
            mv.visitInsn(ICONST_0);
            mv.visitMethodInsn(INVOKESPECIAL, "java/util/concurrent/atomic/AtomicBoolean", "<init>", "(Z)V", false);
            mv.visitFieldInsn(PUTSTATIC, "com/tomitribe/fluxcapacitor/gen/FluxCapacitor", "debug",
                    "Ljava/util/concurrent/atomic/AtomicBoolean;");
            mv.visitTypeInsn(NEW, "java/util/concurrent/atomic/AtomicLong");
            mv.visitInsn(DUP);
            mv.visitInsn(LCONST_0);
            mv.visitMethodInsn(INVOKESPECIAL, "java/util/concurrent/atomic/AtomicLong", "<init>", "(J)V", false);
            mv.visitFieldInsn(PUTSTATIC, "com/tomitribe/fluxcapacitor/gen/FluxCapacitor", "offset",
                    "Ljava/util/concurrent/atomic/AtomicLong;");
            mv.visitFieldInsn(GETSTATIC, "com/tomitribe/fluxcapacitor/gen/FluxCapacitor", "debug",
                    "Ljava/util/concurrent/atomic/AtomicBoolean;");
            mv.visitLdcInsn("fluxcapacitor.debug");
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Boolean", "getBoolean", "(Ljava/lang/String;)Z", false);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/util/concurrent/atomic/AtomicBoolean", "set", "(Z)V", false);
            mv.visitLdcInsn("fluxcapacitor.offset");
            mv.visitLdcInsn("0 milliseconds");
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "getProperty",
                    "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", false);
            mv.visitMethodInsn(INVOKESTATIC, "com/tomitribe/fluxcapacitor/gen/FluxCapacitor", "setOffset", "(Ljava/lang/String;)V", false);
            mv.visitInsn(RETURN);
            mv.visitMaxs(4, 0);
            mv.visitEnd();
        }
        cw.visitEnd();

        return cw.toByteArray();
    }
}
