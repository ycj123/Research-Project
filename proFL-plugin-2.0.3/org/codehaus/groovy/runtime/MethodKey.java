// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class MethodKey
{
    private int hash;
    private String name;
    private Class sender;
    private boolean isCallToSuper;
    
    public MethodKey(final Class sender, final String name, final boolean isCallToSuper) {
        this.sender = sender;
        this.name = name;
        this.isCallToSuper = isCallToSuper;
    }
    
    public MethodKey createCopy() {
        final int size = this.getParameterCount();
        final Class[] paramTypes = new Class[size];
        for (int i = 0; i < size; ++i) {
            paramTypes[i] = this.getParameterType(i);
        }
        return new DefaultMethodKey(this.sender, this.name, paramTypes, this.isCallToSuper);
    }
    
    @Override
    public boolean equals(final Object that) {
        return this == that || (that instanceof MethodKey && this.equals((MethodKey)that));
    }
    
    public boolean equals(final MethodKey that) {
        if (this.sender != that.sender) {
            return false;
        }
        if (this.isCallToSuper != that.isCallToSuper) {
            return false;
        }
        if (!this.name.equals(that.name)) {
            return false;
        }
        final int size;
        if ((size = this.getParameterCount()) != that.getParameterCount()) {
            return false;
        }
        for (int i = 0; i < size; ++i) {
            if (this.getParameterType(i) != that.getParameterType(i)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        if (this.hash == 0) {
            this.hash = this.createHashCode();
            if (this.hash == 0) {
                this.hash = -889275714;
            }
        }
        return this.hash;
    }
    
    @Override
    public String toString() {
        return super.toString() + "[name:" + this.name + "; params:" + this.getParamterTypes();
    }
    
    public String getName() {
        return this.name;
    }
    
    public List getParamterTypes() {
        final int size = this.getParameterCount();
        if (size <= 0) {
            return Collections.EMPTY_LIST;
        }
        final List params = new ArrayList(size);
        for (int i = 0; i < size; ++i) {
            params.add(this.getParameterType(i));
        }
        return params;
    }
    
    public abstract int getParameterCount();
    
    public abstract Class getParameterType(final int p0);
    
    protected int createHashCode() {
        int answer = this.name.hashCode();
        for (int size = this.getParameterCount(), i = 0; i < size; ++i) {
            answer *= 37;
            answer += 1 + this.getParameterType(i).hashCode();
        }
        answer *= 37;
        answer += (this.isCallToSuper ? 1 : 0);
        answer *= 37;
        answer += 1 + this.sender.hashCode();
        return answer;
    }
}
