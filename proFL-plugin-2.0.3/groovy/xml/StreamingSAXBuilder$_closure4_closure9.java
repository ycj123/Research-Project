// 
// Decompiled by Procyon v0.5.36
// 

package groovy.xml;

import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class StreamingSAXBuilder$_closure4_closure9 extends Closure implements GeneratedClosure
{
    private Reference<Object> attributes;
    private Reference<Object> namespaces;
    private static /* synthetic */ SoftReference $callSiteArray;
    
    public StreamingSAXBuilder$_closure4_closure9(final Object _outerInstance, final Object _thisObject, final Reference<Object> attributes, final Reference<Object> namespaces) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.attributes = attributes;
        this.namespaces = namespaces;
    }
    
    public Object doCall(final Object key, final Object value) {
        final Object key2 = new Reference(key);
        final Object value2 = new Reference(value);
        return $getCallSiteArray()[0].callCurrent(this, this.attributes.get(), ((Reference<Object>)key2).get(), ((Reference<Object>)value2).get(), this.namespaces.get());
    }
    
    public Object call(final Object key, final Object value) {
        final Object key2 = new Reference(key);
        final Object value2 = new Reference(value);
        return $getCallSiteArray()[1].callCurrent(this, ((Reference<Object>)key2).get(), ((Reference<Object>)value2).get());
    }
    
    public Object getAttributes() {
        $getCallSiteArray();
        return this.attributes.get();
    }
    
    public Object getNamespaces() {
        $getCallSiteArray();
        return this.namespaces.get();
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$xml$StreamingSAXBuilder$_closure4_closure9(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (StreamingSAXBuilder$_closure4_closure9.$callSiteArray == null || ($createCallSiteArray = StreamingSAXBuilder$_closure4_closure9.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            StreamingSAXBuilder$_closure4_closure9.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
}
