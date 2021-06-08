// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ast;

import java.util.Iterator;
import java.util.HashMap;
import java.util.Collections;
import java.util.Map;

public class VariableScope
{
    private Map<String, Variable> declaredVariables;
    private Map<String, Variable> referencedLocalVariables;
    private Map<String, Variable> referencedClassVariables;
    private boolean inStaticContext;
    private boolean resolvesDynamic;
    private ClassNode clazzScope;
    private VariableScope parent;
    
    public VariableScope() {
        this.declaredVariables = Collections.emptyMap();
        this.referencedLocalVariables = Collections.emptyMap();
        this.referencedClassVariables = Collections.emptyMap();
        this.inStaticContext = false;
        this.resolvesDynamic = false;
    }
    
    public VariableScope(final VariableScope parent) {
        this.declaredVariables = Collections.emptyMap();
        this.referencedLocalVariables = Collections.emptyMap();
        this.referencedClassVariables = Collections.emptyMap();
        this.inStaticContext = false;
        this.resolvesDynamic = false;
        this.parent = parent;
    }
    
    public Variable getDeclaredVariable(final String name) {
        return this.declaredVariables.get(name);
    }
    
    public boolean isReferencedLocalVariable(final String name) {
        return this.referencedLocalVariables.containsKey(name);
    }
    
    public boolean isReferencedClassVariable(final String name) {
        return this.referencedClassVariables.containsKey(name);
    }
    
    public VariableScope getParent() {
        return this.parent;
    }
    
    public boolean isInStaticContext() {
        return this.inStaticContext;
    }
    
    public void setInStaticContext(final boolean inStaticContext) {
        this.inStaticContext = inStaticContext;
    }
    
    @Deprecated
    public boolean isResolvingDynamic() {
        return this.resolvesDynamic;
    }
    
    @Deprecated
    public void setDynamicResolving(final boolean resolvesDynamic) {
        this.resolvesDynamic = resolvesDynamic;
    }
    
    public void setClassScope(final ClassNode node) {
        this.clazzScope = node;
    }
    
    public ClassNode getClassScope() {
        return this.clazzScope;
    }
    
    public boolean isClassScope() {
        return this.clazzScope != null;
    }
    
    public boolean isRoot() {
        return this.parent == null;
    }
    
    public VariableScope copy() {
        final VariableScope copy = new VariableScope();
        copy.clazzScope = this.clazzScope;
        if (this.declaredVariables.size() > 0) {
            (copy.declaredVariables = new HashMap<String, Variable>()).putAll(this.declaredVariables);
        }
        copy.inStaticContext = this.inStaticContext;
        copy.parent = this.parent;
        if (this.referencedClassVariables.size() > 0) {
            (copy.referencedClassVariables = new HashMap<String, Variable>()).putAll(this.referencedClassVariables);
        }
        if (this.referencedLocalVariables.size() > 0) {
            (copy.referencedLocalVariables = new HashMap<String, Variable>()).putAll(this.referencedLocalVariables);
        }
        copy.resolvesDynamic = this.resolvesDynamic;
        return copy;
    }
    
    public void putDeclaredVariable(final Variable var) {
        if (this.declaredVariables == Collections.EMPTY_MAP) {
            this.declaredVariables = new HashMap<String, Variable>();
        }
        this.declaredVariables.put(var.getName(), var);
    }
    
    public Iterator<Variable> getReferencedLocalVariablesIterator() {
        return this.referencedLocalVariables.values().iterator();
    }
    
    public int getReferencedLocalVariablesCount() {
        return this.referencedLocalVariables.size();
    }
    
    public Variable getReferencedLocalVariable(final String name) {
        return this.referencedLocalVariables.get(name);
    }
    
    public void putReferencedLocalVariable(final Variable var) {
        if (this.referencedLocalVariables == Collections.EMPTY_MAP) {
            this.referencedLocalVariables = new HashMap<String, Variable>();
        }
        this.referencedLocalVariables.put(var.getName(), var);
    }
    
    public void putReferencedClassVariable(final Variable var) {
        if (this.referencedClassVariables == Collections.EMPTY_MAP) {
            this.referencedClassVariables = new HashMap<String, Variable>();
        }
        this.referencedClassVariables.put(var.getName(), var);
    }
    
    public Variable getReferencedClassVariable(final String name) {
        return this.referencedClassVariables.get(name);
    }
    
    public Object removeReferencedClassVariable(final String name) {
        if (this.referencedClassVariables == Collections.EMPTY_MAP) {
            return null;
        }
        return this.referencedClassVariables.remove(name);
    }
    
    public Map<String, Variable> getReferencedClassVariables() {
        if (this.referencedClassVariables == Collections.EMPTY_MAP) {
            return this.referencedClassVariables;
        }
        return Collections.unmodifiableMap((Map<? extends String, ? extends Variable>)this.referencedClassVariables);
    }
    
    public Iterator<Variable> getReferencedClassVariablesIterator() {
        return this.getReferencedClassVariables().values().iterator();
    }
}
