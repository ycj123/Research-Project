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

class genDgmMath$_run_closure1 extends Closure implements GeneratedClosure
{
    private Reference<Object> types;
    private static /* synthetic */ SoftReference $callSiteArray;
    
    public genDgmMath$_run_closure1(final Object _outerInstance, final Object _thisObject, final Reference<Object> types) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.types = types;
    }
    
    public Object doCall(final Object a) {
        final Object a2 = new Reference(a);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[0].callCurrent(this, new GStringImpl(new Object[] { ((Reference<Object>)a2).get() }, new String[] { "\n    if (receiver instanceof ", ") {" }));
        $getCallSiteArray[1].call(this.types.get(), new genDgmMath$_run_closure1_closure2(this, this.getThisObject(), (Reference<Object>)a2));
        return $getCallSiteArray[2].callCurrent(this, "}");
    }
    
    public Object getTypes() {
        $getCallSiteArray();
        return this.types.get();
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[3];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$classgen$genDgmMath$_run_closure1(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (genDgmMath$_run_closure1.$callSiteArray == null || ($createCallSiteArray = genDgmMath$_run_closure1.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            genDgmMath$_run_closure1.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
}
