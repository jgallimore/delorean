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

import junit.framework.TestCase;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @version $Revision$ $Date$
 */
public class AsmModifiersTest extends TestCase {

    public void testIsPublic() throws Exception {
        final ClassReader classReader = new ClassReader(IsPublic.class.getName());
        classReader.accept(new ClassVisitor(Opcodes.ASM9) {
            @Override
            public MethodVisitor visitMethod(final int access, final String name, final String desc, final String signature, final String[] exceptions) {
                if (name.equals("<init>")) {
                    return super.visitMethod(access, name, desc, signature, exceptions);
                }

                assertTrue(AsmModifiers.isPublic(access));
                assertFalse(AsmModifiers.isPrivate(access));
                assertFalse(AsmModifiers.isProtected(access));
                assertFalse(AsmModifiers.isFinal(access));
                assertFalse(AsmModifiers.isAbstract(access));
                assertFalse(AsmModifiers.isNative(access));
                assertFalse(AsmModifiers.isStatic(access));
                assertFalse(AsmModifiers.isSynchronized(access));

                return super.visitMethod(access, name, desc, signature, exceptions);
            }
        }, 0);
    }

    public void testIsPrivate() throws Exception {
        final ClassReader classReader = new ClassReader(IsPrivate.class.getName());
        classReader.accept(new ClassVisitor(Opcodes.ASM9) {
            @Override
            public MethodVisitor visitMethod(final int access, final String name, final String desc, final String signature, final String[] exceptions) {
                if (name.equals("<init>")) {
                    return super.visitMethod(access, name, desc, signature, exceptions);
                }

                assertFalse(AsmModifiers.isPublic(access));
                assertTrue(AsmModifiers.isPrivate(access));
                assertFalse(AsmModifiers.isProtected(access));
                assertFalse(AsmModifiers.isFinal(access));
                assertFalse(AsmModifiers.isAbstract(access));
                assertFalse(AsmModifiers.isNative(access));
                assertFalse(AsmModifiers.isStatic(access));
                assertFalse(AsmModifiers.isSynchronized(access));

                return super.visitMethod(access, name, desc, signature, exceptions);
            }
        }, 0);
    }

    public void testIsProtected() throws Exception {
        final ClassReader classReader = new ClassReader(IsProtected.class.getName());
        classReader.accept(new ClassVisitor(Opcodes.ASM9) {
            @Override
            public MethodVisitor visitMethod(final int access, final String name, final String desc, final String signature, final String[] exceptions) {
                if (name.equals("<init>")) {
                    return super.visitMethod(access, name, desc, signature, exceptions);
                }

                assertFalse(AsmModifiers.isPublic(access));
                assertFalse(AsmModifiers.isPrivate(access));
                assertTrue(AsmModifiers.isProtected(access));
                assertFalse(AsmModifiers.isFinal(access));
                assertFalse(AsmModifiers.isAbstract(access));
                assertFalse(AsmModifiers.isNative(access));
                assertFalse(AsmModifiers.isStatic(access));
                assertFalse(AsmModifiers.isSynchronized(access));

                return super.visitMethod(access, name, desc, signature, exceptions);
            }
        }, 0);

    }

    public void testIsStatic() throws Exception {
        final ClassReader classReader = new ClassReader(IsStatic.class.getName());
        classReader.accept(new ClassVisitor(Opcodes.ASM9) {
            @Override
            public MethodVisitor visitMethod(final int access, final String name, final String desc, final String signature, final String[] exceptions) {
                if (name.equals("<init>")) {
                    return super.visitMethod(access, name, desc, signature, exceptions);
                }

                assertTrue(AsmModifiers.isPublic(access));
                assertFalse(AsmModifiers.isPrivate(access));
                assertFalse(AsmModifiers.isProtected(access));
                assertFalse(AsmModifiers.isFinal(access));
                assertFalse(AsmModifiers.isAbstract(access));
                assertFalse(AsmModifiers.isNative(access));
                assertTrue(AsmModifiers.isStatic(access));
                assertFalse(AsmModifiers.isSynchronized(access));

                return super.visitMethod(access, name, desc, signature, exceptions);
            }
        }, 0);

    }

    public void testIsFinal() throws Exception {
        final ClassReader classReader = new ClassReader(IsFinal.class.getName());
        classReader.accept(new ClassVisitor(Opcodes.ASM9) {
            @Override
            public MethodVisitor visitMethod(final int access, final String name, final String desc, final String signature, final String[] exceptions) {
                if (name.equals("<init>")) {
                    return super.visitMethod(access, name, desc, signature, exceptions);
                }

                assertTrue(AsmModifiers.isPublic(access));
                assertFalse(AsmModifiers.isPrivate(access));
                assertFalse(AsmModifiers.isProtected(access));
                assertTrue(AsmModifiers.isFinal(access));
                assertFalse(AsmModifiers.isAbstract(access));
                assertFalse(AsmModifiers.isNative(access));
                assertFalse(AsmModifiers.isStatic(access));
                assertFalse(AsmModifiers.isSynchronized(access));

                return super.visitMethod(access, name, desc, signature, exceptions);
            }
        }, 0);
    }

    public void testIsSynchronized() throws Exception {
        final ClassReader classReader = new ClassReader(IsSynchronized.class.getName());
        classReader.accept(new ClassVisitor(Opcodes.ASM9) {
            @Override
            public MethodVisitor visitMethod(final int access, final String name, final String desc, final String signature, final String[] exceptions) {
                if (name.equals("<init>")) {
                    return super.visitMethod(access, name, desc, signature, exceptions);
                }

                assertTrue(AsmModifiers.isPublic(access));
                assertFalse(AsmModifiers.isPrivate(access));
                assertFalse(AsmModifiers.isProtected(access));
                assertFalse(AsmModifiers.isFinal(access));
                assertFalse(AsmModifiers.isAbstract(access));
                assertFalse(AsmModifiers.isNative(access));
                assertFalse(AsmModifiers.isStatic(access));
                assertTrue(AsmModifiers.isSynchronized(access));

                return super.visitMethod(access, name, desc, signature, exceptions);
            }
        }, 0);
    }

    public void testIsVolatile() throws Exception {
        final ClassReader classReader = new ClassReader(IsVolatile.class.getName());
        classReader.accept(new ClassVisitor(Opcodes.ASM9) {
            @Override
            public FieldVisitor visitField(final int access, final String s, final String s1, final String s2, final Object o) {

                assertFalse(AsmModifiers.isPublic(access));
                assertTrue(AsmModifiers.isPrivate(access));
                assertFalse(AsmModifiers.isProtected(access));

                assertFalse(AsmModifiers.isFinal(access));
                assertFalse(AsmModifiers.isStatic(access));
                assertFalse(AsmModifiers.isTransient(access));
                assertTrue(AsmModifiers.isVolatile(access));

                return super.visitField(access, s, s1, s2, o);
            }
        }, 0);
    }

    public void testIsTransient() throws Exception {
        final ClassReader classReader = new ClassReader(IsTransient.class.getName());
        classReader.accept(new ClassVisitor(Opcodes.ASM9) {
            @Override
            public FieldVisitor visitField(final int access, final String s, final String s1, final String s2, final Object o) {

                assertFalse(AsmModifiers.isPublic(access));
                assertTrue(AsmModifiers.isPrivate(access));
                assertFalse(AsmModifiers.isProtected(access));

                assertFalse(AsmModifiers.isFinal(access));
                assertFalse(AsmModifiers.isStatic(access));
                assertTrue(AsmModifiers.isTransient(access));
                assertFalse(AsmModifiers.isVolatile(access));

                return super.visitField(access, s, s1, s2, o);
            }
        }, 0);
    }

    public void testIsNative() throws Exception {
        final ClassReader classReader = new ClassReader(IsNative.class.getName());
        classReader.accept(new ClassVisitor(Opcodes.ASM9) {
            @Override
            public MethodVisitor visitMethod(final int access, final String name, final String desc, final String signature, final String[] exceptions) {
                if (name.equals("<init>")) {
                    return super.visitMethod(access, name, desc, signature, exceptions);
                }

                assertTrue(AsmModifiers.isPublic(access));
                assertFalse(AsmModifiers.isPrivate(access));
                assertFalse(AsmModifiers.isProtected(access));
                assertFalse(AsmModifiers.isFinal(access));
                assertFalse(AsmModifiers.isAbstract(access));
                assertTrue(AsmModifiers.isNative(access));
                assertFalse(AsmModifiers.isStatic(access));
                assertFalse(AsmModifiers.isSynchronized(access));

                return super.visitMethod(access, name, desc, signature, exceptions);
            }
        }, 0);
    }

    public void testIsAbstract() throws Exception {
        final ClassReader classReader = new ClassReader(IsAbstract.class.getName());
        classReader.accept(new ClassVisitor(Opcodes.ASM9) {
            @Override
            public MethodVisitor visitMethod(final int access, final String name, final String desc, final String signature, final String[] exceptions) {
                if (name.equals("<init>")) {
                    return super.visitMethod(access, name, desc, signature, exceptions);
                }

                assertTrue(AsmModifiers.isPublic(access));
                assertFalse(AsmModifiers.isPrivate(access));
                assertFalse(AsmModifiers.isProtected(access));
                assertFalse(AsmModifiers.isFinal(access));
                assertTrue(AsmModifiers.isAbstract(access));
                assertFalse(AsmModifiers.isNative(access));
                assertFalse(AsmModifiers.isStatic(access));
                assertFalse(AsmModifiers.isSynchronized(access));

                return super.visitMethod(access, name, desc, signature, exceptions);
            }
        }, 0);
    }

    public static class IsPublic {
        public void method() {
        }
    }

    public static class IsPrivate {
        private void method() {
        }
    }

    public static class IsProtected {
        protected void method() {
        }
    }

    public static class IsFinal {
        public final void method() {
        }
    }

    public static class IsStatic {
        public static void method() {
        }
    }

    public static class IsSynchronized {
        public synchronized void method() {
        }
    }

    public static class IsNative {
        public native void method();
    }

    public abstract static class IsAbstract {
        public abstract void method();
    }

    public static class IsTransient {
        private transient Object field;
    }

    public static class IsVolatile {
        private volatile Object field;
    }
}
