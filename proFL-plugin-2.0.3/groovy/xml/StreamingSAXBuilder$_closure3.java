// 
// Decompiled by Procyon v0.5.36
// 

package groovy.xml;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.ArrayUtil;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.Reference;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class StreamingSAXBuilder$_closure3 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    
    public StreamingSAXBuilder$_closure3(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object doc, final Object pendingNamespaces, final Object namespaces, final Object namespaceSpecificTags, final Object prefix, final Object attrs, final Object body, final Object contentHandler) {
        final Object doc2 = new Reference(doc);
        final Object body2 = new Reference(body);
        final Object contentHandler2 = new Reference(contentHandler);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (ScriptBytecodeAdapter.compareNotEqual(((Reference<Object>)body2).get(), null)) {
            return $getCallSiteArray[0].callCurrent(this, ((Reference<Object>)body2).get(), ((Reference<Object>)doc2).get(), ((Reference<Object>)contentHandler2).get());
        }
        return null;
    }
    
    public Object call(final Object doc, final Object pendingNamespaces, final Object namespaces, final Object namespaceSpecificTags, final Object prefix, final Object attrs, final Object body, final Object contentHandler) {
        final Object doc2 = new Reference(doc);
        final Object body2 = new Reference(body);
        final Object contentHandler2 = new Reference(contentHandler);
        return $getCallSiteArray()[1].callCurrent(this, ArrayUtil.createArray(((Reference<Object>)doc2).get(), pendingNamespaces, namespaces, namespaceSpecificTags, prefix, attrs, ((Reference<Object>)body2).get(), ((Reference<Object>)contentHandler2).get()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$xml$StreamingSAXBuilder$_closure3(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (StreamingSAXBuilder$_closure3.$callSiteArray == null || ($createCallSiteArray = StreamingSAXBuilder$_closure3.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            StreamingSAXBuilder$_closure3.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
}
