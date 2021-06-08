// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.GStringImpl;
import groovy.lang.GroovyObject;
import groovy.lang.Reference;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class Groovysh$_displayBuffer_closure4 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    
    public Groovysh$_displayBuffer_closure4(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object line, final Object index) {
        final Object line2 = new Reference(line);
        final Object index2 = new Reference(index);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object lineNum = $getCallSiteArray[0].callCurrent(this, ((Reference<Object>)index2).get());
        return $getCallSiteArray[1].call($getCallSiteArray[2].callGetProperty($getCallSiteArray[3].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[] { lineNum, ((Reference<Object>)line2).get() }, new String[] { " ", "@|bold >|@ ", "" }));
    }
    
    public Object call(final Object line, final Object index) {
        final Object line2 = new Reference(line);
        final Object index2 = new Reference(index);
        return $getCallSiteArray()[4].callCurrent(this, ((Reference<Object>)line2).get(), ((Reference<Object>)index2).get());
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[5];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$Groovysh$_displayBuffer_closure4(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (Groovysh$_displayBuffer_closure4.$callSiteArray == null || ($createCallSiteArray = Groovysh$_displayBuffer_closure4.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            Groovysh$_displayBuffer_closure4.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
}
