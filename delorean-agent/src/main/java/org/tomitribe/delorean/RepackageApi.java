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

import org.tomitribe.delorean.util.Bytecode;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Handle;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.TypePath;

import static org.tomitribe.delorean.Packages.replace;
import static org.tomitribe.delorean.Packages.replaceObj;

public class RepackageApi extends ClassVisitor implements Opcodes {

    public RepackageApi(final ClassVisitor classVisitor) {
        super(Opcodes.ASM9, classVisitor);
    }

    public static byte[] enhance(byte[] bytes, final String className) {
        try {

            final ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
            final RepackageApi updateMethods = new RepackageApi(cw);
            Bytecode.read(bytes, updateMethods);

            return cw.toByteArray();

        } catch (Exception e) {

            Log.err("Enhance Failed for '%s' : %s %s", className, e.getClass().getName(), e.getMessage());
            e.printStackTrace();

            return bytes;
        }
    }

    @Override
    public void visit(int i, int i1, String s, String s1, String s2, String[] strings) {
        super.visit(i, i1, Packages.replace(s), Packages.replace(s1), Packages.replace(s2), Packages.replace(strings));
    }

    @Override
    public AnnotationVisitor visitAnnotation(String s, boolean b) {
        final AnnotationVisitor visitor = super.visitAnnotation(Packages.replace(s), b);

        return new RepackageAnnotation(visitor);
    }

    @Override
    public MethodVisitor visitMethod(final int access, final String name, final String desc, final String signature, final String[] exceptions) {
        final MethodVisitor visitor = super.visitMethod(access, Packages.replace(name), Packages.replace(desc), Packages.replace(signature), Packages.replace(exceptions));
        return new MethodVisitor(Opcodes.ASM9, visitor) {
            @Override
            public void visitMethodInsn(int i, String s, String s1, String s2, boolean b) {
                super.visitMethodInsn(i, Packages.replace(s), Packages.replace(s1), Packages.replace(s2), b);
            }

            @Override
            public void visitFieldInsn(int i, String s, String s1, String s2) {

                super.visitFieldInsn(i, Packages.replace(s), Packages.replace(s1), Packages.replace(s2));
            }

            @Override
            public void visitTypeInsn(int i, String s) {
                super.visitTypeInsn(i, Packages.replace(s));
            }

            @Override
            public void visitParameter(String s, int i) {
                super.visitParameter(Packages.replace(s), i);
            }

            @Override
            public AnnotationVisitor visitAnnotation(String s, boolean b) {
                return new RepackageAnnotation(super.visitAnnotation(Packages.replace(s), b));
            }

            @Override
            public AnnotationVisitor visitTypeAnnotation(int i, TypePath typePath, String s, boolean b) {
                return new RepackageAnnotation(super.visitTypeAnnotation(i, typePath, Packages.replace(s), b));
            }

            @Override
            public AnnotationVisitor visitParameterAnnotation(int i, String s, boolean b) {
                return new RepackageAnnotation(super.visitParameterAnnotation(i, Packages.replace(s), b));
            }

            @Override
            public void visitInvokeDynamicInsn(String s, String s1, Handle handle, Object... objects) {
                super.visitInvokeDynamicInsn(Packages.replace(s), Packages.replace(s1), handle, Packages.replaceObj(objects));
            }

            @Override
            public void visitLdcInsn(Object o) {
                super.visitLdcInsn(Packages.replaceObj(o));
            }

            @Override
            public void visitFrame(int type, int nLocal, Object[] local, int nStack, Object[] stack) {
                super.visitFrame(type, nLocal, Packages.replaceObj(local), nStack, Packages.replaceObj(stack));
            }

            @Override
            public AnnotationVisitor visitTryCatchAnnotation(int typeRef, TypePath typePath, String desc, boolean visible) {
                return new RepackageAnnotation(super.visitTryCatchAnnotation(typeRef, typePath, Packages.replace(desc), visible));
            }

            @Override
            public AnnotationVisitor visitLocalVariableAnnotation(int typeRef, TypePath typePath, Label[] start, Label[] end, int[] index, String desc, boolean visible) {
                return new RepackageAnnotation(super.visitLocalVariableAnnotation(typeRef, typePath, start, end, index, Packages.replace(desc), visible));
            }

            @Override
            public AnnotationVisitor visitInsnAnnotation(int typeRef, TypePath typePath, String desc, boolean visible) {
                return new RepackageAnnotation(super.visitInsnAnnotation(typeRef, typePath, Packages.replace(desc), visible));
            }

            @Override
            public void visitMultiANewArrayInsn(String desc, int dims) {
                super.visitMultiANewArrayInsn(Packages.replace(desc), dims);
            }

            @Override
            public void visitLocalVariable(String name, String desc, String signature, Label start, Label end, int index) {
                super.visitLocalVariable(name, Packages.replace(desc), Packages.replace(signature), start, end, index);
            }

            @Override
            public void visitTryCatchBlock(Label start, Label end, Label handler, String type) {
                super.visitTryCatchBlock(start, end, handler, Packages.replace(type));
            }

            @Override
            public AnnotationVisitor visitAnnotationDefault() {
                return new RepackageAnnotation(super.visitAnnotationDefault());
            }
        };
    }

    @Override
    public FieldVisitor visitField(int i, String s, String s1, String s2, Object o) {

        final FieldVisitor visitor = super.visitField(i, Packages.replace(s), Packages.replace(s1), Packages.replace(s2), Packages.replaceObj(o));

        return new FieldVisitor(Opcodes.ASM9, visitor) {
            @Override
            public AnnotationVisitor visitAnnotation(String s, boolean b) {
                return super.visitAnnotation(Packages.replace(s), b);
            }

            @Override
            public AnnotationVisitor visitTypeAnnotation(int i, TypePath typePath, String s, boolean b) {
                return super.visitTypeAnnotation(i, typePath, Packages.replace(s), b);
            }
        };
    }

    @Override
    public void visitSource(String s, String s1) {
        super.visitSource(Packages.replace(s), Packages.replace(s1));
    }

    @Override
    public void visitOuterClass(String s, String s1, String s2) {
        super.visitOuterClass(Packages.replace(s), Packages.replace(s1), Packages.replace(s2));
    }

    @Override
    public AnnotationVisitor visitTypeAnnotation(int i, TypePath typePath, String s, boolean b) {
        return new RepackageAnnotation(super.visitTypeAnnotation(i, typePath, Packages.replace(s), b));
    }


    @Override
    public void visitInnerClass(String s, String s1, String s2, int i) {
        super.visitInnerClass(Packages.replace(s), Packages.replace(s1), Packages.replace(s2), i);
    }

    private static class RepackageAnnotation extends AnnotationVisitor {
        public RepackageAnnotation(AnnotationVisitor visitor) {
            super(Opcodes.ASM9, visitor);
        }

        @Override
        public void visit(String s, Object o) {
            super.visit(Packages.replace(s), Packages.replaceObj(o));
        }

        @Override
        public void visitEnum(String s, String s1, String s2) {
            super.visitEnum(Packages.replace(s), Packages.replace(s1), Packages.replace(s2));
        }

        @Override
        public AnnotationVisitor visitAnnotation(String s, String s1) {
            return super.visitAnnotation(Packages.replace(s), Packages.replace(s1));
        }

        @Override
        public AnnotationVisitor visitArray(String s) {
            return super.visitArray(Packages.replace(s));
        }
    }
}
