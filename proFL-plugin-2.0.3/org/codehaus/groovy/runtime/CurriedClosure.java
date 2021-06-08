// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import groovy.lang.Closure;

public final class CurriedClosure extends Closure
{
    private Object[] curriedParams;
    private int index;
    private int numTrailingArgs;
    private Class varargType;
    
    public CurriedClosure(final int index, final Closure uncurriedClosure, final Object[] arguments) {
        super(uncurriedClosure.clone());
        this.numTrailingArgs = 0;
        this.varargType = null;
        this.curriedParams = arguments;
        this.index = index;
        final int origMaxLen = uncurriedClosure.getMaximumNumberOfParameters();
        this.maximumNumberOfParameters = origMaxLen - arguments.length;
        final Class[] classes = uncurriedClosure.getParameterTypes();
        final Class lastType = (classes.length == 0) ? null : classes[classes.length - 1];
        if (lastType != null && lastType.isArray()) {
            this.varargType = lastType;
        }
        if (this.isVararg()) {
            if (index < 0) {
                this.numTrailingArgs = -index - arguments.length;
            }
        }
        else {
            if (index < 0) {
                this.index += origMaxLen;
            }
            if (this.maximumNumberOfParameters < 0) {
                throw new IllegalArgumentException("Can't curry " + arguments.length + " arguments for a closure with " + origMaxLen + " parameters.");
            }
            if (index < 0) {
                if (index < -origMaxLen || index > -arguments.length) {
                    throw new IllegalArgumentException("To curry " + arguments.length + " argument(s) expect index range " + -origMaxLen + ".." + -arguments.length + " but found " + index);
                }
            }
            else if (index > this.maximumNumberOfParameters) {
                throw new IllegalArgumentException("To curry " + arguments.length + " argument(s) expect index range 0.." + this.maximumNumberOfParameters + " but found " + index);
            }
        }
    }
    
    public CurriedClosure(final Closure uncurriedClosure, final Object[] arguments) {
        this(0, uncurriedClosure, arguments);
    }
    
    @Deprecated
    public CurriedClosure(final Closure uncurriedClosure, final int i) {
        this(uncurriedClosure, new Object[] { i });
    }
    
    public Object[] getUncurriedArguments(final Object[] arguments) {
        if (!this.isVararg()) {
            final Object[] newCurriedParams = new Object[this.curriedParams.length + arguments.length];
            System.arraycopy(arguments, 0, newCurriedParams, 0, this.index);
            System.arraycopy(this.curriedParams, 0, newCurriedParams, this.index, this.curriedParams.length);
            if (arguments.length - this.index > 0) {
                System.arraycopy(arguments, this.index, newCurriedParams, this.curriedParams.length + this.index, arguments.length - this.index);
            }
            return newCurriedParams;
        }
        final int normalizedIndex = (this.index < 0) ? (this.index + arguments.length + this.curriedParams.length) : this.index;
        if (normalizedIndex < 0 || normalizedIndex > arguments.length) {
            throw new IllegalArgumentException("When currying expected index range between " + (-arguments.length - this.curriedParams.length) + ".." + (arguments.length + this.curriedParams.length) + " but found " + this.index);
        }
        final Object[] newCurriedParams2 = new Object[this.curriedParams.length + arguments.length];
        System.arraycopy(arguments, 0, newCurriedParams2, 0, normalizedIndex);
        System.arraycopy(this.curriedParams, 0, newCurriedParams2, normalizedIndex, this.curriedParams.length);
        if (arguments.length - normalizedIndex > 0) {
            System.arraycopy(arguments, normalizedIndex, newCurriedParams2, this.curriedParams.length + normalizedIndex, arguments.length - normalizedIndex);
        }
        return newCurriedParams2;
    }
    
    @Override
    public void setDelegate(final Object delegate) {
        ((Closure)this.getOwner()).setDelegate(delegate);
    }
    
    @Override
    public Object getDelegate() {
        return ((Closure)this.getOwner()).getDelegate();
    }
    
    @Override
    public void setResolveStrategy(final int resolveStrategy) {
        ((Closure)this.getOwner()).setResolveStrategy(resolveStrategy);
    }
    
    @Override
    public int getResolveStrategy() {
        return ((Closure)this.getOwner()).getResolveStrategy();
    }
    
    @Override
    public Object clone() {
        final Closure uncurriedClosure = (Closure)((Closure)this.getOwner()).clone();
        return new CurriedClosure(this.index, uncurriedClosure, this.curriedParams);
    }
    
    @Override
    public Class[] getParameterTypes() {
        final Class[] oldParams = ((Closure)this.getOwner()).getParameterTypes();
        int extraParams = 0;
        int gobbledParams = this.curriedParams.length;
        if (!this.isVararg()) {
            final Class[] newParams = new Class[oldParams.length - gobbledParams + extraParams];
            System.arraycopy(oldParams, 0, newParams, 0, this.index);
            if (newParams.length - this.index > 0) {
                System.arraycopy(oldParams, this.curriedParams.length + this.index, newParams, this.index, newParams.length - this.index);
            }
            return newParams;
        }
        final int numNonVarargs = oldParams.length - 1;
        if (this.index < 0) {
            final int absIndex = (this.index < 0) ? (-this.index) : this.index;
            if (absIndex > numNonVarargs) {
                gobbledParams = numNonVarargs;
            }
            final int newNumNonVarargs = numNonVarargs - gobbledParams;
            if (absIndex - this.curriedParams.length > newNumNonVarargs) {
                extraParams = absIndex - this.curriedParams.length - newNumNonVarargs;
            }
            final int keptParams = Math.max(numNonVarargs - absIndex, 0);
            final Class[] newParams2 = new Class[keptParams + newNumNonVarargs + extraParams + 1];
            System.arraycopy(oldParams, 0, newParams2, 0, keptParams);
            for (int i = 0; i < newNumNonVarargs; ++i) {
                newParams2[keptParams + i] = Object.class;
            }
            for (int i = 0; i < extraParams; ++i) {
                newParams2[keptParams + newNumNonVarargs + i] = this.varargType.getComponentType();
            }
            newParams2[newParams2.length - 1] = this.varargType;
            return newParams2;
        }
        final int leadingKept = Math.min(this.index, numNonVarargs);
        final int trailingKept = Math.max(numNonVarargs - leadingKept - this.curriedParams.length, 0);
        if (this.index > leadingKept) {
            extraParams = this.index - leadingKept;
        }
        final Class[] newParams3 = new Class[leadingKept + trailingKept + extraParams + 1];
        System.arraycopy(oldParams, 0, newParams3, 0, leadingKept);
        if (trailingKept > 0) {
            System.arraycopy(oldParams, leadingKept + this.curriedParams.length, newParams3, leadingKept, trailingKept);
        }
        for (int j = 0; j < extraParams; ++j) {
            newParams3[leadingKept + trailingKept + j] = this.varargType.getComponentType();
        }
        newParams3[newParams3.length - 1] = this.varargType;
        return newParams3;
    }
    
    private boolean isVararg() {
        return this.varargType != null;
    }
}
