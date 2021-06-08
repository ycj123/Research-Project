// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast;

import org.codehaus.groovy.GroovyBugError;
import java.util.HashMap;
import org.codehaus.groovy.ast.expr.Expression;
import java.util.Map;

public class AnnotationNode extends ASTNode
{
    public static final int TYPE_TARGET = 1;
    public static final int CONSTRUCTOR_TARGET = 2;
    public static final int METHOD_TARGET = 4;
    public static final int FIELD_TARGET = 8;
    public static final int PARAMETER_TARGET = 16;
    public static final int LOCAL_VARIABLE_TARGET = 32;
    public static final int ANNOTATION_TARGET = 64;
    public static final int PACKAGE_TARGET = 128;
    private static final int ALL_TARGETS = 255;
    private final ClassNode classNode;
    private Map<String, Expression> members;
    private boolean runtimeRetention;
    private boolean sourceRetention;
    private boolean classRetention;
    private int allowedTargets;
    
    public AnnotationNode(final ClassNode classNode) {
        this.members = new HashMap<String, Expression>();
        this.runtimeRetention = false;
        this.sourceRetention = false;
        this.classRetention = false;
        this.allowedTargets = 255;
        this.classNode = classNode;
    }
    
    public ClassNode getClassNode() {
        return this.classNode;
    }
    
    public Map<String, Expression> getMembers() {
        return this.members;
    }
    
    public Expression getMember(final String name) {
        return this.members.get(name);
    }
    
    public void addMember(final String name, final Expression value) {
        final Expression oldValue = this.members.get(name);
        if (oldValue == null) {
            this.members.put(name, value);
            return;
        }
        throw new GroovyBugError(String.format("Annotation member %s has already been added", name));
    }
    
    public void setMember(final String name, final Expression value) {
        this.members.put(name, value);
    }
    
    public boolean isBuiltIn() {
        return false;
    }
    
    public boolean hasRuntimeRetention() {
        return this.runtimeRetention;
    }
    
    public void setRuntimeRetention(final boolean flag) {
        this.runtimeRetention = flag;
    }
    
    public boolean hasSourceRetention() {
        return (!this.runtimeRetention && !this.classRetention) || this.sourceRetention;
    }
    
    public void setSourceRetention(final boolean flag) {
        this.sourceRetention = flag;
    }
    
    public boolean hasClassRetention() {
        return this.classRetention;
    }
    
    public void setClassRetention(final boolean flag) {
        this.classRetention = flag;
    }
    
    public void setAllowedTargets(final int bitmap) {
        this.allowedTargets = bitmap;
    }
    
    public boolean isTargetAllowed(final int target) {
        return (this.allowedTargets & target) == target;
    }
    
    public static String targetToName(final int target) {
        switch (target) {
            case 1: {
                return "TYPE";
            }
            case 2: {
                return "CONSTRUCTOR";
            }
            case 4: {
                return "METHOD";
            }
            case 8: {
                return "FIELD";
            }
            case 16: {
                return "PARAMETER";
            }
            case 32: {
                return "LOCAL_VARIABLE";
            }
            case 64: {
                return "ANNOTATION";
            }
            case 128: {
                return "PACKAGE";
            }
            default: {
                return "unknown target";
            }
        }
    }
}
