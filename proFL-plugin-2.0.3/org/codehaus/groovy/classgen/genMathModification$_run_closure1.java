// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.classgen;

import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.MetaClass;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class genMathModification$_run_closure1 extends Closure implements GeneratedClosure
{
    private Reference<Object> numbers;
    private static /* synthetic */ SoftReference $callSiteArray;
    
    public genMathModification$_run_closure1(final Object _outerInstance, final Object _thisObject, final Reference<Object> numbers) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.numbers = numbers;
    }
    
    public Object doCall(final Object op) {
        final Object op2 = new Reference(op);
        return $getCallSiteArray()[0].call(this.numbers.get(), new genMathModification$_run_closure1_closure4(this, this.getThisObject(), (Reference<Object>)op2));
    }
    
    public Object getNumbers() {
        $getCallSiteArray();
        return this.numbers.get();
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = { null };
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$classgen$genMathModification$_run_closure1(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (genMathModification$_run_closure1.$callSiteArray == null || ($createCallSiteArray = genMathModification$_run_closure1.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            genMathModification$_run_closure1.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
}
