// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.classgen;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class genMathModification$_run_closure3 extends Closure implements GeneratedClosure
{
    private Reference<Object> numbers;
    private static /* synthetic */ SoftReference $callSiteArray;
    
    public genMathModification$_run_closure3(final Object _outerInstance, final Object _thisObject, final Reference<Object> numbers) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.numbers = numbers;
    }
    
    public Object doCall(final Object op) {
        final Object op2 = new Reference(op);
        final CallSite callSite = $getCallSiteArray()[0];
        final Object value = this.numbers.get();
        final Object thisObject = this.getThisObject();
        final Object op3 = op2;
        final Reference numbers = this.numbers;
        return callSite.call(value, new genMathModification$_run_closure3_closure7(this, thisObject, (Reference<Object>)op3, numbers));
    }
    
    public Object getNumbers() {
        $getCallSiteArray();
        return this.numbers.get();
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = { null };
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$classgen$genMathModification$_run_closure3(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (genMathModification$_run_closure3.$callSiteArray == null || ($createCallSiteArray = genMathModification$_run_closure3.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            genMathModification$_run_closure3.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
}
