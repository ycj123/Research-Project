// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.reflection;

import org.codehaus.groovy.runtime.ArrayUtil;

public class MethodHandle
{
    protected MethodHandle() {
    }
    
    public Object invoke(final Object receiver, final Object[] args) throws Throwable {
        throw new AbstractMethodError();
    }
    
    public Object invoke(final Object receiver) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray());
    }
    
    public Object invoke(final Object receiver, final Object arg1, final Object arg2, final Object arg3, final Object arg4) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3, arg4));
    }
    
    public Object invoke(final Object receiver, final boolean arg1) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final boolean arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final boolean arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final boolean arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final boolean arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final boolean arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final boolean arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final boolean arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final boolean arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final boolean arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final boolean arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final char arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final char arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final char arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final char arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final char arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final char arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final char arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final char arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final char arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final char arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final byte arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final byte arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final byte arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final byte arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final byte arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final byte arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final byte arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final byte arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final byte arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final byte arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final short arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final short arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final short arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final short arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final short arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final short arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final short arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final short arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final short arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final short arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final int arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final int arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final int arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final int arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final int arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final int arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final int arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final int arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final int arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final int arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final long arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final long arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final long arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final long arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final long arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final long arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final long arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final long arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final long arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final long arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final float arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final float arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final float arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final float arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final float arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final float arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final float arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final float arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final float arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final float arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final double arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final double arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final double arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final double arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final double arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final double arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final double arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final double arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final double arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final double arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final Object arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final Object arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final Object arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final Object arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final Object arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final Object arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final Object arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final Object arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final Object arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final boolean arg1, final Object arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1));
    }
    
    public Object invoke(final Object receiver, final char arg1, final boolean arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final char arg1, final boolean arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final boolean arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final boolean arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final boolean arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final boolean arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final boolean arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final boolean arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final boolean arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final boolean arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final char arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final char arg1, final char arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final char arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final char arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final char arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final char arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final char arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final char arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final char arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final char arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final byte arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final char arg1, final byte arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final byte arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final byte arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final byte arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final byte arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final byte arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final byte arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final byte arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final byte arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final short arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final char arg1, final short arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final short arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final short arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final short arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final short arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final short arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final short arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final short arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final short arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final int arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final char arg1, final int arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final int arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final int arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final int arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final int arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final int arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final int arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final int arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final int arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final long arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final char arg1, final long arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final long arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final long arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final long arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final long arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final long arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final long arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final long arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final long arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final float arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final char arg1, final float arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final float arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final float arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final float arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final float arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final float arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final float arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final float arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final float arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final double arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final char arg1, final double arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final double arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final double arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final double arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final double arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final double arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final double arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final double arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final double arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final Object arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final char arg1, final Object arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final Object arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final Object arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final Object arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final Object arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final Object arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final Object arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final Object arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final char arg1, final Object arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final boolean arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final boolean arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final boolean arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final boolean arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final boolean arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final boolean arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final boolean arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final boolean arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final boolean arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final boolean arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final char arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final char arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final char arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final char arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final char arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final char arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final char arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final char arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final char arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final char arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final byte arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final byte arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final byte arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final byte arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final byte arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final byte arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final byte arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final byte arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final byte arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final byte arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final short arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final short arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final short arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final short arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final short arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final short arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final short arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final short arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final short arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final short arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final int arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final int arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final int arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final int arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final int arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final int arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final int arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final int arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final int arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final int arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final long arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final long arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final long arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final long arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final long arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final long arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final long arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final long arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final long arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final long arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final float arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final float arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final float arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final float arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final float arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final float arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final float arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final float arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final float arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final float arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final double arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final double arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final double arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final double arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final double arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final double arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final double arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final double arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final double arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final double arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final Object arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final Object arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final Object arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final Object arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final Object arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final Object arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final Object arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final Object arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final Object arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final byte arg1, final Object arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1));
    }
    
    public Object invoke(final Object receiver, final short arg1, final boolean arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final short arg1, final boolean arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final boolean arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final boolean arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final boolean arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final boolean arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final boolean arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final boolean arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final boolean arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final boolean arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final char arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final short arg1, final char arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final char arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final char arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final char arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final char arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final char arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final char arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final char arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final char arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final byte arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final short arg1, final byte arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final byte arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final byte arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final byte arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final byte arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final byte arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final byte arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final byte arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final byte arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final short arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final short arg1, final short arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final short arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final short arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final short arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final short arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final short arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final short arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final short arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final short arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final int arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final short arg1, final int arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final int arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final int arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final int arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final int arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final int arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final int arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final int arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final int arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final long arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final short arg1, final long arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final long arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final long arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final long arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final long arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final long arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final long arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final long arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final long arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final float arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final short arg1, final float arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final float arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final float arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final float arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final float arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final float arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final float arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final float arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final float arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final double arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final short arg1, final double arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final double arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final double arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final double arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final double arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final double arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final double arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final double arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final double arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final Object arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final short arg1, final Object arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final Object arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final Object arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final Object arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final Object arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final Object arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final Object arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final Object arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final short arg1, final Object arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1));
    }
    
    public Object invoke(final Object receiver, final int arg1, final boolean arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final int arg1, final boolean arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final boolean arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final boolean arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final boolean arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final boolean arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final boolean arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final boolean arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final boolean arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final boolean arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final char arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final int arg1, final char arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final char arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final char arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final char arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final char arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final char arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final char arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final char arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final char arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final byte arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final int arg1, final byte arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final byte arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final byte arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final byte arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final byte arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final byte arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final byte arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final byte arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final byte arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final short arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final int arg1, final short arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final short arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final short arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final short arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final short arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final short arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final short arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final short arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final short arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final int arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final int arg1, final int arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final int arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final int arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final int arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final int arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final int arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final int arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final int arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final int arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final long arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final int arg1, final long arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final long arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final long arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final long arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final long arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final long arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final long arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final long arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final long arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final float arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final int arg1, final float arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final float arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final float arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final float arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final float arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final float arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final float arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final float arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final float arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final double arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final int arg1, final double arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final double arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final double arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final double arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final double arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final double arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final double arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final double arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final double arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final Object arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final int arg1, final Object arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final Object arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final Object arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final Object arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final Object arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final Object arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final Object arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final Object arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final int arg1, final Object arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1));
    }
    
    public Object invoke(final Object receiver, final long arg1, final boolean arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final long arg1, final boolean arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final boolean arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final boolean arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final boolean arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final boolean arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final boolean arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final boolean arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final boolean arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final boolean arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final char arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final long arg1, final char arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final char arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final char arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final char arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final char arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final char arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final char arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final char arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final char arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final byte arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final long arg1, final byte arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final byte arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final byte arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final byte arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final byte arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final byte arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final byte arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final byte arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final byte arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final short arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final long arg1, final short arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final short arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final short arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final short arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final short arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final short arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final short arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final short arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final short arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final int arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final long arg1, final int arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final int arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final int arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final int arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final int arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final int arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final int arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final int arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final int arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final long arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final long arg1, final long arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final long arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final long arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final long arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final long arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final long arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final long arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final long arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final long arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final float arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final long arg1, final float arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final float arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final float arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final float arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final float arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final float arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final float arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final float arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final float arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final double arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final long arg1, final double arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final double arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final double arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final double arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final double arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final double arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final double arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final double arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final double arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final Object arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final long arg1, final Object arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final Object arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final Object arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final Object arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final Object arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final Object arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final Object arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final Object arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final long arg1, final Object arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1));
    }
    
    public Object invoke(final Object receiver, final float arg1, final boolean arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final float arg1, final boolean arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final boolean arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final boolean arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final boolean arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final boolean arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final boolean arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final boolean arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final boolean arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final boolean arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final char arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final float arg1, final char arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final char arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final char arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final char arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final char arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final char arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final char arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final char arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final char arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final byte arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final float arg1, final byte arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final byte arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final byte arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final byte arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final byte arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final byte arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final byte arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final byte arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final byte arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final short arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final float arg1, final short arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final short arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final short arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final short arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final short arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final short arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final short arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final short arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final short arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final int arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final float arg1, final int arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final int arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final int arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final int arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final int arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final int arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final int arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final int arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final int arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final long arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final float arg1, final long arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final long arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final long arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final long arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final long arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final long arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final long arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final long arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final long arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final float arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final float arg1, final float arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final float arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final float arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final float arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final float arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final float arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final float arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final float arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final float arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final double arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final float arg1, final double arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final double arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final double arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final double arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final double arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final double arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final double arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final double arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final double arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final Object arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final float arg1, final Object arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final Object arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final Object arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final Object arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final Object arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final Object arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final Object arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final Object arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final float arg1, final Object arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1));
    }
    
    public Object invoke(final Object receiver, final double arg1, final boolean arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final double arg1, final boolean arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final boolean arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final boolean arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final boolean arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final boolean arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final boolean arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final boolean arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final boolean arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final boolean arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final char arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final double arg1, final char arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final char arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final char arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final char arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final char arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final char arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final char arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final char arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final char arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final byte arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final double arg1, final byte arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final byte arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final byte arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final byte arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final byte arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final byte arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final byte arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final byte arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final byte arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final short arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final double arg1, final short arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final short arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final short arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final short arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final short arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final short arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final short arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final short arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final short arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final int arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final double arg1, final int arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final int arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final int arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final int arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final int arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final int arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final int arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final int arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final int arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final long arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final double arg1, final long arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final long arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final long arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final long arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final long arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final long arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final long arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final long arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final long arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final float arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final double arg1, final float arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final float arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final float arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final float arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final float arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final float arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final float arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final float arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final float arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final double arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final double arg1, final double arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final double arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final double arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final double arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final double arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final double arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final double arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final double arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final double arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final Object arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final double arg1, final Object arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final Object arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final Object arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final Object arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final Object arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final Object arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final Object arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final Object arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final double arg1, final Object arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final boolean arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final boolean arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final boolean arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final boolean arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final boolean arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final boolean arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final boolean arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final boolean arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final boolean arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final boolean arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final char arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final char arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final char arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final char arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final char arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final char arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final char arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final char arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final char arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final char arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final byte arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final byte arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final byte arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final byte arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final byte arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final byte arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final byte arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final byte arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final byte arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final byte arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final short arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final short arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final short arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final short arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final short arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final short arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final short arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final short arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final short arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final short arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final int arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final int arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final int arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final int arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final int arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final int arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final int arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final int arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final int arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final int arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final long arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final long arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final long arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final long arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final long arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final long arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final long arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final long arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final long arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final long arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final float arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final float arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final float arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final float arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final float arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final float arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final float arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final float arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final float arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final float arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final double arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final double arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final double arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final double arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final double arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final double arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final double arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final double arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final double arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final double arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final Object arg2) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final Object arg2, final boolean arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final Object arg2, final char arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final Object arg2, final byte arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final Object arg2, final short arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final Object arg2, final int arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final Object arg2, final long arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final Object arg2, final float arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final Object arg2, final double arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
    
    public Object invoke(final Object receiver, final Object arg1, final Object arg2, final Object arg3) throws Throwable {
        return this.invoke(receiver, ArrayUtil.createArray(arg1, arg2, arg3));
    }
}
