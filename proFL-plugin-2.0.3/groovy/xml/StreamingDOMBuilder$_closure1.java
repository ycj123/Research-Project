// 
// Decompiled by Procyon v0.5.36
// 

package groovy.xml;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ArrayUtil;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.Reference;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class StreamingDOMBuilder$_closure1 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    
    public StreamingDOMBuilder$_closure1(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object doc, final Object pendingNamespaces, final Object namespaces, final Object namespaceSpecificTags, final Object prefix, final Object attrs, final Object body, final Object dom) {
        final Object body2 = new Reference(body);
        final Object dom2 = new Reference(dom);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object comment = new Reference($getCallSiteArray[0].call($getCallSiteArray[1].callGetProperty(((Reference<Object>)dom2).get()), ((Reference<Object>)body2).get()));
        if (ScriptBytecodeAdapter.compareNotEqual(((Reference<Object>)comment).get(), null)) {
            return $getCallSiteArray[2].call($getCallSiteArray[3].callGetProperty(((Reference<Object>)dom2).get()), ((Reference<Object>)comment).get());
        }
        return null;
    }
    
    public Object call(final Object doc, final Object pendingNamespaces, final Object namespaces, final Object namespaceSpecificTags, final Object prefix, final Object attrs, final Object body, final Object dom) {
        final Object body2 = new Reference(body);
        final Object dom2 = new Reference(dom);
        return $getCallSiteArray()[4].callCurrent(this, ArrayUtil.createArray(doc, pendingNamespaces, namespaces, namespaceSpecificTags, prefix, attrs, ((Reference<Object>)body2).get(), ((Reference<Object>)dom2).get()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[5];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$xml$StreamingDOMBuilder$_closure1(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (StreamingDOMBuilder$_closure1.$callSiteArray == null || ($createCallSiteArray = StreamingDOMBuilder$_closure1.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            StreamingDOMBuilder$_closure1.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
}
