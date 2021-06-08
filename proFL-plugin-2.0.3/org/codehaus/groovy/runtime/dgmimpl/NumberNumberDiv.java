// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.dgmimpl;

import groovy.lang.MetaMethod;
import groovy.lang.MetaClassImpl;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.NumberMath;

public final class NumberNumberDiv extends NumberNumberMetaMethod
{
    @Override
    public String getName() {
        return "div";
    }
    
    @Override
    public Object invoke(final Object object, final Object[] arguments) {
        return NumberMath.divide((Number)object, (Number)arguments[0]);
    }
    
    public static Number div(final Number left, final Number right) {
        return NumberMath.divide(left, right);
    }
    
    @Override
    public CallSite createPojoCallSite(final CallSite site, final MetaClassImpl metaClass, final MetaMethod metaMethod, final Class[] params, final Object receiver, final Object[] args) {
        if (receiver instanceof Integer) {
            if (args[0] instanceof Float) {
                return new IntegerFloat(site, metaClass, metaMethod, params, receiver, args);
            }
            if (args[0] instanceof Double) {
                return new IntegerDouble(site, metaClass, metaMethod, params, receiver, args);
            }
        }
        if (receiver instanceof Long) {
            if (args[0] instanceof Float) {
                return new LongFloat(site, metaClass, metaMethod, params, receiver, args);
            }
            if (args[0] instanceof Double) {
                return new LongDouble(site, metaClass, metaMethod, params, receiver, args);
            }
        }
        if (receiver instanceof Float) {
            if (args[0] instanceof Integer) {
                return new FloatInteger(site, metaClass, metaMethod, params, receiver, args);
            }
            if (args[0] instanceof Long) {
                return new FloatLong(site, metaClass, metaMethod, params, receiver, args);
            }
            if (args[0] instanceof Float) {
                return new FloatFloat(site, metaClass, metaMethod, params, receiver, args);
            }
            if (args[0] instanceof Double) {
                return new FloatDouble(site, metaClass, metaMethod, params, receiver, args);
            }
        }
        if (receiver instanceof Double) {
            if (args[0] instanceof Integer) {
                return new DoubleInteger(site, metaClass, metaMethod, params, receiver, args);
            }
            if (args[0] instanceof Long) {
                return new DoubleLong(site, metaClass, metaMethod, params, receiver, args);
            }
            if (args[0] instanceof Float) {
                return new DoubleFloat(site, metaClass, metaMethod, params, receiver, args);
            }
            if (args[0] instanceof Double) {
                return new DoubleDouble(site, metaClass, metaMethod, params, receiver, args);
            }
        }
        return new NumberNumber(site, metaClass, metaMethod, params, receiver, args);
    }
    
    private static class IntegerFloat extends NumberNumberCallSite
    {
        public IntegerFloat(final CallSite site, final MetaClassImpl metaClass, final MetaMethod metaMethod, final Class[] params, final Object receiver, final Object[] args) {
            super(site, metaClass, metaMethod, params, (Number)receiver, (Number)args[0]);
        }
        
        @Override
        public final Object call(final Object receiver, final Object arg) throws Throwable {
            try {
                if (this.checkPojoMetaClass()) {
                    return new Double((double)receiver / (double)arg);
                }
            }
            catch (ClassCastException ex) {}
            return super.call(receiver, arg);
        }
    }
    
    private static class IntegerDouble extends NumberNumberCallSite
    {
        public IntegerDouble(final CallSite site, final MetaClassImpl metaClass, final MetaMethod metaMethod, final Class[] params, final Object receiver, final Object[] args) {
            super(site, metaClass, metaMethod, params, (Number)receiver, (Number)args[0]);
        }
        
        @Override
        public final Object call(final Object receiver, final Object arg) throws Throwable {
            try {
                if (this.checkPojoMetaClass()) {
                    return new Double((int)receiver / (double)arg);
                }
            }
            catch (ClassCastException ex) {}
            return super.call(receiver, arg);
        }
    }
    
    private static class LongFloat extends NumberNumberCallSite
    {
        public LongFloat(final CallSite site, final MetaClassImpl metaClass, final MetaMethod metaMethod, final Class[] params, final Object receiver, final Object[] args) {
            super(site, metaClass, metaMethod, params, (Number)receiver, (Number)args[0]);
        }
        
        @Override
        public final Object call(final Object receiver, final Object arg) throws Throwable {
            try {
                if (this.checkPojoMetaClass()) {
                    return new Double((double)receiver / (double)arg);
                }
            }
            catch (ClassCastException ex) {}
            return super.call(receiver, arg);
        }
    }
    
    private static class LongDouble extends NumberNumberCallSite
    {
        public LongDouble(final CallSite site, final MetaClassImpl metaClass, final MetaMethod metaMethod, final Class[] params, final Object receiver, final Object[] args) {
            super(site, metaClass, metaMethod, params, (Number)receiver, (Number)args[0]);
        }
        
        @Override
        public final Object call(final Object receiver, final Object arg) throws Throwable {
            try {
                if (this.checkPojoMetaClass()) {
                    return new Double((double)receiver / (double)arg);
                }
            }
            catch (ClassCastException ex) {}
            return super.call(receiver, arg);
        }
    }
    
    private static class FloatInteger extends NumberNumberCallSite
    {
        public FloatInteger(final CallSite site, final MetaClassImpl metaClass, final MetaMethod metaMethod, final Class[] params, final Object receiver, final Object[] args) {
            super(site, metaClass, metaMethod, params, (Number)receiver, (Number)args[0]);
        }
        
        @Override
        public final Object call(final Object receiver, final Object arg) throws Throwable {
            try {
                if (this.checkPojoMetaClass()) {
                    return new Double((double)receiver / (double)arg);
                }
            }
            catch (ClassCastException ex) {}
            return super.call(receiver, arg);
        }
    }
    
    private static class FloatLong extends NumberNumberCallSite
    {
        public FloatLong(final CallSite site, final MetaClassImpl metaClass, final MetaMethod metaMethod, final Class[] params, final Object receiver, final Object[] args) {
            super(site, metaClass, metaMethod, params, (Number)receiver, (Number)args[0]);
        }
        
        @Override
        public final Object call(final Object receiver, final Object arg) throws Throwable {
            try {
                if (this.checkPojoMetaClass()) {
                    return new Double((double)receiver / (double)arg);
                }
            }
            catch (ClassCastException ex) {}
            return super.call(receiver, arg);
        }
    }
    
    private static class FloatFloat extends NumberNumberCallSite
    {
        public FloatFloat(final CallSite site, final MetaClassImpl metaClass, final MetaMethod metaMethod, final Class[] params, final Object receiver, final Object[] args) {
            super(site, metaClass, metaMethod, params, (Number)receiver, (Number)args[0]);
        }
        
        @Override
        public final Object call(final Object receiver, final Object arg) throws Throwable {
            try {
                if (this.checkPojoMetaClass()) {
                    return new Double((double)receiver / (double)arg);
                }
            }
            catch (ClassCastException ex) {}
            return super.call(receiver, arg);
        }
        
        @Override
        public final Object invoke(final Object receiver, final Object[] args) {
            return new Double((double)receiver / (double)args[0]);
        }
        
        public final Object invoke(final Object receiver, final Object arg) {
            return new Double((double)receiver / (double)arg);
        }
    }
    
    private static class FloatDouble extends NumberNumberCallSite
    {
        public FloatDouble(final CallSite site, final MetaClassImpl metaClass, final MetaMethod metaMethod, final Class[] params, final Object receiver, final Object[] args) {
            super(site, metaClass, metaMethod, params, (Number)receiver, (Number)args[0]);
        }
        
        @Override
        public final Object call(final Object receiver, final Object arg) throws Throwable {
            try {
                if (this.checkPojoMetaClass()) {
                    return new Double((double)receiver / (double)arg);
                }
            }
            catch (ClassCastException ex) {}
            return super.call(receiver, arg);
        }
    }
    
    private static class DoubleInteger extends NumberNumberCallSite
    {
        public DoubleInteger(final CallSite site, final MetaClassImpl metaClass, final MetaMethod metaMethod, final Class[] params, final Object receiver, final Object[] args) {
            super(site, metaClass, metaMethod, params, (Number)receiver, (Number)args[0]);
        }
        
        @Override
        public final Object call(final Object receiver, final Object arg) throws Throwable {
            try {
                if (this.checkPojoMetaClass()) {
                    return new Double((double)receiver / (double)arg);
                }
            }
            catch (ClassCastException ex) {}
            return super.call(receiver, arg);
        }
        
        @Override
        public final Object invoke(final Object receiver, final Object[] args) {
            return new Double((double)receiver / (double)args[0]);
        }
        
        public final Object invoke(final Object receiver, final Object arg) {
            return new Double((double)receiver / (double)arg);
        }
    }
    
    private static class DoubleLong extends NumberNumberCallSite
    {
        public DoubleLong(final CallSite site, final MetaClassImpl metaClass, final MetaMethod metaMethod, final Class[] params, final Object receiver, final Object[] args) {
            super(site, metaClass, metaMethod, params, (Number)receiver, (Number)args[0]);
        }
        
        @Override
        public final Object call(final Object receiver, final Object arg) throws Throwable {
            try {
                if (this.checkPojoMetaClass()) {
                    return new Double((double)receiver / (double)arg);
                }
            }
            catch (ClassCastException ex) {}
            return super.call(receiver, arg);
        }
    }
    
    private static class DoubleFloat extends NumberNumberCallSite
    {
        public DoubleFloat(final CallSite site, final MetaClassImpl metaClass, final MetaMethod metaMethod, final Class[] params, final Object receiver, final Object[] args) {
            super(site, metaClass, metaMethod, params, (Number)receiver, (Number)args[0]);
        }
        
        @Override
        public final Object call(final Object receiver, final Object arg) throws Throwable {
            try {
                if (this.checkPojoMetaClass()) {
                    return new Double((double)receiver / (double)arg);
                }
            }
            catch (ClassCastException ex) {}
            return super.call(receiver, arg);
        }
    }
    
    private static class DoubleDouble extends NumberNumberCallSite
    {
        public DoubleDouble(final CallSite site, final MetaClassImpl metaClass, final MetaMethod metaMethod, final Class[] params, final Object receiver, final Object[] args) {
            super(site, metaClass, metaMethod, params, (Number)receiver, (Number)args[0]);
        }
        
        @Override
        public final Object call(final Object receiver, final Object arg) throws Throwable {
            try {
                if (this.checkPojoMetaClass()) {
                    return new Double((double)receiver / (double)arg);
                }
            }
            catch (ClassCastException ex) {}
            return super.call(receiver, arg);
        }
    }
    
    private static class NumberNumber extends NumberNumberCallSite
    {
        public NumberNumber(final CallSite site, final MetaClassImpl metaClass, final MetaMethod metaMethod, final Class[] params, final Object receiver, final Object[] args) {
            super(site, metaClass, metaMethod, params, (Number)receiver, (Number)args[0]);
        }
        
        @Override
        public final Object invoke(final Object receiver, final Object[] args) {
            return this.math.divideImpl((Number)receiver, (Number)args[0]);
        }
        
        public final Object invoke(final Object receiver, final Object arg) {
            return this.math.divideImpl((Number)receiver, (Number)arg);
        }
    }
}
