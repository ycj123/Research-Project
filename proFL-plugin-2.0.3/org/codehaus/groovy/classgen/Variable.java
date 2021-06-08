// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.classgen;

import org.codehaus.groovy.ast.ClassHelper;
import groovyjarjarasm.asm.Label;
import org.codehaus.groovy.ast.ClassNode;

public class Variable
{
    public static final Variable THIS_VARIABLE;
    public static final Variable SUPER_VARIABLE;
    private int index;
    private ClassNode type;
    private String name;
    private final int prevCurrent;
    private boolean holder;
    private boolean property;
    private Label startLabel;
    private Label endLabel;
    private boolean dynamicTyped;
    private int prevIndex;
    
    private Variable() {
        this.startLabel = null;
        this.endLabel = null;
        this.dynamicTyped = true;
        this.index = 0;
        this.holder = false;
        this.property = false;
        this.prevCurrent = 0;
    }
    
    public Variable(final int index, final ClassNode type, final String name, final int prevCurrent) {
        this.startLabel = null;
        this.endLabel = null;
        this.index = index;
        this.type = type;
        this.name = name;
        this.prevCurrent = prevCurrent;
    }
    
    public String getName() {
        return this.name;
    }
    
    public ClassNode getType() {
        return this.type;
    }
    
    public String getTypeName() {
        return this.type.getName();
    }
    
    public int getIndex() {
        return this.index;
    }
    
    public boolean isHolder() {
        return this.holder;
    }
    
    public void setHolder(final boolean holder) {
        this.holder = holder;
    }
    
    public boolean isProperty() {
        return this.property;
    }
    
    public void setProperty(final boolean property) {
        this.property = property;
    }
    
    public Label getStartLabel() {
        return this.startLabel;
    }
    
    public void setStartLabel(final Label startLabel) {
        this.startLabel = startLabel;
    }
    
    public Label getEndLabel() {
        return this.endLabel;
    }
    
    public void setEndLabel(final Label endLabel) {
        this.endLabel = endLabel;
    }
    
    @Override
    public String toString() {
        return super.toString() + "[" + this.type + " " + this.name + " (" + this.index + ")";
    }
    
    public void setType(final ClassNode type) {
        this.type = type;
        this.dynamicTyped |= (type == ClassHelper.DYNAMIC_TYPE);
    }
    
    public void setDynamicTyped(final boolean b) {
        this.dynamicTyped = b;
    }
    
    public boolean isDynamicTyped() {
        return this.dynamicTyped;
    }
    
    public int getPrevIndex() {
        return this.prevIndex;
    }
    
    static {
        THIS_VARIABLE = new Variable();
        SUPER_VARIABLE = new Variable();
    }
}
