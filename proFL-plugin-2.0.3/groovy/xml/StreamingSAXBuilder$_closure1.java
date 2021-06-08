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
import org.xml.sax.ext.LexicalHandler;
import groovy.lang.Reference;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class StreamingSAXBuilder$_closure1 extends Closure implements GeneratedClosure
{
    private static final /* synthetic */ Integer $const$0;
    private static /* synthetic */ SoftReference $callSiteArray;
    
    public StreamingSAXBuilder$_closure1(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object doc, final Object pendingNamespaces, final Object namespaces, final Object namespaceSpecificTags, final Object prefix, final Object attrs, final Object body, final Object contentHandler) {
        final Object body2 = new Reference(body);
        final Object contentHandler2 = new Reference(contentHandler);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (((Reference<Object>)contentHandler2).get() instanceof LexicalHandler) {
            return $getCallSiteArray[0].call(((Reference<Object>)contentHandler2).get(), $getCallSiteArray[1].call(((Reference<Object>)body2).get()), StreamingSAXBuilder$_closure1.$const$0, $getCallSiteArray[2].call(((Reference<Object>)body2).get()));
        }
        return null;
    }
    
    public Object call(final Object doc, final Object pendingNamespaces, final Object namespaces, final Object namespaceSpecificTags, final Object prefix, final Object attrs, final Object body, final Object contentHandler) {
        final Object body2 = new Reference(body);
        final Object contentHandler2 = new Reference(contentHandler);
        return $getCallSiteArray()[3].callCurrent(this, ArrayUtil.createArray(doc, pendingNamespaces, namespaces, namespaceSpecificTags, prefix, attrs, ((Reference<Object>)body2).get(), ((Reference<Object>)contentHandler2).get()));
    }
    
    static {
        $const$0 = 0;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[4];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$xml$StreamingSAXBuilder$_closure1(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (StreamingSAXBuilder$_closure1.$callSiteArray == null || ($createCallSiteArray = StreamingSAXBuilder$_closure1.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            StreamingSAXBuilder$_closure1.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
}
