// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ant;

import java.util.List;
import groovyjarjarasm.asm.Label;
import groovyjarjarasm.asm.tree.AbstractInsnNode;
import groovyjarjarasm.asm.MethodVisitor;
import groovyjarjarasm.asm.tree.analysis.Frame;
import groovyjarjarasm.asm.util.TraceMethodVisitor;
import groovyjarjarasm.asm.tree.analysis.Interpreter;
import groovyjarjarasm.asm.tree.analysis.Analyzer;
import groovyjarjarasm.asm.tree.analysis.SimpleVerifier;
import groovyjarjarasm.asm.tree.MethodNode;
import groovyjarjarasm.asm.ClassVisitor;
import groovyjarjarasm.asm.util.CheckClassAdapter;
import groovyjarjarasm.asm.tree.ClassNode;
import java.io.InputStream;
import groovyjarjarasm.asm.ClassReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.taskdefs.MatchingTask;

public class VerifyClass extends MatchingTask
{
    private String topDir;
    private boolean verbose;
    
    public VerifyClass() {
        this.topDir = null;
        this.verbose = false;
    }
    
    public void execute() throws BuildException {
        if (this.topDir == null) {
            throw new BuildException("no dir attribute is set");
        }
        final File top = new File(this.topDir);
        if (!top.exists()) {
            throw new BuildException("the directory " + top + " does not exist");
        }
        this.log("top dir is " + top);
        final int fails = this.execute(top);
        if (fails == 0) {
            this.log("no bytecode problems found");
        }
        else {
            this.log("found " + fails + " failing classes");
        }
    }
    
    public void setDir(final String dir) throws BuildException {
        this.topDir = dir;
    }
    
    public void setVerbose(final boolean v) {
        this.verbose = v;
    }
    
    private int execute(final File dir) {
        int fails = 0;
        final File[] files = dir.listFiles();
        for (int i = 0; i < files.length; ++i) {
            final File f = files[i];
            if (f.isDirectory()) {
                fails += this.execute(f);
            }
            else if (f.getName().endsWith(".class")) {
                try {
                    final boolean ok = this.readClass(f.getCanonicalPath());
                    if (!ok) {
                        ++fails;
                    }
                }
                catch (IOException ioe) {
                    this.log(ioe.getMessage());
                    throw new BuildException((Throwable)ioe);
                }
            }
        }
        return fails;
    }
    
    private boolean readClass(final String clazz) throws IOException {
        final ClassReader cr = new ClassReader(new FileInputStream(clazz));
        final ClassNode ca = new ClassNode() {
            @Override
            public void visitEnd() {
            }
        };
        cr.accept((ClassVisitor)new CheckClassAdapter((ClassVisitor)ca), 1);
        boolean failed = false;
        final List methods = ca.methods;
        for (int i = 0; i < methods.size(); ++i) {
            final MethodNode method = methods.get(i);
            if (method.instructions.size() > 0) {
                final Analyzer a = new Analyzer((Interpreter)new SimpleVerifier());
                try {
                    a.analyze(ca.name, method);
                }
                catch (Exception e) {
                    e.printStackTrace();
                    final Frame[] frames = a.getFrames();
                    if (!failed) {
                        failed = true;
                        this.log("verifying of class " + clazz + " failed");
                    }
                    if (this.verbose) {
                        this.log(method.name + method.desc);
                    }
                    final TraceMethodVisitor mv = new TraceMethodVisitor(null) {
                        public void visitMaxs(final int maxStack, final int maxLocals) {
                            final StringBuffer buffer = new StringBuffer();
                            for (int i = 0; i < this.text.size(); ++i) {
                                String s;
                                for (s = ((frames[i] == null) ? "null" : frames[i].toString()); s.length() < maxStack + maxLocals + 1; s += " ") {}
                                buffer.append(Integer.toString(i + 100000).substring(1));
                                buffer.append(" ");
                                buffer.append(s);
                                buffer.append(" : ");
                                buffer.append(this.text.get(i));
                            }
                            if (VerifyClass.this.verbose) {
                                VerifyClass.this.log(buffer.toString());
                            }
                        }
                    };
                    for (int j = 0; j < method.instructions.size(); ++j) {
                        final Object insn = method.instructions.get(j);
                        if (insn instanceof AbstractInsnNode) {
                            ((AbstractInsnNode)insn).accept((MethodVisitor)mv);
                        }
                        else {
                            mv.visitLabel((Label)insn);
                        }
                    }
                    mv.visitMaxs(method.maxStack, method.maxLocals);
                }
            }
        }
        return !failed;
    }
}
