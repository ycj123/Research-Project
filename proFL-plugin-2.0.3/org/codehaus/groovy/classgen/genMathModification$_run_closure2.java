// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.classgen;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.GStringImpl;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class genMathModification$_run_closure2 extends Closure implements GeneratedClosure
{
    private Reference<Object> numbers;
    private static /* synthetic */ SoftReference $callSiteArray;
    
    public genMathModification$_run_closure2(final Object _outerInstance, final Object _thisObject, final Reference<Object> numbers) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.numbers = numbers;
    }
    
    public Object doCall(final Object op) {
        final Object op2 = new Reference(op);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[0].callCurrent(this, new GStringImpl(new Object[] { ((Reference<Object>)op2).get() }, new String[] { "if (\"", "\".equals(name)) {" }));
        $getCallSiteArray[1].call(this.numbers.get(), new genMathModification$_run_closure2_closure5(this, this.getThisObject(), (Reference<Object>)op2));
        $getCallSiteArray[2].callCurrent(this, "if (klazz==Object.class) {");
        $getCallSiteArray[3].call(this.numbers.get(), new genMathModification$_run_closure2_closure6(this, this.getThisObject(), (Reference<Object>)op2));
        $getCallSiteArray[4].callCurrent(this, "}");
        return $getCallSiteArray[5].callCurrent(this, "}");
    }
    
    public Object getNumbers() {
        $getCallSiteArray();
        return this.numbers.get();
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[6];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$classgen$genMathModification$_run_closure2(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (genMathModification$_run_closure2.$callSiteArray == null || ($createCallSiteArray = genMathModification$_run_closure2.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            genMathModification$_run_closure2.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
}
