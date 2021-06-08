// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.classgen;

import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.GStringImpl;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class genMathModification$_run_closure2_closure6 extends Closure implements GeneratedClosure
{
    private Reference<Object> op;
    private static /* synthetic */ SoftReference $callSiteArray;
    
    public genMathModification$_run_closure2_closure6(final Object _outerInstance, final Object _thisObject, final Reference<Object> op) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.op = op;
    }
    
    public Object doCall(final Object wrappedType, final Object type) {
        final Object type2 = new Reference(type);
        return $getCallSiteArray()[0].callCurrent(this, new GStringImpl(new Object[] { ((Reference<Object>)type2).get(), this.op.get() }, new String[] { "", "_", " = true;" }));
    }
    
    public Object call(final Object wrappedType, final Object type) {
        final Object type2 = new Reference(type);
        return $getCallSiteArray()[1].callCurrent(this, wrappedType, ((Reference<Object>)type2).get());
    }
    
    public Object getOp() {
        $getCallSiteArray();
        return this.op.get();
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$classgen$genMathModification$_run_closure2_closure6(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (genMathModification$_run_closure2_closure6.$callSiteArray == null || ($createCallSiteArray = genMathModification$_run_closure2_closure6.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            genMathModification$_run_closure2_closure6.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
}
