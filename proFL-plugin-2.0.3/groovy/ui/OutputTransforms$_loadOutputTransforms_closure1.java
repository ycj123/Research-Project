// 
// Decompiled by Procyon v0.5.36
// 

package groovy.ui;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.awt.Window;
import java.awt.Component;
import groovy.lang.Reference;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class OutputTransforms$_loadOutputTransforms_closure1 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    
    public OutputTransforms$_loadOutputTransforms_closure1(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object it) {
        final Object it2 = new Reference(it);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox((((Reference<Object>)it2).get() instanceof Component && !(((Reference<Object>)it2).get() instanceof Window)) ? Boolean.TRUE : Boolean.FALSE) && ScriptBytecodeAdapter.compareEqual($getCallSiteArray[0].callGetProperty(((Reference<Object>)it2).get()), null)) ? Boolean.TRUE : Boolean.FALSE)) {
            return ((Reference<Object>)it2).get();
        }
        return null;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = { null };
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$ui$OutputTransforms$_loadOutputTransforms_closure1(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (OutputTransforms$_loadOutputTransforms_closure1.$callSiteArray == null || ($createCallSiteArray = OutputTransforms$_loadOutputTransforms_closure1.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            OutputTransforms$_loadOutputTransforms_closure1.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
}
