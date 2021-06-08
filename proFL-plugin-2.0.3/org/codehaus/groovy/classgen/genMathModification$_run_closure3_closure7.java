// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.classgen;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class genMathModification$_run_closure3_closure7 extends Closure implements GeneratedClosure
{
    private Reference<Object> op;
    private Reference<Object> numbers;
    private static /* synthetic */ SoftReference $callSiteArray;
    
    public genMathModification$_run_closure3_closure7(final Object _outerInstance, final Object _thisObject, final Reference<Object> op, final Reference<Object> numbers) {
        final Reference numbers2 = new Reference((T)numbers);
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.op = op;
        this.numbers = numbers2.get();
    }
    
    public Object doCall(final Object wrappedType1, final Object type1) {
        final Object wrappedType2 = new Reference(wrappedType1);
        final Object type2 = new Reference(type1);
        final CallSite callSite = $getCallSiteArray()[0];
        final Object value = this.numbers.get();
        final Object thisObject = this.getThisObject();
        final Reference op = this.op;
        return callSite.call(value, new genMathModification$_run_closure3_closure7_closure8(this, thisObject, op, (Reference<Object>)wrappedType2, (Reference<Object>)type2));
    }
    
    public Object call(final Object wrappedType1, final Object type1) {
        final Object wrappedType2 = new Reference(wrappedType1);
        final Object type2 = new Reference(type1);
        return $getCallSiteArray()[1].callCurrent(this, ((Reference<Object>)wrappedType2).get(), ((Reference<Object>)type2).get());
    }
    
    public Object getOp() {
        $getCallSiteArray();
        return this.op.get();
    }
    
    public Object getNumbers() {
        $getCallSiteArray();
        return this.numbers.get();
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$classgen$genMathModification$_run_closure3_closure7(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (genMathModification$_run_closure3_closure7.$callSiteArray == null || ($createCallSiteArray = genMathModification$_run_closure3_closure7.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            genMathModification$_run_closure3_closure7.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
}
