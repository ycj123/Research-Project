// 
// Decompiled by Procyon v0.5.36
// 

package groovy.xml;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ArrayUtil;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.Reference;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class StreamingMarkupBuilder$_closure5 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    
    public StreamingMarkupBuilder$_closure5(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object doc, final Object pendingNamespaces, final Object namespaces, final Object namespaceSpecificTags, final Object prefix, final Object attrs, final Object body, final Object out) {
        final Object body2 = new Reference(body);
        final Object out2 = new Reference(out);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return $getCallSiteArray[0].call($getCallSiteArray[1].call(((Reference<Object>)out2).get()), ((Reference<Object>)body2).get());
    }
    
    public Object call(final Object doc, final Object pendingNamespaces, final Object namespaces, final Object namespaceSpecificTags, final Object prefix, final Object attrs, final Object body, final Object out) {
        final Object body2 = new Reference(body);
        final Object out2 = new Reference(out);
        return $getCallSiteArray()[2].callCurrent(this, ArrayUtil.createArray(doc, pendingNamespaces, namespaces, namespaceSpecificTags, prefix, attrs, ((Reference<Object>)body2).get(), ((Reference<Object>)out2).get()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[3];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$xml$StreamingMarkupBuilder$_closure5(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (StreamingMarkupBuilder$_closure5.$callSiteArray == null || ($createCallSiteArray = StreamingMarkupBuilder$_closure5.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            StreamingMarkupBuilder$_closure5.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
}
