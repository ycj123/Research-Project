// 
// Decompiled by Procyon v0.5.36
// 

package groovy.xml;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class StreamingDOMBuilder$_closure4_closure10 extends Closure implements GeneratedClosure
{
    private Reference<Object> nsAttributes;
    private Reference<Object> defaultNamespace;
    private Reference<Object> namespaces;
    private Reference<Object> hiddenNamespaces;
    private static /* synthetic */ SoftReference $callSiteArray;
    
    public StreamingDOMBuilder$_closure4_closure10(final Object _outerInstance, final Object _thisObject, final Reference<Object> nsAttributes, final Reference<Object> defaultNamespace, final Reference<Object> namespaces, final Reference<Object> hiddenNamespaces) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.nsAttributes = nsAttributes;
        this.defaultNamespace = defaultNamespace;
        this.namespaces = namespaces;
        this.hiddenNamespaces = hiddenNamespaces;
    }
    
    public Object doCall(final Object key, final Object value) {
        final Object key2 = new Reference(key);
        final Object value2 = new Reference(value);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (ScriptBytecodeAdapter.compareEqual(((Reference<Object>)key2).get(), ":")) {
            this.defaultNamespace.set(new GStringImpl(new Object[] { ((Reference<Object>)value2).get() }, new String[] { "", "" }));
            return $getCallSiteArray[0].call(this.nsAttributes.get(), ScriptBytecodeAdapter.createList(new Object[] { "http://www.w3.org/2000/xmlns/", "xmlns", this.defaultNamespace.get() }));
        }
        $getCallSiteArray[1].call(this.hiddenNamespaces.get(), ((Reference<Object>)key2).get(), $getCallSiteArray[2].call(this.namespaces.get(), ((Reference<Object>)key2).get()));
        $getCallSiteArray[3].call(this.namespaces.get(), ((Reference<Object>)key2).get(), ((Reference<Object>)value2).get());
        return $getCallSiteArray[4].call(this.nsAttributes.get(), ScriptBytecodeAdapter.createList(new Object[] { "http://www.w3.org/2000/xmlns/", new GStringImpl(new Object[] { ((Reference<Object>)key2).get() }, new String[] { "xmlns:", "" }), new GStringImpl(new Object[] { ((Reference<Object>)value2).get() }, new String[] { "", "" }) }));
    }
    
    public Object call(final Object key, final Object value) {
        final Object key2 = new Reference(key);
        final Object value2 = new Reference(value);
        return $getCallSiteArray()[5].callCurrent(this, ((Reference<Object>)key2).get(), ((Reference<Object>)value2).get());
    }
    
    public Object getNsAttributes() {
        $getCallSiteArray();
        return this.nsAttributes.get();
    }
    
    public Object getDefaultNamespace() {
        $getCallSiteArray();
        return this.defaultNamespace.get();
    }
    
    public Object getNamespaces() {
        $getCallSiteArray();
        return this.namespaces.get();
    }
    
    public Object getHiddenNamespaces() {
        $getCallSiteArray();
        return this.hiddenNamespaces.get();
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[6];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$xml$StreamingDOMBuilder$_closure4_closure10(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (StreamingDOMBuilder$_closure4_closure10.$callSiteArray == null || ($createCallSiteArray = StreamingDOMBuilder$_closure4_closure10.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            StreamingDOMBuilder$_closure4_closure10.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
}
