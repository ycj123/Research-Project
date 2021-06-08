// 
// Decompiled by Procyon v0.5.36
// 

package groovy.xml;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class StreamingSAXBuilder$_bind_closure6 extends Closure implements GeneratedClosure
{
    private Reference<Object> boundClosure;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$xml$StreamingSAXBuilder$_bind_closure6;
    
    public StreamingSAXBuilder$_bind_closure6(final Object _outerInstance, final Object _thisObject, final Reference<Object> boundClosure) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.boundClosure = boundClosure;
    }
    
    public Object doCall(final Object contentHandler) {
        final Object contentHandler2 = new Reference(contentHandler);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[0].call(((Reference<Object>)contentHandler2).get());
        ScriptBytecodeAdapter.setProperty(((Reference<Object>)contentHandler2).get(), $get$$class$groovy$xml$StreamingSAXBuilder$_bind_closure6(), this.boundClosure.get(), "trigger");
        return $getCallSiteArray[1].call(((Reference<Object>)contentHandler2).get());
    }
    
    public Object getBoundClosure() {
        $getCallSiteArray();
        return this.boundClosure.get();
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$xml$StreamingSAXBuilder$_bind_closure6(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (StreamingSAXBuilder$_bind_closure6.$callSiteArray == null || ($createCallSiteArray = StreamingSAXBuilder$_bind_closure6.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            StreamingSAXBuilder$_bind_closure6.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$xml$StreamingSAXBuilder$_bind_closure6() {
        Class $class$groovy$xml$StreamingSAXBuilder$_bind_closure6;
        if (($class$groovy$xml$StreamingSAXBuilder$_bind_closure6 = StreamingSAXBuilder$_bind_closure6.$class$groovy$xml$StreamingSAXBuilder$_bind_closure6) == null) {
            $class$groovy$xml$StreamingSAXBuilder$_bind_closure6 = (StreamingSAXBuilder$_bind_closure6.$class$groovy$xml$StreamingSAXBuilder$_bind_closure6 = class$("groovy.xml.StreamingSAXBuilder$_bind_closure6"));
        }
        return $class$groovy$xml$StreamingSAXBuilder$_bind_closure6;
    }
    
    static /* synthetic */ Class class$(final String className) {
        try {
            return Class.forName(className);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
