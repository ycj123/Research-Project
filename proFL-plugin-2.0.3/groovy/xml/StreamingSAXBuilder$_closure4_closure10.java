// 
// Decompiled by Procyon v0.5.36
// 

package groovy.xml;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ArrayUtil;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class StreamingSAXBuilder$_closure4_closure10 extends Closure implements GeneratedClosure
{
    private Reference<Object> contentHandler;
    private Reference<Object> attributes;
    private Reference<Object> namespaces;
    private Reference<Object> hiddenNamespaces;
    private static /* synthetic */ SoftReference $callSiteArray;
    
    public StreamingSAXBuilder$_closure4_closure10(final Object _outerInstance, final Object _thisObject, final Reference<Object> contentHandler, final Reference<Object> attributes, final Reference<Object> namespaces, final Reference<Object> hiddenNamespaces) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.contentHandler = contentHandler;
        this.attributes = attributes;
        this.namespaces = namespaces;
        this.hiddenNamespaces = hiddenNamespaces;
    }
    
    public Object doCall(final Object key, final Object value) {
        final Object key2 = new Reference(key);
        final Object value2 = new Reference(value);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object k = ScriptBytecodeAdapter.compareEqual(((Reference<Object>)key2).get(), ":") ? "" : ((Reference<String>)key2).get();
        $getCallSiteArray[0].call(this.hiddenNamespaces.get(), k, $getCallSiteArray[1].call(this.namespaces.get(), ((Reference<Object>)key2).get()));
        $getCallSiteArray[2].call(this.namespaces.get(), k, ((Reference<Object>)value2).get());
        $getCallSiteArray[3].call(this.attributes.get(), ArrayUtil.createArray("http://www.w3.org/2000/xmlns/", k, new GStringImpl(new Object[] { ScriptBytecodeAdapter.compareEqual(k, "") ? "" : new GStringImpl(new Object[] { k }, new String[] { ":", "" }) }, new String[] { "xmlns", "" }), "CDATA", new GStringImpl(new Object[] { ((Reference<Object>)value2).get() }, new String[] { "", "" })));
        return $getCallSiteArray[4].call(this.contentHandler.get(), k, ((Reference<Object>)value2).get());
    }
    
    public Object call(final Object key, final Object value) {
        final Object key2 = new Reference(key);
        final Object value2 = new Reference(value);
        return $getCallSiteArray()[5].callCurrent(this, ((Reference<Object>)key2).get(), ((Reference<Object>)value2).get());
    }
    
    public Object getContentHandler() {
        $getCallSiteArray();
        return this.contentHandler.get();
    }
    
    public Object getAttributes() {
        $getCallSiteArray();
        return this.attributes.get();
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
        return new CallSiteArray($get$$class$groovy$xml$StreamingSAXBuilder$_closure4_closure10(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (StreamingSAXBuilder$_closure4_closure10.$callSiteArray == null || ($createCallSiteArray = StreamingSAXBuilder$_closure4_closure10.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            StreamingSAXBuilder$_closure4_closure10.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
}
