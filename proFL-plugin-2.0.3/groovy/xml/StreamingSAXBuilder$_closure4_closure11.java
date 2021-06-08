// 
// Decompiled by Procyon v0.5.36
// 

package groovy.xml;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class StreamingSAXBuilder$_closure4_closure11 extends Closure implements GeneratedClosure
{
    private Reference<Object> contentHandler;
    private Reference<Object> namespaces;
    private static /* synthetic */ SoftReference $callSiteArray;
    
    public StreamingSAXBuilder$_closure4_closure11(final Object _outerInstance, final Object _thisObject, final Reference<Object> contentHandler, final Reference<Object> namespaces) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.contentHandler = contentHandler;
        this.namespaces = namespaces;
    }
    
    public Object doCall(final Object key, final Object value) {
        final Object key2 = new Reference(key);
        final Object value2 = new Reference(value);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[0].call(this.contentHandler.get(), ((Reference<Object>)key2).get());
        if (ScriptBytecodeAdapter.compareEqual(((Reference<Object>)value2).get(), null)) {
            return $getCallSiteArray[1].call(this.namespaces.get(), ((Reference<Object>)key2).get());
        }
        final CallSite callSite = $getCallSiteArray[2];
        final Object value3 = this.namespaces.get();
        final Object value4 = ((Reference<Object>)key2).get();
        final Object value5 = ((Reference<Object>)value2).get();
        callSite.call(value3, value4, value5);
        return value5;
    }
    
    public Object call(final Object key, final Object value) {
        final Object key2 = new Reference(key);
        final Object value2 = new Reference(value);
        return $getCallSiteArray()[3].callCurrent(this, ((Reference<Object>)key2).get(), ((Reference<Object>)value2).get());
    }
    
    public Object getContentHandler() {
        $getCallSiteArray();
        return this.contentHandler.get();
    }
    
    public Object getNamespaces() {
        $getCallSiteArray();
        return this.namespaces.get();
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[4];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$xml$StreamingSAXBuilder$_closure4_closure11(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (StreamingSAXBuilder$_closure4_closure11.$callSiteArray == null || ($createCallSiteArray = StreamingSAXBuilder$_closure4_closure11.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            StreamingSAXBuilder$_closure4_closure11.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
}
