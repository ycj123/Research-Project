// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.classgen;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.GStringImpl;
import groovy.lang.GroovyObject;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class genDgmMath$_run_closure1_closure2 extends Closure implements GeneratedClosure
{
    private Reference<Object> a;
    private static /* synthetic */ SoftReference $callSiteArray;
    
    public genDgmMath$_run_closure1_closure2(final Object _outerInstance, final Object _thisObject, final Reference<Object> a) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.a = a;
    }
    
    public Object doCall(final Object b) {
        final Object b2 = new Reference(b);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return $getCallSiteArray[0].callCurrent(this, new GStringImpl(new Object[] { ((Reference<Object>)b2).get(), $getCallSiteArray[1].callCurrent(this, this.a.get(), ((Reference<Object>)b2).get()), this.a.get(), ((Reference<Object>)b2).get(), $getCallSiteArray[2].callCurrent(this, this.a.get(), ((Reference<Object>)b2).get()), this.a.get(), ((Reference<Object>)b2).get() }, new String[] { "\n        if (args[0] instanceof ", ")\n            return new NumberNumberCallSite (site, metaClass, metaMethod, params, (Number)receiver, (Number)args[0]){\n                public final Object invoke(Object receiver, Object[] args) {\n                    return ", ".INSTANCE.addImpl((", ")receiver,(", ")args[0]);\n                }\n\n                public final Object invokeBinop(Object receiver, Object arg) {\n                    return ", ".INSTANCE.addImpl((", ")receiver,(", ")arg);\n                }\n            };\n        " }));
    }
    
    public Object getA() {
        $getCallSiteArray();
        return this.a.get();
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[3];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$classgen$genDgmMath$_run_closure1_closure2(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (genDgmMath$_run_closure1_closure2.$callSiteArray == null || ($createCallSiteArray = genDgmMath$_run_closure1_closure2.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            genDgmMath$_run_closure1_closure2.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
}
