// 
// Decompiled by Procyon v0.5.36
// 

package groovy.xml;

import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ArrayUtil;
import groovy.lang.Reference;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class StreamingSAXBuilder$_closure2 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    
    public StreamingSAXBuilder$_closure2(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object doc, final Object pendingNamespaces, final Object namespaces, final Object namespaceSpecificTags, final Object prefix, final Object attrs, final Object body, final Object contentHandler) {
        final Object attrs2 = new Reference(attrs);
        final Object contentHandler2 = new Reference(contentHandler);
        return $getCallSiteArray()[0].call(((Reference<Object>)attrs2).get(), new StreamingSAXBuilder$_closure2_closure7(this, this.getThisObject(), (Reference<Object>)contentHandler2));
    }
    
    public Object call(final Object doc, final Object pendingNamespaces, final Object namespaces, final Object namespaceSpecificTags, final Object prefix, final Object attrs, final Object body, final Object contentHandler) {
        final Object attrs2 = new Reference(attrs);
        final Object contentHandler2 = new Reference(contentHandler);
        return $getCallSiteArray()[1].callCurrent(this, ArrayUtil.createArray(doc, pendingNamespaces, namespaces, namespaceSpecificTags, prefix, ((Reference<Object>)attrs2).get(), body, ((Reference<Object>)contentHandler2).get()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$xml$StreamingSAXBuilder$_closure2(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (StreamingSAXBuilder$_closure2.$callSiteArray == null || ($createCallSiteArray = StreamingSAXBuilder$_closure2.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            StreamingSAXBuilder$_closure2.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
}
