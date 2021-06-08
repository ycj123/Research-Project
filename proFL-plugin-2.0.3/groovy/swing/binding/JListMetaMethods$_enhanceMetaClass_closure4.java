// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.binding;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.Reference;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class JListMetaMethods$_enhanceMetaClass_closure4 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    
    public JListMetaMethods$_enhanceMetaClass_closure4(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object item) {
        final Object item2 = new Reference(item);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return $getCallSiteArray[0].call($getCallSiteArray[1].callGroovyObjectGetProperty(this), ((Reference<Object>)item2).get(), Boolean.TRUE);
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$binding$JListMetaMethods$_enhanceMetaClass_closure4(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (JListMetaMethods$_enhanceMetaClass_closure4.$callSiteArray == null || ($createCallSiteArray = JListMetaMethods$_enhanceMetaClass_closure4.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            JListMetaMethods$_enhanceMetaClass_closure4.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
}
