// 
// Decompiled by Procyon v0.5.36
// 

package groovy.xml.streamingmarkupsupport;

import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.Reference;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class AbstractStreamingBuilder$_closure4 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    
    public AbstractStreamingBuilder$_closure4(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object doc, final Object pendingNamespaces, final Object namespaces, final Object... rest) {
        final Object pendingNamespaces2 = new Reference(pendingNamespaces);
        final Object namespaces2 = new Reference(namespaces);
        $getCallSiteArray();
        return ScriptBytecodeAdapter.createList(new Object[] { ((Reference<Object>)namespaces2).get(), ((Reference<Object>)pendingNamespaces2).get() });
    }
    
    public Object call(final Object doc, final Object pendingNamespaces, final Object namespaces, final Object... rest) {
        final Object pendingNamespaces2 = new Reference(pendingNamespaces);
        final Object namespaces2 = new Reference(namespaces);
        return $getCallSiteArray()[0].callCurrent(this, doc, ((Reference<Object>)pendingNamespaces2).get(), ((Reference<Object>)namespaces2).get(), rest);
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = { null };
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$xml$streamingmarkupsupport$AbstractStreamingBuilder$_closure4(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AbstractStreamingBuilder$_closure4.$callSiteArray == null || ($createCallSiteArray = AbstractStreamingBuilder$_closure4.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AbstractStreamingBuilder$_closure4.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
}
