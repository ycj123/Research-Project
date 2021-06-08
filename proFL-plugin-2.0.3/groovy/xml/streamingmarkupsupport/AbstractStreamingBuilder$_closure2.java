// 
// Decompiled by Procyon v0.5.36
// 

package groovy.xml.streamingmarkupsupport;

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

class AbstractStreamingBuilder$_closure2 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    
    public AbstractStreamingBuilder$_closure2(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object doc, final Object pendingNamespaces, final Object namespaces, final Object namespaceSpecificTags, final Object prefix, final Object attrs, final Object... rest) {
        final Object pendingNamespaces2 = new Reference(pendingNamespaces);
        final Object namespaces2 = new Reference(namespaces);
        final Object namespaceSpecificTags2 = new Reference(namespaceSpecificTags);
        final Object attrs2 = new Reference(attrs);
        return $getCallSiteArray()[0].call(((Reference<Object>)attrs2).get(), new AbstractStreamingBuilder$_closure2_closure5(this, this.getThisObject(), (Reference<Object>)pendingNamespaces2, (Reference<Object>)namespaceSpecificTags2, (Reference<Object>)namespaces2));
    }
    
    public Object call(final Object doc, final Object pendingNamespaces, final Object namespaces, final Object namespaceSpecificTags, final Object prefix, final Object attrs, final Object... rest) {
        final Object pendingNamespaces2 = new Reference(pendingNamespaces);
        final Object namespaces2 = new Reference(namespaces);
        final Object namespaceSpecificTags2 = new Reference(namespaceSpecificTags);
        final Object attrs2 = new Reference(attrs);
        return $getCallSiteArray()[1].callCurrent(this, ArrayUtil.createArray(doc, ((Reference<Object>)pendingNamespaces2).get(), ((Reference<Object>)namespaces2).get(), ((Reference<Object>)namespaceSpecificTags2).get(), prefix, ((Reference<Object>)attrs2).get(), rest));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$xml$streamingmarkupsupport$AbstractStreamingBuilder$_closure2(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AbstractStreamingBuilder$_closure2.$callSiteArray == null || ($createCallSiteArray = AbstractStreamingBuilder$_closure2.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AbstractStreamingBuilder$_closure2.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
}
