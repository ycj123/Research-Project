// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.classgen;

import org.codehaus.groovy.ast.ClassHelper;
import org.codehaus.groovy.ast.MethodNode;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.CompileUnit;

public class GeneratorContext
{
    private int innerClassIdx;
    private CompileUnit compileUnit;
    
    public GeneratorContext(final CompileUnit compileUnit) {
        this.innerClassIdx = 1;
        this.compileUnit = compileUnit;
    }
    
    public int getNextInnerClassIdx() {
        return this.innerClassIdx++;
    }
    
    public CompileUnit getCompileUnit() {
        return this.compileUnit;
    }
    
    public String getNextClosureInnerName(final ClassNode owner, final ClassNode enclosingClass, final MethodNode enclosingMethod) {
        final String ownerShortName = owner.getNameWithoutPackage();
        String classShortName = enclosingClass.getNameWithoutPackage();
        if (classShortName.equals(ownerShortName)) {
            classShortName = "";
        }
        else {
            classShortName += "_";
        }
        int dp = classShortName.lastIndexOf("$");
        if (dp >= 0) {
            classShortName = classShortName.substring(++dp);
        }
        if (classShortName.startsWith("_")) {
            classShortName = classShortName.substring(1);
        }
        String methodName = "";
        if (enclosingMethod != null) {
            methodName = enclosingMethod.getName() + "_";
            if (enclosingClass.isDerivedFrom(ClassHelper.CLOSURE_TYPE)) {
                methodName = "";
            }
            methodName = methodName.replace('<', '_');
            methodName = methodName.replace('>', '_');
            methodName = methodName.replaceAll(" ", "_");
        }
        return "_" + classShortName + methodName + "closure" + this.getNextInnerClassIdx();
    }
}
