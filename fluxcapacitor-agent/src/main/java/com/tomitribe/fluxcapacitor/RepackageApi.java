/*
 * Tomitribe Confidential
 *
 * Copyright Tomitribe Corporation. 2017
 *
 * The source code for this program is not published or otherwise divested 
 * of its trade secrets, irrespective of what has been deposited with the 
 * U.S. Copyright Office.
 */
package com.tomitribe.fluxcapacitor;

import com.tomitribe.fluxcapacitor.util.Bytecode;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Handle;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.TypePath;

import static com.tomitribe.fluxcapacitor.Packages.replace;
import static com.tomitribe.fluxcapacitor.Packages.replaceObj;

public class RepackageApi extends ClassVisitor implements Opcodes {

    public RepackageApi(final ClassVisitor classVisitor) {
        super(Opcodes.ASM5, classVisitor);
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
        super.visit(i, i1, replace(s), replace(s1), replace(s2), replace(strings));
    }

    @Override
    public AnnotationVisitor visitAnnotation(String s, boolean b) {
        final AnnotationVisitor visitor = super.visitAnnotation(replace(s), b);

        return new RepackageAnnotation(visitor);
    }

    @Override
    public MethodVisitor visitMethod(final int access, final String name, final String desc, final String signature, final String[] exceptions) {
        final MethodVisitor visitor = super.visitMethod(access, replace(name), replace(desc), replace(signature), replace(exceptions));
        return new MethodVisitor(Opcodes.ASM5, visitor) {
            @Override
            public void visitMethodInsn(int i, String s, String s1, String s2, boolean b) {
                super.visitMethodInsn(i, replace(s), replace(s1), replace(s2), b);
            }

            @Override
            public void visitFieldInsn(int i, String s, String s1, String s2) {

                super.visitFieldInsn(i, replace(s), replace(s1), replace(s2));
            }

            @Override
            public void visitTypeInsn(int i, String s) {
                super.visitTypeInsn(i, replace(s));
            }

            @Override
            public void visitParameter(String s, int i) {
                super.visitParameter(replace(s), i);
            }

            @Override
            public AnnotationVisitor visitAnnotation(String s, boolean b) {
                return new RepackageAnnotation(super.visitAnnotation(replace(s), b));
            }

            @Override
            public AnnotationVisitor visitTypeAnnotation(int i, TypePath typePath, String s, boolean b) {
                return new RepackageAnnotation(super.visitTypeAnnotation(i, typePath, replace(s), b));
            }

            @Override
            public AnnotationVisitor visitParameterAnnotation(int i, String s, boolean b) {
                return new RepackageAnnotation(super.visitParameterAnnotation(i, replace(s), b));
            }

            @Override
            public void visitInvokeDynamicInsn(String s, String s1, Handle handle, Object... objects) {
                super.visitInvokeDynamicInsn(replace(s), replace(s1), handle, replaceObj(objects));
            }

            @Override
            public void visitLdcInsn(Object o) {
                super.visitLdcInsn(replaceObj(o));
            }

            @Override
            public void visitFrame(int type, int nLocal, Object[] local, int nStack, Object[] stack) {
                super.visitFrame(type, nLocal, replaceObj(local), nStack, replaceObj(stack));
            }

            @Override
            public AnnotationVisitor visitTryCatchAnnotation(int typeRef, TypePath typePath, String desc, boolean visible) {
                return new RepackageAnnotation(super.visitTryCatchAnnotation(typeRef, typePath, replace(desc), visible));
            }

            @Override
            public AnnotationVisitor visitLocalVariableAnnotation(int typeRef, TypePath typePath, Label[] start, Label[] end, int[] index, String desc, boolean visible) {
                return new RepackageAnnotation(super.visitLocalVariableAnnotation(typeRef, typePath, start, end, index, replace(desc), visible));
            }

            @Override
            public AnnotationVisitor visitInsnAnnotation(int typeRef, TypePath typePath, String desc, boolean visible) {
                return new RepackageAnnotation(super.visitInsnAnnotation(typeRef, typePath, replace(desc), visible));
            }

            @Override
            public void visitMultiANewArrayInsn(String desc, int dims) {
                super.visitMultiANewArrayInsn(replace(desc), dims);
            }

            @Override
            public void visitLocalVariable(String name, String desc, String signature, Label start, Label end, int index) {
                super.visitLocalVariable(name, replace(desc), replace(signature), start, end, index);
            }

            @Override
            public void visitTryCatchBlock(Label start, Label end, Label handler, String type) {
                super.visitTryCatchBlock(start, end, handler, replace(type));
            }

            @Override
            public AnnotationVisitor visitAnnotationDefault() {
                return new RepackageAnnotation(super.visitAnnotationDefault());
            }
        };
    }

    @Override
    public FieldVisitor visitField(int i, String s, String s1, String s2, Object o) {

        final FieldVisitor visitor = super.visitField(i, replace(s), replace(s1), replace(s2), replaceObj(o));

        return new FieldVisitor(Opcodes.ASM5, visitor) {
            @Override
            public AnnotationVisitor visitAnnotation(String s, boolean b) {
                return super.visitAnnotation(replace(s), b);
            }

            @Override
            public AnnotationVisitor visitTypeAnnotation(int i, TypePath typePath, String s, boolean b) {
                return super.visitTypeAnnotation(i, typePath, replace(s), b);
            }
        };
    }

    @Override
    public void visitSource(String s, String s1) {
        super.visitSource(replace(s), replace(s1));
    }

    @Override
    public void visitOuterClass(String s, String s1, String s2) {
        super.visitOuterClass(replace(s), replace(s1), replace(s2));
    }

    @Override
    public AnnotationVisitor visitTypeAnnotation(int i, TypePath typePath, String s, boolean b) {
        return new RepackageAnnotation(super.visitTypeAnnotation(i, typePath, replace(s), b));
    }


    @Override
    public void visitInnerClass(String s, String s1, String s2, int i) {
        super.visitInnerClass(replace(s), replace(s1), replace(s2), i);
    }

    private static class RepackageAnnotation extends AnnotationVisitor {
        public RepackageAnnotation(AnnotationVisitor visitor) {
            super(Opcodes.ASM5, visitor);
        }

        @Override
        public void visit(String s, Object o) {
            super.visit(replace(s), replaceObj(o));
        }

        @Override
        public void visitEnum(String s, String s1, String s2) {
            super.visitEnum(replace(s), replace(s1), replace(s2));
        }

        @Override
        public AnnotationVisitor visitAnnotation(String s, String s1) {
            return super.visitAnnotation(replace(s), replace(s1));
        }

        @Override
        public AnnotationVisitor visitArray(String s) {
            return super.visitArray(replace(s));
        }
    }
}
