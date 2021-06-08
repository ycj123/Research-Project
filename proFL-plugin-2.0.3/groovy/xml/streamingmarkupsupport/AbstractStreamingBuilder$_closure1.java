// 
// Decompiled by Procyon v0.5.36
// 

package groovy.xml.streamingmarkupsupport;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ArrayUtil;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.Reference;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class AbstractStreamingBuilder$_closure1 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$GroovyRuntimeException;
    
    public AbstractStreamingBuilder$_closure1(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object tag, final Object doc, final Object pendingNamespaces, final Object namespaces, final Object namespaceSpecificTags, final Object prefix, final Object... rest) {
        final Object tag2 = new Reference(tag);
        final Object pendingNamespaces2 = new Reference(pendingNamespaces);
        final Object namespaces2 = new Reference(namespaces);
        final Object prefix2 = new Reference(prefix);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object uri = new Reference($getCallSiteArray[0].call(((Reference<Object>)pendingNamespaces2).get(), ((Reference<Object>)prefix2).get()));
        if (ScriptBytecodeAdapter.compareEqual(((Reference<Object>)uri).get(), null)) {
            ((Reference<Object>)uri).set($getCallSiteArray[1].call(((Reference<Object>)namespaces2).get(), ((Reference<Object>)prefix2).get()));
        }
        throw (Throwable)$getCallSiteArray[2].callConstructor($get$$class$groovy$lang$GroovyRuntimeException(), new GStringImpl(new Object[] { ((Reference<Object>)tag2).get(), ((Reference<Object>)uri).get() }, new String[] { "Tag ", " is not allowed in namespace ", "" }));
    }
    
    public Object call(final Object tag, final Object doc, final Object pendingNamespaces, final Object namespaces, final Object namespaceSpecificTags, final Object prefix, final Object... rest) {
        final Object tag2 = new Reference(tag);
        final Object pendingNamespaces2 = new Reference(pendingNamespaces);
        final Object namespaces2 = new Reference(namespaces);
        final Object prefix2 = new Reference(prefix);
        return $getCallSiteArray()[3].callCurrent(this, ArrayUtil.createArray(((Reference<Object>)tag2).get(), doc, ((Reference<Object>)pendingNamespaces2).get(), ((Reference<Object>)namespaces2).get(), namespaceSpecificTags, ((Reference<Object>)prefix2).get(), rest));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[4];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$xml$streamingmarkupsupport$AbstractStreamingBuilder$_closure1(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AbstractStreamingBuilder$_closure1.$callSiteArray == null || ($createCallSiteArray = AbstractStreamingBuilder$_closure1.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AbstractStreamingBuilder$_closure1.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$GroovyRuntimeException() {
        Class $class$groovy$lang$GroovyRuntimeException;
        if (($class$groovy$lang$GroovyRuntimeException = AbstractStreamingBuilder$_closure1.$class$groovy$lang$GroovyRuntimeException) == null) {
            $class$groovy$lang$GroovyRuntimeException = (AbstractStreamingBuilder$_closure1.$class$groovy$lang$GroovyRuntimeException = class$("groovy.lang.GroovyRuntimeException"));
        }
        return $class$groovy$lang$GroovyRuntimeException;
    }
    
    static /* synthetic */ Class class$(final String className) {
        try {
            return Class.forName(className);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
